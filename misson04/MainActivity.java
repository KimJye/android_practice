package org.androidtown.misson04;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;

public class MainActivity extends AppCompatActivity {
    EditText text;
    TextView textView;
    Button button1;
    Button button2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = (EditText)findViewById(R.id.text);
        textView= (TextView)findViewById(R.id.textView);

        Button button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String message = text.getText().toString();
                Toast.makeText(getApplicationContext(), "전송\n\n" + message, Toast.LENGTH_LONG).show();
            }
        });

        Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
        TextWatcher watcher = new TextWatcher() {
            public void onTextChanged(CharSequence str, int start, int before, int count) {
                byte[] bytes = null;
                try {
                    bytes = str.toString().getBytes("KSC5601");
                    int strCount = bytes.length;
                    textView.setText(strCount + "10/ 80바이트");
                } catch(UnsupportedEncodingException ex) {
                    ex.printStackTrace();
                }
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            public void afterTextChanged(Editable strEditable) {
                String str = strEditable.toString();
                try {
                    byte[] strBytes = str.getBytes("KSC5601");
                    if(strBytes.length > 80) {
                        strEditable.delete(strEditable.length()-2, strEditable.length()-1);
                    }
                } catch(Exception ex) {
                    ex.printStackTrace();
                }
            }
        };

        text.addTextChangedListener(watcher);

    }
}
