package br.com.gr.api.io.chucknorris.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity
data class Piada(
        @PrimaryKey(autoGenerate = true)
        @SerializedName("id_interno")
        @Expose
        val id: Long = 0,
        @SerializedName("id")
        @Expose
        val pk: String = "",
        val categories: MutableList<String> = mutableListOf(),
        val icon_url: String = "",
        val url: String = "",
        val value: String = "")