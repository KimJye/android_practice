package org.androidtown.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by 김지혜 on 2017-11-19.
 */

public class MenuFragment extends Fragment {//Fragment클래스 상속.fragment_menu.xml파일을 인플레이션.
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {//객체 inflate메소드 호출
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_menu,container,false);//두번째 파라미터가 이 프래그먼트의 가장 상위 레이아웃
        return rootView;
    }
}
