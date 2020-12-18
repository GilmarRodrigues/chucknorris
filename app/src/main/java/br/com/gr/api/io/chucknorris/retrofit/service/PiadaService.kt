package br.com.gr.api.io.chucknorris.retrofit.service

import br.com.gr.api.io.chucknorris.model.Piada
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PiadaService {

    @GET("random")
    fun buscaPorCategoria(
            @Query("category") categoria: String): Call<Piada>
}