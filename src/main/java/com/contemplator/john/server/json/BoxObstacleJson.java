package com.contemplator.john.server.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeId;
import com.rozum.robot.collision.environment.ObstacleType;

import java.util.Objects;

public class BoxObstacleJson extends ObstacleJson {
    @JsonTypeId
    @JsonProperty("type")
    private final ObstacleType type = ObstacleType.BOX;

    private final PointJson sides;
    private final PositionJson position;

    @JsonCreator
    public BoxObstacleJson(@JsonProperty("name") final String name,
                           @JsonProperty("axisLength") final PointJson sides,
                           @JsonProperty("centerPosition") final PositionJson position) {
        super(name);
        this.sides = sides;
        this.position = position;
    }

    public PointJson getSides() {
        return sides;
    }

    public PositionJson getPosition() {
        return position;
    }

    @Override
    public ObstacleType getType() {
        return type;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final BoxObstacleJson that = (BoxObstacleJson) o;
        return type == that.type &&
                Objects.equals(sides, that.sides) &&
                Objects.equals(position, that.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, sides, position);
    }
}
