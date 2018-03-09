package org.androidtown.sampleservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {//서비스는 Service 클래스를 상속한다.
    private static final String TAG="MyService";//문자열을 상수로 정의한 후 사용
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
    //Service클래스에 있는 onCreate, onDestory, onStartCommand 메소드에서는 Log.d()메소드를 사용해 로그를 출력한다.
    @Override
    public void onCreate() {
        super.onCreate();

        Log.d(TAG,"onCreate() 호출됨");//로그 출력 위해서는 첫번째 파라미터로 태그 문자열을 전달해야한다./ 태그 문자열은 로그를 구별하는 역할
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {//인텐트 객체를 전달 받는 중요한 역할
        Log.d(TAG,"onStartCommand() 호출됨");

        if(intent==null){//시스템에 의해 자동으로 서비스가 다시 시작될수 있으므로 인텐트 객체는 null일 수도 있다.
            return Service.START_STICKY; //서비스가 비정상 종료되었을 때 시스템이 자동으로 재시작한다.
                                           // 만약 재시작하지 않도록 만들고 싶다면 다른 상수를 사용하면된다.
        }else{
            processCommand(intent);//메소드 복잡성을 피하기위해 새로운 메소드 호출
        }
        return super.onStartCommand(intent, flags, startId);
    }

    private void processCommand(Intent intent){
        String command = intent.getStringExtra("command");
        String name = intent.getStringExtra("name");

        Log.d(TAG,"command : "+command+", name: "+name);

        for(int i=0; i<5; i++){//5초 동안 1초에 한 번씩 로그를 출력
            try{
                Thread.sleep(1000);
            }catch (Exception e){};
            Log.d(TAG,"Waiting"+i+"seconds");
        }

        Intent showIntent = new Intent(getApplicationContext(),MainActivity.class);//인텐트 객체 생성/Context객체 전달,메인클래스 객체 전달

        showIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_SINGLE_TOP| Intent.FLAG_ACTIVITY_CLEAR_TOP);
        //서비스는 화면이 없기 때문에 화면이 있는 액티비티를 띄우려면 새로운 태스크를 만들어야 한다.(FLAG_ACTIVITY_NEW_TASK)
        //MainActivity 객체가 이미 만들어져 있을 때 재사용하도록 FLAG_ACTIVITY_SINGLE_TOP,FLAG_ACTIVITY_CLEAR_TOP 플래그 추가

        showIntent.putExtra("command", "show");//부가 데이터 추가
        showIntent.putExtra("name", name + " from service.");//부가 데이터 추가
        startActivity(showIntent);//서비스에서 액티비티로 데이터를 전달하고 액티비티에서는 그 안에 들어있는 부가데이터를 받아볼수 있다.
                                  //서비스에서 5초 후에 메인 액택비티에 전달한 인텐트는 메인 액티비티에서 받아 처리할수 있다.
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("MyService", "onDestroy() 호출됨");
    }

}
