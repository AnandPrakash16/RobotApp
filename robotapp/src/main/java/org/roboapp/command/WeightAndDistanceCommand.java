package org.roboapp.command;

import java.util.Scanner;

import org.roboapp.Robot;
import org.roboapp.constant.RoboAppConstants;
import org.roboapp.display.ShowMessage;
import org.roboapp.event.EventSource;

public class WeightAndDistanceCommand implements Command{

	public void executeCommand(double i, Robot robo, EventSource eventSource, Scanner sc) {
		System.out.println("ENTER THE DISTANCE FOR ROBO TO WALK");
		double k = sc.nextDouble();
		System.out.println("ENTER THE LOAD FOR ROBO TO TAKE");
		double j = sc.nextDouble();
		if (j > 10) {
			eventSource.addEvent(new ShowMessage(RoboAppConstants.MSG_TYPE_WARNING,RoboAppConstants.OVERWEIGHT_MSG));
		} else {
			robo.processLoad(k, j);
		}
		
	}

}
