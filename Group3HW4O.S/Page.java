/**
 * 
 * @author SHEETHAL
 *  Structure of 
 */
public class Page {
    int pageNumber;// the page number in main memory or for each process? 
    String  processName;
    int referencedTime;
    int recentlyUsedTime;
  
   /**
   * Constructor that creates a page
   * @param PageNumber is the number of a page
   * @param ProcessName is the name of a process
  */
	public Page(int PageNumber, String ProcessName) {
		pageNumber = PageNumber;
		processName = ProcessName;
		referencedTime = 0;
		recentlyUsedTime=-1;
	}
	
	/**
	 * method counts the number of times process was referenced
	 * this is helpful in finding the least frequently used, etc.
	 */
	public void referencedTime()
	{
		referencedTime++;
	}
	
	/**
	 * 
	 * @param number, page number assigned to keep track
	 */
	public void setPageNumber(int number)
	{
		pageNumber = number;
	}
	/**
	 * 
	 * @param pName, name of each process, only one per directory.
	 */
	public void setProcessName(String pName)
	{
		processName=pName;
	}
	
	public void setRecentlyUsedTime(int time){recentlyUsedTime= time;
	}
}
