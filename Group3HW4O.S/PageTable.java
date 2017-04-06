import java.util.LinkedList;
import java.util.Random;

public class PageTable extends LinkedList<Page>{
	private int freeSpace;
	private static int[] deltaI = {-1, 0, 1};

	static Random rand = new Random();
		
	public PageTable(int free) {
		this.freeSpace = free;
		initializeList();
	}
	
	private void initializeList(){
		for(int i = 0; i < this.freeSpace; i++)
			this.add(new Page(-1, "."));
	}
	
	/**public Page usePage(Process process){
		Page page = new Page(freePageIndex, process.getName());
		this.set(freePageIndex, page);
		this.freeSpace--;
		freePageIndex++;
		return page;
	}*/
	
	
	public Page usePage(Process process, int index){
		this.set(index, new Page(index, process.getName()));
		this.freeSpace--;
		return new Page(index, process.getName());
	}
	
	//clean up the finished frames
	public void clean(int index){
		this.set(index, new Page(index, "."));
	}
	
	
	// if there is enough free space
	public boolean hasEnoughSpace(){
		return (this.freeSpace >= 4);
	}
	
	public boolean hasFreePage() {
		return this.freeSpace >= 1;
	}
	
	public int generateLoR(int index) {
		int pageReference;
		int j;
		int first = rand.nextInt(10);
		if (first >= 0 && first <= 6)
			pageReference = index + deltaI[rand.nextInt(3)];
		else {
			j = rand.nextInt(8) + 2;
			pageReference = index + rand.nextInt(j+1);
		}
		return pageReference;
	}
}
