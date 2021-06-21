package com.kontrakanprojects.al_kisah25nabidanrosul.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kontrakanprojects.al_kisah25nabidanrosul.model.ResponseKisahNabi
import com.kontrakanprojects.al_kisah25nabidanrosul.network.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    private var _kisahNabi: MutableLiveData<ResponseKisahNabi>? = null

    private val TAG = MainViewModel::class.simpleName

    fun getKisahNabi(): LiveData<ResponseKisahNabi> {
        if (_kisahNabi == null) {
            _kisahNabi = MutableLiveData<ResponseKisahNabi>()
            kisahNabi()
        }
        return _kisahNabi as MutableLiveData<ResponseKisahNabi>
    }

    private fun kisahNabi() {
        val client = ApiConfig.getApiService().getKisahNabi()
        client.enqueue(object : Callback<ResponseKisahNabi> {
            override fun onResponse(
                call: Call<ResponseKisahNabi>,
                response: Response<ResponseKisahNabi>
            ) {
                if (response.isSuccessful) {
                    val result = response.body()
                    _kisahNabi?.postValue(result!!)
                }
            }

            override fun onFailure(call: Call<ResponseKisahNabi>, t: Throwable) {
                _kisahNabi?.postValue(null)
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }
}