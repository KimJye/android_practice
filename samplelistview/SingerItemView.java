package org.androidtown.samplelistview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by 김지혜 on 2017-12-01.
 */

public class SingerItemView extends LinearLayout {//리니어 레이아웃을 상속하므로 다른 뷰들을 포함할수 있다.
    TextView textView;
    TextView textView2;
    TextView textView3;
    ImageView imageView;

    public SingerItemView(Context context) {
        super(context);

        init(context);
    }

    public SingerItemView(Context context, AttributeSet attrs){
        super(context, attrs);
        init(context);
    }
    public void init(Context context){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);//레이아웃 인플레이션(xml레이아웃을 객체화)
        inflater.inflate(R.layout.singer_item, this, true);

        textView =(TextView) findViewById(R.id.textView);
        textView2 = (TextView) findViewById(R.id.textView2);
        textView3=(TextView) findViewById(R.id.textView3);
        imageView = (ImageView) findViewById(R.id.imageView);
    }
    //데이터 설정
    public void setName(String name){
        textView.setText(name);
    }

    public void setMobile(String mobile){
        textView2.setText(mobile);
    }

    public void setAge(int age){
        textView3.setText(String.valueOf(age));
    }
    //이미지 뷰에 설정할 이미지는 정수 타입의 리소스로 저장할 수 있어서 int타입 변수로 선언
    public void setImage(int resId){
        imageView.setImageResource(resId);
    }
}
