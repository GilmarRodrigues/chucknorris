package br.com.gr.api.io.chucknorris.ui.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import br.com.gr.api.io.chucknorris.R
import br.com.gr.api.io.chucknorris.databinding.FragmentVisualizaPiadaBinding
import br.com.gr.api.io.chucknorris.model.Piada
import br.com.gr.api.io.chucknorris.ui.databinding.PiadaData
import br.com.gr.api.io.chucknorris.ui.extensions.mostraMensagem
import br.com.gr.api.io.chucknorris.ui.viewmodel.ComponentesVisuais
import br.com.gr.api.io.chucknorris.ui.viewmodel.EstadoAppViewModel
import br.com.gr.api.io.chucknorris.ui.viewmodel.VisualizaPiadaViewModel
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

private const val MENSAGEM_FALHA_CARREGAR_PIADA = "Não foi possível carregar a piada, tente mais tarde."
private const val PIADA_EXISTE = "Ops... Essa piada já foi adicionada a sua lista de favoritas."
private const val PIADA_SALVA = "Piada salva nos favoritos."

class VisualizaPiadaFragment : Fragment() {
    private val argumentos by navArgs<VisualizaPiadaFragmentArgs>()
    private val categoria by lazy { argumentos.categoria}
    private val visualizaPiadaViewModel: VisualizaPiadaViewModel by viewModel { parametersOf(categoria)}
    private val estadoAppViewModel: EstadoAppViewModel by sharedViewModel()
    private val controlador by lazy { findNavController() }
    private lateinit var viewDataBinding : FragmentVisualizaPiadaBinding
    private val piadaData: PiadaData by lazy { PiadaData() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as AppCompatActivity?)?.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setHasOptionsMenu(true)
        buscaPiadaSelecionada()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewDataBinding = FragmentVisualizaPiadaBinding.inflate(inflater, container, false)
        viewDataBinding.lifecycleOwner = this
        viewDataBinding.piada = piadaData
        vaiParaWebFonteDaPiada()
        return viewDataBinding.root
    }

    private fun vaiParaWebFonteDaPiada() {
        viewDataBinding.clicaNoBotaoFonte = View.OnClickListener {
            val webSite = Intent(Intent.ACTION_VIEW)
            webSite.data = Uri.parse(piadaData.url.value)
            startActivity(webSite)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        componentesVisuais()
    }

    private fun buscaPiadaSelecionada() {
        visualizaPiadaViewModel.piadaEncontrada().observe(this, {
            it?.let { resource ->
                if (resource.dado != null) {
                    piadaData.atualiza(resource.dado)
                } else {
                    resource.erro?.run { mostraMensagem(MENSAGEM_FALHA_CARREGAR_PIADA) }
                }
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.visualiza_piada_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> vaiParaListaCategorias()
            R.id.visualiza_piada_menu_salva -> {
                val novaPiada = criaPiada()
                novaPiada?.let { salvaPiada(novaPiada) }
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun criaPiada() = piadaData.paraPiada()

    private fun salvaPiada(piada: Piada) {
        visualizaPiadaViewModel.salvaPiada(piada).observe(this, { resource ->
            if(resource.dado != null) {
                mostraMensagem(PIADA_SALVA)
            } else {
                mostraMensagem(PIADA_EXISTE)
            }

        })
    }

    private fun vaiParaListaCategorias() {
        controlador.popBackStack()
    }

    private fun componentesVisuais() {
        estadoAppViewModel.temComponentes = ComponentesVisuais(appBar = true, bottomNavigation = false)
    }
}