package br.com.gr.api.io.chucknorris.database

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.gr.api.io.chucknorris.database.dao.CategoriaDAO
import br.com.gr.api.io.chucknorris.database.dao.PiadaDAO
import br.com.gr.api.io.chucknorris.model.Categoria
import br.com.gr.api.io.chucknorris.model.Piada

private const val VERSAO_BANCO_DADOS  = 3

@Database(entities = [Categoria::class, Piada::class], version = VERSAO_BANCO_DADOS, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract val categoriaDAO: CategoriaDAO
    abstract val piadaDAO: PiadaDAO
}