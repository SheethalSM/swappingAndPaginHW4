import java.util.LinkedList;

//least recently used
public class LRU implements PickReplacementAlgorithm{

	String name;
	public LRU(String name1)
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
