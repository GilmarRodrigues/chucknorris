package br.com.gr.api.io.chucknorris.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import br.com.gr.api.io.chucknorris.model.Categoria

@Dao
interface CategoriaDAO {
    @Query("SELECT * FROM Categoria ORDER BY id DESC")
    fun buscaTodos(): LiveData<List<Categoria>>

    @Insert(onConflict = REPLACE)
    fun salva(categoria: Categoria)

    @Query("SELECT * FROM Categoria WHERE id = :id")
    fun buscaPorId(id: Long): LiveData<Categoria?>

    @Query("SELECT * FROM Categoria WHERE `desc` = :desc")
    fun buscaPorDesc(desc: String): Categoria?

    @Insert(onConflict = REPLACE)
    fun salva(categorias: List<Categoria>)
}