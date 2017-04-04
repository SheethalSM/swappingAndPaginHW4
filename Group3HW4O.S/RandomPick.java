import java.util.LinkedList;
import java.util.Random;

public class RandomPick implements PickReplacementAlgorithm {

	String name;
	//constructor of fifo (name of algorithm)
	public RandomPick(String name1){
		name = name1;
	}
	
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
		Random rand = new Random();
		String evictPage;
		
		int generatedIndex = rand.nextInt(PageQueue.size());

		evictPage = PageQueue.get(generatedIndex).processName;
		int number = PageQueue.get(generatedIndex).pageNumber;           //store page number of the page to be removed
		PageQueue.remove(number);                                //evicted page is removed from the list of pages called pageQueue
		
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
