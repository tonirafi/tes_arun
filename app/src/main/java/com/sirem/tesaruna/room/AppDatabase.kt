package com.sirem.tesaruna.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sirem.tesaruna.model.DataNews


@Database(entities = arrayOf(DataNews::class), version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun DAO(): DAO

}



