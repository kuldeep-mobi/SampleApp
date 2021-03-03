package com.mobikasa.logisticapp.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

class SettingManager(private var context: Context) {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
        name = "settings_pref"
    )

    //private val dataStore = context.createDataStore("settings_pref")
    companion object {
        val COUNTER = intPreferencesKey("count")
        val NAME = stringPreferencesKey("name")
        var mInstance: SettingManager? = null
        fun getInstance(mContext: Context): SettingManager {
            if (mInstance == null) {
                mInstance = SettingManager(mContext)
            }
            return mInstance as SettingManager
        }
    }

    val data: Flow<Int> = context.dataStore.data.catch {
        if (it is IOException) {
            it.printStackTrace()
            emit(emptyPreferences())
        } else {
            throw it
        }
    }.map {
        it[COUNTER] ?: 0
    }

    suspend fun updateCounter(count: Int) {
        context.dataStore.edit {
            it[COUNTER] = count
        }
    }

    suspend fun updateName(name: String) {
        context.dataStore.edit {
            it[NAME] = name
        }
    }

    val nameData = context.dataStore.data.catch {
        if (it is IOException) {
            it.printStackTrace()
            emit(emptyPreferences())
        } else {
            throw it
        }
    }.map {
        it[NAME]
    }
}