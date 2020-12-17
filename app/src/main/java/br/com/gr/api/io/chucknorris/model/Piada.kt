package br.com.gr.api.io.chucknorris.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Piada(
        @PrimaryKey(autoGenerate = true)
        val id: Long = 0,
        val categories: String = "",
        val icon_url: String = "",
        val url: String = "",
        val value: String = "")