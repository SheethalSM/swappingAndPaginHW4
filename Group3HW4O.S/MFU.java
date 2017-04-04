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
