package com.example.tim.yahooweather.service;

import com.example.tim.yahooweather.data.Channel;

/**
 * Created by tim on 10/13/17.
 */

public interface WeatherServiceCallback {
    void serviceSucess(Channel channel);
    void serviceFailure(Exception exception);
}
