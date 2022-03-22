package com.websarva.wings.android.gourmet_search_app.backEnd

import java.io.Serializable

/** APIのデータを直列化(シリアライズ)で保存するクラス**/

data class APIData(
    var range: Int,         // 検索範囲
    var key: String,        // APIキー
    var latitude: Double,   // 緯度
    var longitude: Double  // 経度
): Serializable