package org.androidtown.samplelayoutinflater;
/*
2017/10/28
부분 레이아웃파일 연습 Inflation이용
*/

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;

public class MenuActivity extends AppCompatActivity {
    LinearLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        container = (LinearLayout) findViewById(R.id.container);//activity_menu.xml안에 들어있는 리니어 레이아웃 객체를 참조

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);//부분화면으로 보여줄sub1.xml내용을 메모리에 객체화위해 getSystemService()메소드 사용해 LayoutInflater 객체 참조
                inflater.inflate(R.layout.sub1, container, true);//inflate메소드 호출로 su1,container객체 전달. container를 id로 갖고있는 리니어 레이아웃 객체에 sub.xml파일의 레이아웃을 설정.객체화 과정

                CheckBox checkBox = (CheckBox) container.findViewById(R.id.checkBox);//이제 sub.xml속 참조 가능. 다만 xml레이아웃이 container객체에 설정되어있으므로 container 객체의 findViewById() 메소드를 사용해야 한다.
                checkBox.setText("로딩되었어요.");
            }
        });
    }
}