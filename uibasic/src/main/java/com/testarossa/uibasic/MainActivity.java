package com.testarossa.uibasic;

import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {
    private static final String TAG = "Main";
    private Button btnChooseDate;
    final Calendar c = Calendar.getInstance();
    int year = c.get(Calendar.YEAR);
    int month = c.get(Calendar.MONTH);
    int day = c.get(Calendar.DAY_OF_MONTH);
    private Spinner sp;
    private String s[] = {"axxxaa", "aaaaa", "ccc"};
    private Button btnChooseTime;
    int hour = c.get(Calendar.HOUR_OF_DAY);
    int min = c.get(Calendar.MINUTE);
    private Button btnSingleChooseSubmit;
    private RadioButton rb;

    private CheckBox cb1, cb2, cb3, cb4;
    private TextView tvMutiChooseResult;

    private Button btnNotifi;
    public static final int NOTIFICATION_ID = 1200;
    private     int counter =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: " + year + "/" + (month + 1) + "/" + day);
        btnChooseDate = (Button) findViewById(R.id.btnChooseDate);
        btnChooseDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        btnChooseDate.setText(String.format("%d年%d月%d日", i, i1 + 1, i2));
                    }
                }, year, month, day).show();
            }
        });
        sp = (Spinner) findViewById(R.id.spinner);

        sp.setAdapter(new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_dropdown_item, s));
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d(TAG, "onItemSelected: " + s[i]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnChooseTime = (Button) findViewById(R.id.btnChooseTime);
        btnChooseTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        String s = String.format("%d:%d", i, i1);
                        btnChooseTime.setText(s);
                    }
                }, hour, min, true).show();
            }
        });
        btnSingleChooseSubmit = (Button) findViewById(R.id.btnSingleChooseSumbit);
        rb = (RadioButton) findViewById(R.id.rbC);
        btnSingleChooseSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rb.isChecked()) {
                    Toast t =Toast.makeText(MainActivity.this, "选择正确", Toast.LENGTH_SHORT);
                    t.setGravity(Gravity.CENTER,0,0);
                    t.show();
                    Toast showImageToast =Toast.makeText(MainActivity.this,"这是一个带有图片的toast",Toast.LENGTH_LONG);
                    ImageView iv = new ImageView(MainActivity.this);
                    iv.setImageResource(R.mipmap.ic_launcher);
                    showImageToast.setView(iv);
                    showImageToast.show();

                } else {
                    Toast.makeText(MainActivity.this, "选择错误", Toast.LENGTH_SHORT).show();
                }
            }
        });

        cb1 = (CheckBox) findViewById(R.id.cb1);
        cb2 = (CheckBox) findViewById(R.id.cb2);
        cb3 = (CheckBox) findViewById(R.id.cb3);
        cb4 = (CheckBox) findViewById(R.id.cb4);

        cb1.setOnCheckedChangeListener(this);
        cb2.setOnCheckedChangeListener(this);
        cb3.setOnCheckedChangeListener(this);
        cb4.setOnCheckedChangeListener(this);

        tvMutiChooseResult = (TextView) findViewById(R.id.tcMutiChooseResult);

        btnNotifi = (Button) findViewById(R.id.btnNotifi);
        btnNotifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                counter++;
                NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this);
                builder.setSmallIcon(R.mipmap.ic_launcher);
                builder.setContentTitle("有"+counter+"个新消息");
                builder.setContentText("你已经可以创建新的Notification了");

                //创建可点击的Notification
                Intent resultIntent = new Intent(MainActivity.this,MainActivity.class);
                resultIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                TaskStackBuilder stackBuilder = TaskStackBuilder.create(MainActivity.this);
                stackBuilder.addParentStack(MainActivity.class);
                stackBuilder.addNextIntent(resultIntent);
                PendingIntent resultPendingIntent =
                        PendingIntent.getActivity(MainActivity.this,0,resultIntent,PendingIntent.FLAG_ONE_SHOT);
//                        stackBuilder.getPendingIntent(
//                                0,
//                                PendingIntent.FLAG_ONE_SHOT
//                        );
                builder.setContentIntent(resultPendingIntent);


                Notification notification = builder.build();

                //点击后通知消失
                notification.flags = Notification.FLAG_AUTO_CANCEL;

                NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

                manager.notify(NOTIFICATION_ID,notification);
            }
        });
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        String s = "you have chosen ";
        if (cb1.isChecked()) {
            s += cb1.getText() + " ";
        }
        if (cb2.isChecked()) {
            s += cb2.getText() + " ";
        }
        if (cb3.isChecked()) {
            s += cb3.getText() + " ";
        }
        if (cb4.isChecked()) {
            s += cb4.getText() + " ";
        }
        tvMutiChooseResult.setText(s);
    }

}
