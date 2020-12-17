package br.com.gr.api.io.chucknorris.retrofit

import br.com.gr.api.io.chucknorris.retrofit.service.CategoriaService
import br.com.gr.api.io.chucknorris.retrofit.service.PiadaService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://api.chucknorris.io/jokes/"

class AppRetrofit {

    private val client by lazy {
        val interceptador = HttpLoggingInterceptor()
        interceptador.level = HttpLoggingInterceptor.Level.BODY
        OkHttpClient.Builder()
            .addInterceptor(interceptador)
            .build()
    }
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }
    val categoriaService: CategoriaService by lazy {
        retrofit.create(CategoriaService::class.java)
    }
    val piadaService: PiadaService by lazy {
        retrofit.create(PiadaService::class.java)
    }
}