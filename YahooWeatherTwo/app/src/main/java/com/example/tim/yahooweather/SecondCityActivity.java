package com.example.tim.yahooweather;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tim.yahooweather.data.Channel;
import com.example.tim.yahooweather.data.Item;
import com.example.tim.yahooweather.service.WeatherServiceCallback;
import com.example.tim.yahooweather.service.YahooWeatherService;

public class SecondCityActivity extends AppCompatActivity implements WeatherServiceCallback {

    private ImageView weatherIconImageView;
    private TextView temperatureTextView;
    private TextView conditionTextView;
    private TextView locationTextView;
    private Button charlotteButton;

    private YahooWeatherService service;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_city);

        weatherIconImageView = (ImageView) findViewById(R.id.weatherIconImageView);
        temperatureTextView = (TextView) findViewById(R.id.temperatureTextView);
        conditionTextView = (TextView) findViewById(R.id.conditionTextView);
        locationTextView = (TextView) findViewById(R.id.locationTextView);

        service = new YahooWeatherService(this);
        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading...");
        dialog.show();

        service.refreshWeather("Sydney, Australia");

        charlotteButton = (Button) findViewById(R.id.charlotteButton);
        charlotteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToCharlotte();
            }
        });

    }

    @Override
    public void serviceSucess(Channel channel) {
        dialog.hide();

        Item item = channel.getItem();
        int resourceId = getResources().getIdentifier("drawable/icon_" + item.getCondition().getCode(), null, getPackageName());

        Drawable weatherIconDrawable = getResources().getDrawable(resourceId, null);

        weatherIconImageView.setImageDrawable(weatherIconDrawable);

        temperatureTextView.setText(item.getCondition().getTemperature() + "\u00B0" + channel.getUnits().getTemperature());

        conditionTextView.setText(item.getCondition().getDescription());

        locationTextView.setText(service.getLocation());


    }

    @Override
    public void serviceFailure(Exception exception) {
        dialog.hide();
        Toast.makeText(this, exception.getMessage(), Toast.LENGTH_LONG).show();
    }

    private void goToCharlotte() {
        Intent charlotteIntent = new Intent(this, WeatherActivity.class);
        startActivity(charlotteIntent);
    }

}