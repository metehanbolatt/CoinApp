package com.metehanbolat.coinapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.metehanbolat.coinapp.model.Coin
import com.metehanbolat.coinapp.service.CoinAPIService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class MainViewModel: ViewModel() {

    private val coinAPIService = CoinAPIService()
    private val disposable = CompositeDisposable()

    val coins = MutableLiveData<List<Coin>>()
    val coinError = MutableLiveData<Boolean>()
    val coinLoading = MutableLiveData<Boolean>()

    fun refreshData() {
        getDataFromAPI()
    }

    private fun getDataFromAPI() {
        coinLoading.value = true
        disposable.add(
            coinAPIService.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Coin>>() {
                    override fun onSuccess(t: List<Coin>) {
                        coins.value = t
                        coinError.value = false
                        coinLoading.value = false
                    }

                    override fun onError(e: Throwable) {
                        coinLoading.value = false
                        coinError.value = true
                        println(e.localizedMessage)
                    }

                })
        )
    }

}