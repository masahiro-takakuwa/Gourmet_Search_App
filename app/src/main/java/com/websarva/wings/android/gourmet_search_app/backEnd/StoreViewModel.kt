package com.websarva.wings.android.gourmet_search_app.backEnd

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

/** API から受け取った店舗データを管理するクラス**/

class StoreViewModel: ViewModel()  {
    //シングルトンにしたRetrofitClientをインスタンス化
    private val repository = RetrofitClient.instance

    // 店舗のリストをデータホルダークラス(LiveData)として作製
    private val _StoreList = MutableLiveData<List<Shop>>()
    val StoreList: LiveData<List<Shop>> = _StoreList

    // APIデータから店舗データを取得する
    fun getStoreData(data: APIData) {
        viewModelScope.launch {
            val response = repository.getStoreData(
                data.range.toString(),    // 検索範囲
                data.key,                 // APIキー
                data.latitude.toString(), // 緯度
                data.longitude.toString() // 経度
            )
            if (response.isSuccessful) {
                val shopList = response.body()?.results?.shop
                if (shopList != null) {
                    // LiveDataのobserveを用いて、Search_ResultActivityで画面を更新する
                    _StoreList.postValue(shopList)
                }
            }
        }
    }
}