package com.contemplator.john.server.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PointJson {

    private final double x;
    private final double y;
    private final double z;


    @JsonCreator
    public PointJson(@JsonProperty("x") final double x,
                     @JsonProperty("y") final double y,
                     @JsonProperty("z") final double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    @Override
    public String toString() {
        return "PointJson{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}