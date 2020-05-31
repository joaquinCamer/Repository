package com.example.android.marsrealestate.database

import androidx.lifecycle.Transformations.map
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.android.marsrealestate.network.MarsProperty
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Entity
data class DatabaseV constructor (


        @PrimaryKey
        val id: String,
        // used to map img_src from the JSON to imgSrcUrl in our class
        @Json(name = "img_src") val imgSrcUrl: String,
        val type: String,
        val price: Double)



fun List<DatabaseV>.asDomainModel(): List<MarsProperty> {
    return map {
        MarsProperty(
                id= it.id   ,
                imgSrcUrl = it.imgSrcUrl,
                price= it.price,
                type= it.type)
    }
}



