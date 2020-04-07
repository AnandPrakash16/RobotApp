package org.roboapp.command;

import java.util.Scanner;

import org.roboapp.Robot;
import org.roboapp.constant.RoboAppConstants;
import org.roboapp.display.ShowMessage;
import org.roboapp.event.EventSource;

public class NotValidCommand implements Command{

	public void executeCommand(double i, Robot robo, EventSource eventSource, Scanner sc) {
		eventSource.addEvent(new ShowMessage(RoboAppConstants.MSG_TYPE_WARNING,RoboAppConstants.NOT_VALID_INPUT));
	}

}
