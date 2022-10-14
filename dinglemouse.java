package com.company;

import java.util.*;

public class DingleMouse {

    public static void main(String[] args) {
        final int[][] queues = {
                new int[0], // G
                new int[0], // 1
                new int[]{4, 4, 4, 4}, // 2
                new int[0], // 3
                new int[]{2, 2, 2, 2}, // 4
                new int[0], // 5
                new int[0], // 6
        };

        System.out.println(Arrays.toString(theLift(queues, 2)));
    }

    public static int[] theLift(final int[][] queues, final int capacity) {
        List<Integer>[] floors = new ArrayList[queues.length];
        List<Integer> lift = new ArrayList<>(capacity);
        int totalPeopleInQueue = 0;
        for(int i = 0; i < floors.length; ++i) {
            floors[i] = new ArrayList<>();

            for(int j = 0; j < queues[i].length; ++j) {
                floors[i].add(queues[i][j]);
            }

            totalPeopleInQueue += queues[i].length;
        }

        boolean isGoingUp = true;
        int floorNum = 0;

        List<Integer> result = new LinkedList<>();

        main:
        while(totalPeopleInQueue > 0) {

            boolean hasStopped = false;

            //unload
            if(lift.contains(floorNum)) {
                totalPeopleInQueue -= lift.size();
                lift.removeAll(Collections.singletonList(floorNum));
                totalPeopleInQueue += lift.size();
                hasStopped = true;
            }

            //load
            for(int i = 0; i < floors[floorNum].size(); ++i) {
                if(isGoingUp) {
                    if(floors[floorNum].get(i) > floorNum) {

                        if(lift.size() < capacity) {
                            lift.add(floors[floorNum].remove(i));
                            i--;
                        } else {
                            break;
                        }

                        hasStopped = true;
                    }
                } else { //isGoingDown
                    if(floors[floorNum].get(i) < floorNum) {

                        if(lift.size() < capacity) {
                            lift.add(floors[floorNum].remove(i));
                            i--;
                        } else {
                            break;
                        }

                        hasStopped = true;
                    }
                }
            }

            if(hasStopped) {
                result.add(floorNum);
            }

            //update floorNum and direction
            Boolean

            if(isGoingUp) {
                floorNum++;
                if(floorNum == floors.length - 1) {
                    isGoingUp = false;
                }
            } else { //isGoingDown
                floorNum--;
                if(floorNum == 0) {
                    isGoingUp = true;
                }
            }

        }

        if(result.isEmpty()) {
            result.add(0);
        } else {
            if(result.get(0) != 0) {
                result.add(0, 0);
            }

            if(result.get(result.size() - 1) != 0) {
                result.add(0);
            }
        }

        return result.stream().mapToInt(x -> x).toArray();
    }

    private static Boolean isGoingUp(boolean isGoingUp, int floorNum, List<Integer> lift, List<Integer>[] floors) {
        if(isGoingUp) {

            //check if people in the lift want to go higher floors than floorNum
            boolean peopleInLiftWantToGoHigher = false;
            for(int p : lift) {
                if(p > floorNum) {
                    peopleInLiftWantToGoHigher = true;
                    break;
                }
            }

            //check if people in higher floors than floorNum want to go higher
            boolean peopleInHigherFloorsWantToGoHigher = false;
            outer:
            for(int f = floorNum + 1; f < floors.length - 1; ++f) {
                for(int p : floors[f]) {
                    if(p > floorNum) {
                        peopleInHigherFloorsWantToGoHigher = true;
                        break outer;
                    }
                }
            }

            if(!(peopleInLiftWantToGoHigher || peopleInHigherFloorsWantToGoHigher)) {
                isGoingUp = false;

                if(lift.isEmpty()) {
                    //get floorNum of highest person floor wanting to go down and set direction
                    for(int f = floors.length - 1; f > floorNum; --f) {
                        for(int p : floors[f]) {
                            if(p < f) {
                                floorNum = f;
                                return null;
                            }
                        }
                    }
                }
            }

        } else { //isGoingDown

            //check if people in the lift want to go to lower floors than floorNum
            boolean peopleInLiftWantToGoLower = false;
            for(int p : lift) {
                if(p < floorNum) {
                    peopleInLiftWantToGoLower = true;
                    break;
                }
            }

            //check if people in lower floors than floorNum want to go lower
            boolean peopleInLowerFloorsWantToGoLower = false;
            outer:
            for(int f = floorNum - 1; f > 0; --f) {
                for(int p : floors[f]) {
                    if(p < floorNum) {
                        peopleInLowerFloorsWantToGoLower = true;
                        break outer;
                    }
                }
            }

            if(!(peopleInLiftWantToGoLower || peopleInLowerFloorsWantToGoLower)) {
                if(lift.isEmpty()) {
                    //get floorNum of lowest person floor wanting to go up and set direction
                    for(int f = 0; f < floorNum; ++f) {
                        for(int p : floors[f]) {
                            if(p > f) {
                                floorNum = f;
                                isGoingUp = true;
                                return null;
                            }
                        }
                    }

                }
            }

        }

        return isGoingUp;
    }

}
