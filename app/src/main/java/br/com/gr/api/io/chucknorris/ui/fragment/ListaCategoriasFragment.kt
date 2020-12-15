package br.com.gr.api.io.chucknorris.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import br.com.gr.api.io.chucknorris.R
import br.com.gr.api.io.chucknorris.ui.viewmodel.ComponentesVisuais
import br.com.gr.api.io.chucknorris.ui.viewmodel.EstadoAppViewModel
import br.com.gr.api.io.chucknorris.ui.viewmodel.ListaCategoriasViewModel
import org.koin.android.viewmodel.ext.android.sharedViewModel

class ListaCategoriasFragment : Fragment() {

    private lateinit var listaCategoriasViewModel: ListaCategoriasViewModel
    private val estadoAppViewModel: EstadoAppViewModel by sharedViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        listaCategoriasViewModel = ViewModelProvider(this).get(ListaCategoriasViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_lista_categorias, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        listaCategoriasViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        estadoAppViewModel.temComponentes = ComponentesVisuais(appBar = true, bottomNavigation = true)
    }
}