package br.com.gr.api.io.chucknorris.ui.recyclerview.adapter

import android.content.Context
import br.com.gr.api.io.chucknorris.model.Categoria
import junit.framework.TestCase
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.doNothing
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.mockito.Spy
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CategoriasAdapterTest : TestCase() {

    @Mock
    private val context = Mockito.mock(Context::class.java)
    @Spy
    private val adapter = CategoriasAdapter(context = context)

    @Test
    fun deve_AtualizarListaDeCategorias_QuandoReceberListaCategorias() {
        doNothing().`when`(adapter).removeLista()
        doNothing().`when`(adapter).atualizaLista()

        adapter.atualiza(listOf(Categoria(desc = "animal")))

        val quantidadeCategoriasDeveolvidas = adapter.itemCount

        verify(adapter).removeLista()
        verify(adapter).atualizaLista()

        assertThat(quantidadeCategoriasDeveolvidas, `is`(1))

    }
}