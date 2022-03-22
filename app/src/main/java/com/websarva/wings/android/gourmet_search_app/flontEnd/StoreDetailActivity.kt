package com.websarva.wings.android.gourmet_search_app.flontEnd

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.websarva.wings.android.gourmet_search_app.R
import com.websarva.wings.android.gourmet_search_app.backEnd.Shop
import com.websarva.wings.android.gourmet_search_app.backEnd.SubViewModel
import com.websarva.wings.android.gourmet_search_app.databinding.ActivityStoreDetailBinding
import java.net.URLEncoder

/** 店舗詳細を表示するクラス**/

class StoreDetailActivity : AppCompatActivity() {

    // static変数として shopData を宣言
    companion object {
        const val SHOP_DATA = "shopData"
    }

    // 店舗データ
    private lateinit var shop: Shop
    //店舗詳細データ
    private val viewModel by viewModels<SubViewModel>()
    //レイアウトファイルそのものをBindingオブジェクトとしてオブジェクト化
    private lateinit var binding: ActivityStoreDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store_detail)

        //引継ぎデータの受け取り
        shop = intent.getSerializableExtra(Search_ResultActivity.SHOP_DATA) as Shop

        // DataBindingの設定
        binding = DataBindingUtil.setContentView(this, R.layout.activity_store_detail)
        binding.lifecycleOwner = this // Binding式にLiveDataを用いる場合に記述
        binding.viewModel = viewModel // レイアウトファイル内の viewModel に SubViewModel を宛がう

        viewModel.getDetail(shop) // 店舗の詳細データを取得
    }

    /* 地図検索ボタンをクリック時、地図アプリを起動する
       使わないパラメータでWarningを出さない為に @Suppress("UNUSED_PARAMETER") を記述 */
    fun onMapSearchButtonClick(@Suppress("UNUSED_PARAMETER") view: View) {
        // 住所をURLエンコードする
        val searchAddress = URLEncoder.encode(shop.address, "UTF-8")
        // 地図アプリと連携するURI文字列を生成
        val uriStr = "geo:0:0?q=${searchAddress}"
        val uri = Uri.parse(uriStr)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }
}

// ImageView に URL から画像を表示する
@BindingAdapter("imageUrl")
fun loadImageUrl(imageView: ImageView, url: String?) {
    Glide.with(imageView.context)
        .load(url) // 画像を読み込む
        .apply(RequestOptions().override(300, 300))
        .apply(RequestOptions().error(R.drawable.ic_baseline_photo_size_select_actual_24))
        .into(imageView)
}