package ru.yandex.dunaev.mick.myandroidcontentproviders;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.provider.CallLog;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final int REQUEST_PERMISION_CODE = 6688;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CALL_LOG) == PackageManager.PERMISSION_GRANTED) {
            readCallLog();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CALL_LOG}, REQUEST_PERMISION_CODE);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_PERMISION_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //Разрешение таки получено
                    readCallLog();
                }
                break;
        }
    }

    private void readCallLog() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        List<CallInfo> callInfoList = new ArrayList<>();
        Cursor cursor = getContentResolver().query(CallLog.Calls.CONTENT_URI, null, null, null, null);
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            while(cursor.moveToNext()) {
                int columnNumber = cursor.getColumnIndex(CallLog.Calls.NUMBER);
                CallInfo ci = new CallInfo();
                ci.setNumber(cursor.getString(cursor.getColumnIndex(CallLog.Calls.NUMBER)));
                ci.setFormatedNumber(cursor.getString(cursor.getColumnIndex(CallLog.Calls.CACHED_FORMATTED_NUMBER)));
                ci.setName(cursor.getString(cursor.getColumnIndex(CallLog.Calls.CACHED_NAME)));
                ci.setPhotoUri(cursor.getString(cursor.getColumnIndex(CallLog.Calls.CACHED_PHOTO_URI)));
                callInfoList.add(ci);
            }
        }


        cursor.close();
    }

}
