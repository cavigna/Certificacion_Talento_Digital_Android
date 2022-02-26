package com.cavigna.talentodigital.model.models


import com.google.gson.annotations.SerializedName

data class D(
    @SerializedName("volume")
    var volume: String = "",
    @SerializedName("price_change")
    var priceChange: String = "",
    @SerializedName("price_change_pct")
    var priceChangePct: String = "",
    @SerializedName("volume_change")
    var volumeChange: String = "",
    @SerializedName("volume_change_pct")
    var volumeChangePct: String = "",
    @SerializedName("market_cap_change")
    var marketCapChange: String = "",
    @SerializedName("market_cap_change_pct")
    var marketCapChangePct: String = ""
)