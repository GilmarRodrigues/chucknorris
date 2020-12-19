package br.com.gr.api.io.chucknorris.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.gr.api.io.chucknorris.R
import br.com.gr.api.io.chucknorris.ui.viewmodel.ComponentesVisuais
import br.com.gr.api.io.chucknorris.ui.viewmodel.EstadoAppViewModel
import org.koin.android.viewmodel.ext.android.sharedViewModel

class SobreFragment : Fragment() {
    private val estadoAppViewModel: EstadoAppViewModel by sharedViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_sobre, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        componentesVisuais()
    }

    private fun componentesVisuais() {
        estadoAppViewModel.temComponentes = ComponentesVisuais(appBar = true, bottomNavigation = true)
    }
}