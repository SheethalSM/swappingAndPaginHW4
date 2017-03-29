/**
 * @author SHEETHAL 
 * Process deals with each algorithm and how it correlated eviction with arrival time or number of references
 */

import java.util.*;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class Process {
	private String name;
	private int arrivalTime;
	private int serviceDuration;
	private int numOfReferences;
	
	/**
     * Constructor that creates a process
     * @param arrivalTime1 is the time a process arrives
     * @param runTime1 time to finish its task
     * @param name1 is name of Process at hand
     */
	public Process(int arrivalTime1, int duration, String name1)
	{
		arrivalTime = arrivalTime1;
		serviceDuration= duration;
		name = name1;
	}
	
	//setter methods are below for all other global variables
	public void setName(String name1)
	{
		name = name1;
	}
	public void setArrivalTime(int arrivalTime1)
	{
		arrivalTime=arrivalTime1;
	}
	public void setNumOfReferences(int numOfRefer1)
	{
		numOfReferences = numOfRefer1;
	}
	public void setServiceDuration(int duration)
	{
		serviceDuration = duration;
	}
	
	//getters are below
	public String getName(){
		return name;
	}
	public int getArrivaltime()
	{
		return arrivalTime;
	}
	/**
	 * returns number of references on each process
	 * @return numOfReferences, number of References
	 */
	public int getNumOfReferences()
	{
		return numOfReferences;
	}
	public int getServiceDuration()
	{
		return serviceDuration;
	}
	
}

