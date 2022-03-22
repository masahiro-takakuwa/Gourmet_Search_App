package com.websarva.wings.android.gourmet_search_app.flontEnd

import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.text.method.LinkMovementMethod
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.websarva.wings.android.gourmet_search_app.backEnd.APIData
import com.websarva.wings.android.gourmet_search_app.R
import com.websarva.wings.android.gourmet_search_app.backEnd.TutorialActivity.Companion.showForcibly
import com.websarva.wings.android.gourmet_search_app.backEnd.TutorialActivity.Companion.showIfNeeded

/** ホットペッパーAPI の APIkey 入力クラス**/

class Input_APIkeyActivity : AppCompatActivity() {

    // static変数として APIData を宣言
    companion object {
        const val API_DATA = "APIData"
    }

    // API 情報を格納するためのデータ
    private val data = APIData( 0, "",0.0, 0.0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input_apikey)

        //まだ表示していない場合チュートリアルを表示(初回のみ)
        showIfNeeded(this,savedInstanceState)

        //常にチュートリアルを表示したい場合(恒常)
        //showForcibly(this)

        //TextView内のテキストにリンクを宛がう
        val linkText = findViewById<View>(R.id.linkText) as TextView
        linkText.linksClickable = true
        linkText.text = Html.fromHtml("<a href=\"https://webservice.recruit.co.jp/register/\">こちら</a>")
        //開きたいURLを設定
        linkText.movementMethod = LinkMovementMethod.getInstance()

        // button オブジェクトの取得
        val btClick = findViewById<Button>(R.id.btClick)
        val btClear =findViewById<Button>(R.id.btCliear)
        //リスナクラスのインスタンスを生成
        val listener = ButtonListener()
        //各 button にリスナを設定
        btClick.setOnClickListener(listener)
        btClear.setOnClickListener(listener)
    }

    //ボタンが押されたかどうかを判定するリスナクラス
    private inner class ButtonListener : View.OnClickListener{
        override fun onClick(view: View){
            val input = findViewById<EditText>(R.id.etAPIkey)

            //ボタンが押された時の処理
            when(view.id){
                R.id.btClick ->{
                    if (input.text.isNotEmpty()) {
                        //入力した値を APIData の key に保存
                        data.key = input.text.toString()
                        nextscreen()
                    } else {
                        //トーストの作成
                        Toast.makeText(applicationContext, "APIキーが入力されていません", Toast.LENGTH_SHORT).show()
                    }

                }R.id.btCliear ->{
                    input.setText("")
                }
            }
        }
    }
    // 画面遷移用のメソッド
    private fun nextscreen() {
        // 遷移する対象をクラスオブジェクトで指定する
        val intent = Intent(this, Range_SelectionActivity::class.java)
        //遷移先に渡すデータを格納する
        intent.putExtra(Range_SelectionActivity.API_DATA, data)
        startActivity(intent)
    }

}