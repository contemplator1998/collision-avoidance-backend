package com.contemplator.john.server;

import com.contemplator.john.server.json.CapsuleObstacleJson;
import com.contemplator.john.server.json.ObstacleJson;
import com.contemplator.john.server.json.PoseJson;
import com.contemplator.john.server.json.PositionJson;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;

@RestController
public class CollisionsServerController {

    @RequestMapping("/date")
    public Date date() {
        return new Date();
    }

    @ResponseBody
    @RequestMapping(value = "/run", method = RequestMethod.PUT)
    public PoseJson[] run(@RequestBody PoseJson[] poses) {
        System.out.println("Running");
        for (PoseJson p : poses) {
            System.out.println(Arrays.toString(p.getPose()));
        }
        return poses;
    }

    @RequestMapping(value = "/base", method = RequestMethod.PUT)
    public void base(@RequestBody PositionJson position) {
        System.out.println("Setting base");
        System.out.println(position);
    }

    @RequestMapping(value = "/environment", method = RequestMethod.PUT)
    public void environment(@RequestBody ObstacleJson[] arg) {
        System.out.println("Setting environment");
        for (ObstacleJson p : arg) {
            System.out.println(p.getName());
        }
    }

    @RequestMapping(value = "/tool", method = RequestMethod.PUT)
    public void tool(@RequestBody CapsuleObstacleJson[] arg) {
        System.out.println("Setting tool");
        for (CapsuleObstacleJson p : arg) {
            System.out.println(p.getName());
        }
    }
}
