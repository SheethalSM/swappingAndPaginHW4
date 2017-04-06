/**
 * @author SHEETHAL 
 * Process deals with each algorithm and how it correlated eviction with arrival time or number of references
 */

import java.util.*;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class Process implements Comparable<Process>{
	private String name;
	private int arrivalTime;
	private int serviceDuration;
	private int sizeMB;
	private int numOfReferences;
	private LinkedList<Page> pageReferences;
	private int currentPage = 0;
	
	/**
     * Constructor that creates a process
     * @param arrivalTime1 is the time a process arrives
     * @param runTime1 time to finish its task
     * @param name1 is name of Process at hand
     */
	public Process(int arrivalTime1, int duration, int processSize, String name1)
	{
		arrivalTime = arrivalTime1;
		serviceDuration= duration;
		name = name1;
		sizeMB = processSize;
		pageReferences = new LinkedList<Page>();
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
	public void decServiceDuration()
	{
		serviceDuration--;
	}
	public void addPageReference(Page addingPage)
	{
		if (pageReferences.size() < sizeMB) {
			pageReferences.add(addingPage);
			numOfReferences++;
		}
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
	public LinkedList<Page> getPageReferences()
	{
		return pageReferences;
	}
	
	public int getMaxPages() {
		return sizeMB;
	}

	@Override
	public int compareTo(Process p) {
		if (this.arrivalTime < p.arrivalTime) return -1;
		else if (this.arrivalTime > p.arrivalTime) return 1;
		else return 0;
	}
	
	public Page paging(){
		Random r = new Random();
		int probability = r.nextInt() % 10;
		if(probability < 7){
			if(currentPage == 0){
				switch(r.nextInt() % 2){
				case 0: currentPage += 0; break;
				case 1: currentPage += 1; break;
				}
			}
			else if(currentPage == pageReferences.size() - 1){
				switch(r.nextInt() % 2){
				case 0: currentPage += 0; break;
				case 1: currentPage += -1; break;
				}
			}
			else{
				switch(r.nextInt() % 3){
				case 0: currentPage += 0; break;
				case 1: currentPage += 1; break;
				case 2: currentPage += -1; break;
				}
			}
		}
		else if(probability <= 7 && probability < 10){
			if(currentPage - 2 < 0){
				currentPage = r.nextInt(pageReferences.size() - (currentPage + 2)) + currentPage + 2;
			}
			else if(currentPage + 2 >= pageReferences.size() - 1){
				currentPage = r.nextInt() % currentPage - 2;
			}
			else if(r.nextBoolean() == true){
				currentPage = r.nextInt() % currentPage - 2;
			}
			else{
				currentPage = r.nextInt(pageReferences.size() - (currentPage + 2)) + currentPage + 2;
			}
		}
		return pageReferences.get(currentPage); 
	}
	
}

