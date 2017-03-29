/**
 * 
 * @author SHEETHAL
 *  Structure of 
 */
public class Page {
    int pageNumber;
    String processName;
    int referencedTime;
  
   /**
   * Constructor that creates a page
   * @param PageNumber is the number of a page
   * @param ProcessName is the name of a process
  */
	public Page(int PageNumber, String ProcessName) {
		pageNumber = PageNumber;
		processName = ProcessName;
		referencedTime = 0;
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
}
