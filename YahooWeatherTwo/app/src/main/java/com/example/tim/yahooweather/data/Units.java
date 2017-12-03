package com.example.tim.yahooweather.data;

import org.json.JSONObject;

/**
 * Created by tim on 10/13/17.
 */

public class Units implements JSONPopulator {
    private String temperature;

    public String getTemperature() {
        return temperature;
    }

    @Override
    public void populate(JSONObject data) {
        temperature = data.optString("temperature");
    }
}
