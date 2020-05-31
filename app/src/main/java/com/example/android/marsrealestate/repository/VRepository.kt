package com.example.android.marsrealestate.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.android.marsrealestate.database.DatabaseV
import com.example.android.marsrealestate.database.VDatabase
import com.example.android.marsrealestate.database.asDomainModel
import com.example.android.marsrealestate.network.MarsApi
import com.example.android.marsrealestate.network.MarsProperty
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class VRepository(private val database: VDatabase) {

    val v: LiveData<List<MarsProperty>> = Transformations.map(database.vDao.getV()) {
        it.asDomainModel()
    }




    suspend fun refreshVideos() {
        withContext(Dispatchers.IO) {

            val playlist = MarsApi.retrofitService.getProperties().await()
            database.vDao.insertAll(playlist.asDatamodel())
        }
    }
}

