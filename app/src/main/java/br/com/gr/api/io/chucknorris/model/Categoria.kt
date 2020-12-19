package br.com.gr.api.io.chucknorris.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Categoria(
        @PrimaryKey(autoGenerate = true)
        val id: Long = 0,
        val desc: String = "")
