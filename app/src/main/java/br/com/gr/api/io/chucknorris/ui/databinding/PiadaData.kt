package br.com.gr.api.io.chucknorris.ui.databinding

import android.util.Log
import androidx.lifecycle.MutableLiveData
import br.com.gr.api.io.chucknorris.model.Piada

class PiadaData(
        private var piada: Piada = Piada(),
        val pk: MutableLiveData<String> = MutableLiveData<String>().also { it.value = piada.pk},
        val categories: MutableLiveData<String> = MutableLiveData<String>().also { it.value = transformaListCategoriasEmString(piada.categories)},
        val icon_url: MutableLiveData<String> = MutableLiveData<String>().also { it.value = piada.icon_url},
        val url: MutableLiveData<String> = MutableLiveData<String>().also { it.value = piada.url},
        val value: MutableLiveData<String> = MutableLiveData<String>().also { it.value = piada.value}) {

    fun atualiza(piada: Piada) {
        this.piada = piada
        pk.postValue(this.piada.pk)
        categories.postValue(transformaListCategoriasEmString(this.piada.categories))
        icon_url.postValue(this.piada.icon_url)
        url.postValue(this.piada.url)
        value.postValue(this.piada.value)
    }

    fun paraPiada(): Piada? {
        /*categories.value?.let { transformaCategoriaEmList(it).forEach {
            Log.i("Script", "aqui $it")
        } }*/
        return this.piada.copy(
                pk = pk.value ?: return null,
                categories = categories.value?.let { transformaCategoriaEmList(it) } ?: mutableListOf(),
                icon_url = icon_url.value ?: return null,
                url = url.value ?: return null,
                value = value.value ?: return null
        )
    }
}



fun transformaListCategoriasEmString(categories: List<String>): String? {
    var categoria = ""
    categories.forEach {
        categoria += " $it"
    }
    return categoria
}

fun transformaCategoriaEmList(categories: String) : MutableList<String> {
    val categoriasList  = mutableListOf<String>()
    var categoria = ""
    Log.i("Script", "1")
    categories.forEach { char ->
        categoria += char.toString()
    }
    categoriasList.add(categoria)
    return categoriasList
}

