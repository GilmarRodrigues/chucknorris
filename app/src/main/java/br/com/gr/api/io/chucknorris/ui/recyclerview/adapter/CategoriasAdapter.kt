package br.com.gr.api.io.chucknorris.ui.recyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import br.com.gr.api.io.chucknorris.R
import br.com.gr.api.io.chucknorris.databinding.AdapterCategoriasBinding
import br.com.gr.api.io.chucknorris.model.Categoria
import br.com.gr.api.io.chucknorris.ui.databinding.CategoriaData
import br.com.gr.api.io.chucknorris.ui.extensions.loadDrawable
import kotlinx.android.synthetic.main.adapter_categorias.view.*

private val coresCardView = listOf(
        R.color.cordview_animal, R.color.cordview_career, R.color.cordview_celebrity, R.color.cordview_dev, R.color.cordview_explicit,
        R.color.cordview_fashion, R.color.cordview_food, R.color.cordview_history, R.color.cordview_monei, R.color.cordview_movie,
        R.color.cordview_music, R.color.cordview_political, R.color.cordview_religion, R.color.cordview_science, R.color.cordview_sport,
        R.color.cordview_travel)

private val iconeCardView = listOf(
        R.drawable.ic_categoria_animal, R.drawable.ic_categoria_career, R.drawable.ic_categoria_celebrity, R.drawable.ic_categoria_dev, R.drawable.ic_categoria_explicit,
        R.drawable.ic_categoria_fashion, R.drawable.ic_categoria__food, R.drawable.ic_categoria_history, R.drawable.ic_categoria_money, R.drawable.ic_categoria_movie,
        R.drawable.ic_categoria_music, R.drawable.ic_categoria_political, R.drawable.ic_categoria_religion, R.drawable.ic_categoria_science, R.drawable.ic_categoria_sport,
        R.drawable.ic_categoria_travel)

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
        holder.vincula(categoria, position)
    }

    fun atualiza(categorias: List<Categoria>) {
        removeLista()
        this.categorias.clear()
        this.categorias.addAll(categorias)
        atualizaLista()
    }

    fun atualizaLista() {
        notifyItemRangeInserted(0, this.categorias.size)
    }

    fun removeLista() {
        notifyItemRangeRemoved(0, this.categorias.size)
    }

    inner class ViewHolder(private val viewDataBinding: AdapterCategoriasBinding)
        : RecyclerView.ViewHolder(viewDataBinding.root), View.OnClickListener {
        private lateinit var categoria: Categoria

        init {
            viewDataBinding.clicaNaCategoria = this
        }

        fun vincula(categoria: Categoria, posicao: Int) {
            this.categoria = categoria
            viewDataBinding.categoria = CategoriaData(categoria)
            componentesVisuaisCardView(posicao)
        }

        override fun onClick(view: View?) {
            if (::categoria.isInitialized) {
                quandoItemClicado(categoria)
            }
        }
    }

    private fun ViewHolder.componentesVisuaisCardView(posicao: Int) {
        itemView.constraint_layout_adapter_categoria.setBackgroundColor(ContextCompat.getColor(context, coresCardView[posicao]))
        itemView.iv_icone_adapter_categoria.loadDrawable(iconeCardView[posicao])
    }
}
