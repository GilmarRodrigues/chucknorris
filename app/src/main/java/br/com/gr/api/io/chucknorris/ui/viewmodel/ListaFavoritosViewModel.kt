package br.com.gr.api.io.chucknorris.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.gr.api.io.chucknorris.model.Piada
import br.com.gr.api.io.chucknorris.repository.PiadaRepository

class ListaFavoritosViewModel(private val repository: PiadaRepository) : ViewModel() {

    fun buscaTodos(): LiveData<List<Piada>> = repository.buscaTodas()
}