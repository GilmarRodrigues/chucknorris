package br.com.gr.api.io.chucknorris.ui.activity


import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import androidx.test.espresso.contrib.RecyclerViewActions
import br.com.gr.api.io.chucknorris.R
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test

class MainTelaTest {

    @get:Rule
    val activity = ActivityTestRule(MainActivity::class.java, true, true)

    @Test
    fun deve_AparecerAprimeiraCategoria_QuandoCarregarCategorias() {
        onView(allOf(
            withId(R.id.fragment_recycleview_categoria),
            isDisplayed()))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(0))

        onView(allOf(
            withId(R.id.tv_desc_categoria_adapter),
            withText("animal"),
            isDisplayed()))
            .check(matches(withText("animal")))
    }

    @Test
    fun deve_AparecerAOitavaCategoria_QuandoCarregarcategoriaEFazeroScroll() {
        onView(allOf(
            withId(R.id.fragment_recycleview_categoria),
            isDisplayed()))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(7))

        onView(allOf(
            withId(R.id.tv_desc_categoria_adapter),
            withText("history"),
            isDisplayed()))
            .check(matches(withText("history")))
    }

    @Test
    fun deve_ApareceOBotaoFonte_QuandoClicarEmUmaCategoria() {
        onView(allOf(
            withId(R.id.fragment_recycleview_categoria),
            isDisplayed()))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        Thread.sleep(5000)

        onView(allOf(
            withId(R.id.btn_url_fragment_visualiza_piada),
            withText("FONTE"),
            isDisplayed()))
            .check(matches(withText("FONTE")))
    }


}