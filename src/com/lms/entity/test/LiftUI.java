package com.lms.entity.test;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.lms.entity.Floor;
import com.lms.entity.Lift;
import com.lms.entity.system.LiftManagementSystem;

public class LiftUI {
	private static final int WIDTH = 400;
	private static final int HEIGHT = 100;
	private static final Dimension BALL_PANEL_SIZE = new Dimension(WIDTH,
			HEIGHT);
	private static final int DIAMETER = 20;
	private static final int DELTA = 2;
	
	private JPanel mainPanel = new JPanel();
	private JPanel subPanel = new JPanel();
	private Lift ballPanel = new Lift(1, BALL_PANEL_SIZE, new Point(10, 750));
	private Lift ballPanel2 = new Lift(2, BALL_PANEL_SIZE, new Point(10, 750));

	public LiftUI() {


		int blGap = 5;
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
		ballPanel.getComponent().setAlignmentX(Component.RIGHT_ALIGNMENT);
		ballPanel2.getComponent().setAlignmentX(Component.LEFT_ALIGNMENT);
		mainPanel.add(ballPanel.getComponent());
		mainPanel.add(ballPanel2.getComponent());

		Floor floorOne = new Floor(BALL_PANEL_SIZE, DIAMETER, DELTA, new Point(
				0, 45),1);

		Floor floorTwo = new Floor(BALL_PANEL_SIZE, DIAMETER, DELTA, new Point(
				0, 45),2);
		//    
		Floor floorThree = new Floor(BALL_PANEL_SIZE, DIAMETER, DELTA,
				new Point(0, 45),3);

		Floor floorFour = new Floor(BALL_PANEL_SIZE, DIAMETER, DELTA,
				new Point(0, 45),4);
		
		Floor floorFive = new Floor(BALL_PANEL_SIZE, DIAMETER, DELTA,
				new Point(0, 45),5);
		
		LiftManagementSystem system=LiftManagementSystem.getInstance();
		system.addFloors(1, floorOne);
		system.addFloors(2, floorTwo);
		system.addFloors(3, floorThree);
		system.addFloors(4, floorFour);
		system.addFloors(5, floorFive);
		system.addLifts(ballPanel);
		system.addLifts(ballPanel2);
		
		subPanel.setLayout(new BoxLayout(subPanel, BoxLayout.Y_AXIS));
		subPanel.add(Box.createRigidArea(new Dimension(0, 45)));
		subPanel.add(floorFive.getComponent());
		subPanel.add(Box.createRigidArea(new Dimension(0, 45)));
		subPanel.add(floorFour.getComponent());
		subPanel.add(Box.createRigidArea(new Dimension(0, 45)));
		subPanel.add(floorThree.getComponent());
		subPanel.add(Box.createRigidArea(new Dimension(0, 45)));
		subPanel.add(floorTwo.getComponent());
		subPanel.add(Box.createRigidArea(new Dimension(0, 50)));
		subPanel.add(floorOne.getComponent());

		mainPanel.add(subPanel);
	}

	public JComponent getComponent() {
		return mainPanel;
	}

	private static void createAndShowUI() {
		JFrame frame = new JFrame("Lift Management System");
		frame.getContentPane().add(new LiftUI().getComponent());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				createAndShowUI();
			}
		});
	}
}
