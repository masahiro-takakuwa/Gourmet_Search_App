package com.websarva.wings.android.gourmet_search_app.backEnd

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/** API から受け取った店舗データを管理するサブクラス**/

class SubViewModel :  ViewModel() {

    // 新たに店舗のデータをデータホルダークラス(LiveData)として作製
    private val _shop = MutableLiveData<Shop>()
    val shop: LiveData<Shop> = _shop

    // APIデータから店舗詳細を取得する
    fun getDetail(shop: Shop) {
        _shop.postValue(shop)
    }

}