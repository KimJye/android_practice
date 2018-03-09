package org.androidtown.sampleintent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    public static final int REQUEST_CODE_MENU=101;//다른 액티비티를 띄우기 위한 요청코드 정의

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void onButton1Clicked(View v){
        Intent intent = new Intent(getApplicationContext(),MenuActivity.class);//또 다른 액티비티를 띄우기 위한 인텐트 객체 생성
        startActivityForResult(intent,REQUEST_CODE_MENU);//액티비티 띄우기
    }
}
