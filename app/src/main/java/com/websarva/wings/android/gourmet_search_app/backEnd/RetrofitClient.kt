package com.websarva.wings.android.gourmet_search_app.backEnd

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**  Retrofit2 の設定を行うクラス**/

class RetrofitClient {

    // Gson のインスタンスの生成
    private val gson: Gson = GsonBuilder().setLenient().create()
    // retrofit のインスタンスを生成
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(gson)) // 受け取ったJSON形式のデータを Gson でパースする
        .baseUrl("http://webservice.recruit.co.jp/hotpepper/") //アクセス先のURL
        .build()

    //データクラスの型を記述したインターフェースを指定する
    private var APIService: API_Service = retrofit.create(API_Service::class.java)

    // ホットペッパーAPIから周辺の店を検索し、レスポンスとして店舗データを受け取る
    suspend fun getStoreData(

        range: String,      // 検索範囲
        key: String,        // APIキー
        lat: String,        // 緯度
        lng: String,        // 経度

    ) = APIService.getStoreData(

        range,
        key,
        lat,
        lng,
        format = "json")

    // RetrofitClientをシングルトンにする
    companion object Factory {
        val instance: RetrofitClient
            @Synchronized get() {
                return RetrofitClient()
            }
    }
}