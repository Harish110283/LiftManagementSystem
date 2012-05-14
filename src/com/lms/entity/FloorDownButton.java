package com.lms.entity;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import com.lms.entity.FloorUpButton.FloorUpButtonListener;
import com.lms.entity.system.LiftManagementSystem;

public class FloorDownButton extends JButton {
	
	static ImageIcon downArrow = new ImageIcon(MetaData.FLOOR_DOWN);

	public FloorDownButton(String buttonString, int floorNumber) {
		super(downArrow);
		FloorBottomButtonListener floorBottomButtonListener = new FloorBottomButtonListener(
				floorNumber);
		this.addActionListener(floorBottomButtonListener);
	}

	/**
	 * Holds the Instance
	 */
	LiftManagementSystem system = LiftManagementSystem.getInstance();

	public class FloorBottomButtonListener implements ActionListener,
			FloorButton {
		int floorNumber;

		public FloorBottomButtonListener(int floorNumber) {
			this.floorNumber = floorNumber;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			execute(floorNumber);

		}

		@Override
		public void execute(int floorNumber) {
			Lift lift = system.allocateLift(floorNumber, Direction.DOWN);
			lift.setDirection(Direction.DOWN);
			Thread liftThread = new Thread(lift);
			liftThread.start();
		}

	}

}
