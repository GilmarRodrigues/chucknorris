package br.com.gr.api.io.chucknorris.di

import android.content.SharedPreferences
import android.preference.PreferenceManager
import br.com.gr.api.io.chucknorris.ui.viewmodel.EstadoAppViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val daoModule = module {
    single<SharedPreferences> { PreferenceManager.getDefaultSharedPreferences(get())}
}
val viewModelModule = module {
    viewModel<EstadoAppViewModel> {EstadoAppViewModel()}
}
