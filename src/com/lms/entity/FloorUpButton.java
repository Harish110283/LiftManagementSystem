package com.lms.entity;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import com.lms.entity.system.LiftManagementSystem;

public class FloorUpButton extends JButton {
	
	static ImageIcon upArrow = new ImageIcon(MetaData.FLOOR_UP);
	
	/**
	 * 
	 * @param buttonString
	 * @param floorNumber
	 */
	public FloorUpButton(String buttonString,int floorNumber){
		super(upArrow);
		FloorUpButtonListener floorUpButtonListener = new FloorUpButtonListener(floorNumber);
		this.addActionListener(floorUpButtonListener);
	}
	/**
	 * Holds the Instance
	 */
	LiftManagementSystem system = LiftManagementSystem.getInstance();
	
	
	/**
	 * 
	 * @author harishr
	 *
	 */
	public class FloorUpButtonListener implements ActionListener,FloorButton{
		int floorNumber;
		
		public FloorUpButtonListener(int floorNumber){
			this.floorNumber=floorNumber;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			execute(floorNumber);
		}
		
		@Override
		public void execute(int floorNumber) {
			Lift lift=system.allocateLift(floorNumber, Direction.UP);
			//lift.setDirection(Direction.UP);
			Thread liftThread = new Thread(lift);
			liftThread.start();
		}
		
	}
}
