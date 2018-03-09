package org.androidtown.samplebitmapwidget;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by 김지혜 on 2017-12-01.
 */

public class BitmapButton extends AppCompatButton {//AppCompatButton 클래스를 상속하여 새로운 클래스 정의
    //아이콘 리소스 정의
    int iconNormal = R.drawable.bitmap_button_normal;
    int iconClicked = R.drawable.bitmap_button_clicked;

    //아이콘 상태 정의
    int iconStatus = STATUS_NORMAL;
    public static int STATUS_NORMAL=0;
    public static int STATUS_CLICKED=1;

    //소스 코드에서 객체를 생성했을 때 호출되는 생성자
    public BitmapButton(Context context){
        super(context);

        init();
    }

    //XML에 추가된 버튼이 인플레이션될 때 호출되는 생성자
    public BitmapButton(Context context, AttributeSet atts){
        super(context, atts);

        //객체 초기화하는 메소드
        init();
    }

    //초기화-텍스트 크기는 /res/values/dimens,xml에 정의한 값을 참조함
    public void init(){//배경이미지, 폰트 크기, 색상, 글꼴 설정.
        setBackgroundResource(iconNormal);//비트맵 버튼 객체 생성시 배경이미지를 iconNormal로 지정.

        int defaultTextColor = Color.WHITE;//default값 TextColor는 White(하얀색)//글자 색상 설정
        float defaultTextSize = getResources().getDimension(R.dimen.text_size);//글자 크기 설정
        Typeface defaultTypeface = Typeface.DEFAULT_BOLD;//글자 폰트 설정

        setTextColor(defaultTextColor);
        setTextSize(defaultTextSize);
        setTypeface(defaultTypeface);
    }

    //아이콘 리소스 설정
    public void setIcon(int iconNormal, int iconClicked){
        this.iconNormal=iconNormal;
        this.iconClicked=iconClicked;
    }

    //터치 이벤트 처리
    public boolean onTouchEvent(MotionEvent event){
        super.onTouchEvent(event);

        int action = event.getAction();

        switch (action){
            case MotionEvent.ACTION_DOWN:
                setBackgroundResource(R.drawable.bitmap_button_clicked);//클릭하면 나인패치 이미지 바뀜

                iconStatus=STATUS_CLICKED;

                break;

            case MotionEvent.ACTION_OUTSIDE:
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                setBackgroundResource(R.drawable.bitmap_button_normal);

                iconStatus = STATUS_NORMAL;

                break;
        }

        //다시 그리기
        invalidate();
        return true;
    }
}
