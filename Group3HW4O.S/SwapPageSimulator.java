import java.util.ArrayList;
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
		List<PickReplacementAlgorithm> algorithms = new ArrayList<>();
		
		algorithms.add(new FIFO ("fifo"));
		algorithms.add(new LeastFrequentlyUsed ("lfu"));
		algorithms.add(new LRU("lru"));
		algorithms.add(new MFU());
		algorithms.add(new RandomPick("randomPick"));

	}

}
