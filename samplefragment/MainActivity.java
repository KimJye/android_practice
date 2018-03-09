package org.androidtown.fragment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    MainFragment mainFragment;
    MenuFragment menuFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //메인 프래그먼트는 activity_main.xml파일에 추가되어있어서 id를 이용해 찾아야 하지만 뷰가 아니라서 Activity클래스에 있는 findViewById()메소드 사용
        mainFragment=(MainFragment)getSupportFragmentManager().findFragmentById(R.id.mainFragment);//아이디 찾은 후 변수에 할당
        menuFragment=new MenuFragment();//new 연산지 사용해 새로운 객체로 만든 후 변수에 할당
    }

    public void onFragmentChanged(int index){
        if(index==0){//메뉴프래그먼트가 보이도록
            getSupportFragmentManager().beginTransaction().replace(R.id.container,menuFragment).commit();
        }else if(index==1){//메인프래그먼트가 보이도록
            getSupportFragmentManager().beginTransaction().replace(R.id.container,mainFragment).commit();
            //프래그먼트 추가,삭제,교체등 작업 후 오류가 생기면 원상태로 돌릴수있게 트랜잭션 객체를 만들어 실행한다.
        }
    }
}
