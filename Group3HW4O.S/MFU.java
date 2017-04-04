import java.util.LinkedList;

//most frequently used
public class MFU implements PickReplacementAlgorithm{

	String name;
	
	/**constructor of MFU object*/
	public MFU(String name1)
	{
		name = name1;
	}
	
	/**gets the name of the algorthm that pickReplacement picks*/
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

//	/**
//	 * find the most frequently used page and evict it 
//	 * @param replacementList the free pages 
//	 * @param PageQueue  the page queue that store the concurrently being used pages
//	 * @return the name of evicted process 
//	 */
//	@Override
//	public String run(LinkedList<Page> replacementList, LinkedList<Page> PageQueue) {
//		// TODO Auto-generated method stub
//		String evictPage;
//		int max = PageQueue.get(0).referencedTime;                //max is the process that is referenced the most number of times
//		int count = 0;
//		for(int i =0; i< PageQueue.size(); i++) {
//			if(max< PageQueue.get(i).referencedTime)            //if there exists a page more than current max
//			{
//				max = PageQueue.get(i).referencedTime;           //replace that less than max page with max and set count to current index of page on the list.
//				count =i;
//			}
//		}
//		
//		evictPage = PageQueue.get(count).processName;           //this is new name of evictPage based on LFU
//		int number = PageQueue.get(count).pageNumber;           //store page number of the page to be removed
//		PageQueue.remove(count);                                //evicted page is removed from the list of pages called pageQueue
//		
//		//since page is removed we need to worry about replacing every page there after the removal
//		for(int i =0; i<replacementList.size(); i++) {
//			//compare the two process to find the arrived one based on index number
//			if(replacementList.get(i).pageNumber == number && replacementList.get(i).processName.equals(evictPage)) 
//			{
//				replacementList.set(i, new Page(-1, "."));
//				break;
//			}
//		}
//		return evictPage;
//		
/**
	 * Method finds the page the most frequently used page and evict that page
	 * which has the most frequently used page
	 * @param linkedlist of replacement in main memory
	 * @Param page queue linkedlist, it will used be used to find the arrival time
	 * @return process that got evicted
	 */
	@Override
	public String run(LinkedList<Page> replacementList, LinkedList<Page> PageQueue) {
		String evict;
		int pageNum;
		int most = PageQueue.get(0).referencedTime;             //set most as page from pagequeue referenced the maximum time
		int count =0; 
		for(int i = 1; i<PageQueue.size(); i++)                 //traverse the list of pages in PageQueue      
		{
			if(most > PageQueue.get(i).referencedTime)          //if there exists a page that is referenced more times than the current most, 
			{
				most = PageQueue.get(i).referencedTime;         //then make that the new the new most Referenced/frequently used Page
			}
			count =i;										    //Since we have new MFU page, the index is reset to the index of the latest updated MFU
		}
		
		//get the name of that process and remove from Queue
		evict = PageQueue.get(count).processName;               //since count=i,index, now we can use get(count) to get name of the process to be removed
		pageNum = PageQueue.get(count).pageNumber;              //get pageNumber of the to be evicted
		PageQueue.remove(count);                                //remove MFU page
		
		
		//since page is removed, we need to swap and replace everything there after
		for(int i =0; i<replacementList.size();i++) {
			//compare the two process to find the arrived one based on index number
			if(replacementList.get(i).pageNumber == pageNum && replacementList.get(i).processName.equals(evict)) 
			{
				//set() in List class java: Replaces the element at the specified
				//position in this list with the specified element (optional operation).
				replacementList.set(i, new Page(-1, "."));
			}
		}
		return evict;

	}

}
