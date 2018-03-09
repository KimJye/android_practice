package org.androidtown.sampledatetimepicker;


import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TimePicker;
import android.widget.TimePicker.OnTimeChangedListener;

import java.util.Calendar;

/**
 * Created by 김지혜 on 2017-12-02.
 */

public class DateTimePicker extends LinearLayout {//리니어 레이아웃을 상속하여 새로운 클래스 정의
    public static interface OnDateTimeChangedListener{//날짜나 시간 변경되는 이벤트 함께 처리하기 위한 새로운 리스너인터페이스 정의
        void onDateTimeChanged(DateTimePicker view, int year, int monthOfTear, int dayOfYear, int hourOfDay, int minute);
    }

    private OnDateTimeChangedListener listener;
    private DatePicker datePicker;
    private TimePicker timePicker;
    private CheckBox enableTimeCheckBox;

    public DateTimePicker(Context context){
        super(context);
        init(context);
    }
    public DateTimePicker(Context context, @Nullable AttributeSet attrs){
        super(context, attrs);
        init(context);
    }
    //새로 정의한 클래스를 위한 레이아웃 인플레이션
    private void init(Context context){
        //XML 레이아웃을 인플레이션함
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.picker, this, true);

        //시간 정보 참조
        Calendar calendar = Calendar.getInstance();
        final int curYear = calendar.get(Calendar.YEAR);
        final int curMonth = calendar.get(Calendar.MONTH);
        final int curDay = calendar.get(Calendar.DAY_OF_MONTH);
        final int curHour = calendar.get(Calendar.HOUR_OF_DAY);
        final int curMinute = calendar.get(Calendar.MINUTE);

        //날짜 선택 위젯 초기화
        datePicker = (DatePicker)findViewById(R.id.datePicker);
        datePicker.init(curYear, curMonth, curDay, new DatePicker.OnDateChangedListener() {
            public void onDateChanged(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                //새로 정의한 리스너로 이벤트 전달
                if(listener !=null){//시간이 변경되는 이벤트가 발생했을때 새로운 리스너의 메소드 호출
                    listener.onDateTimeChanged(
                            DateTimePicker.this, year, monthOfYear, dayOfMonth, timePicker.getCurrentHour(), timePicker.getCurrentMinute());
                }
            }
        });

        //체크박스 이벤트 처리
        enableTimeCheckBox = (CheckBox)findViewById(R.id.enableTimeCheckBox);
        enableTimeCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                timePicker.setEnabled(isChecked);
                timePicker.setVisibility((enableTimeCheckBox).isChecked()? View.VISIBLE: View.INVISIBLE);
            }
        });
        //시간 선택 위젯 이벤트 처리
        timePicker = (TimePicker)findViewById(R.id.timePicker);
        timePicker.setOnTimeChangedListener(new OnTimeChangedListener() {
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                if(listener != null) {//날짜가 변경되는 이벤트가 발생했을 때 새로운 리스너의 메소드 호출
                    listener.onDateTimeChanged(
                            DateTimePicker.this, datePicker.getYear(),
                            datePicker.getMonth(), datePicker.getDayOfMonth(), hourOfDay, minute);
                }
            }
        });

        timePicker.setCurrentHour(curHour);
        timePicker.setCurrentMinute(curMinute);
        timePicker.setEnabled(enableTimeCheckBox.isChecked());
        timePicker.setVisibility((enableTimeCheckBox.isChecked()?View.VISIBLE:View.INVISIBLE));
    }

    public void setOnDateTimeChangedListener(OnDateTimeChangedListener dateTimeListener){//새로운 리스너 객체 설정 메소드 정의
        this.listener = dateTimeListener;
    }

    public void updateDateTime(int year, int monthOfYear, int dayOfMonth, int currentHour, int currentMinute){
        datePicker.updateDate(year, monthOfYear, dayOfMonth);
        timePicker.setCurrentHour(currentHour);
        timePicker.setCurrentMinute(currentMinute);
    }

    public void updateDate(int year, int monthOfYear, int dayOfMonth){
        datePicker.updateDate(year, monthOfYear, dayOfMonth);
    }

    public void setIs24HourView(final boolean is24HourView){
        timePicker.setIs24HourView(is24HourView);
    }

    public int getYear() {
        return datePicker.getYear();
    }

    public int getMonth() {
        return datePicker.getMonth();
    }

    public int getDayOfMonth() {
        return datePicker.getDayOfMonth();
    }

    public int getCurrentHour() {
        return timePicker.getCurrentHour();
    }

    public int getCurrentMinute() {
        return timePicker.getCurrentMinute();
    }

    public boolean enableTime() {
        return enableTimeCheckBox.isChecked();
    }

}
