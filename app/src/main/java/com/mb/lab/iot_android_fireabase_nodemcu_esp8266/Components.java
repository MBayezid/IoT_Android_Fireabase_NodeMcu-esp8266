package com.mb.lab.iot_android_fireabase_nodemcu_esp8266;

public class Components {
    private String key;
    private boolean state;

    Components(String key, boolean state) {
        this.key = key;
        this.state = state;
    }

    String getKey() {
        return key;
    }

    boolean getState() {
        return state;
    }

    void setState(boolean state) {
        this.state = state;
    }
}
