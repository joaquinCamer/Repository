package com.example.android.marsrealestate.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface VDao {

    @Query("select * from databasev")
    fun getV(): LiveData<List<DatabaseV>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll( videos: List<DatabaseV>)
}

@Database(entities = [DatabaseV::class], version = 1)
abstract class VDatabase: RoomDatabase() {
    abstract val vDao: VDao
}

private lateinit var INSTANCE: VDatabase

fun getDatabase(context: Context): VDatabase {
    synchronized(VDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(context.applicationContext,
                    VDatabase::class.java,
                    "videos").build()
        }
    }
    return INSTANCE
}