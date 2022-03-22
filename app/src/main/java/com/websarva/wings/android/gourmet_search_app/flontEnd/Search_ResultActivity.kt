package com.websarva.wings.android.gourmet_search_app.flontEnd

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.websarva.wings.android.gourmet_search_app.backEnd.APIData
import com.websarva.wings.android.gourmet_search_app.backEnd.Search_Result_Adapter
import com.websarva.wings.android.gourmet_search_app.backEnd.StoreViewModel
import com.websarva.wings.android.gourmet_search_app.R

/**検索結果を表示するクラス**/

class Search_ResultActivity : AppCompatActivity() {

	// static変数として APIData , shopData を宣言
	companion object {
		const val API_DATA = "APIData"
		const val SHOP_DATA = "shopData"
	}

	private lateinit var data: APIData     // APIデータ
	private val viewModel by viewModels<StoreViewModel>() //店舗データ

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_store_result)

		//引継ぎデータの受け取り
		data = intent.getSerializableExtra(GPSActivity.API_DATA) as APIData

		// RecyclerView の取得
		val searchResultList = findViewById<RecyclerView>(R.id.searchResultList)
		// adapter オブジェクトの生成
		val adapter = Search_Result_Adapter(this)
		//アイテムの装飾
		val itemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)

		// RecyclerView の設定
		searchResultList.addItemDecoration(itemDecoration)
		searchResultList.adapter = adapter
		searchResultList.layoutManager = LinearLayoutManager(this) // 各画面部品を縦に並べる

		// API を viewModel に渡して周辺の店舗データを取得する
		viewModel.getStoreData(data)

		// API から受け取った店舗データをリストとして表示する
		viewModel.StoreList.observe(this, {
			// 店舗データを Adapter に設定
			adapter.setShopList(it)
			val listErrorView = findViewById<TextView>(R.id.listErrorView)
			// API からのレスポンスが0件の場合
			if (it.isEmpty()) {
				// エラーメッセージを TextView に表示させる
				listErrorView.text = "周辺にレストランがありません\n検索範囲を広げてください"
			} else {
				// TextView に元から書いてあるエラーメッセージを消去する
				listErrorView.text = ""
			}
		})

		  /* List の Item がクリックされた時の処理
             選択された店舗データを店舗詳細画面に渡す*/
		adapter.setOnClickListener {
			// 遷移する対象をクラスオブジェクトで指定する
			val intent = Intent(this, StoreDetailActivity::class.java)
			intent.putExtra(StoreDetailActivity.SHOP_DATA, it)
			startActivity(intent)
		}
	}

	// Back キーを押した際のメソッド
	override fun onBackPressed() {
			// 遷移する対象をクラスオブジェクトで指定する
			val intent = Intent(this, Range_SelectionActivity::class.java)
			intent.putExtra(Range_SelectionActivity.API_DATA, data)
			startActivity(intent)
	}


}
