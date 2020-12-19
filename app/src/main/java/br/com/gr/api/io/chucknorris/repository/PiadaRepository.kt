package br.com.gr.api.io.chucknorris.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.gr.api.io.chucknorris.database.dao.PiadaDAO
import br.com.gr.api.io.chucknorris.model.Piada
import br.com.gr.api.io.chucknorris.retrofit.webclient.PiadaWebClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception

private const val PIADA_EXISTE = "Piada existe"

class PiadaRepository(
        private val dao: PiadaDAO,
        private val webclient: PiadaWebClient) {

    private val liveData = MutableLiveData<Resource<Piada>>()
    private val job = Job()
    private val scope = CoroutineScope(Dispatchers.IO + job)

    fun buscaTodas() = dao.buscaTodos()

    fun buscaNaApi(categoria: String): LiveData<Resource<Piada>> {
        webclient.buscaPiadaPorCategoria(
            categoria,
                quandoSucesso = {
                    it?.let { piada ->
                        liveData.value = Resource(dado = piada, erro = null)
                    }
                }, quandoFalha = { erro ->
                    liveData.value = Resource(dado = null, erro = erro)
                })
        return liveData
    }

    fun salvaInterno(piada: Piada) =
        MutableLiveData<Resource<Piada>>().also { liveData ->
            scope.launch {
                if (piadaNaoExiste(piada)) {
                    dao.salva(piada)
                    liveData.postValue(Resource(dado = piada, erro = null))
                } else {
                    liveData.postValue(Resource(dado = null, erro = PIADA_EXISTE))
                }
            }
        }

    private fun piadaNaoExiste(piada: Piada) = piada.pk != dao.buscaPorPk(piada.pk)?.pk
}