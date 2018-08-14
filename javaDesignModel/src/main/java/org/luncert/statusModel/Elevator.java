package org.luncert.statusModel;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

public class Elevator implements Runnable {

    public final static OpenningState openningState = new OpenningState();
    public final static ClosingState closingState = new ClosingState();
    public final static RunningState runningState = new RunningState();
    public final static StoppingState stoppingState = new StoppingState();

    private final static boolean DIRECTION_UP = true;
    private final static boolean DIRECTION_DOWN = false;

    private static final int MAX_FLOOR = 100;

    private static final int SPEED = 2; // 2 floors/second

    private int curFloor = 1;

    private boolean direction = DIRECTION_DOWN;

    private LiftState state;

    private List<Integer> targetFloorStack;

    Thread t = new Thread(this);

    public Elevator() {
        state = stoppingState;
        targetFloorStack = new ArrayList<>();
        t.start();
    }

    public void entry(int targetFloor) {
        if (0 <= targetFloor && targetFloor <= MAX_FLOOR) {
            synchronized(Elevator.class) {
                targetFloorStack.add(targetFloor);
            }
        }
        else throw new InvalidParameterException("invalid targetFloor");
    }

	@Override
	public void run() {
		while (true) {
            if (state instanceof RunningState) {
                int upFloor = MAX_FLOOR + 1, downFloor = 0;
                for (Integer floor : targetFloorStack) {
                    if (floor > curFloor && floor < upFloor) upFloor = floor;
                    else if (floor < curFloor && floor > downFloor) downFloor = floor;
                }
                // direction = upFloor < MAX_FLOOR + 1;
                try {
                    int floortDis;
                    if (direction) {
                        if (upFloor < MAX_FLOOR + 1) {
                            floortDis = upFloor - curFloor;
                        }
                        else {
                            direction = DIRECTION_DOWN;
                            floortDis = curFloor - downFloor;
                        }
                    }
                    else {
                        if (downFloor > 0) {
                            floortDis = curFloor - downFloor;
                        }
                        else {
                            direction = DIRECTION_UP;
                            floortDis = upFloor - curFloor;
                        }
                    }
                    Thread.sleep(new Double(floortDis / SPEED).intValue());
                } catch (InterruptedException e) {}
            }
        }
	}

}