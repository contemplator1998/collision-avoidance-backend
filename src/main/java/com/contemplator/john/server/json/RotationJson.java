package com.contemplator.john.server.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RotationJson {

    private final double roll;
    private final double pitch;
    private final double yaw;


    @JsonCreator
    public RotationJson(@JsonProperty("roll") final double roll,
                     @JsonProperty("pitch") final double pitch,
                     @JsonProperty("yaw") final double yaw) {
        this.roll = roll;
        this.pitch = pitch;
        this.yaw = yaw;
    }

    public double getRoll() {
        return roll;
    }

    public double getPitch() {
        return pitch;
    }

    public double getYaw() {
        return yaw;
    }

    @Override
    public String toString() {
        return "RotationJson{" +
                "roll=" + roll +
                ", pitch=" + pitch +
                ", yaw=" + yaw +
                '}';
    }
}
