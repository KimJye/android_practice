/*
2017/11/04
김지혜
서비스 공부하기
 */
package org.androidtown.sampleservice;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {//버튼을 클릭했을 때 startService()메소드를 호출하도록
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText =(EditText) findViewById(R.id.editText);

        Intent passedIntent = getIntent();//MainActivity가 메모리에 처음 만들어진다면 getintent()메소드 호출하여 인텐트 객체 참조

        processIntent(passedIntent);
    }
    @Override//만약 MainActivity가 메모리에 만들어져 있다면 onNewIntent()메소드로 전달되므로 메소드 재정의
    protected void onNewIntent(Intent intent) {
        processIntent(intent);

        super.onNewIntent(intent);
    }
    private void processIntent(Intent intent) {//인텐트 객체 처리 메소드
        if (intent != null) {
            String command = intent.getStringExtra("command");
            String name = intent.getStringExtra("name");

            //인텐트로 전달받은 데이터는 토스트 메시지로 보이도록 하기
            Toast.makeText(this, "command : " + command + ", name : " + name, Toast.LENGTH_LONG).show();
        }
    }

    public void onButton1Clicked(View v ){
        String name =editText.getText().toString();

        Intent intent = new Intent(this, MyService.class);
        intent.putExtra("command","show"); //부가 데이터 삽입/키값 부여/ 서비스 쪽으로 전달한 인텐트 객체의 데이터가
                                             //어떤 목적으로 사용되는지 구별하기 위해
        intent.putExtra("name",name);//부가데이터 삽입/키값 부여/ 입력상자에서 가져온 문자열을 전달하기 위한 것.
        startService(intent); //서비스쪽으로 전달할때 startService()메소드 사용./인텐트 객체 전달
    }
}
