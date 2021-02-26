
public class ArraySum {
	public ArraySum() {
		
	}
	
	public int sumOfArray(Integer[] a, int index) {
		if(index==0) {
			return a[0];
		}
		return a[index] + sumOfArray(a, --index);
	}
}	
