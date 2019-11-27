package com.ian.app.muviepedia.data.util

import android.app.Application
import android.content.Context
import com.ian.app.muviepedia.data.util.UtilConstant.prefHelperInit

class PreferenceHelper(app: Application) {
    private val prefHelp by lazy {
        app.getSharedPreferences(prefHelperInit, Context.MODE_PRIVATE)
    }
    private val preHelperEditor = prefHelp.edit()

    fun saveStringInSharedPreference(key: String?, value: String?) {
        preHelperEditor.putString(key, value).apply()
    }

    fun getStringInSharedPreference(key: String?): String? {
        return prefHelp.getString(key, "")
    }

    fun deleteSharedPreference() {
        preHelperEditor.clear().apply()
    }
}