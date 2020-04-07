package org.roboapp.command;

import java.util.Scanner;

import org.roboapp.Robot;
import org.roboapp.constant.RoboAppConstants;
import org.roboapp.display.ShowMessage;
import org.roboapp.event.EventSource;

public class ChargeCommand implements Command{


	public void executeCommand(double i, Robot robo, EventSource eventSource, Scanner sc) {
		if (robo.getCharge() < 15) {
			eventSource.addEvent(new ShowMessage(RoboAppConstants.MSG_TYPE_WARNING,RoboAppConstants.RED_LIGHT));
			eventSource.addEvent(new ShowMessage(RoboAppConstants.MSG_TYPE_INFO,RoboAppConstants.BATTERY_STATUS + robo.getChargePercent()));
		} else {
			eventSource.addEvent(new ShowMessage(RoboAppConstants.MSG_TYPE_INFO,RoboAppConstants.BATTERY_STATUS + robo.getChargePercent()));
		}
		
		
	}

}
