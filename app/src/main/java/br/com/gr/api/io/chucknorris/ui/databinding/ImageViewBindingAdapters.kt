package br.com.gr.api.io.chucknorris.ui.databinding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import br.com.gr.api.io.chucknorris.ui.extensions.loadUrl

@BindingAdapter("carregaImagem")
fun ImageView.carregaImagemUrl(url: String?) {
    url?.let { loadUrl(url) }
}