package com.example.groupassignment2.components.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class RoutineItems(context: Context) : ViewModel() {
    private val timeData = MutableStateFlow("")
    private val notificationData = MutableStateFlow("")
    private val routineData = MutableStateFlow("")
    private val dataStoreContext: Context = context

    private val timeKey = stringPreferencesKey("time_key")
    private val notificationKey = stringPreferencesKey("notification_key")
    private val routineKey = stringPreferencesKey("routine_key")



    fun saveTimeData(time: String) {
        viewModelScope.launch {
            dataStoreContext.datastore.edit { preferences ->
                preferences[timeKey] = time
            }
        }
    }

    fun saveNotificationData(notify: String) {
        viewModelScope.launch {
            dataStoreContext.datastore.edit { preferences ->
                preferences[notificationKey] = notify
            }
        }
    }

    fun saveRoutineData(name: String) {

        viewModelScope.launch {
            dataStoreContext.datastore.edit { preferences ->
                preferences[routineKey] = name
            }
        }

    }

    suspend fun loadTimeData():String {
        var time= ""
        viewModelScope.launch {
            val preferences = dataStoreContext.datastore.data.first()

             time = preferences[timeKey] ?: ""
            timeData.value = time

        }
        return time
    }

    suspend fun loadNotificationData():String {
        var notification=""
        viewModelScope.launch {
            val preferences = dataStoreContext.datastore.data.first()
            notification = preferences[notificationKey] ?: ""
            notificationData.value = notification
        }
        return notification
    }

    suspend fun loadRoutineData():String {
        var routine =""
        viewModelScope.launch {
            val preferences = dataStoreContext.datastore.data.first()
            routine = preferences[routineKey] ?: ""
            routineData.value = routine
        }
        return routine
    }


}
