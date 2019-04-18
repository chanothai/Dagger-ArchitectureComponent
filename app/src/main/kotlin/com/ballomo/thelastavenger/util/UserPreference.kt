package com.ballomo.thelastavenger.util

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject

class UserPreference @Inject constructor(context: Context) {
    companion object {
        private const val PREFERENCE_NAME = "user_preference"
        private const val KEY_ID = "key_id"
        private const val KEY_NANE = "key_name"
    }

    private val sharedPreference: SharedPreferences =
        context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)

    fun saveUserId(id: Long) {
        sharedPreference.edit().putLong(KEY_ID, id).apply()
    }

    fun saveUserName(name: String) {
        sharedPreference.edit().putString(KEY_NANE, name).apply()
    }

    fun getUserID(): Long = sharedPreference.getLong(KEY_ID, 0)
    fun getUserName(): String? = sharedPreference.getString(KEY_NANE, null)
}