package com.metehanbolat.coinapp.service

import com.metehanbolat.coinapp.model.Coin
import io.reactivex.Single
import retrofit2.http.GET

interface CoinAPI {

    @GET("data/api/coins")
    fun getCoins():Single<List<Coin>>
}