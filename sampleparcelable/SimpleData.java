package org.androidtown.sampleparcelable;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by 김지혜 on 2017-08-22.
 * Parcelable 인터페이스를 구현하기위해서 만든것이다.
 */

public class SimpleData implements Parcelable { // SimpleData 클래스는 Parcelable인터페이스를 구현한다.
    int number; //정의된 인스턴스 변수.정수형
    String message;//정의된 인스턴스 변수 .문자열

    public SimpleData(int num, String msg){
        number=num;
        message= msg;
    }

    public SimpleData(Parcel src){ //Parcel 객체에서 읽기
        number = src.readInt();
        message = src.readString();
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator(){ //CREATOR 상수 정의
        public SimpleData createFromParcel(Parcel in){
            return new SimpleData(in);//SimpleData 생성자를 호출해 Parcel 객체에서 읽기
        }

        public SimpleData[] newArray(int size){
            return new SimpleData[size];
        }
    };

    public int describeContents(){
        return 0;
    }

    public void writeToParcel(Parcel dest, int flages){ //SimpleData 객체 안에 들어 있는 데이터를 Parcel객체로 만드는 역할.
        dest.writeInt(number); //Parcel 객체로 데이터를 쓴다.
        dest.writeString(message);//Parcel 객체로 데이터를 쓴다.
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}


