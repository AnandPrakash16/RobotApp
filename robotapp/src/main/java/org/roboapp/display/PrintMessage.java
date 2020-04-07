package org.roboapp.display;

import java.util.Observable;
import java.util.Observer;

public class PrintMessage implements Observer{
	private DisplayEngine resp;
	
	
	public void update(Observable o, Object arg) {
		if (arg instanceof DisplayEngine) {
            resp = (DisplayEngine) arg;
            resp.showMessage();
        }
	}

}
