package com.sirem.tesaruna.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
@Entity(tableName = "DataNews")
class data {

    @PrimaryKey(autoGenerate = false)
    @SerializedName("id")
    @Expose
    var id: Int? = null

    @SerializedName("userId")
    @Expose
    var userId: Int? = 0

    @SerializedName("title")
    @Expose
    var title: String? = ""

    @SerializedName("body")
    @Expose
    var body: String? = ""
}