package org.androidtown.samplegridview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText editText;

    GridView gridView;
    SingerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView= (GridView) findViewById(R.id.gridView);//그리드뷰 객체 생성

        adapter = new SingerAdapter();//어댑터 객체 생성
        //어댑터에 각 아이템의 데이터 추가
        adapter.addItem(new SingerItem("곰", "010-1111-1234", 20, R.drawable.animal_bear));
        adapter.addItem(new SingerItem("돼지", "010-2222-1234", 21, R.drawable.animal_pig));
        adapter.addItem(new SingerItem("고양이", "010-3333-1234", 22, R.drawable.animal_cat_large));
        adapter.addItem(new SingerItem("루비", "010-4444-1234", 23, R.drawable.animal_dog_large));
        adapter.addItem(new SingerItem("부엉이", "010-5555-1234", 24, R.drawable.animal_owl_large));

        gridView.setAdapter(adapter);//리스트뷰에 어댑터 객체 설정


        editText = (EditText) findViewById(R.id.editText);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editText.getText().toString();
                String mobile = "010-1000-1000";
                int age = 20;

                adapter.addItem(new SingerItem(name, mobile, age, R.drawable.animal_cat_large));
                adapter.notifyDataSetChanged();
            }
        });


        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {//아이템을 클릭했을 때 토스트 메시지를 보여주도록 리스너 설정
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                SingerItem item = (SingerItem) adapter.getItem(position);
                Toast.makeText(getApplicationContext(), "선택 : " + item.getName(), Toast.LENGTH_LONG).show();
            }
        });

    }

    //리스트뷰 구성 하기. 리스트뷰에는 데이터를 직접 설정할 수 없고 어댑터 객체를 통해 설정한다.
    class SingerAdapter extends BaseAdapter {//BaseAdapter를 상속하여 새로운 어댑터 정의
        ArrayList<SingerItem> items = new ArrayList<SingerItem>();//각 아이템의 데이터를 담고 있는 SingerItem 객체를 저장할 ArrayList 객체 생성

        @Override
        public int getCount() {//이 어댑터에서 관리하는 아이템의 개수
            return items.size();
        }

        public void addItem(SingerItem item) {
            items.add(item);
        }

        public Object getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup viewGroup) {//각 아이템에 보일 뷰를 반환하는 메소드
        /*
        첫번째 파라미터: 아이템의 인덱스/ 두번째 파라미터: 현재 인덱스에 해당하는 뷰 객체. 이미 만들어진 뷰 재사용하면서 데이터만 바꾸어 보여주는 방식.
        세번째 파라미터: 이 뷰를 포함하고 있는 부모 컨테이너 객체
         */
            SingerItemView view = new SingerItemView(getApplicationContext());
            SingerItem item = items.get(position);
            view.setName(item.getName());
            view.setMobile(item.getMobile());
            view.setAge(item.getAge());
            view.setImage(item.getResId());

            return view;
        }
    }
}
