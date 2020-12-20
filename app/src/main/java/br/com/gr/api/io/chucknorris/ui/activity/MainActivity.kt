package br.com.gr.api.io.chucknorris.ui.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import br.com.gr.api.io.chucknorris.R
import br.com.gr.api.io.chucknorris.ui.viewmodel.EstadoAppViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val controlador by lazy {
        findNavController(R.id.main_activity_nav_host)
    }
    private val viewModel: EstadoAppViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        componentesVisuais()
        main_activity_bottom_navigation.setupWithNavController(controlador)
    }

    private fun componentesVisuais() {
        controlador.addOnDestinationChangedListener { _, destination, _ ->
            title = destination.label
            viewModel.componentes.observe(this, Observer {
                it?.let { temComponentes ->
                    if (temComponentes.appBar) {
                        supportActionBar?.show()
                    } else {
                        supportActionBar?.hide()
                    }
                    if (temComponentes.bottomNavigation) {
                        main_activity_bottom_navigation.visibility = View.VISIBLE
                    } else {
                        main_activity_bottom_navigation.visibility = View.GONE
                    }
                    if (temComponentes.setaHomeToolbar) {
                        supportActionBar?.setDisplayHomeAsUpEnabled(true)
                    } else {
                        supportActionBar?.setDisplayHomeAsUpEnabled(false)
                    }
                }
            })
        }
    }
}