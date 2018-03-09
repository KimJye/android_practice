package org.androidtown.samplecallintent;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText inputText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputText = (EditText) findViewById(R.id.inputText); // 뷰 객체 참조

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String data = inputText.getText().toString(); //입력상자에 입력된 전화번호 확인

                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(data));//전화걸기 화면을 보여줄 인텐트 객체 생성
                startActivity(intent);//액티비티 띄우기
            }
        });
    }
}
