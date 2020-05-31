package com.example.android.marsrealestate.network

import com.example.android.marsrealestate.database.DatabaseV
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NetworkContainer(val v: List<DatabaseV>)


@JsonClass(generateAdapter = true)
data class NetworkV(
        val id: String,
        // used to map img_src from the JSON to imgSrcUrl in our class
        @Json(name = "img_src") val imgSrcUrl: String,
        val type: String,
        val price: Double)


fun NetworkContainer.asDomainModel(): List<MarsProperty> {
    return v.map {
        MarsProperty(
                id= it.id   ,
                imgSrcUrl = it.imgSrcUrl,
                price= it.price,
                type= it.type)
    }
}





fun NetworkContainer.asDatabaseModel(): List<DatabaseV> {
    return v.map {
        DatabaseV(
                id= it.id   ,
                imgSrcUrl = it.imgSrcUrl,
                price= it.price,
                type= it.type)
    }
}