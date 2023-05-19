package com.example.groupassignment2.components.datastore

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.runtime.mutableStateListOf
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

val Context.datastore by preferencesDataStore("my_datastore")


data class RoutineData(
    val routineName: String,
    val timeOfRunning: String,
    val notificationText: String
)

class RoutineViewModel(context: Context) : ViewModel() {
    @SuppressLint("StaticFieldLeak")
    private val dataStoreContext: Context = context
     val routineDataList = mutableStateListOf<RoutineData>()
    private val dataKey = stringPreferencesKey("my_data_key")
    init {
        loadData()
    }

    fun saveData(routineData: RoutineData) {


        viewModelScope.launch {
            dataStoreContext.datastore.edit { preferences ->
                val currentData = preferences[dataKey] ?: ""
                val newData = if (currentData.isEmpty()) {
                    routineDataToString(routineData)
                } else {
                    currentData + DATA_SEPARATOR + routineDataToString(routineData)
                }
                preferences[dataKey] = newData
                routineDataList.add(routineData)
            }
        }
    }

    fun updateData(index: Int, updatedRoutineData: RoutineData) {
        viewModelScope.launch {
            dataStoreContext.datastore.edit { preferences ->
                val currentData = preferences[dataKey] ?: ""
                val dataItems = currentData.split(DATA_SEPARATOR).toMutableList()
                if (index in dataItems.indices) {
                    dataItems[index] = routineDataToString(updatedRoutineData)
                    val newData = dataItems.joinToString(DATA_SEPARATOR)
                    preferences[dataKey] = newData
                    routineDataList[index] = updatedRoutineData
                }
            }
        }
    }

    fun deleteData(index: Int) {
        viewModelScope.launch {
            dataStoreContext.datastore.edit { preferences ->
                val currentData = preferences[dataKey] ?: ""
                val dataItems = currentData.split(DATA_SEPARATOR).toMutableList()
                if (index in dataItems.indices) {
                    dataItems.removeAt(index)
                    val newData = dataItems.joinToString(DATA_SEPARATOR)
                    preferences[dataKey] = newData
                    routineDataList.removeAt(index)
                }
            }
        }
    }

    fun clearAllData() {
        viewModelScope.launch {
            dataStoreContext.datastore.edit { preferences ->
                preferences.remove(dataKey)
                routineDataList.clear()
            }
        }
    }


    fun loadData() {
        viewModelScope.launch {
            val preferences = dataStoreContext.datastore.data.first()
            val data = preferences[dataKey] ?: ""
            val dataItemsList = data.split(DATA_SEPARATOR)
            routineDataList.clear()
            routineDataList.addAll(dataItemsList.mapNotNull { stringToRoutineData(it) })
        }
    }

    companion object {
        private const val DATA_KEY = "my_data_key"
        private const val DATA_SEPARATOR = ","
        private const val ITEM_SEPARATOR = ";"

        private fun routineDataToString(routineData: RoutineData): String {
            return "${routineData.routineName}$ITEM_SEPARATOR${routineData.timeOfRunning}$ITEM_SEPARATOR${routineData.notificationText}"
        }

        private fun stringToRoutineData(string: String): RoutineData? {
            val items = string.split(ITEM_SEPARATOR)
            return if (items.size == 3) {
                RoutineData(items[0], items[1], items[2])
            } else {
                null
            }
        }
    }
}
