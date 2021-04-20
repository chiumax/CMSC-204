import java.util.ArrayList;
import java.util.LinkedList;

public class CourseDBStructure implements CourseDBStructureInterface {

	public LinkedList<CourseDBElement>[] hashTable;

	public CourseDBStructure(String string, int i) {
		hashTable = new LinkedList[i];
		for (int j = 0; j < i; j++) {
			hashTable[j] = new LinkedList<CourseDBElement>();
		}
	}

	public CourseDBStructure(int i) {
		hashTable = new LinkedList[i];
		for (int j = 0; j < i; j++) {
			hashTable[j] = new LinkedList<CourseDBElement>();
		}
	}

	@Override
	public void add(CourseDBElement element) {
		element.hashcode = element.crn % hashTable.length;
		hashTable[element.hashCode()%hashTable.length].add(element);
	}

	@Override
	public CourseDBElement get(int crn) {
		return hashTable[crn%hashTable.length].poll();
	}

	@Override
	public int getTableSize() {
		return hashTable.length;
	}

}
