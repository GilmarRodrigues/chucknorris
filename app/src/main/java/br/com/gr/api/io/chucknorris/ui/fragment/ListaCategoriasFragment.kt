package br.com.gr.api.io.chucknorris.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout.VERTICAL
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import br.com.gr.api.io.chucknorris.databinding.FragmentListaCategoriasBinding
import br.com.gr.api.io.chucknorris.ui.extensions.mostraErro
import br.com.gr.api.io.chucknorris.ui.recyclerview.adapter.CategoriaAdapter
import br.com.gr.api.io.chucknorris.ui.viewmodel.ComponentesVisuais
import br.com.gr.api.io.chucknorris.ui.viewmodel.EstadoAppViewModel
import br.com.gr.api.io.chucknorris.ui.viewmodel.ListaCategoriasViewModel
import kotlinx.android.synthetic.main.fragment_lista_categorias.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel

private const val MENSAGEM_FALHA_CARREGAR_NOTICIAS = "Não foi possível carregar as novas categorias"

class ListaCategoriasFragment : Fragment() {
    private val adapter: CategoriaAdapter by inject()
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
        val divisor = DividerItemDecoration(context, VERTICAL)
        fragment_recycleview_categoria.addItemDecoration(divisor)
        fragment_recycleview_categoria.adapter = adapter
        configuraAdapter()
    }

    private fun configuraAdapter() {
        adapter.quandoItemClicado = { categoriaSelecionada ->
            vaiParaVisualizaPiada(categoriaSelecionada.id)
        }
    }

    private fun buscaCategorias() {
        listaCategoriasViewModel.buscaTodos().observe(this, { resource ->
            resource.dado?.let { adapter.atualiza(it) }
            resource.erro?.let { mostraErro(MENSAGEM_FALHA_CARREGAR_NOTICIAS)}
        })
    }

    private fun vaiParaVisualizaPiada(id: Long) {
        mostraErro(id.toString())
    }

    private fun componentesVisuais() {
        estadoAppViewModel.temComponentes = ComponentesVisuais(appBar = true, bottomNavigation = true)
    }
}