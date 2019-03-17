package com.contemplator.john.server.model;

import com.contemplator.john.server.json.PoseJson;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.rozum.leonardo.application.gui.configs.ApplicationModule;
import com.rozum.robot.api.core.context.RobotContext;
import com.rozum.robot.api.core.data.Pose;
import com.rozum.robot.collision.NumericalCollisionValidator;
import com.rozum.robot.collision.environment.RobotEnvironment;

import java.util.List;

public class ModelRunner {

    private final RobotContext robotContext;
    private final ObstacleAvoidingPathCalculation calculation;

    public ModelRunner() {
        ApplicationModule applicationModule = new ApplicationModule(true);
        Injector injector = Guice.createInjector(applicationModule);
        robotContext = injector.getInstance(RobotContext.class);
        NumericalCollisionValidator validator = injector.getInstance(NumericalCollisionValidator.class);
        RobotEnvironment environment = injector.getInstance(RobotEnvironment.class);
        calculation = new ObstacleAvoidingPathCalculation(validator, robotContext, environment);
    }

    public RobotContext getRobotContext() {
        return robotContext;
    }

    public PoseJson[] run(PoseJson[] poses) {
        Pose from = new Pose(poses[0].getPose());
        Pose to = new Pose(poses[1].getPose());
        List<Pose> poseList = calculation.aStar(from, to);
        PoseJson[] result = new PoseJson[poseList.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = new PoseJson(poseList.get(i).getAngles());
        }
        return result;
    }
}
