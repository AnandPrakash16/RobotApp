package org.roboapp.command;

import java.util.Scanner;

import org.roboapp.Robot;
import org.roboapp.event.EventSource;

public interface Command {

	public void executeCommand(double i, Robot robo, EventSource eventSource, Scanner sc);
}
