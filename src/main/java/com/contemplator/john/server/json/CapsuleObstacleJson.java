package com.contemplator.john.server.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeId;
import com.rozum.robot.collision.environment.ObstacleType;

import java.util.Objects;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CapsuleObstacleJson extends ObstacleJson {

    private static final String NAME_FILED_TITLE = "name";
    private static final String RADIUS_FILED_TITLE = "radius";
    private static final String FIRST_POINT_FIELD_TITLE = "begin";
    private static final String SECOND_POINT_FIELD_TITLE = "end";

    @JsonTypeId
    @JsonProperty("type")
    private final static ObstacleType type = ObstacleType.CAPSULE;


    private final double radius;
    private final PointJson begin;
    private final PointJson finish;

    @JsonCreator
    public CapsuleObstacleJson(@JsonProperty(NAME_FILED_TITLE) final String name,
                               @JsonProperty(RADIUS_FILED_TITLE) final double radius,
                               @JsonProperty(FIRST_POINT_FIELD_TITLE) final PointJson begin,
                               @JsonProperty(SECOND_POINT_FIELD_TITLE) final PointJson finish) {
        super(name);
        this.radius = radius;
        this.begin = begin;
        this.finish = finish;
    }

    public double getRadius() {
        return radius;
    }

    public PointJson getBegin() {
        return begin;
    }

    public PointJson getFinish() {
        return finish;
    }

    public static String getNameFiledTitle() {
        return NAME_FILED_TITLE;
    }

    public static String getRadiusFiledTitle() {
        return RADIUS_FILED_TITLE;
    }

    public static String getFirstPointFieldTitle() {
        return FIRST_POINT_FIELD_TITLE;
    }

    public static String getSecondPointFieldTitle() {
        return SECOND_POINT_FIELD_TITLE;
    }

    @Override
    public ObstacleType getType() {
        return type;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final CapsuleObstacleJson that = (CapsuleObstacleJson) o;
        return Double.compare(that.radius, radius) == 0 &&
                Objects.equals(begin, that.begin) &&
                Objects.equals(finish, that.finish);
    }

    @Override
    public int hashCode() {
        return Objects.hash(radius, begin, finish);
    }
}
