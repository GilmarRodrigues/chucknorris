package br.com.gr.api.io.chucknorris.di

import androidx.room.Room
import br.com.gr.api.io.chucknorris.database.AppDatabase
import br.com.gr.api.io.chucknorris.database.dao.CategoriaDAO
import br.com.gr.api.io.chucknorris.repository.CategoriaRepository
import br.com.gr.api.io.chucknorris.retrofit.webclient.CategoriaWebClient
import br.com.gr.api.io.chucknorris.ui.recyclerview.adapter.CategoriaAdapter
import br.com.gr.api.io.chucknorris.ui.viewmodel.EstadoAppViewModel
import br.com.gr.api.io.chucknorris.ui.viewmodel.ListaCategoriasViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

private const val NOME_BANCO_DE_DADOS = "categoria.db"

val dbModule = module {
    single<AppDatabase> {
        Room.databaseBuilder(
                get(),
                AppDatabase::class.java,
                NOME_BANCO_DE_DADOS
        ).build()
    }
}

val daoModule = module {
    single<CategoriaDAO> { get<AppDatabase>().categoriaDAO }
}
val clientModule = module {
    single<CategoriaWebClient> { CategoriaWebClient() }
}
val repositoryModule = module {
    single<CategoriaRepository> { CategoriaRepository(get(), get()) }
}
val uiModule = module {
    factory<CategoriaAdapter> { CategoriaAdapter(get()) }
}
val viewModelModule = module {
    viewModel<EstadoAppViewModel> { EstadoAppViewModel() }
    viewModel<ListaCategoriasViewModel> { ListaCategoriasViewModel(get()) }
}

val appModule = listOf(
        dbModule,
        clientModule,
        uiModule,
        repositoryModule,
        viewModelModule,
        daoModule
)
