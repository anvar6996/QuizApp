package uz.usoft.quizapp.data.pref

import android.content.Context
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MyPref @Inject constructor(private val context: Context) {

    private val pref = context.getSharedPreferences("cache", Context.MODE_PRIVATE)

    var phoneNumber: String
        set(value) {
            pref.edit().putString("phone", value).apply()
        }
        get() = pref.getString("phone", "")!!


    var starsCount: Int
        set(value) {
            pref.edit().putInt("starsCount", value).apply()
        }
        get() = pref.getInt("starsCount", 0)

    var level: Int
        set(value) {
            pref.edit().putInt("level", value).apply()
        }
        get() = pref.getInt("level", 1)


    var contrellBase: Boolean
        set(value) {
            pref.edit().putBoolean("contrellBase", value).apply()
        }
        get() = pref.getBoolean("contrellBase", false)

    var authControll: Boolean
        set(value) {
            pref.edit().putBoolean("authControll", value).apply()
        }
        get() = pref.getBoolean("authControll", false)

    var name: String
        set(value) {
            pref.edit().putString("name", value).apply()
        }
        get() = pref.getString("name", "")!!
    var surname: String
        set(value) {
            pref.edit().putString("surname", value).apply()
        }
        get() = pref.getString("surname", "")!!

    var access_token: String
        set(value) {
            pref.edit().putString("access_token", value).apply()
        }
        get() = pref.getString("access_token", "")!!


    var startScreen: Boolean
        set(value) {
            pref.edit().putBoolean("startScreen", value).apply()
        }
        get() = pref.getBoolean("startScreen", false)

    var passed: String
        set(value) {
            pref.edit().putString("passed", value).apply()
        }
        get() = pref.getString("passed", "")!!
}