package com.lms.entity;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.lms.entity.system.LiftManagementSystem;
/**
 * Each Floor has a List of Buttons.
 * @author harishr
 *
 */
public class Floor implements Visitable{
	
	 int floorNumber;
	 Direction direction;
	 private Point line = new Point(0, 0);
	 private JPanel mainPanel = new JPanel()
	  {
	    protected void paintComponent(Graphics g)
	    {
	      super.paintComponent(g);
	      myPaint(g);
	    }
	  };
	  
	  public Floor(Dimension preferredSize, int diameter, int delta,Point ballPoint,int floorNumber)
	  {
	    mainPanel.setPreferredSize(preferredSize);
	    this.line = ballPoint;
	    this.floorNumber=floorNumber;
	    floorUpButton = new FloorUpButton("Up",floorNumber);
	    floorDownButton = new FloorDownButton("Down",floorNumber);
	    addComponent();
	  } 
	  
	 
	 
	  private void myPaint(Graphics g)
	  {
		  Graphics g2 = (Graphics)g;
	      g.drawLine(0,line.y,500,line.y);   
	      
	  }
	  
	  public JComponent getComponent()
	  {
	    return mainPanel;
	  }
 
	  
	/**
	 * Holds the floor up Button
	 */
	 FloorUpButton floorUpButton=null;

	 /**
	  * Holds the floor Down Button
	  */
	 FloorDownButton floorDownButton=null;
	 
	 
	
	 
	 
	@Override
	public void accept(Visitor vistor) {
		Lift lifts =(Lift)vistor;
		if(lifts.determineLiftMovingDirection(floorNumber).equals(Direction.UP)) {
			moveUp(lifts);
		}else {
			moveDown(lifts);
		}
		
		lifts.setPreviousVisitedFloor(this);
	}
	
	
	private void moveUp(Lift lifts){
		int distance=lifts.determineDelta(floorNumber,Direction.UP);
		System.out.println("Welcome Lift Number  "  + lifts.getLiftNumber() +"  You are visiting floor Number" + floorNumber);
		for(int i=0;i<distance;i++){
			lifts.moveLiftUp(14);
			lifts.moveLiftUp(14);
			
			lifts.moveLiftUp(14);
			lifts.moveLiftUp(14);
			
			lifts.moveLiftUp(14);
			lifts.moveLiftUp(14);
			
			lifts.moveLiftUp(14);
			lifts.moveLiftUp(14);
			
			lifts.moveLiftUp(14);
			lifts.moveLiftUp(14);
			
			lifts.moveLiftUp(14);
			
			LiftManagementSystem managementSystem=LiftManagementSystem.getInstance();
			if(lifts.getPreviousVisitedFloor() != null){
				lifts.setPreviousVisitedFloor(managementSystem.getFloor(lifts.getPreviousVisitedFloor().floorNumber+1));
			}else {
				lifts.setPreviousVisitedFloor(managementSystem.getFloor(1));
			}
		}
	}
	
	private void moveDown(Lift lifts){
		int distance=lifts.determineDelta(floorNumber,Direction.DOWN);
		System.out.println("Welcome Lift Number  "  + lifts.getLiftNumber() +"  You are visiting floor Number" + floorNumber);
		for(int i=0;i<distance;i++){
			lifts.moveLiftDown(14);
			lifts.moveLiftDown(14);
			
			lifts.moveLiftDown(14);
			lifts.moveLiftDown(14);
			
			lifts.moveLiftDown(14);
			lifts.moveLiftDown(14);
			
			lifts.moveLiftDown(14);
			lifts.moveLiftDown(14);
			
			lifts.moveLiftDown(14);
			lifts.moveLiftDown(14);
			
			lifts.moveLiftDown(14);
			
			LiftManagementSystem managementSystem=LiftManagementSystem.getInstance();
			if(lifts.getPreviousVisitedFloor() != null){
				lifts.setPreviousVisitedFloor(managementSystem.getFloor(lifts.getPreviousVisitedFloor().floorNumber-1));
			}else {
				lifts.setPreviousVisitedFloor(managementSystem.getFloor(1));
			}
		}
	}
	
	 /**
	  * Adds the Button Component to the Screen	
	  */
	 public void addComponent(){
		  mainPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		  mainPanel.add(new JTextArea("Floor Number is " + floorNumber));
		  if(floorNumber != MetaData.LAST_FLOOR)
		  mainPanel.add(floorUpButton);
		  if(floorNumber != MetaData.FIRST_FLOOR)
		  mainPanel.add(floorDownButton);
	  }
	
}
