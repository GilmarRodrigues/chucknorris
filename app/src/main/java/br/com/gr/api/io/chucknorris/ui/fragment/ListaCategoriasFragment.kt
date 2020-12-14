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
import br.com.gr.api.io.chucknorris.ui.viewmodel.ListaCategoriasViewModel

class ListaCategoriasFragment : Fragment() {

    private lateinit var listaCategoriasViewModel: ListaCategoriasViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        listaCategoriasViewModel = ViewModelProvider(this).get(ListaCategoriasViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_lista_categorias, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        listaCategoriasViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}