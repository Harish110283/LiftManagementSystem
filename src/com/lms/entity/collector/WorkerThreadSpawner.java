package com.lms.entity.collector;

import java.util.ArrayList;
import java.util.List;

import com.lms.entity.Direction;
import com.lms.entity.Lift;

public class WorkerThreadSpawner extends Thread {
	
	int floorNumber;
	Direction directionToMove;
	List<Lift> lifts;
	Collector collector;
	public WorkerThreadSpawner (Collector collector,int floorNumber,Direction directionToMove,List<Lift> lifts){
		this.collector=collector;
		this.floorNumber =floorNumber;
		this.directionToMove=directionToMove;
		this.lifts = lifts;
	}
	
	@Override
	public void run() {
		 List threads = new ArrayList();  
		 for(Lift lift:lifts){
			 WorkerThread thread = new WorkerThread(collector,lift,floorNumber,directionToMove);
			 thread.start();
			 threads.add(thread);
		 }
		 
		  for (int i = 0; i < threads.size(); i++)
				try {
					((Thread) threads.get(i)).join();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
			}  
		 
	}

}
