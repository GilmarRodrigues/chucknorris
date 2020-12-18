package br.com.gr.api.io.chucknorris.ui.databinding

import android.util.Log
import androidx.lifecycle.MutableLiveData
import br.com.gr.api.io.chucknorris.model.Piada

class PiadaData(
    private var piada: Piada = Piada(),
    val pk: MutableLiveData<String> = MutableLiveData<String>().also { it.value = piada.pk},
    val categories: MutableLiveData<String> = MutableLiveData<String>().also { it.value = categorias(piada.categories)},
    val icon_url: MutableLiveData<String> = MutableLiveData<String>().also { it.value = piada.icon_url},
    val url: MutableLiveData<String> = MutableLiveData<String>().also { it.value = piada.url},
    val value: MutableLiveData<String> = MutableLiveData<String>().also { it.value = piada.value}) {

    fun atualiza(piada: Piada) {
        this.piada = piada
        pk.postValue(this.piada.pk)
        categories.postValue(categorias(this.piada.categories))
        icon_url.postValue(this.piada.icon_url)
        url.postValue(this.piada.url)
        value.postValue(this.piada.value)
    }
}

fun categorias(categories: List<String>): String? {
    var categoria = ""
    categories.forEach {
        categoria += " $it"
    }
    return categoria
}

