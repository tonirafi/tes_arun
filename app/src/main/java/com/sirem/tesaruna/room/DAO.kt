package com.sirem.tesaruna.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.sirem.tesaruna.model.DataNews


@Dao
interface DAO {

    @Insert
    fun insertPurchased(prodakModel: Array<out DataNews?>)

    @Query("SELECT * FROM DataNews")
    fun getAllPurchased(): List<DataNews>


    @Insert
    fun insertProdak(prodakModel: Array<out DataNews?>)

    @Query("SELECT * FROM DataNews WHERE title LIKE :search")
    fun getAllProduk(search:String): List<DataNews>

    @Query("DELETE FROM DataNews")
    fun deleteAllProdak()
}