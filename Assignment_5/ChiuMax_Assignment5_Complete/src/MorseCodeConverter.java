import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class MorseCodeConverter {
	
	private static MorseCodeTree t = new MorseCodeTree();

	public static String convertToEnglish(File inputFile) throws FileNotFoundException {
		Scanner s = new Scanner(inputFile);
		return convertToEnglish(s.nextLine());
	}

	public static String convertToEnglish(String string) {
		string += " ";
		String translated = "";
		String code = "";
		for(String c : string.split("")) {
			if(c.equals("/")) {
				translated += " ";
			}
			else if(c.equals(" ")) {
				if(code.length()!=0) {
					translated += t.fetch(code);
					code = "";
				}
			} else {
				code += c;
			}
		}
		return translated;
	}

	public static String printTree() {
		String o = "";
		ArrayList<String> arr = t.toArrayList();
		for (String s: arr) {
			o += s + " ";
			System.out.println(s);
		}
		return o;
	}

}
