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
import br.com.gr.api.io.chucknorris.ui.viewmodel.ListaFavoritosViewModel

class ListaFavoritosFragment : Fragment() {

    private lateinit var dashboardViewModel: ListaFavoritosViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
                ViewModelProvider(this).get(ListaFavoritosViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_lista_favoritos, container, false)
        val textView: TextView = root.findViewById(R.id.text_dashboard)
        dashboardViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}