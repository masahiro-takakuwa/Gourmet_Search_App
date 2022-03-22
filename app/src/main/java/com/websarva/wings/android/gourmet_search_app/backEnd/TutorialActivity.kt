package com.websarva.wings.android.gourmet_search_app.backEnd

import android.app.Activity
import android.os.Bundle
import com.stephentuso.welcome.*

/** アプリケーション の チュートリアル を設定するクラス**/

class TutorialActivity : WelcomeActivity() {

    companion object {
        //まだ表示していない場合チュートリアルを表示(初回のみ)
        fun showIfNeeded(activity: Activity, savedInstanceState: Bundle?) {
            WelcomeHelper(activity, TutorialActivity::class.java).show(savedInstanceState)
        }
        //常にチュートリアルを表示したい場合(恒常)
        fun showForcibly(activity: Activity) {
            WelcomeHelper(activity, TutorialActivity::class.java).forceShow()
        }
    }

    //表示するチュートリアル画面を定義する
    override fun configuration(): WelcomeConfiguration {
        return WelcomeConfiguration.Builder(this)
            .defaultBackgroundColor(com.websarva.wings.android.gourmet_search_app.R.color.orange_700)

            .page(TitlePage(com.websarva.wings.android.gourmet_search_app.R.drawable.free_icon,
                "初めに\nこれはこのアプリについての説明です。"))

            .page(BasicPage(com.websarva.wings.android.gourmet_search_app.R.drawable.first,
                "1 : APIキーの入力",
                "ホットペッパーAPIキーを入力してください"))

            .page(BasicPage(com.websarva.wings.android.gourmet_search_app.R.drawable.second,
                "2 : 検索半径の設定",
                "お好みの検索半径を選んでください"))

            .page(BasicPage(com.websarva.wings.android.gourmet_search_app.R.drawable.third,
                "3 : 検索結果",
                "検索範囲内のお店がリストで表示されます"))

            .page(BasicPage(com.websarva.wings.android.gourmet_search_app.R.drawable.fourth,
                "4 : 店舗詳細",
                "営業時間や住所を閲覧できます。\n下記の地図検索ボタンを選択することで\nグーグルマップで閲覧することが出来ます。"))

            .page(TitlePage(com.websarva.wings.android.gourmet_search_app.R.drawable.good_icon,
                "終わりに\nこのアプリがあなたの助けになることを願います。"))
            .swipeToDismiss(true)
            .build()
    }
}