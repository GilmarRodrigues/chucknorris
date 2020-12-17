package br.com.gr.api.io.chucknorris.repository

class Resource<T>(
        val dado: T?,
        val erro: String? = null)