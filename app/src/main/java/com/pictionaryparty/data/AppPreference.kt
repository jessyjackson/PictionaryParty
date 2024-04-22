package com.pictionaryparty.data

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject

class AppPreference @Inject constructor(
    context: Context
) {
    private val sharedPreferences : SharedPreferences = context.getSharedPreferences(SHARED_PREFS,Context.MODE_PRIVATE)

    var userId: String?
        get() = sharedPreferences.getString(KEY_USER_ID,null)
        set(value) = sharedPreferences.edit().putString(KEY_USER_ID,value).apply()
    companion object{
        private const val KEY_USER_ID = "key_user_id"
        private const val SHARED_PREFS = "pictionarypart-pref"

    }
}