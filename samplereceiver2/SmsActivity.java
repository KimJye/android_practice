/*
2017/11/05
김지혜
브로드캐스트 수신자 연습
 */
package org.androidtown.samplereceiver;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SmsActivity extends AppCompatActivity {
    EditText editText1;
    EditText editText2;
    EditText editText3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);

        editText1=(EditText) findViewById(R.id.editText1);
        editText2=(EditText) findViewById(R.id.editText2);
        editText3=(EditText) findViewById(R.id.editText3);
        Button button = (Button) findViewById(R.id.button);

        Intent passedIntent = getIntent();//브로드캐스트 수신자로부터 인텐트를 전달 받음
        processIntent(passedIntent);

        button.setOnClickListener(new View.OnClickListener() {//버튼 클릭시 동작
            @Override
            public void onClick(View view) {
                finish();//화면에 있는 확인버튼을 눌렀을 때 화면을 닫아준다.
            }
        });
    }

    @Override
    protected void onNewIntent(Intent intent){//이미 액티비티가 만들어져 있는 상태에서 전달 받은 인텐트도 처리하도록 재정의
        processIntent(intent);

        super.onNewIntent(intent);
    }

    private void processIntent(Intent intent){//인텐트 객체 안에 들어있는 부가데이트를 꺼내서 입력상자에 설정
        if(intent != null){
            String sender = intent.getStringExtra("sender");
            String contents = intent.getStringExtra("contents");
            String receivedDate = intent.getStringExtra("receivedDate");

            editText1.setText(sender);
            editText2.setText(contents);
            editText3.setText(receivedDate);
        }
    }
}
