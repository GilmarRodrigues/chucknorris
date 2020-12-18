package br.com.gr.api.io.chucknorris.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import br.com.gr.api.io.chucknorris.database.converter.ConversorList
import br.com.gr.api.io.chucknorris.database.dao.CategoriaDAO
import br.com.gr.api.io.chucknorris.database.dao.PiadaDAO
import br.com.gr.api.io.chucknorris.model.Categoria
import br.com.gr.api.io.chucknorris.model.Piada

private const val VERSAO_BANCO_DADOS  = 5

@Database(entities = [Categoria::class, Piada::class], version = VERSAO_BANCO_DADOS, exportSchema = false)
@TypeConverters(ConversorList::class)
abstract class AppDatabase : RoomDatabase() {
    abstract val categoriaDAO: CategoriaDAO
    abstract val piadaDAO: PiadaDAO
}