package com.cavigna.talentodigital.model.models


import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "coin_details_table")
data class CoinDetails(

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
    @SerializedName("circulating_supply")
    var circulatingSupply: String = "",
    @SerializedName("market_cap")
    var marketCap: String = "",
    @SerializedName("num_exchanges")
    var numExchanges: String = "",
    @SerializedName("num_pairs")
    var numPairs: String = "",
    @SerializedName("num_pairs_unmapped")
    var numPairsUnmapped: String = "",
    @SerializedName("first_candle")
    var firstCandle: String = "",
    @SerializedName("first_trade")
    var firstTrade: String = "",
    @SerializedName("first_order_book")
    var firstOrderBook: String = "",
    @SerializedName("rank")
    var rank: String = "",
    @SerializedName("rank_delta")
    var rankDelta: String = "",
    @SerializedName("high")
    var high: String = "",
    @SerializedName("high_timestamp")
    var highTimestamp: String = "",

    @Embedded
    @SerializedName("1d")
    var d: D = D()
)