package br.com.gr.api.io.chucknorris.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import br.com.gr.api.io.chucknorris.model.Categoria
import br.com.gr.api.io.chucknorris.model.Piada
import br.com.gr.api.io.chucknorris.repository.PiadaRepository
import br.com.gr.api.io.chucknorris.repository.Resource

class VisualizaPiadaViewModel(
        private val categoria: String,
        private val repository: PiadaRepository): ViewModel() {

    fun piadaEncontrada() = repository.buscaNaApi(categoria)

    fun salvaPiada(piada: Piada)= repository.salvaInterno(piada)

}