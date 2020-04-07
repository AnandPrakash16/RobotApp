package org.roboapp.command;

import java.util.Scanner;

import org.roboapp.Robot;
import org.roboapp.event.EventSource;

public class ScanCommand implements Command{

	public void executeCommand(double i, Robot robo, EventSource eventSource, Scanner sc) {
		System.out.println("ENTER THE BARCODE FOR SCAN");
		int j = sc.nextInt();
		robo.scanItem(j);
		
	}

}
