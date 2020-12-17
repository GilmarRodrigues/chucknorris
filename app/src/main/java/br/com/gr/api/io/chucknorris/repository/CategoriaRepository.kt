package br.com.gr.api.io.chucknorris.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import br.com.gr.api.io.chucknorris.database.dao.CategoriaDAO
import br.com.gr.api.io.chucknorris.model.Categoria
import br.com.gr.api.io.chucknorris.retrofit.webclient.CategoriaWebClient
import com.google.gson.JsonArray
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class CategoriaRepository(
        private val dao: CategoriaDAO,
        private val webclient: CategoriaWebClient) {

    private val mediador = MediatorLiveData<Resource<List<Categoria>?>>()
    private val job = Job()
    private val scope = CoroutineScope(Dispatchers.IO + job)

    fun buscaTodos(): LiveData<Resource<List<Categoria>?>> {

        mediador.addSource(buscaInterna()) { categoriasEncontrados ->
            mediador.value = Resource(dado = categoriasEncontrados)
        }

        val falhasDaWebApiLiveData = MutableLiveData<Resource<List<Categoria>?>>()
        mediador.addSource(falhasDaWebApiLiveData) { resourceDeFalha ->
            val resourceAtual = mediador.value
            val resourceNovo: Resource<List<Categoria>?> = if (resourceAtual != null) {
                Resource(dado = resourceAtual.dado, erro = resourceDeFalha.erro)
            } else {
                resourceDeFalha
            }
            mediador.value = resourceNovo
        }
        buscaNaApi( quandoFalha = {erro ->
            falhasDaWebApiLiveData.value = Resource(dado = null, erro = erro)
        })
        return mediador
    }

    private fun buscaInterna(): LiveData<List<Categoria>> {
        return dao.buscaTodos()
    }

    private fun buscaNaApi(quandoFalha: (erro: String?) -> Unit) {
        webclient.buscaTodas(
            quandoSucesso = {
                it?.let { categoriasJsonArray ->
                    salvaInterno(categorias(categoriasJsonArray))
                    }
            }, quandoFalha = quandoFalha)
    }

    private fun salvaInterno(categorias: List<Categoria>) {
        scope.launch {
            categorias.forEach { categoria ->
                if (categoriaNaoExiste(categoria)) {
                    dao.salva(categorias)
                }
            }
        }
    }

    private fun categoriaNaoExiste(categoria: Categoria) =
            categoria.desc != dao.buscaPorDesc(categoria.desc)?.desc

    fun buscaPorId(categoriaId: Long): LiveData<Categoria?> {
        return dao.buscaPorId(categoriaId)
    }

    private fun categorias(categoriasJsonArray: JsonArray): MutableList<Categoria> {
        var categorias: MutableList<Categoria> = mutableListOf()
        categoriasJsonArray.forEach {
            val categoria = it.asString.replace("\"", "")
            categorias.add(Categoria(desc = categoria))
        }
        return categorias

    }

}

