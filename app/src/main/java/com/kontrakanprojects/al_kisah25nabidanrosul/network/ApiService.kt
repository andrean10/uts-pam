package com.kontrakanprojects.al_kisah25nabidanrosul.network

import com.kontrakanprojects.al_kisah25nabidanrosul.model.ResponseKisahNabi
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("data/json/kisahnabi")
    fun getKisahNabi(): Call<ResponseKisahNabi>
}