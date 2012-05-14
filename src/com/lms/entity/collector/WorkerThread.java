package com.lms.entity.collector;

import java.util.List;

import com.lms.entity.Direction;
import com.lms.entity.Floor;
import com.lms.entity.Lift;

public class WorkerThread extends Thread {
	Collector  collector;
	Lift lift;
	int floorNumber;
	Direction directionToMove;
	public WorkerThread (Collector collector,Lift lift,int floorNumber,Direction directionToMove){
		this.collector = collector;
		this.lift = lift;
		this.floorNumber =floorNumber;
		this.directionToMove=directionToMove;
	}
	
	@Override
	public void run() {
		collector.put(lift,lift.getLiftPositionToFloor(directionToMove, floorNumber));
		}
}
