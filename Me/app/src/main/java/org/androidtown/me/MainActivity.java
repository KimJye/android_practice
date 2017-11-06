package org.androidtown.me;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayShowHomeEnabled(true); //앱 아이콘 생성
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);//앱 아이콘 상단 넣기


    }
}
