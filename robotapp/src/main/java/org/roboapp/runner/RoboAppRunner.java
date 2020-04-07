package org.roboapp.runner;

import java.util.Scanner;

import org.roboapp.Robot;
import org.roboapp.command.CommandExecutor;
import org.roboapp.constant.RoboAppConstants;
import org.roboapp.display.PrintMessage;
import org.roboapp.display.ShowMessage;
import org.roboapp.event.EventSource;

public class RoboAppRunner {

	public static void main(String[] args) {
		final EventSource eventSource = EventSource.getInstance();

		final PrintMessage printMessage = new PrintMessage();
		eventSource.addObserver(printMessage);
		
		CommandExecutor commandExecutor = new CommandExecutor();

		// starts the event thread
		Thread thread = new Thread(eventSource);
		thread.start();
		Scanner sc = new Scanner(System.in);

		try {

			Robot robo = new Robot("Robot", 100, 0, eventSource, 0, 0);
			robo.loadItemsMap();
			boolean isRobotRunning = true;

			System.out.println("=========================== ROBO APP =============================");
			System.out.println();
			System.out.println("=========================== ROBO IS SWITCHED ON AND THE BATTERY IS FULL("
					+ robo.getChargePercent() + ")===========================");
			outer: while (isRobotRunning) {
				Thread.sleep(300);
				System.out.println();
				System.out.println("#################################################################################");
				System.out.println("#SELECT A FUNCTION FOR ROBOT");
				System.out.println("#################################################################################");
				System.out.println("## 1 ==> CHECK BATTERY STATUS");
				System.out.println("## 2 ==> CHECK CURRENT LOAD");
				System.out.println("## 3 ==> ASK ROBOT TO WALK");
				System.out.println("## 4 ==> ASK ROBOT TO WALK WITH LOAD");
				System.out.println("## 5 ==> SCAN A PRODUCT");
				System.out.println("## 6 ==> RECHARGE ROBOT AND START OVER");
				System.out.println("## 7 ==> SWITCH OFF");
				System.out.println("#################################################################################");
				String input = sc.next();

				try {

					// Validating the input against Integer value
					int i = Integer.parseInt(input);

					if (i == 6) {
						commandExecutor.executeCommand(i, robo, eventSource, sc);
						continue outer;
					} else if (i == 7) {
						System.out.println("=========================== SWITCHING OFF ROBOT ===========================");
						isRobotRunning = false;
						System.out.println();
						System.out.println("=========================== ROBOT IS SWITCHED OFF ===========================");
					} else {
						commandExecutor.executeCommand(i, robo, eventSource, sc);
					}

				} catch (NumberFormatException nfe) {
					eventSource.addEvent(
							new ShowMessage(RoboAppConstants.MSG_TYPE_WARNING, RoboAppConstants.NOT_VALID_INPUT));
				}
			}

		} catch (Exception ex) {
			eventSource.addEvent(new ShowMessage(RoboAppConstants.MSG_TYPE_ERR, RoboAppConstants.HELP));
		} finally {
			try {
				thread.join();
			} catch (InterruptedException e) {
				eventSource.addEvent(
						new ShowMessage(RoboAppConstants.MSG_TYPE_ERR, RoboAppConstants.HELP_INTERRUPTED_EXC));
			}
			sc.close();
		}

	}

}
