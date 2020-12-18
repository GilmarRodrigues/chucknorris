package br.com.gr.api.io.chucknorris.retrofit.webclient

import br.com.gr.api.io.chucknorris.model.Piada
import br.com.gr.api.io.chucknorris.retrofit.AppRetrofit
import br.com.gr.api.io.chucknorris.retrofit.service.PiadaService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
private const val REQUISICAO_NAO_SUCEDIDA = "Requisição não sucedida"

class PiadaWebClient (private val service: PiadaService = AppRetrofit().piadaService) {

    private fun <T> executaRequisicao(
            call: Call<T>,
            quandoSucesso: (piadasNovas: T?) -> Unit,
            quandoFalha: (erro: String?) -> Unit) {
        call.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                if (response.isSuccessful) {
                    quandoSucesso(response.body())
                } else {
                    quandoFalha(REQUISICAO_NAO_SUCEDIDA)
                }
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                quandoFalha(t.message)
            }

        })
    }

    fun buscaPiadaPorCategoria(categoria: String, quandoSucesso: (piadasNovas: Piada?) -> Unit, quandoFalha: (erro: String?) -> Unit) {
        executaRequisicao(
                service.buscaPorCategoria(categoria = categoria), quandoSucesso, quandoFalha)
    }
}