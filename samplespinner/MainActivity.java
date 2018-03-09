package org.androidtown.spinner;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    String[] items ={"유상신","노은하","홍은지","문성현","임형","이승진","김지혜","루비"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView =(TextView) findViewById(R.id.textView);//텍스트뷰 객체를 찾아낸 후 변수에 할당

        Spinner spinner =(Spinner) findViewById(R.id.spinner);//스피너 객체를 찾아낸 후 변수에 할당
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(//문자열로만 구성된 아이템들을 스피너로 보여줄때 사용. ArrayAdapter 객체 생성
                this, android.R.layout.simple_spinner_item,items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);//아이템들을 보여줄 뷰의 레이아웃을 지정

        spinner.setAdapter(adapter);//스피너 객체도 선택 위젯이므로 어댑터 객체를 전달해야한다.

        //아이템 선택 이벤트 처리
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){//스피너 객체가 아이템 선택 이벤트를 처리할 수 있도록 사용하는 리스너
            //아이템이 선택되었을 때 자동 호출됨
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id){
                textView.setText(items[position]);//선택된 값을 텍스트로 표시
            }

            //아무것도 선택되지 않았을 때 호출됨
            @Override
            public void onNothingSelected(AdapterView<?> adapterView){
                textView.setText("");
            }
        });
    }
}
