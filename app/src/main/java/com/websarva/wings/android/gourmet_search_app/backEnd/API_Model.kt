package com.websarva.wings.android.gourmet_search_app.backEnd

import java.io.Serializable

/** Refrofit2 から受け取ったクエリデータを格納するデータクラス**/

class API_Model (
    val results: Result,
)

data class Result(
    val shop: List<Shop>,       //リストで管理
)

data class Shop(
    // 検索結果画面で使用
    val name: String?,          // 店舗名称
    val mobile_access: String?, // 交通アクセス
    val photo: Photo?,          // 写真
    val genre: Genre?,          // ジャンル

    // 店舗詳細画面で使用
    val address: String?,       // 住所
    val open: String?,          // 営業時間
    val station_name: String?,  // 最寄り駅
) : Serializable

data class Photo(
    val pc: PC?,                 // PC向けの写真
) : Serializable

data class PC(
    val l: String?,             // 店舗トップ写真（大）URL
    val m: String,              // 店舗トップ写真（中）URL
    val s: String?,             // 店舗トップ写真（小）URL
) : Serializable

data class Genre(
    val name: String?,          // ジャンル
) : Serializable