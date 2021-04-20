import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

public class CourseDBManager implements CourseDBManagerInterface {
	public CourseDBStructure cds = new CourseDBStructure(20);

	@Override
	public void add(String id, int crn, int credits, String roomNum, String instructor) {
		cds.add(new CourseDBElement(id, crn, credits, roomNum, instructor));
	}

	@Override
	public CourseDBElement get(int crn) {
		return cds.get(crn);
	}

	@Override
	public void readFile(File input){
		try {
			BufferedReader br = new BufferedReader(new FileReader(input));
			String st;
			while((st=br.readLine())!=null) {
				System.out.println(st);	
			}
			
		} catch(Exception e) {
			System.out.println(e);
		}
		
	}

	@Override
	public ArrayList<String> showAll() {
		ArrayList<String> ret = new ArrayList<String>();
		for(LinkedList<CourseDBElement> i : cds.hashTable) {
			for(Object j : i.toArray()) {
				ret.add(((CourseDBElement)j).toString());
			}
		}
		return ret;
	}

}
