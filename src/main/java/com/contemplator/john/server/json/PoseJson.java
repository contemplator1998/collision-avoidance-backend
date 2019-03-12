package com.contemplator.john.server.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PoseJson {

    private final double[] pose;

    @JsonCreator
    public PoseJson(@JsonProperty("a1") final double a1,
                    @JsonProperty("a2") final double a2,
                    @JsonProperty("a3") final double a3,
                    @JsonProperty("a4") final double a4,
                    @JsonProperty("a5") final double a5,
                    @JsonProperty("a6") final double a6) {
        this.pose = new double[]{a1, a2, a3, a4, a5, a6};
    }

    public double[] getPose() {
        return pose;
    }
}
