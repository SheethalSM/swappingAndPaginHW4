import java.util.LinkedList;

//most frequently used
public class MFU implements PickReplacementAlgorithm{

	String name;
	
	/**constructor of MFU object*/
	public MFU(String name1)
	{
		name = name1;
	}
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public String run(LinkedList<Page> replacementList, LinkedList<Page> PageQueue) {
		// TODO Auto-generated method stub
		return null;
	}

}
