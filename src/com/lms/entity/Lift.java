package com.lms.entity;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JPanel;

public class Lift implements Runnable, Visitor {

	private int liftNumber;

	private Floor previousVisitedFloor;

	private List<Floor> currentlyVisitedFloor = new LinkedList<Floor>();

	private Direction direction;

	private Point location = new Point(10, 400);
	private int delta = 100;

	private JPanel mainPanel = new JPanel() {
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			myPaint(g);
		}
	};

	public Lift(int liftNumber, Dimension preferredSize, Point ballPoint) {
		this.liftNumber = liftNumber;
		this.direction = Direction.NOTRUNNING;
		this.mainPanel.setPreferredSize(preferredSize);
		this.location = ballPoint;

	}

	@Override
	public void run() {
		synchronized(this){
			for (Floor floor : currentlyVisitedFloor) {
				visit(floor);
			}
			direction = Direction.NOTRUNNING;
			currentlyVisitedFloor = new LinkedList<Floor>();
		}
	}

	/**
	 * 
	 * @return
	 */
	public Floor getPreviousVisitedFloor() {
		return previousVisitedFloor;
	}

	public void setPreviousVisitedFloor(Floor previousVisitedFloor) {
		this.previousVisitedFloor = previousVisitedFloor;
	}

	public List<Floor> getCurrentlyVisitedFloor() {
		return currentlyVisitedFloor;
	}

	public void setCurrentlyVisitedFloor(List<Floor> currentlyVisitedFloor) {
		this.currentlyVisitedFloor = currentlyVisitedFloor;
	}

	public void addCurrentlyVisitedFloor(Floor floor) {
		this.currentlyVisitedFloor.add(floor);
	}

	public int getLiftNumber() {
		return liftNumber;
	}

	public void setLiftNumber(int liftNumber) {
		this.liftNumber = liftNumber;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	@Override
	public void visit(Visitable visitable) {
		try {
			Thread.sleep(200);
			Floor floor = (Floor) visitable;
			floor.accept(this);

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public int getLiftPositionToFloor(Direction directionToMove, int floorNumber) {
		int relativePosition = -1;
		
		if (previousVisitedFloor != null) {

			if (direction.equals(Direction.UP)) {
				relativePosition = floorNumber
						- previousVisitedFloor.floorNumber;
				if (relativePosition < 0) {
					relativePosition = 9999;
				}
			} else if (direction.equals(Direction.DOWN)) {
				relativePosition = previousVisitedFloor.floorNumber
						- floorNumber;
				if (relativePosition < 0) {
					relativePosition = 9999;
				}
			}
		}

		if (direction == Direction.NOTRUNNING) {
			/**
			 * Relative Position is made zero to give the lift the priority.
			 */
			if(previousVisitedFloor == null){
				relativePosition = floorNumber-0;
			}else {
				relativePosition = Math.abs(floorNumber-previousVisitedFloor.floorNumber);
			}
		}
		return relativePosition;

	}

	public int determineDelta(int floorNumber, Direction direction) {
		int relativePosition;
		if (previousVisitedFloor != null && direction.equals(Direction.UP)) {
			relativePosition = floorNumber - previousVisitedFloor.floorNumber;
		} else if (previousVisitedFloor != null
				&& direction.equals(Direction.DOWN)) {
			relativePosition = previousVisitedFloor.floorNumber - floorNumber;
		} else {
			relativePosition = floorNumber;
		}
		return relativePosition;

	}
	
	public Direction determineLiftMovingDirection(int floorNumber) {
		int relativePosition=0;
		Direction liftMustMove=Direction.UP;
		if (previousVisitedFloor != null ) {
			relativePosition = floorNumber - previousVisitedFloor.floorNumber;
		}else {
			relativePosition = floorNumber;
		}
		if(relativePosition < 0){
			liftMustMove=Direction.DOWN;
		}
		direction=liftMustMove;
		return liftMustMove;
	}

	private void myPaint(Graphics g) {
		Graphics g2 = (Graphics) g;

		BufferedImage image = null;
		try {
			image = ImageIO.read(new File(
					MetaData.LIFT_IMAGE));
		} catch (IOException ex) {
			// handle exception...
		}
		
		LiftImage liftImage = new LiftImage(image);
		
		g2.drawImage(image, location.x, location.y, null);
		
		
		
		

	}

	public void moveLiftUp(int delta) {
		location = new Point(location.x, location.y - delta);
		mainPanel.repaint();
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void moveLiftDown(int delta) {
		location = new Point(location.x, location.y + delta);
		mainPanel.repaint();
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public JComponent getComponent() {
		return mainPanel;
	}

}
