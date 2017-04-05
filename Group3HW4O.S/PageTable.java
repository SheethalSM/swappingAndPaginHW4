import java.util.LinkedList;

public class PageTable extends LinkedList<Page>{
	private int freeSpace;
	int freePageIndex = 0;
		
	public PageTable(int free) {
		this.freeSpace = free;
		initializeList();
	}
	
	private void initializeList(){
		for(int i = 0; i < this.freeSpace; i++)
			this.add(new Page(-1, "."));
	}
	
	public Page usePage(Process process){
		Page page = new Page(freePageIndex, process.getName());
		this.set(freePageIndex, page);
		this.freeSpace--;
		freePageIndex++;
		return page;
	}
	
	
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
	
	/*
	public String toString(){
		StringBuilder builder = new StringBuilder();
		for(int i = 0; i < this.freeSpace; i++){
			builder.append(this.get(i).getPName());
		}
		/*
		for(int i = 0; i < this.freeSpace; i++){
			builder.append(this.get(i) + "\n");
		}
		return builder.toString();
	}
*/
}
