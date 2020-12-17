package br.com.gr.api.io.chucknorris.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.gr.api.io.chucknorris.model.Categoria
import br.com.gr.api.io.chucknorris.repository.CategoriaRepository
import br.com.gr.api.io.chucknorris.repository.Resource

class ListaCategoriasViewModel(private val repository: CategoriaRepository) : ViewModel() {

    fun buscaTodos() : LiveData<Resource<List<Categoria>?>> {
        return repository.buscaTodos()
    }
}