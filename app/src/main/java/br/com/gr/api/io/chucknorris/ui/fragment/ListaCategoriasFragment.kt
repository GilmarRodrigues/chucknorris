package br.com.gr.api.io.chucknorris.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout.VERTICAL
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import br.com.gr.api.io.chucknorris.databinding.FragmentListaCategoriasBinding
import br.com.gr.api.io.chucknorris.ui.extensions.mostraMensagem
import br.com.gr.api.io.chucknorris.ui.recyclerview.adapter.CategoriasAdapter
import br.com.gr.api.io.chucknorris.ui.viewmodel.ComponentesVisuais
import br.com.gr.api.io.chucknorris.ui.viewmodel.EstadoAppViewModel
import br.com.gr.api.io.chucknorris.ui.viewmodel.ListaCategoriasViewModel
import kotlinx.android.synthetic.main.fragment_lista_categorias.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel

private const val MENSAGEM_FALHA_CARREGAR_CATEGORIA = "Não foi possível carregar as novas categorias"

class ListaCategoriasFragment : Fragment() {
    private val adapter: CategoriasAdapter by inject()
    private val listaCategoriasViewModel: ListaCategoriasViewModel by viewModel()
    private val estadoAppViewModel: EstadoAppViewModel by sharedViewModel()
    private val controlador by lazy { findNavController() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        buscaCategorias()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val viewBinding = FragmentListaCategoriasBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        componentesVisuais()
        configuraRecycleView()
    }

    private fun configuraRecycleView() {
        fragment_recycleview_categoria.itemAnimator = DefaultItemAnimator()
        fragment_recycleview_categoria.setHasFixedSize(true)
        fragment_recycleview_categoria.adapter = adapter
        configuraAdapter()
    }

    private fun configuraAdapter() {
        adapter.quandoItemClicado = { categoriaSelecionada ->
            vaiParaVisualizaPiada(categoriaSelecionada.desc)
        }
    }

    fun buscaCategorias() {
        listaCategoriasViewModel.buscaTodos().observe(this, { resource ->
            resource.dado?.let { adapter.atualiza(it) }
            resource.erro?.let { mostraMensagem(MENSAGEM_FALHA_CARREGAR_CATEGORIA)}
        })
    }

    private fun vaiParaVisualizaPiada(categoria: String) {
        val direcao = ListaCategoriasFragmentDirections
            .actionNavigationListaCategoriasToVisualizaPiadaFragment(categoria)
        controlador.navigate(direcao)
    }

    private fun componentesVisuais() {
        estadoAppViewModel.temComponentes = ComponentesVisuais(appBar = true, bottomNavigation = true)
    }

}