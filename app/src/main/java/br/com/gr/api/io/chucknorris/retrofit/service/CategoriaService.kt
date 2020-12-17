package br.com.gr.api.io.chucknorris.retrofit.service

import br.com.gr.api.io.chucknorris.model.Categoria
import com.google.gson.JsonArray
import org.json.JSONArray
import retrofit2.Call
import retrofit2.http.GET

interface CategoriaService {

    @GET("categories")
    fun categorias(): Call<JsonArray>
}