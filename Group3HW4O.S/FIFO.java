import java.util.LinkedList;
import java.util.*;
import java.util.PriorityQueue;

public class FIFO implements PickReplacementAlgorithm{

	String name;
	//constructor of fifo
	public FIFO(String name1){
		name = name1;
	}
	
	/**
	 * Exit out the page arrived first.
	 * @param linkedlist of Replacement
	 * @param page queue linkedlist
	 * @return String exited process name
	 */
	@Override
	public String run(LinkedList<Page> replacementList, LinkedList<Page> PageQueue) {

		String evictProcess;
		Page p = PageQueue.poll();//poll() in prirityQueue.java removes head of the Queue
		evictProcess = p.processName;
		
		for(int i =0; i<replacementList.size(); i++) {
			//compare the two process to find the arrived one based on index number
			if(replacementList.get(i).pageNumber == p.pageNumber && replacementList.get(i).processName.equals(p.processName)) 
			{
				replacementList.set(i, new Page(-1, "."));
				break;
			}
		}
		return evictProcess;
	}
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	
}
