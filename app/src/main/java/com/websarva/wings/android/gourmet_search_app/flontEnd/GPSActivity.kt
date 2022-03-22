package com.websarva.wings.android.gourmet_search_app.flontEnd

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.websarva.wings.android.gourmet_search_app.backEnd.APIData
import com.websarva.wings.android.gourmet_search_app.R

/** GPS による緯度と経度(位置情報)の取得クラス**/

class GPSActivity : AppCompatActivity() {

    //static変数として APIData を宣言
    companion object {
        const val API_DATA = "APIData"
    }

    //初期化タイミングをonCreate()呼び出しまで遅延
    private lateinit var data: APIData
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gpsactivity)

        //引継ぎデータの受け取り
        data = intent.getSerializableExtra(Range_SelectionActivity.API_DATA) as APIData
        // fusedLocationClient オブジェクトを取得
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        // 位置情報許可の確認(ACCESS_FINE_LOCATION)
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED) {

            // ACCESS_FINE_LOCATION の許可を求めるダイアログの生成
            val permissions = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION)
            ActivityCompat.requestPermissions(this@GPSActivity, permissions, 1000)
            return
        }

        // GPS から位置情報を取得
        fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
            if (location != null) {

                data.latitude = location.latitude     // 緯度を検索データに格納
                data.longitude = location.longitude   // 経度を検索データに格納
                nextscreen()
            }
        }
    }
    //許可ダイアログの承認結果を受け取る
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray, ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        // パーミッションダイアログで許可を選択した場合
        if (requestCode == 1000 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
            //再度 ACCESS_FIND_LOCATION の許可が下りていないかのチェックを行い、降りていない場合は動作を停止
            if (ActivityCompat.checkSelfPermission(
                    applicationContext, Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED) {
                    return
            }
        }
        // GPS から位置情報を取得
        fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
            if (location != null) {

                data.latitude = location.latitude     // 緯度を検索データに格納
                data.longitude = location.longitude   // 経度を検索データに格納
                nextscreen()
            }
        }
    }

    private fun nextscreen() {
        // 遷移する対象をクラスオブジェクトで指定する
        val intent = Intent(this, Search_ResultActivity::class.java)
        intent.putExtra(Search_ResultActivity.API_DATA, data)
        startActivity(intent)
    }
}