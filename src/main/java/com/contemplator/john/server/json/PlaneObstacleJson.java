package com.contemplator.john.server.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeId;
import com.rozum.robot.collision.environment.ObstacleType;

import java.util.Arrays;

public class PlaneObstacleJson extends ObstacleJson{
private static final int PLANE_POINT_QUANTITY = 3;
    @JsonTypeId
    @JsonProperty("type")
    private final static ObstacleType type = ObstacleType.PLANE;

    private final PointJson[] points;

    @JsonCreator
    public PlaneObstacleJson(@JsonProperty("name") final String name,
                             @JsonProperty("firstPoint") final PointJson firstPoint,
                             @JsonProperty("secondPoint") final PointJson secondPoint,
                             @JsonProperty("thirdPoint") final PointJson thirdPoint) {
        super(name);
        this.points = new PointJson[]{firstPoint, secondPoint, thirdPoint};
    }

    public PointJson[] getPoints() {
        return points;
    }

    @Override
    public ObstacleType getType() {
        return type;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final PlaneObstacleJson that = (PlaneObstacleJson) o;
        return Arrays.equals(points, that.points);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(points);
    }
}
