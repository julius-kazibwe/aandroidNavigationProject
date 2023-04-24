/*package com.example.groupassignment2.components.datastore

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch


private val Context.datastore by preferencesDataStore("my_datastore")

class RoutineViewModel(context: Context): ViewModel() {
    private val name = mutableStateOf("")
    val routineName = this.name
    @SuppressLint("StaticFieldLeak")
    private val dataStoreContext: Context = context


    fun saveData(data: String){
        viewModelScope.launch{
            dataStoreContext.datastore.edit{ preferences ->
                preferences[stringPreferencesKey("my_data_key")] = data
            }
        }
    }

    fun loadData(){
        viewModelScope.launch{
            val data = dataStoreContext.datastore.data.first()[stringPreferencesKey("my_data_key")] ?: ""
            name.value = data
        }
    }
}*/