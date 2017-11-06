package org.androidtown.me;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

public class Intro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        Handler hd = new Handler(); //핸들러는 실행할수있는 객체나 다른액티비티로 메세지를 보낼때 사용하는 명령어
        hd.postDelayed(new splashhandler(),2000);//postDelayed()는 일정시간 지나면 new Runnable이 지정한 것을 샐행하라는 명령어
    }
    private class splashhandler implements Runnable{
        public void run(){
            startActivity(new Intent(getApplication(),MainActivity.class));//intent는 다른 액티비티로 넘어가기 위한 명령어
            Intro.this.finish();//로딩페이지에서 Activity stack에서 제거
        }
    }
    @Override
    public void onBackPressed(){
        //초반 플래시화면에서 뒤로 누르기 못하게함
    }
}

