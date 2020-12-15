package br.com.gr.api.io.chucknorris.ui.fragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import br.com.gr.api.io.chucknorris.R
import br.com.gr.api.io.chucknorris.databinding.FragmentSplashBinding
import br.com.gr.api.io.chucknorris.ui.viewmodel.ComponentesVisuais
import br.com.gr.api.io.chucknorris.ui.viewmodel.EstadoAppViewModel
import org.koin.android.viewmodel.ext.android.sharedViewModel

private const val SPLASH_SEGUNDOS: Long = 2000

class SplashFragment : Fragment() {
    private val controlador by lazy { findNavController() }
    private val estadoAppViewModel: EstadoAppViewModel by sharedViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val viewBinding = FragmentSplashBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        estadoAppViewModel.temComponentes = ComponentesVisuais()
        vaiParaListaCategorias()
    }

    private fun vaiParaListaCategorias() {
        Handler(Looper.getMainLooper()).postDelayed({
            val direcao = SplashFragmentDirections.actionSplashFragmentToNavigationListaCategorias()
            controlador.navigate(direcao)
        }, SPLASH_SEGUNDOS)
    }

}