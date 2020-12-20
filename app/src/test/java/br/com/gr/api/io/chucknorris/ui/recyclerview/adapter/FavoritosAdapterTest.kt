package br.com.gr.api.io.chucknorris.ui.recyclerview.adapter

import android.content.Context
import br.com.gr.api.io.chucknorris.model.Piada
import junit.framework.TestCase
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.doNothing
import org.mockito.Mockito.verify
import org.mockito.Spy
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FavoritosAdapterTest : TestCase() {

    @Mock
    private val context = Mockito.mock(Context::class.java)
    @Spy
    private val adapter = FavoritosAdapter(context = context)

    @Test
    fun deve_AtualizarListaDeFavoritos_QuandoReceberListaFavoritos() {
        doNothing().`when`(adapter).removeLista()
        doNothing().`when`(adapter).atualizaLista()

        adapter.atualiza(listOf(Piada(
            pk = "7a3kONTsR7yblF18EPfkfg",
            categories = mutableListOf(),
            icon_url = "https://assets.chucknorris.host/img/avatar/chuck-norris.png",
            value = "The last words Johnny Cash ever heard - \"I'm Chuck Norris",
            url = "https://api.chucknorris.io/jokes/kWTabh87Rj2zZgnRfpGG7A"
        )))

        val quantidadeFavoritosDeveolvidas = adapter.itemCount

        verify(adapter).removeLista()
        verify(adapter).atualizaLista()

        assertThat(quantidadeFavoritosDeveolvidas, CoreMatchers.`is`(1))

    }
}