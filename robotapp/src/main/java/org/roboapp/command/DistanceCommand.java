package org.roboapp.command;

import java.util.Scanner;

import org.roboapp.Robot;
import org.roboapp.event.EventSource;

public class DistanceCommand implements Command{

	public void executeCommand(double i, Robot robo, EventSource eventSource, Scanner sc) {
		System.out.println("ENTER THE DISTANCE FOR ROBO TO WALK");
		double value = sc.nextDouble();
		robo.processDistance(value);
		
	}

}
