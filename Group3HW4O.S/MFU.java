import java.util.LinkedList;

//most frequently used
public class MFU implements PickReplacementAlgorithm{

	String name;
	
	/**constructor of MFU object*/
	public MFU(String name1)
	{
		name = name1;
	}
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	/**
	 * find the most frequently used page and evict it 
	 * @param replacementList the free pages 
	 * @param PageQueue  the page queue that store the concurrently being used pages
	 * @return the name of evicted process 
	 */
	@Override
	public String run(LinkedList<Page> replacementList, LinkedList<Page> PageQueue) {
		// TODO Auto-generated method stub
		String evictPage;
		int max = PageQueue.get(0).referencedTime;                //max is the process that is referenced the most number of times
		int count = 0;
		for(int i =0; i< PageQueue.size(); i++) {
			if(max< PageQueue.get(i).referencedTime)            //if there exists a page more than current max
			{
				max = PageQueue.get(i).referencedTime;           //replace that less than max page with max and set count to current index of page on the list.
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
