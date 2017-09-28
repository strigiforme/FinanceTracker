import java.util.Scanner;
import java.util.ArrayList;

public class LatticePath {
	public static void main(String args[]) {
		Scanner kb = new Scanner(System.in);
		
		System.out.print("Enter the size of the lattice: ");
		int width = kb.nextInt();
		
		//Every path must be 2 * width long
		//Every path is 1/2 N and 1/2 E, therefore we can limit every statement
		
		ArrayList<String> paths = new ArrayList<String>();
		
		ArrayList<Character> tempPath = new ArrayList<Character>(width*2);
		for(int f = 0; f < width * 2; f++) {
			tempPath.add(f,'E');
		}
		
	
		
		//The following for statements handle the case where we travel horizontally without going up until we can't
		for(int i = 0; i <= width; i++) {
			//Reset array to be full of E's
			for(int f = 0; f < width * 2; f++) {
				tempPath.add(f,'E');
			}
			//Fill in new N's
			for(int x = 0; x < width; x++) {
				tempPath.set(x+i,'N');
			}
			//Add temp char array to list of paths
			char[] temp = new char[width * 2];
			//No way to convert from Character array to Char array, so I do it myself
			for(int f = 0; f < width * 2; f++) {
				temp[f] = tempPath.get(f);
			}
			paths.add(String.valueOf(temp));
		}
		
		//In the following we add spaces between our N directions
		for(int i = 0; i <= width; i++) {
			//Reset array to be full of E's
			for(int f = 0; f < width * 2; f++) {
				tempPath.add(f,'E');
			}
			//Fill in new N's
			for(int x = 0; x <= width + 1; x++) {
				if(x == width)
					tempPath.set(x+i-1, 'N');
				else
					tempPath.set(x+i,'N');
			}
			//tempPath.set(x+i+1, 'E');
			//Add temp char array to list of paths
			char[] temp = new char[width * 2];
			//No way to convert from Character array to Char array, so I do it myself
			for(int f = 0; f < width * 2; f++) {
				temp[f] = tempPath.get(f);
			}
			paths.add(String.valueOf(temp));
		}
		
		//show all paths generated
		System.out.println(paths.toString());
		
	}
}
