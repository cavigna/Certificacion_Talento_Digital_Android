package com.cavigna.talentodigital.model.models


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "coins_table")
data class Coin(
    @PrimaryKey
    var id: String = "",
    @SerializedName("currency")
    var currency: String = "",
    @SerializedName("symbol")
    var symbol: String = "",
    @SerializedName("name")
    var name: String = "",
    @SerializedName("logo_url")
    var logoUrl: String = "",
    @SerializedName("status")
    var status: String = "",
    @SerializedName("price")
    var price: String = "",
    @SerializedName("price_date")
    var priceDate: String = "",
    @SerializedName("price_timestamp")
    var priceTimestamp: String = "",
    @SerializedName("rank")
    var rank: String = ""
)