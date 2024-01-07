package com.test.app;

public class NativeMain {
    static {
        System.loadLibrary("native-lib");
    }

    public native String stringFromJNI();
}
