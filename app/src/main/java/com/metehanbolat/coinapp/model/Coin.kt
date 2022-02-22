package com.metehanbolat.coinapp.model

data class Coin(
    val current_price: Double,
    val id: String,
    val market_cap: Double,
    val market_cap_rank: Double,
    val price_change_24h: Double,
    val price_change_percentage_24h: Double,
    val symbol: String,
    val total_volume: Double
)