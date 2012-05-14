package com.lms.entity.system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.lms.entity.Direction;
import com.lms.entity.Floor;
import com.lms.entity.Lift;
import com.lms.entity.collector.Collector;
import com.lms.entity.collector.WorkerThread;
import com.lms.entity.collector.WorkerThreadSpawner;

public class LiftManagementSystem {
	/**
	 * 
	 */
	public static LiftManagementSystem system = null;

	/**
	 * 
	 */
	private LiftManagementSystem() {

	}

	public static LiftManagementSystem getInstance() {
		if (system == null) {
			system = new LiftManagementSystem();
		}

		return system;
	}

	/**
	 * Holds a List of Floors
	 */
	HashMap<Integer,Floor> floors = new HashMap<Integer,Floor>();
	/**
	 * Holds a List of Lifts
	 */
	List<Lift> lifts = new ArrayList<Lift>();

	

	public HashMap<Integer, Floor> getFloors() {
		return floors;
	}

	public void setFloors(HashMap<Integer, Floor> floors) {
		this.floors = floors;
	}

	public void addFloors(Integer floorNumber,Floor floor) {
		this.floors.put(floorNumber,floor);
	}
	
	public Floor getFloor(Integer floorNumber){
		return this.floors.get(floorNumber);
	}

	public List<Lift> getLifts() {
		return lifts;
	}

	public void setLifts(List<Lift> lifts) {
		this.lifts = lifts;
	}

	public void addLifts(Lift lift) {
		this.lifts.add(lift);
	}
	
	

	public Lift allocateLift(int floorNumber, Direction directionToMove) {
		Collector collector = new Collector();

		WorkerThreadSpawner st = new WorkerThreadSpawner(collector,
				floorNumber, directionToMove, lifts);
		st.start();
		try {
			st.join();
			Thread.sleep(400);
		} catch (InterruptedException e) {
			System.err.println("Interrupted");
		}
		Floor floor=floors.get(floorNumber);
		Lift lift =collector.determineLift();
		lift.addCurrentlyVisitedFloor(floor);
		return lift;
	}

}
