package br.com.gr.api.io.chucknorris.ui.databinding

import android.util.Log
import androidx.lifecycle.MutableLiveData
import br.com.gr.api.io.chucknorris.R
import br.com.gr.api.io.chucknorris.model.Categoria


class CategoriaData(
        private var categoria: Categoria = Categoria(),
        val desc: MutableLiveData<String> = MutableLiveData<String>().also { it.value = categoria.desc})