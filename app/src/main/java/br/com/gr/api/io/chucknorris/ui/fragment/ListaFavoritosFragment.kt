package br.com.gr.api.io.chucknorris.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import br.com.gr.api.io.chucknorris.databinding.FragmentListaFavoritosBinding
import br.com.gr.api.io.chucknorris.ui.extensions.mostraMensagem
import br.com.gr.api.io.chucknorris.ui.recyclerview.adapter.FavoritosAdapter
import br.com.gr.api.io.chucknorris.ui.viewmodel.ComponentesVisuais
import br.com.gr.api.io.chucknorris.ui.viewmodel.EstadoAppViewModel
import br.com.gr.api.io.chucknorris.ui.viewmodel.ListaFavoritosViewModel
import kotlinx.android.synthetic.main.fragment_lista_favoritos.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel

private const val MENSAGEM_FALHA_CARREGAR_PIADA = "Você não tem piadas nos favoritos :/"

class ListaFavoritosFragment : Fragment() {
    private val adapter: FavoritosAdapter by inject()
    private val listaFavoritosViewModel: ListaFavoritosViewModel by viewModel()
    private val estadoAppViewModel: EstadoAppViewModel by sharedViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        buscaCategorias()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val viewBinding = FragmentListaFavoritosBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        componentesVisuais()
        configuraRecycleView()
    }

    private fun configuraRecycleView() {
        val divisor = DividerItemDecoration(context, LinearLayout.VERTICAL)
        fragment_recycleview_favorito.addItemDecoration(divisor)
        fragment_recycleview_favorito.adapter = adapter
    }

    private fun buscaCategorias() {
        listaFavoritosViewModel.buscaTodos().observe(this, { piadasEncontardas ->
            if (piadasEncontardas.isNotEmpty()) {
                adapter.atualiza(piadas = piadasEncontardas)
            } else {
                mostraMensagem(MENSAGEM_FALHA_CARREGAR_PIADA)
            }
        })
    }

    private fun componentesVisuais() {
        estadoAppViewModel.temComponentes = ComponentesVisuais(appBar = true, bottomNavigation = true)
    }
}