package com.websarva.wings.android.gourmet_search_app.backEnd

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.websarva.wings.android.gourmet_search_app.R

/** 1行分のデータを1行分の View に設定するクラス (Adapterの設定)**/

class Search_Result_Adapter (context: Context,) : RecyclerView.Adapter<Search_Result_Adapter.SearchResultListHolder>() {

    //レイアウトファイルをViewにロードする
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    // 店舗リスト
    private var shopList: List<Shop> = emptyList()
    //リスナ
    private var listener: ((Shop) -> Unit)? = null

    //1データ分の参照を保持するホルダークラス
    inner class SearchResultListHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val photoSmall: ImageView = itemView.findViewById(R.id.photoSmall)  // 店舗写真
        val shopName: TextView = itemView.findViewById(R.id.shopName)       // 店舗名
        val access: TextView = itemView.findViewById(R.id.access)           // アクセス
    }

    //レイアウトマネージャーによって起動され、新しいviewを作成する
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultListHolder {
        //parentにRecyclerViewを指定してlist_item(レイアウト)からviewを作成
        val itemView = inflater.inflate(R.layout.list_item, parent, false)
        return SearchResultListHolder(itemView)
    }

    //レイアウトマネージャーによって起動され、作成したview上に値を設定する
    override fun onBindViewHolder(holder: SearchResultListHolder, position: Int) {
        //データリストから１つのデータを取得し、holderの持つレイアウト上のプロパティに値を設定
        val shop: Shop = shopList[position]
        // URLから店舗トップ写真を表示
        Glide.with(holder.photoSmall.context).load(shop.photo?.pc?.s).into(holder.photoSmall)
        holder.shopName.text = "${shop.name} (${shop.genre?.name})\"" // 店舗名
        holder.access.text = shop.mobile_access.toString()          // アクセス

        // ItemがクリックされたことをSearch_ResultActivityへ通知
        holder.itemView.setOnClickListener {
            listener?.invoke(shop)
        }
    }

    //  データセットのサイズを返す(店舗リストの要素数を取得)
    override fun getItemCount() = shopList.size

    // 店舗リストのセッター
    fun setShopList(shopList: List<Shop>) {
        this.shopList = shopList
        notifyDataSetChanged()
    }

    // 表示されるItemのリスナ設定
    fun setOnClickListener(listener: (Shop) -> Unit) {
        this.listener = listener
    }
}