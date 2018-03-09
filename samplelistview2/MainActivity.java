package org.androidtown.samplelistview2;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends ListActivity {
    String[] items ={"유상신","노은하","홍은지","문성현","임형","이승진","김지혜","루비"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //AraayAdapter객체생성. 리스트뷰 보여주기.
        setListAdapter(new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                items));
    }
    //리스트뷰의 한 항목을 터치하여 선택했을 때 텍스트뷰에 선택된 아이템을 보여주는 메소드
    protected void onListItemClick(ListView list, View v, int position, long id) {
        super.onListItemClick(list, v, position, id);

        String text = "position : " + position + " " + items[position];
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }

}
