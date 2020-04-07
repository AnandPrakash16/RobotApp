package org.roboapp.event;
import java.util.Observable;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.roboapp.display.DisplayEngine;


public class EventSource extends Observable implements Runnable {
	private  BlockingQueue<DisplayEngine> queue = new ArrayBlockingQueue<DisplayEngine>(20) ;
  
	static EventSource eventSource = new EventSource();
	
	public static synchronized EventSource getInstance() {
		return eventSource;
	}
	
	public void addEvent(DisplayEngine display) {
		queue.add(display);
	}
	
    public void run() {
        try {
            while (true) {
        		DisplayEngine disp =queue.take();
                setChanged();
                notifyObservers(disp);
            }
        }
        catch ( InterruptedException e) {
            e.printStackTrace();
        }
    }
}