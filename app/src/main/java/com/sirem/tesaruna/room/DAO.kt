package com.sirem.tesaruna.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.sirem.tesaruna.model.DataNews


@Dao
interface DAO {

    @Insert
    fun insert(dataNews: DataNews)


    @Insert
    fun insert(listData: ArrayList<DataNews>)

    @Query("SELECT * FROM DataNews WHERE title LIKE :search")
    fun getAll(search:String): List<DataNews>
}