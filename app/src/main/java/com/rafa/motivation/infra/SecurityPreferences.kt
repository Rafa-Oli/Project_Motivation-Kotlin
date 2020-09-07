package com.rafa.motivation.infra

import android.content.Context


class SecurityPreferences(context: Context) {
    private val mSharedPreferences =
        context.getSharedPreferences("motivation", Context.MODE_PRIVATE)

    fun storeString(key: String, value: String) {
        mSharedPreferences.edit().putString(key, value).apply() // salvando os valores de entrada
    }

    fun getString(key: String): String {
        return mSharedPreferences.getString(key,"") ?: "" // se for diferente de nulo, ira retornar o valor do getString, se for igual nulo retorna string vazia

    }
}