package com.testarossa.androidlearndemo;

import android.app.Application;

/**
 * Created by sola on 2016/6/26.
 */
public class App extends Application {
    private String textData = "default";

    public String getTextData() {
        return textData;
    }

    public void setTextData(String textData) {
        this.textData = textData;
    }
}
