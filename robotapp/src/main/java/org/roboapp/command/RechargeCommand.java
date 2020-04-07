package org.roboapp.command;

import java.util.Scanner;

import org.roboapp.Robot;
import org.roboapp.event.EventSource;

public class RechargeCommand implements Command{

	public void executeCommand(double i, Robot robo, EventSource eventSource, Scanner sc) {
		robo.setCharge(100);
		robo.setDistance(0);
		robo.setScannedItemPrice(0);
		robo.setWeightCarrying(0);
		
	}

}
