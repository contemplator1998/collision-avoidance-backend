package com.contemplator.john.server.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.rozum.robot.collision.environment.ObstacleType;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = CapsuleObstacleJson.class, name = "CAPSULE"),
        @JsonSubTypes.Type(value = PlaneObstacleJson.class, name = "PLANE"),
        @JsonSubTypes.Type(value = BoxObstacleJson.class, name = "BOX")})
public abstract class ObstacleJson {

    private final String name;

    @JsonCreator
    ObstacleJson(@JsonProperty("name") final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @JsonIgnore
    abstract public ObstacleType getType();
}
