package com.websarva.wings.android.gourmet_search_app.backEnd

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**Refrofit2 で GET アクセスをするためのインターフェース**/

interface API_Service {
    //クエリパラメータ部分
    @GET("gourmet/v1/")

    //呼び出す際に必要なリクエストURLのパラメータの設定
    suspend fun getStoreData(

        @Query("range") range: String,      // 検索範囲
        @Query("key") key: String,          // APIキー
        @Query("lat") lat: String,          // 緯度
        @Query("lng") lng: String,          // 経度
        @Query("format") format: String,    // JSON形式

    ): Response<API_Model> //取得したクエリ情報をAPI_Modelへ
}