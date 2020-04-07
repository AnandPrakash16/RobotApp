package org.roboapp.command;

import java.util.Scanner;

import org.roboapp.Robot;
import org.roboapp.constant.RoboAppConstants;
import org.roboapp.display.ShowMessage;
import org.roboapp.event.EventSource;

public class WeightCommand implements Command{

	public void executeCommand(double i, Robot robo, EventSource eventSource, Scanner sc) {
		eventSource.addEvent(new ShowMessage(RoboAppConstants.MSG_TYPE_INFO,RoboAppConstants.LOAD_STATUS + robo.getWeightCarrying()/1000+" KG"));
	}

}
