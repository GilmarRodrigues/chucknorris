package br.com.gr.api.io.chucknorris.ui.recyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.gr.api.io.chucknorris.databinding.AdapterFavoritosBinding
import br.com.gr.api.io.chucknorris.model.Piada
import br.com.gr.api.io.chucknorris.ui.databinding.PiadaData

class FavoritosAdapter(
        private val context: Context,
        private val piadas: MutableList<Piada> = mutableListOf()
): RecyclerView.Adapter<FavoritosAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritosAdapter.ViewHolder {
        val inflante = LayoutInflater.from(context)
        val viewDataBinding = AdapterFavoritosBinding.inflate(inflante, parent, false)
        return ViewHolder(viewDataBinding)
    }

    override fun getItemCount() = piadas.size

    override fun onBindViewHolder(holder: FavoritosAdapter.ViewHolder, position: Int) {
        val piada = piadas[position]
        holder.vincula(piada)
    }

    fun atualiza(piadas: List<Piada>) {
        notifyItemRangeRemoved(0, this.piadas.size)
        this.piadas.clear()
        this.piadas.addAll(piadas)
        notifyItemRangeInserted(0, this.piadas.size)
    }

    inner class ViewHolder(private val viewDataBinding: AdapterFavoritosBinding)
        : RecyclerView.ViewHolder(viewDataBinding.root) {
            private lateinit var piada: Piada

            fun vincula(piada: Piada) {
                this.piada = piada
                viewDataBinding.piada = PiadaData(piada)

            }

    }
}