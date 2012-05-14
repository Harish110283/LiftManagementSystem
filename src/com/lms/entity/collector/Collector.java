package com.lms.entity.collector;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import com.lms.entity.Lift;

public class Collector {

	HashMap<Integer, Lift> liftPosition = new HashMap<Integer, Lift>();

	public HashMap<Integer, Lift> getLiftPosition() {
		return liftPosition;
	}

	public void setLiftPosition(HashMap<Integer, Lift> liftPosition) {
		this.liftPosition = liftPosition;
	}

	/**
	 * 
	 * @param lift
	 * @param relativePosition
	 */
	public void put(Lift lift, Integer relativePosition) {
		liftPosition.put(relativePosition, lift);
	}

	public Lift determineLift() {
		if (liftPosition.size() > 0) {
			Set<Integer> set = liftPosition.keySet();
			List myList = new ArrayList(set);
			Collections.sort(myList);
			return liftPosition.get(myList.get(0));
		}
		return null;
	}

}
