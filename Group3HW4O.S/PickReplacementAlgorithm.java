import java.util.LinkedList;

public interface PickReplacementAlgorithm {
	String getName();
	String run(LinkedList<Page> replacementList, LinkedList<Page> PageQueue);
}
