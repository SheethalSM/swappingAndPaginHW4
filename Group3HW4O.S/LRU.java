import java.util.LinkedList;
import java.util.Queue;

//least recently used
public class LRU implements PickReplacementAlgorithm{

	String name;
	public LRU(String name1)
	{
		name = name1;
	}
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	/**
	 * evict the page with least recently used by calculating the recent used time according to the process
	 * @param replacementList  free page list in main memory 
	 *  
	 */
	@Override
	public String run(LinkedList<Page> replacementList, LinkedList<Page> PageQueue) {
		// TODO Auto-generated method stub
		String evictProcessName;
		// get the recently used time 
		double leastRecent = PageQueue.get(0).recentlyUsedTime;
		double  mostRecent = PageQueue.get(0).recentlyUsedTime;
		int count = 0;
		for(int i =0; i< PageQueue.size(); i++) {
			if(leastRecent > PageQueue.get(i).recentlyUsedTime)            //if there exists a page's recently used time smaller than current time
			{
				leastRecent = PageQueue.get(i).recentlyUsedTime;           //replace recentused time with the current  and set count to current index of page on the list.
				count =i;
			}
			//get the most current time for store the time in page 
			if(mostRecent<PageQueue.get(i).recentlyUsedTime) mostRecent=PageQueue.get(i).recentlyUsedTime;
		}
		evictProcessName = PageQueue.get(count).processName;
		int number = PageQueue.get(count).pageNumber;
		PageQueue.remove(count);// remove from the page queue 
		for(int i =0; i<replacementList.size(); i++) {
			//compare the two process to find the arrived one based on index number
			if(replacementList.get(i).pageNumber == number && replacementList.get(i).processName.equals(evictProcessName)) 
			{
				Page initialize = new Page(-1, ".");
				initialize.setRecentlyUsedTime(mostRecent);
				replacementList.set(i, initialize);
				break;
			}
		}
		return evictProcessName;
		
		
	}

}
