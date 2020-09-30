package com.sirem.tesaruna.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.sirem.tesaruna.model.DataNews


@Dao
interface DAO {

    @Insert(onConflict = REPLACE)
    fun insert(dataNews: DataNews)


    @Insert(onConflict = REPLACE)
    fun insert(listData: ArrayList<DataNews>)

    @Query("SELECT * FROM DataNews WHERE title LIKE :search")
    fun getAll(search:String): List<DataNews>
}