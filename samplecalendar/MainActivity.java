package org.androidtown.samplecalendar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    GridView monthView;
    MonthAdapter monthViewAdapter;
    TextView monthText;
    int curYear;
    int curMonth;
    //private ArrayList<String> datyList;//일 저장할 리스트

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        monthView = (GridView) findViewById(R.id.monthView);//레이아웃에 정의된 월별 캘린더 뷰 객체 참조
        monthViewAdapter = new MonthAdapter(this);
        monthView.setAdapter(monthViewAdapter);//어댑터 객체 생성 후 월별 캘린더 뷰 객체에 설정
        //월별 캘린더 뷰에 리스너 설정
        monthView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                MonthItem curItem = (MonthItem) monthViewAdapter.getItem(position);
                int day = curItem.getDay();

                Log.d("MainActivity", "Selected : " + day);
            }
        });
        monthText = (TextView) findViewById(R.id.monthText);
        setMonthText();



        //이전 월 [이동] 버튼 클릭 시 일별 데이터를 다시 계산 하는 메소드 호출하고 화면 갱신
        Button monthPrevious = (Button) findViewById(R.id.monthPrevious);
        monthPrevious.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                monthViewAdapter.setPreviousMonth();
                monthViewAdapter.notifyDataSetChanged();

                setMonthText();
            }
        });

        // 다음 월[이동]버튼 클릭 시 일별 데이터를 다시 계산 하는 메소드 호출하고 화면 갱신
        Button monthNext = (Button) findViewById(R.id.monthNext);
        monthNext.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                monthViewAdapter.setNextMonth();
                monthViewAdapter.notifyDataSetChanged();

                setMonthText();
            }
        });

    }

    private void setMonthText() {
        curYear = monthViewAdapter.getCurYear();
        curMonth = monthViewAdapter.getCurMonth();

        monthText.setText(curYear + "년 " + (curMonth + 1) + "월");
    }
}
