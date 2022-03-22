package com.websarva.wings.android.gourmet_search_app.flontEnd

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.websarva.wings.android.gourmet_search_app.backEnd.APIData
import com.websarva.wings.android.gourmet_search_app.R

/**ホットペッパーAPI の Range 入力クラス**/

class Range_SelectionActivity : AppCompatActivity() {

    // static変数として APIData を宣言
    companion object {
        const val API_DATA = "APIData"
    }

    // APIData の初期化タイミングを onCreate() 呼び出しまで遅延
    private lateinit var data: APIData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_range_selection)

        //引継ぎデータの受け取り
        data = intent.getSerializableExtra(Input_APIkeyActivity.API_DATA) as APIData

        // button オブジェクトの取得
        val btn1 = findViewById<Button>(R.id.button1)
        val btn2 = findViewById<Button>(R.id.button2)
        val btn3 = findViewById<Button>(R.id.button3)
        val btn4 = findViewById<Button>(R.id.button4)
        val btn5 = findViewById<Button>(R.id.button5)
        //リスナクラスのインスタンスを生成
        val listener = RangeListener()
        //各 button にリスナを設定
        btn1.setOnClickListener(listener)
        btn2.setOnClickListener(listener)
        btn3.setOnClickListener(listener)
        btn4.setOnClickListener(listener)
        btn5.setOnClickListener(listener)
    }

    //ボタンをタップした時のリスナクラス
    private inner class RangeListener : View.OnClickListener{
        override fun onClick(view: View){
            // id の R値 によって処理を分岐、押すボタンによって検索範囲をデータに格納
            when(view.id){
                R.id.button1 ->{
                    data.range = 1
                    nextscreen()
                }
                R.id.button2 ->{
                    data.range = 2
                    nextscreen()
                }
                R.id.button3 ->{
                    data.range = 3
                    nextscreen()
                }
                R.id.button4 ->{
                    data.range = 4
                    nextscreen()
                }
                R.id.button5 ->{
                    data.range = 5
                    nextscreen()
                }
            }
        }
    }

    private fun nextscreen() {
        // 遷移する対象をクラスオブジェクトで指定する
        val intent = Intent(this, GPSActivity::class.java)
        intent.putExtra(GPSActivity.API_DATA, data)
        startActivity(intent)
    }

    //戻るボタンを押したときの遷移メソッド
    override fun onBackPressed() {
        val intent = Intent(this, Input_APIkeyActivity::class.java)
        startActivity(intent)
    }
}