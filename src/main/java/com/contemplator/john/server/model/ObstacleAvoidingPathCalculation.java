package com.contemplator.john.server.model;

import com.rozum.robot.api.core.context.RobotContext;
import com.rozum.robot.api.core.data.Pose;
import com.rozum.robot.collision.NumericalCollisionValidator;
import com.rozum.robot.collision.environment.RobotEnvironment;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.*;

public class ObstacleAvoidingPathCalculation {

    private final NumericalCollisionValidator collisionValidator;
    private final RobotContext context;
    private final RobotEnvironment environment;

    public ObstacleAvoidingPathCalculation(NumericalCollisionValidator collisionValidator, RobotContext context, RobotEnvironment environment) {
        this.collisionValidator = collisionValidator;
        this.context = context;
        this.environment = environment;

    }

    static double getHeuristic(double[] a, double[] b) {
        double result = 0;
        for (int i = 0; i < a.length; i++) {
            result += Math.pow(b[i] - a[i], 2);
        }
        return result;
    }

    public boolean isCollided(double[] angles) {
        return collisionValidator.isCollided(new Pose(angles), context, environment);
    }

    double[] range(int start, int stop) {
        double[] doubles = new double[stop - start];
        for (int i = 0; i < doubles.length; i++) {
            doubles[i] = start + i;
        }
        return doubles;
    }

    double[][] generateNeighbors() {
        double[][] doubles = new double[(int) (Math.pow(3, 6) - 1)][6];
        int index = 0;

        for (int i1 = -1; i1 < 2; i1++) {
            for (int i2 = -1; i2 < 2; i2++) {
                for (int i3 = -1; i3 < 2; i3++) {
                    for (int i4 = -1; i4 < 2; i4++) {
                        for (int i5 = -1; i5 < 2; i5++) {
                            if (i1 != 0 || i2 != 0 || i3 != 0 || i4 != 0 || i5 != 0) {
                                doubles[index++] = new double[]{i1, i2, i3, i4, i5};
                            }
                        }
                    }
                }
            }
        }
        return doubles;
    }

    double[][] neighbors = generateNeighbors();
    Set<String> visited = new HashSet<>();
    Map<String, String> cameFrom = new HashMap<>();
    Map<String, Double> d = new HashMap<>();
    Map<String, Double> fscore = new HashMap<>();
    NavigableSet<Pair<Double, String>> heap = new TreeSet<>();

    public List<Pose> aStart(Pose startPose, Pose finishPose) {
        final double[] startDouble = startPose.getAngles();
        final double[] goalDouble = finishPose.getAngles();

        String start = Arrays.toString(startDouble);
        String goal = Arrays.toString(goalDouble);

        visited.clear();
        cameFrom.clear();
        d.clear();
        fscore.clear();
        heap.clear();


        d.put(start, 0.0);
        fscore.put(start, getHeuristic(startDouble, goalDouble));

        heap.add(new ImmutablePair<>(fscore.get(start), start));


        double[] nextDouble = new double[6];
        long lastPrintTime = 0;
        while (!heap.isEmpty()) {
            final Pair<Double, String> doubleStringPair = heap.pollFirst();
            String current = doubleStringPair.getRight();

            if (System.currentTimeMillis() - lastPrintTime > 100) {
                System.out.println(doubleStringPair.getLeft() + " " + heap.size() + " " + current);
                lastPrintTime = System.currentTimeMillis();
            }

            if (current.equals(goal)) {
                System.out.println("Done");
                visited.clear();
                d.clear();
                fscore.clear();
                heap.clear();

                List<Pose> data = new ArrayList<>();
                while (!current.equals(start)) {
                    final double[] decode = decode(current);
                    if(isCollided(decode)){
                        throw new RuntimeException("FUUUUUUUUUUUUUUUUUUU");
                    }
                    data.add(new Pose(decode));
                    current = cameFrom.get(current);
                }
                Collections.reverse(data);
                return data;
            }

            visited.add(current);

            double[] currentDouble = decode(current);

            for (int i = 0; i < neighbors.length; i++) {
                for (int j = 0; j < neighbors[i].length; j++) {
                    nextDouble[j] = Math.round((currentDouble[j] + neighbors[i][j]) * 10.0) / 10.0;
                }
                double curD = d.get(current) + getHeuristic(currentDouble, nextDouble);

                if (!isCollided(nextDouble)) {

                    String next = Arrays.toString(nextDouble);

                    Double dNext = d.get(next);
                    if (dNext == null) {
                        dNext = Double.POSITIVE_INFINITY;
                    }
                    if (!(visited.contains(next) && curD >= dNext)) {

                        if (curD < dNext) {
                            cameFrom.put(next, current);
                            d.put(next, curD);
                            fscore.put(next, curD + getHeuristic(nextDouble, goalDouble));

                            heap.add(new ImmutablePair<>(fscore.get(next), next));
                        }
                    }
                }
            }
        }
        return null;
    }

    private static double[] decode(final String current) {
        double[] currentDouble = new double[6];
        final String[] split = current.substring(1, current.length() - 1).split(",");
        for (int k = 0; k < split.length; k++) {
            currentDouble[k] = Double.parseDouble(split[k]);
        }
        return currentDouble;
    }
}
