package com.example.core

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import javax.inject.Inject

class MobilesListCache @Inject constructor(
    private var context: Context
) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(MOBILES_LIST_FILE_NAME, Context.MODE_PRIVATE)

    fun getMobilesListCache(): List<MobileEntity> {
        val json = sharedPreferences.getString(MOBILES_LIST_KEY, DEFAULT)
        if (!json.isNullOrBlank()) {
            val type = object : TypeToken<List<MobileEntity>>() {}.type
            return Gson().fromJson(json, type)
        }
        return listOf()
    }

    fun setMobilesListCache(list: List<MobileEntity>) {
        sharedPreferences.edit().putString(MOBILES_LIST_KEY, Gson().toJson(list))
            .apply()
    }

    private companion object {
        private const val MOBILES_LIST_FILE_NAME = "MOBILES_LIST_FILE_NAME"
        private const val MOBILES_LIST_KEY = "MOBILES_LIST_KEY"
        private const val DEFAULT = ""
    }
}

