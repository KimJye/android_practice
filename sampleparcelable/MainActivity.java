package org.androidtown.sampleparcelable;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    public static final int REQUEST_CODE_MENU=101;
    public static final String KEY_SIMPLE_DATA="data";
    @Override
    protected void onCreate(Bundle savedInstanceState) {//버튼 클릭하면 메뉴 엑티비티를 띄우는 코드
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void onButton1Clicked(View v){
        Intent intent = new Intent(getApplicationContext(),MenuActivity.class);

        SimpleData data = new SimpleData(100,"Hello Android!");//SimpleData 객체 생성.정수,문자열 데이터가 Parcel 객체로 만들어진다.

        intent.putExtra(KEY_SIMPLE_DATA,data); //인텐트에 부가 데이터로 넣기. 그래서 SimpleData객체는 메뉴 엑티비티에서 꺼내사용할수있음.
        startActivityForResult(intent,REQUEST_CODE_MENU);
    }
}
