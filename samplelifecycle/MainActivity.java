package org.androidtown.samplelifecycle;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText nameInput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(this,"onCreate 호출됨", Toast.LENGTH_LONG).show();
        nameInput = (EditText) findViewById(R.id.nameInput);
    }
    public void onButton1Clicked(View v) {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this,"onStart 호출됨",Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this,"onStop 호출됨",Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this,"onDestroy 호출됨",Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this,"onPause 호출됨",Toast.LENGTH_LONG).show();
        saveState();//현재 입력상자에 입력된 데이터를 저장
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this,"onResume 호출됨",Toast.LENGTH_LONG).show();
        restoreState();//설정 정보에 저장된 데이터를 복원
    }

    protected void restoreState(){//설정 정보에 저장된 데이타=ㅓ를 가져와서 토스트 메세지로 보여준다.
        SharedPreferences pref= getSharedPreferences("pref", Activity.MODE_PRIVATE);
        if ((pref !=null)&& (pref.contains("name"))){
            String name =pref.getString("name", "");
            nameInput.setText(name);
        }
    }

    protected void saveState(){//현재 입력상자에 입력된 데이터 저장
        SharedPreferences pref = getSharedPreferences("pref",Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("name", nameInput.getText().toString());
        editor.commit();
    }
    /*데이터를 저장할때 SharedPreferences를 사용하며 pref 문자열을 저장소의 이름으로 사용한다.
      SharedPreferences객체를 사용하려면 getSharedPeferences()메소드로 참조한다.*/
    protected  void clearMyprefs(){
        SharedPreferences pref = getSharedPreferences("pref",Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.clear();
        editor.commit();
    }
}
