import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
public class SwapPageSimulator {
	
	private static int NUMJOBS = 150;             //total number of jobs
    private static int MEMSIZE = 100;             //100 pages with each 1 MB therefore total 100MB
    
    //??????????GUYS I am assuming we need four more than K since 4 free pages? or is it like K-4? so only use until G intially?
//    private static char [] ProcessName = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
//			'I', 'J', 'K', 'L', 'M', 'N', 'O'};
    private static char [] ProcessName = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
			'I', 'J', 'K'};
    
    //????????????????????DO we need a maximum time stamp?
	public static void main(String[] args) {
		
		//always start at 0th index, then continue looking by locality reference
		//each process has its own queue that references the pages it is using
		
		//Create 150 jobs, assign size and duration, sort by arrival time
		Queue<Process> processJobList = createJobList();
		
		//Create the 100MB page list
		LinkedList<Page> freePageList = new LinkedList<Page>();
		
		//Create the algorithms
		List<PickReplacementAlgorithm> algorithms = new ArrayList<>();
		algorithms.add(new FIFO ("fifo"));
		algorithms.add(new LeastFrequentlyUsed ("lfu"));
		algorithms.add(new LRU("lru"));
		algorithms.add(new MFU("mfu"));
		algorithms.add(new RandomPick("randomPick"));
		
		
		//Timer for 1 min
		//Every sec, check processJobList for process arrival time == current time
					//execute it (add to currently running) only if at least 4 free pages in page list, 
															//else leave in queue and wait until 4 free
		//Every 100 msec, for currently running processes, each add a page, if no free page, use algorithm 
		//Every 1 sec, for currently running processes, each decrement their own duration by 1
		


	}
	
	public static Queue<Process> createJobList()
	{
		Random rand = new Random();
		Queue<Process> processJobList = new LinkedList<Process>();
		for (int numProcess = 0; numProcess < NUMJOBS; numProcess++)
		{
			//Generate the process size, all sizes must be evenly distributed
			int currentProcessSize = 0;
			if (numProcess % 4 == 0) currentProcessSize = 5;
			else if (numProcess % 4 == 1) currentProcessSize = 11;
			else if (numProcess % 4 == 2) currentProcessSize = 17;
			else if (numProcess % 4 == 3) currentProcessSize  = 31;
			
			//Generate the process generation, all durations must be evenly distributed
			int currentDuration = 0;
			if (numProcess % 5 == 0) currentDuration = 1;
			else if (numProcess % 5 == 1) currentDuration = 2;
			else if (numProcess % 5 == 2) currentDuration = 3;
			else if (numProcess % 5 == 3) currentDuration  = 4;
			else if (numProcess % 5 == 4) currentDuration  = 5;
			
			//Create and add the process to the queue
			processJobList.add(new Process(rand.nextInt(60), currentDuration, currentProcessSize, (numProcess+1) + ""));
		}
		sortQueue(processJobList);
		return processJobList;
	}
	
	public static void sortQueue(Queue<Process> jobList){
		Process[] temp = jobList.toArray(new Process[jobList.size()]);
		jobList.clear();
		Arrays.sort(temp);
		for(Process p: temp)
			jobList.add(p);
	}

}
