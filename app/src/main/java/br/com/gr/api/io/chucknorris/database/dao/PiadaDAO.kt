package br.com.gr.api.io.chucknorris.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.gr.api.io.chucknorris.model.Piada

@Dao
interface PiadaDAO {

    @Query("SELECT * FROM Piada ORDER BY id DESC")
    fun buscaTodos(): LiveData<List<Piada>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun salva(piada: Piada) : Long

    @Query("SELECT * FROM Piada WHERE id = :id")
    fun buscaPorId(id: Long): LiveData<Piada?>

    @Query("SELECT * FROM Piada WHERE pk = :pk")
    fun buscaPorPk(pk: String): Piada?
    
}