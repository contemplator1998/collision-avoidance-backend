package com.contemplator.john.server;

import com.contemplator.john.server.json.CapsuleObstacleJson;
import com.contemplator.john.server.json.ObstacleJson;
import com.contemplator.john.server.json.PoseJson;
import com.contemplator.john.server.json.PositionJson;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Date;

@RestController
public class CollisionsServerController {

    @RequestMapping("/date")
    public Date date() {
        return new Date();
    }

    @RequestMapping(value = "/run", method = RequestMethod.PUT)
    public void run(@RequestBody PoseJson[] pose) {
        for (PoseJson p : pose) {
            System.out.println(Arrays.toString(p.getPose()));
        }
    }

    @RequestMapping(value = "/base", method = RequestMethod.PUT)
    public void base(@RequestBody PoseJson[] pose) {
        for (PoseJson p : pose) {
            System.out.println(Arrays.toString(p.getPose()));
        }
    }

    @RequestMapping(value = "/environment", method = RequestMethod.PUT)
    public void environment(@RequestBody ObstacleJson[] arg) {
        for (ObstacleJson p : arg) {
            System.out.println(p.getName());
        }
    }

    @RequestMapping(value = "/tool", method = RequestMethod.PUT)
    public void tool(@RequestBody CapsuleObstacleJson[] arg) {
        for (CapsuleObstacleJson p : arg) {
            System.out.println(p.getName());
        }
    }
}
