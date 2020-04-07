package org.roboapp;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.roboapp.event.EventSource;

import junit.framework.TestCase;

public class RobotTest extends TestCase {

	private Robot robo;
	private EventSource eventSource;

	@Before
	public void setUp() throws Exception {
		eventSource = new EventSource();
		robo = new Robot("Robot", 100, 0, eventSource, 0, 0);
	}

	@After
	public void tearDown() throws Exception {
		eventSource = null;
		robo = null;
	}

	/**
	 * Test case for Robot walks for 3.5 KM 
	 */
	@Test
	public void testProcessDistance() {
	
		assertEquals(30.0, robo.processDistance(3.5));
	}

	/**
	 * Test case for Robot walks for 2 Km carrying 3 Kg weight  
	 */
	@Test
	public void testProcessLoad() {
		
		assertEquals(54.0, robo.processLoad(2, 3));
	}

	/**
	 * Test case for Robot carries 12 Kg weight 
	 */
	@Test
	public void testProcessLoad2() {
		
		assertEquals(100.0, robo.processLoad(2, 12));
	}
	

}
