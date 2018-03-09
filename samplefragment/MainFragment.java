package org.androidtown.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by 김지혜 on 2017-11-19.
 */

public class MainFragment extends Fragment {//Fragment클래스 상속.fragment_main.xml파일을 인플레이션.
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {//객체 inflate메소드 호출
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_main,container,false);//두번째 파라미터가 이 프래그먼트의 가장 상위 레이아웃

        Button button = (Button)rootView.findViewById(R.id.button);//버튼 객체를 찾아낸다.
        button.setOnClickListener(new View.OnClickListener(){//리스너를 등록하면 버튼이 클릭되어있을 때 이벤트를 처리.
            @Override
            public void onClick(View v){//MainActivity객체 참조한 후 onFragmentChanged()메소드 호출
                MainActivity activity=(MainActivity)getActivity();
                activity.onFragmentChanged(0);//프래그먼트 매니저를 이용해 프래그먼트 전환하는 메소드.
            }
        });
        return rootView;
    }
}
