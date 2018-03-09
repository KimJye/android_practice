package org.androidtown.samplereceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SmsReceiver extends BroadcastReceiver {
    public static final String TAG="SmsReciver";
    public SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Override
    public void onReceive(Context context, Intent intent) {//SMS받으면 onReceive()메소드 자동 호출/Intent 객체 안에 SMS 데이터 있음
        Log.i(TAG,"onReceive() 메소드 호출됨.");//onReceive 메소드가 호출됐는지 확인하기 위해.

        //인텐트 안에 들어 있는 SMS 메시지를 파싱
        Bundle bundle = intent.getExtras();
        SmsMessage[] messages = parseSmsMessage(bundle);

        if(messages !=null && messages.length>0){
            //SMS 발신 번호 확인
            String sender = messages[0].getOriginatingAddress();
            Log.i(TAG,"SMS sender : "+sender);

            //SMS 메시지 확인
            String contents = messages[0].getMessageBody().toString();

            Log.i(TAG, "SMS contents: "+contents);
            //SMS 수신 시간 확인
            Date receivedDate = new Date(messages[0].getTimestampMillis());

            Log.i(TAG,"SMS received date: "+receivedDate.toString());

            sendToActivity(context,sender,contents,receivedDate);
        }
    }

    private void sendToActivity(Context context, String sender, String contents, Date receivedDate){
        //메시지를 보여줄 액티비티를 띄워줍니다.
        Intent myIntent = new Intent(context, SmsActivity.class);

        //플래그를 이용합니다.
        myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_SINGLE_TOP| Intent.FLAG_ACTIVITY_CLEAR_TOP);

        myIntent.putExtra("sender",sender);
        myIntent.putExtra("contents",contents);
        myIntent.putExtra("receivedDate",format.format(receivedDate));

        context.startActivity(myIntent);
    }

    private SmsMessage[] parseSmsMessage(Bundle bundle){//SMS 데이터를 확인 할 수 있도록 만드는 메소드.안드로이드API에 정해둔 코드
        Object[] objs =(Object[])bundle.get("pdus");
        SmsMessage[] messages = new SmsMessage[objs.length];

        int smsCount= objs.length;
        for(int i=0;i<smsCount;i++){
            //PDU 포맷으로 되어 있는 메시지를 복원합니다.
            if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){//API 23이상
                String format = bundle.getString("fromat");
                messages[i]=SmsMessage.createFromPdu((byte[]) objs[i], format);//인텐트 객체 안에 부가데이터로 들어있는 SMS 데이터 확인함.
                                                                               //SmsMessage 객체로 변환 후 SMS 데이터를 확인
            }else{
                messages[i]=SmsMessage.createFromPdu((byte[]) objs[i]);
            }
        }
        return messages;
    }
}
