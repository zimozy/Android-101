package com.example.tim.yahooweather.data;

import org.json.JSONObject;

/**
 * Created by tim on 10/13/17.
 */

public class Item implements JSONPopulator {
    private Condition condition;

    public Condition getCondition() {
        return condition;
    }

    @Override
    public void populate(JSONObject data) {
        condition = new Condition();
        condition.populate(data.optJSONObject("condition"));
    }
}
