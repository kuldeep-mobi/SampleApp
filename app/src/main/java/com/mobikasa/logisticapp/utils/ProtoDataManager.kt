package com.mobikasa.logisticapp.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import com.mobikasa.logisticapp.SettingsProto
import com.mobikasa.logisticapp.models.SettingsSerializer
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ProtoDataManager(private val mContext: Context) {
    private val Context.settingsDataStore: DataStore<SettingsProto> by dataStore(
        fileName = "settings.pb",
        serializer = SettingsSerializer
    )

    companion object {
        private var instance: ProtoDataManager? = null
        fun getInstance(mContext: Context): ProtoDataManager {
            if (instance == null) {
                instance = ProtoDataManager(mContext)
            }
            return instance as ProtoDataManager
        }
    }

    val dataFlow: Flow<SettingsProto> = mContext.settingsDataStore.data.map {
        it
    }


    suspend fun incrementCounter() {
        mContext.settingsDataStore.updateData { currentSettings ->
            currentSettings.toBuilder()
                .setExampleCounter(currentSettings.exampleCounter + 1)
                .setName("Kuldeep")
                .build()
        }
    }

    suspend fun updateProfile(mProfile: SettingsProto.Profile) {
        mContext.settingsDataStore.updateData {
            it.toBuilder().setProfile(mProfile).build()
        }
    }

    suspend fun update(count: Int, name: String) {
        mContext.settingsDataStore.updateData {
            it.toBuilder().setExampleCounter(count).setName(name).build()
        }
    }
}