package br.com.gr.api.io.chucknorris.ui.recyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.gr.api.io.chucknorris.databinding.AdapterCategoriasBinding
import br.com.gr.api.io.chucknorris.model.Categoria
import br.com.gr.api.io.chucknorris.ui.databinding.CategoriaData

class CategoriasAdapter(
        private val context: Context,
        private val categorias: MutableList<Categoria> = mutableListOf(),
        var quandoItemClicado: (categoria: Categoria) -> Unit = {}
) : RecyclerView.Adapter<CategoriasAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val viewDataBinding = AdapterCategoriasBinding.inflate(inflater, parent, false)
        return ViewHolder(viewDataBinding)
    }

    override fun getItemCount() = categorias.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val categoria = categorias[position]
        holder.vincula(categoria)
    }

    fun atualiza(categorias: List<Categoria>) {
        notifyItemRangeRemoved(0, this.categorias.size)
        this.categorias.clear()
        this.categorias.addAll(categorias)
        notifyItemRangeInserted(0, this.categorias.size)
    }

    inner class ViewHolder(private val viewDataBinding: AdapterCategoriasBinding)
        : RecyclerView.ViewHolder(viewDataBinding.root), View.OnClickListener {
        private lateinit var categoria: Categoria

        init {
            viewDataBinding.clicaNaCategoria = this
        }

        fun vincula(categoria: Categoria) {
            this.categoria = categoria
            viewDataBinding.categoria = CategoriaData(categoria)
        }

        override fun onClick(view: View?) {
            if (::categoria.isInitialized) {
                quandoItemClicado(categoria)
            }
        }
    }
}
