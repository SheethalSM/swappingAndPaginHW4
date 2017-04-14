import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;

public class App {

	private static int i = 1;
	private static final int MAX = 150;
	private static final int SEC = 60;
	public static final int MEMORY = 100;
	
	private static final int arr[] = {5,11,17,31};
	//private static final int arr[] = {17,17,17,17};
	
	private static AtomicInteger sec = new AtomicInteger(0);
	public volatile static int flagTA = 0; //terminate after 60sec
	public volatile static int okToPop = 0; //0 - not ok to pop, 1 - ok 
	private volatile static FreePageList freePageL;
	
	public static AtomicInteger hit = new AtomicInteger(0);
	public volatile static int miss;
	public static AtomicInteger evict = new AtomicInteger(0);
	public static int successFully = 0;
	
	
	public static ArrayList<Integer> algorithmList; //for algorithm 2 3 4
	public static LinkedList<Integer> FIFOlist; // for fifo algorithm
	
	public volatile static int pick = -1;
	
	
	private Object lock, lockalgor;
	
	private static int sizeIndex;
	private static int durationT = 1;
	private static Random r = new Random();
	
	
	
	public static int prompt;	//dont think volatile is necessary

	public App(){
		
		lock = new Object();
		lockalgor = new Object();
		DiskPageList disk = new DiskPageList();
		LinkedList<JobProcess> jobQ = generateASumulatedQueue();
		
		freePageL = new FreePageList(MEMORY);
		
		System.out.println("FIFO-1 LRU-2 LFU-3 MFU-4 Random-5");
		Scanner prompter = new Scanner(System.in);
		if(prompter.hasNextInt())
			prompt = prompter.nextInt();
		else{
			prompter.close();
			return;
		
		}
		if(prompt > 5 || prompt < 1){
			prompter.close();
			return;
			
		}
		if(prompt == 1){
			FIFOlist = new LinkedList<>();
		}else if(prompt == 2){
			algorithmList = new ArrayList<>(Collections.nCopies(99, SEC));
		}else if(prompt == 3){
			algorithmList = new ArrayList<>(Collections.nCopies(99, 9999));
		}else if(prompt == 4){
			algorithmList = new ArrayList<>(Collections.nCopies(99, 0));
		}
		
	
		
		Timer timer = new Timer();
		timer.schedule(new TimerTask(){
			@Override
			public void run() {
				// no need for synchronization since 
				// only one thread can access the data.
				if(sec.get() >= SEC-1){
					flagTA = 1;
					timer.purge();

					timer.cancel();
				}else{
					sec.incrementAndGet();
				}
				//System.out.println(se);

			}
			
		}, 1000,1000);
		
		while(sec.get() < 60 && flagTA != 1){
			// main job starts here
			if(okToPop == 0){
				okToPop = -1;
				
				if( sec.get() < 60 && jobQ.peek().getArr() <= sec.get()){
					WorkerT workers = new WorkerT(jobQ.peek(), freePageL, lock, lockalgor,sec);
					Thread t = new Thread(workers);
					t.start();
				}
			}
			
			if(okToPop == 1){
				
				jobQ.pop();
				App.successFully++;
				okToPop = 0;
			}
			while(jobQ.isEmpty()){
				if(flagTA == 1)
					break;
			}
				
			
		}
		
		
		/*
		for(int i = 0; i < jobQ.size(); i++){
			System.out.println(jobQ.get(i).getDiskIndex() + "   " 
						+ jobQ.get(i).getSize());
		}
		*/
		try {
			Thread.sleep(1000);
			System.out.println("Hit/Miss ratio: " + App.hit + "/" + App.miss);
			System.out.println("Successfully swapped in: " + successFully);
			System.out.println(App.freePageL);
			//System.out.println(App.evict.get());

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
				
	}
	/*
	 * Generate a queue of jobs 
	 * sort them based on arrival time
	 *
	 */
	public static LinkedList<JobProcess> generateASumulatedQueue(){
		LinkedList<JobProcess> q = new LinkedList<JobProcess>();
		for(int i = 0; i < MAX; i++){
			q.add(generateProcess());
		}

		JobProcess[] temp = q.toArray(new JobProcess[MAX]);
		q.clear();
		Arrays.sort(temp);
		
		q.addAll(Arrays.asList(temp));
		
		return q;
	}
	
	private static JobProcess generateProcess(){
		
		int time = durationT;
		
		int size = arr[sizeIndex];
		
		int arrTime = r.nextInt(SEC);
		
		JobProcess p = new JobProcess("P" + i, size,
				arrTime, time);
		i++;
		
		sizeIndex++;
		durationT++;
		
		if(sizeIndex == 4)
			sizeIndex = 0;
		
		
		if(durationT == 6)
			durationT = 1;
		
		
		return p;
		
	}
	
	
	public static void main(String[] args) {
		new App();
	}

}
