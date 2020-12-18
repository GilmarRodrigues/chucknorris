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

class PiadaRepository(
        private val dao: PiadaDAO,
        private val webclient: PiadaWebClient) {

    private val liveData = MutableLiveData<Resource<Piada>>()
    private val job = Job()
    private val scope = CoroutineScope(Dispatchers.IO + job)

    private fun buscaInterna() = dao.buscaTodos()

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

    private fun salvaInterno(piada: Piada) {
        scope.launch {
            dao.salva(piada)
        }
    }
}