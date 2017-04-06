import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
public class SwapPageSimulator {

	private static int NUMJOBS = 150;             //total number of jobs
	private static int MEMSIZE = 100;             //100 pages with each 1 MB therefore total 100MB

	//??????????GUYS I am assuming we need four more than K since 4 free pages? or is it like K-4? so only use until G intially?
	//    private static char [] ProcessName = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
	//			'I', 'J', 'K', 'L', 'M', 'N', 'O'};
	private static char [] ProcessName = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
			'I', 'J', 'K'};

	private static int[] deltaI = {-1, 0, 1};

	static Random rand = new Random();

	//????????????????????DO we need a maximum time stamp?
	public static void main(String[] args) {

		//always start at 0th index, then continue looking by locality reference
		//each process has its own queue that references the pages it is using

		//Create 150 jobs, assign size and duration, sort by arrival time
		Queue<Process> processList = createJobList();

		// Debugging prints
		System.out.println("Initialized processes:");
		System.out.println("----------------------------------------");
		for (Process p : processList) {
			System.out.println("Arrival: " + p.getArrivaltime() + ", Name: " + p.getName() + ", Size: " + p.getMaxPages());
		}
		System.out.println("----------------------------------------");

		//Currently running processes
		LinkedList<Process> currentProcesses = new LinkedList<Process>();

		LinkedList<Process> trash = new LinkedList<Process>();

		//Create the 100MB page list = 100 pages
		PageTable pageTable = new PageTable(100);

		//Create the algorithms
		List<PickReplacementAlgorithm> algorithms = new ArrayList<>();
		algorithms.add(new FIFO ("fifo"));
		algorithms.add(new LeastFrequentlyUsed ("lfu"));
		algorithms.add(new LRU("lru"));
		algorithms.add(new MFU("mfu"));
		algorithms.add(new RandomPick("randomPick"));

		// Prompt for algorithm pick
		int index;
		PickReplacementAlgorithm pickedAlgorithm;

		System.out.println("FIFO - 1; LRU - 2; LFU - 3; MFU - 4; Random - 5");
		Scanner prompter = new Scanner(System.in);
		System.out.print(">> ");

		if (prompter.hasNextInt()) 
			index = prompter.nextInt();
		else 
			return;

		if (index <= 5 && index >= 1) 
			pickedAlgorithm = algorithms.get(index-1);
		else 
			return;

		//Timer for 1 min
		//Every sec, check processJobList for process arrival time == current time
		//execute it (add to currently running) only if at least 4 free pages in page list, 
		//else leave in queue and wait until 4 free
		//Every 100 msec, for currently running processes, each add a page, if no free page, use algorithm 
		//when add a page set  recentlyUsedTime of sec+ msec page.setRecentlyT....()
		//Every 1 sec, for currently running processes, each decrement their own duration by 1

		Timer timer = new Timer();

		long startTime = System.currentTimeMillis();


		timer.schedule(new TimerTask() {
			public void run() {
				long elapsedTime = System.currentTimeMillis() - startTime; 

				// Test print
				// System.out.println("Current time (normal): " + elapsedTime);

				if (elapsedTime >= 6000) {
					timer.cancel(); // Stop timer after 1 minute
				}


				for (int i = 0; i < currentProcesses.size(); i++) {
					Process p = currentProcesses.get(i);
					//if (pageTable.hasFreePage()) { //Add a page if there is a free page and if process needs page
					while (p.getNumOfReferences() < p.getMaxPages() && pageTable.hasFreePage()) {
						// set recentlyUsedTime of sec+ msec page.setRecentlyT....()
						
						// Don't know what page index to use?
						Page pageUsed = pageTable.usePage(p, pageIndex);
						int recentlyUsedTime = (int) (System.currentTimeMillis() - startTime/1000);
						pageUsed.setRecentlyUsedTime(recentlyUsedTime);
						p.addPageReference(pageUsed);
						//System.out.println("Process " + p.getName() + " used page " + pageUsed.getPageNumber());
					}
					//}
					//else { // Used picked algorithm to swap 
						
					if (pageTable.hasFreePage()) {
					//System.out.println("Swap happens");
						LinkedList<Page> pagesToReplace = new LinkedList<Page>();
						
						pickedAlgorithm.run(p.getPageReferences(), pageTable);
						
						// Find pages that process needs using LoR
					}
					//}
				}
			}


		}, 0, 100);

		timer.schedule(new TimerTask() {
			public void run() {
				long elapsedTime = System.currentTimeMillis() - startTime; 

				if (elapsedTime >= 6000) {
					timer.cancel(); // Stop timer after 1 minute
					// Debugging prints
					for (Process p : trash) {
						System.out.println("Arrival: " + p.getArrivaltime() + ", Name: " + p.getName() + ", Size: " + p.getMaxPages());
						for (Page pa : p.getPageReferences()) {
							System.out.print(pa.getPageNumber() + " ");
						}
						System.out.println();
					}
				}

				// Test print
				//System.out.println("Current time (special): " + elapsedTime);

				// check processJobList for process arrival time == current time
				// check if has page table has at least 4 free pages
				//System.out.println((int)(elapsedTime/1000));
				if (processList.size() > 0)
					//System.out.println(processList.peek().getArrivaltime());

					// Decrement current running process service time
					if (currentProcesses.size() > 0) {
						for (int i = 0; i < currentProcesses.size(); i++) {
							currentProcesses.get(i).decServiceDuration();

							// Remove if service time reaches 0
							if (currentProcesses.get(i).getServiceDuration() == 0) 
								currentProcesses.remove();
						}
					}

				// Run processes arrived 
				while (pageTable.hasEnoughSpace() && processList.size() > 0 
						&& processList.peek().getArrivaltime() <= (int)(elapsedTime/1000)) {
					Process processToAdd = processList.remove();
					trash.add(processToAdd);
					currentProcesses.add(processToAdd);
					System.out.println("Added process " + processToAdd.getName());
				}
			}
			// for currently running processes
		}, 0, 1000);
	}

	public static Queue<Process> createJobList()
	{
		Queue<Process> processList = new LinkedList<Process>();
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
			processList.add(new Process(rand.nextInt(60), currentDuration, currentProcessSize, (numProcess+1) + ""));
		}
		sortQueue(processList);
		return processList;
	}

	public static void sortQueue(Queue<Process> jobList){
		Process[] temp = jobList.toArray(new Process[jobList.size()]);
		jobList.clear();
		Arrays.sort(temp);
		for(Process p: temp)
			jobList.add(p);
	}

}
