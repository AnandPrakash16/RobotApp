package org.roboapp;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.roboapp.constant.RoboAppConstants;
import org.roboapp.display.ShowMessage;
import org.roboapp.event.EventSource;

/**
 * A Robot implementation class
 * @author AnandPrakash
 *
 */
public class Robot implements PowerFunctions,PhysicalFunctions,ScanFunctions{

	private String roboName;
	private double charge;
	private double distance;
	private double weightCarrying;
	EventSource eventSource;
	private double maxDistance = 5000;
	private double maxLoad = 10000;
	private double scannedItemPrice;
	Map<Integer,Double> itemMap = new HashMap<Integer,Double>();
	
	/**
	 * 
	 * @param roboName
	 * @param charge
	 * @param distance
	 * @param eventSource
	 * @param weightCarrying
	 * @param scannedItemPrice
	 */
	public Robot(String roboName, double charge, double distance, EventSource eventSource, double weightCarrying,double scannedItemPrice) {
		super();
		this.roboName = roboName;
		this.charge = charge;
		this.distance = distance;
		this.eventSource = eventSource;
		this.weightCarrying = weightCarrying;
		this.scannedItemPrice = scannedItemPrice;
	}

	
	/**
	 * @return the scannedItemPrice
	 */
	public double getScannedItemPrice() {
		return scannedItemPrice;
	}


	/**
	 * @param scannedItemPrice the scannedItemPrice to set
	 */
	public void setScannedItemPrice(double scannedItemPrice) {
		this.scannedItemPrice = scannedItemPrice;
	}


	/**
	 * @return the weightCarrying
	 */
	public double getWeightCarrying() {
		return weightCarrying;
	}


	/**
	 * @param weightCarrying the weightCarrying to set
	 */
	public void setWeightCarrying(double weightCarrying) {
		this.weightCarrying = weightCarrying;
	}


	public String getRoboName() {
		return roboName;
	}

	public void setRoboName(String roboName) {
		this.roboName = roboName;
	}

	public double getCharge() {
		return charge;
	}

	public void setCharge(double charge) {
		this.charge = charge;
	}

	public String getChargePercent() {
		return getCharge()+"%";
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	/**
	 * Method to load static data for scan functionality
	 */
	public void loadItemsMap() {
		itemMap.put(1,100.00);
		itemMap.put(15,200.00);
		itemMap.put(89,300.00);
		itemMap.put(54,400.00);
		itemMap.put(6,100.00);
		itemMap.put(19,500.00);
		itemMap.put(32,100.00);
		itemMap.put(10,400.00);
		itemMap.put(25,1000.00);
		itemMap.put(20,900.00);
	}
	
	/**
	 * Method to process Robots walking functionality
	 */
	public double processDistance(double dist) {
		
		dist = dist * 1000;
		if(getCharge() > 15) {
		if(dist > maxDistance ) {
			eventSource.addEvent(new ShowMessage(RoboAppConstants.MSG_TYPE_ERR, RoboAppConstants.HIGH_DISTANCE_ERR_MSG));
		}else if((dist+getDistance()) > maxDistance) {
			eventSource.addEvent(new ShowMessage(RoboAppConstants.MSG_TYPE_INFO, RoboAppConstants.BATTERY_STATUS+this.getChargePercent()));
			eventSource.addEvent(new ShowMessage(RoboAppConstants.MSG_TYPE_ERR, RoboAppConstants.DISTANCE_ERR_MSG));
		}else {
			double charge = dist *0.02;
			
			double currentDistance = getDistance()+dist;
			setDistance(currentDistance);
			double currentCharge = getCharge()-charge;
			setCharge(currentCharge);
			eventSource.addEvent(new ShowMessage(RoboAppConstants.MSG_TYPE_INFO, RoboAppConstants.WALK_STATUS+": "+getDistance()/1000 +" KM"));
			eventSource.addEvent(new ShowMessage(RoboAppConstants.MSG_TYPE_INFO, RoboAppConstants.BATTERY_STATUS+this.getChargePercent()));
			
		}
		}else {
			eventSource.addEvent(new ShowMessage(RoboAppConstants.MSG_TYPE_WARNING, RoboAppConstants.RED_LIGHT));
		}
		return getCharge();
	}

	/**
	 * Method to process Robots walking with weight functionality
	 */
	public double processLoad(double dist,double load) {
		load = load * 1000;
		dist = dist * 1000;
		
		if(dist > maxDistance) {
			eventSource.addEvent(new ShowMessage(RoboAppConstants.MSG_TYPE_ERR, RoboAppConstants.HIGH_DISTANCE_ERR_MSG));
		}else if(load > maxLoad) {
			eventSource.addEvent(new ShowMessage(RoboAppConstants.MSG_TYPE_ERR, RoboAppConstants.HIGH_LOAD_ERR_MSG));
		}else if((dist+getDistance()) > maxDistance) {
			eventSource.addEvent(new ShowMessage(RoboAppConstants.MSG_TYPE_INFO, RoboAppConstants.BATTERY_STATUS+this.getChargePercent()));
			eventSource.addEvent(new ShowMessage(RoboAppConstants.MSG_TYPE_ERR, RoboAppConstants.DISTANCE_ERR_MSG));
		}else if((load+getWeightCarrying()) > maxLoad) {
			eventSource.addEvent(new ShowMessage(RoboAppConstants.MSG_TYPE_INFO, RoboAppConstants.OVERWEIGHT_MSG));
			eventSource.addEvent(new ShowMessage(RoboAppConstants.MSG_TYPE_ERR, RoboAppConstants.DISTANCE_ERR_MSG));
		}else {
			
			double charge = dist *0.02;
			double chargeForLoad = load *0.002;
			
			double currentDistance = getDistance()+dist;
			double currentCharge = getCharge()-(charge+chargeForLoad);
			
			double currentLoad = getWeightCarrying()+load;
			if(currentCharge < 0) {
				eventSource.addEvent(new ShowMessage(RoboAppConstants.MSG_TYPE_ERR, RoboAppConstants.OUT_OF_CHARGE));
			}else {
				setCharge(currentCharge);
				setDistance(currentDistance);
				setWeightCarrying(currentLoad);
			eventSource.addEvent(new ShowMessage(RoboAppConstants.MSG_TYPE_INFO, RoboAppConstants.WALK_STATUS+": "+getDistance()/1000 +" KM WITH WEIGHT :"+getWeightCarrying()/1000 +"KG"));
			eventSource.addEvent(new ShowMessage(RoboAppConstants.MSG_TYPE_INFO, RoboAppConstants.BATTERY_STATUS+this.getChargePercent()));
			}
		}
		
		return getCharge();
	}
	
	/**
	 * Method to process Robots scanning functionality
	 */
	public double scanItem(int barcode) {
		
		if(itemMap.get(barcode) != null) {
			scannedItemPrice = scannedItemPrice+itemMap.get(barcode);
			eventSource.addEvent(new ShowMessage(RoboAppConstants.MSG_TYPE_INFO, RoboAppConstants.SCANNED_PRODUCT_PRICE+" :"+getScannedItemPrice()));
		}else {
			eventSource.addEvent(new ShowMessage(RoboAppConstants.MSG_TYPE_WARNING, RoboAppConstants.SCANNED_PRODUCT_NOT_FOUND));
			eventSource.addEvent(new ShowMessage(RoboAppConstants.MSG_TYPE_INFO, RoboAppConstants.SCANNED_PRODUCT_PRICE+" :"+getScannedItemPrice()));
		}
		
		return getScannedItemPrice();
	}


}
