/**
 * @author SHEETHAL
 * LFU page is evicted, and how it works is in this class
 */
import java.util.LinkedList;
import java.util.*;
import java.util.PriorityQueue;

public class LeastFrequentlyUsed implements PickReplacementAlgorithm {

	String name;
    /**
     * constructor LFU
     * 
     */
	public LeastFrequentlyUsed(String name1)
	{
		name = name1;
	}
	/**
	 * constructor LFU
	 * @return LFU name
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * Method finds the page the least frequently used and evict that page
	 * Method that runs LeastFrequenlyUsed, and figure out the page
	 * which has the least frequently used page
     * @param linkedlist of replacement in main memory
	 * @Param page queue linkedlist, it will used be used to find the arrival time
	 * @return the evicted page name
	 */
	@Override
	public String run(LinkedList<Page> replacementList, LinkedList<Page> PageQueue) {

		String evictPage;
		int min = PageQueue.get(0).referencedTime;                //min is the process that is referenced the least number of times
		int count = 0;
		for(int i =0; i< PageQueue.size(); i++) {
			if(min > PageQueue.get(i).referencedTime)            //if there exists a page less than current min
			{
				min = PageQueue.get(i).referencedTime;           //replace that less than min page with min and set count to current index of page on the list.
				count =i;
			}
		}
		
		evictPage = PageQueue.get(count).processName;           //this is new name of evictPage based on LFU
		int number = PageQueue.get(count).pageNumber;           //store page number of the page to be removed
		PageQueue.remove(count);                                //evicted page is removed from the list of pages called pageQueue
		
		//since page is removed we need to worry about replacing every page there after the removal
		for(int i =0; i<replacementList.size(); i++) {
			//compare the two process to find the arrived one based on index number
			if(replacementList.get(i).pageNumber == number && replacementList.get(i).processName.equals(evictPage)) 
			{
				replacementList.set(i, new Page(-1, "."));
				break;
			}
		}
		return evictPage;
	}

}
