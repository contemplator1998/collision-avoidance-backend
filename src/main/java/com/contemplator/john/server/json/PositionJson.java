package com.contemplator.john.server.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PositionJson {

    private final PointJson point;
    private final RotationJson rotation;

    @JsonCreator
    public PositionJson(@JsonProperty("point") final PointJson point,
                        @JsonProperty("rotation") final RotationJson rotation) {
        this.point = point;
        this.rotation = rotation;
    }

    public PointJson getPoint() {
        return point;
    }

    public RotationJson getRotation() {
        return rotation;
    }

    @Override
    public String toString() {
        return "PositionJson{" +
                "point=" + point +
                ", rotation=" + rotation +
                '}';
    }
}
