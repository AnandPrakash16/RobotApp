package org.roboapp.command;

import java.util.Scanner;

import org.roboapp.Robot;
import org.roboapp.event.EventSource;

public class CommandExecutor {

		private Command command = null;
		
		public  void executeCommand(double i, Robot robo, EventSource eventSource, Scanner sc) {
			if (i == 1) {
				command = new ChargeCommand();
			}else if(i == 2) {
				command = new WeightCommand();
			}else if(i == 3) {
				command = new DistanceCommand();
			}else if(i == 4) {
				command = new WeightAndDistanceCommand();
			}else if(i == 5) {
				command = new ScanCommand();
			}else if(i == 6) {
				command = new RechargeCommand();
			}else {
				command = new NotValidCommand();
			}
			
			command.executeCommand(i, robo, eventSource, sc);
		}
}
