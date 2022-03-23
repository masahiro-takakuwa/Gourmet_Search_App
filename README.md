# グルメサーチアプリ

- 使用言語：Kotlin 1.4.30
- フレームワーク：Android Studio 4.1.2
- ライブラリ・API：Data Binding・ViewModel・LiveData・Coroutine・Retrofit2・Gson・Glide・RecyclerView・welcome-android

## 概要
ホットペッパーAPIを利用して周辺のレストランを検索するアプリです。

## 使用上の注意
ホットペッパーAPIを使用するため、APIキーが必要となります。

## WebAPI
使用したWebAPIを以下に示します。
<br>ホットペッパーグルメサーチAPI：https://webservice.recruit.co.jp/doc/hotpepper/reference.html

## 使用方法及び各画面の詳細

### 1. チュートリアル
アプリを起動すると、ウォークスルーが表示されます。
<br>ウォークスルーはプログラム内で常に表示するか、初回のみにするかを選択できます。
<br><img src="Gourmet_Search_App_Contents/Tutorial_Image_1.png" width=10%>
&emsp;<img src="Gourmet_Search_App_Contents/Tutorial_Image_2.png" width=10%>
&emsp;<img src="Gourmet_Search_App_Contents/Tutorial_Image_3.png" width=10%>
&emsp;<img src="Gourmet_Search_App_Contents/Tutorial_Image_4.png" width=10%>
&emsp;<img src="Gourmet_Search_App_Contents/Tutorial_Image_5.png" width=10%>
&emsp;<img src="Gourmet_Search_App_Contents/Tutorial_Image_6.png" width=10%>

### 2. APIキーの入力
ウォークスルーが終了すると、ホットペッパーAPIキーの入力が求められます。
<br>ホットペッパーAPIを入手していない場合はブラウザを使ってホームページに飛ぶこともできます。
<br><img src="Gourmet_Search_App_Contents/Input_Key_Image.png" width=20%>
&emsp;<img src="Gourmet_Search_App_Contents/Hottopeppa_Website_Image.png" width=20%>

### 3. 検索範囲の選択
5つのボタンから、検索範囲の選択を行います。
<br><img src="Gourmet_Search_App_Contents/Range_Selection_Image.png" width=20%>

### 4. GPSの検索
GPSの検索を行います。
<br><img src="Gourmet_Search_App_Contents/GPS_Image.png" width=20%>

### 5. 店舗リストの表示
選択した検索条件から、周辺のレストランを探し、リストとして表示します。
<br> APIキーが間違っている場合や周辺にレストランが無い場合は、それぞれエラーメッセージを表示します。
<br><img src="Gourmet_Search_App_Contents/Search_Result_List_Image.png" width=20%>
&emsp;<img src="Gourmet_Search_App_Contents/Search_Result_API_Error_Image.png" width=20%>
&emsp;<img src="Gourmet_Search_App_Contents/Search_Result_Error_Image.png" width=20%>

### 6. 店舗詳細の表示
リストから選択した店舗の詳細が表示されます。
<br>地図検索ボタンを押すと、住所が地図アプリに渡され、住所の地点が表示されます。
<br><img src="Gourmet_Search_App_Contents/StoreDetail_Image.png" width=20%>
<br>
<br>
<br>＊各画面の詳細は同フォルダ内の[簡易仕様書](https://github.com/masahiro-takakuwa/Gourmet_Search_App/blob/main/%E7%B0%A1%E6%98%93%E4%BB%95%E6%A7%98%E6%9B%B8.md)にあります。
