package com.testarossa.androidlearndemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class AnotherAty extends AppCompatActivity {
    public static final String ACTION = "com.maitestarossa.demojike.intent.action.AnotherAty";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_another_aty);
    }
}
