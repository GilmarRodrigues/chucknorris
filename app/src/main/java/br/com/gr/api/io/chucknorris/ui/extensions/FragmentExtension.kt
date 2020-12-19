package br.com.gr.api.io.chucknorris.ui.extensions

import android.widget.Toast
import androidx.fragment.app.Fragment

fun Fragment.mostraMensagem(mensagem: String) {
    Toast.makeText(
        context,
        mensagem,
        Toast.LENGTH_LONG
    ).show()
}