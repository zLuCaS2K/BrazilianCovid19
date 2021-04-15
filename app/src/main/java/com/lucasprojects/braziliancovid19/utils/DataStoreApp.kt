package com.lucasprojects.braziliancovid19.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStoreApp(context: Context) {

    private val Context._dataStore: DataStore<Preferences> by preferencesDataStore(name = "dataStates")
    private val mDataStore: DataStore<Preferences> = context._dataStore

    suspend fun saveData(key: String, value: String) {
        val prefKey = stringPreferencesKey(key)
        mDataStore.edit { preferences -> preferences[prefKey] = value }
    }

    fun readData(key: String): Flow<String> {
        val prefKey = stringPreferencesKey(key)
        return mDataStore.data.map { preferences -> preferences[prefKey] ?: "0" }
    }
}