import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;

/*
 * Compsci 220 Assignment 4
 * 
 * Name: Kyomin Ku
 * UPI: 5521999
 * 16.10.2014
 * 
 * You are given a maze, represented as a directed graph in standard adjacency lists format, a source node 's' and a target node 't'. 
 * This program reports whether a path from s to t (that is, a path out of the maze) exists.
 */

public class DFS {

	static InputStreamReader input = new InputStreamReader(System.in);
	static BufferedReader br = new BufferedReader(input);
	static boolean pathExists = false;
	static int orderNumber;
	
	private static ArrayList<ArrayList<Integer>> orderArrayList = new ArrayList<ArrayList<Integer>>();

	private static void execute() throws IOException {
		ArrayList<Integer> outDegreeArrayList;
		String line;
		
		String s = br.readLine();
		orderNumber = Integer.parseInt(s); 			// read the first number.
		
		for (int i = 0; i < orderNumber; i++) {						// read repetition ('orderNumber' times).
			line = br.readLine();        	

			if (line.trim().equals("")) {
				orderArrayList.add(null);

			} else {
				String[] outDegree = line.split("\\s+");
				outDegreeArrayList = new ArrayList<Integer>();

				for (int j = 0; j < outDegree.length; j++) {
					outDegreeArrayList.add(Integer.parseInt(outDegree[j])); 	// Assign to ArrayLists<Integer>.
				}
				orderArrayList.add(outDegreeArrayList);							// Assign to ArrayLists<ArrayLists<Integer>>.
			}
		}
		
		s = br.readLine();
		while (s == "") {
			s = br.readLine();
		}
		
		String[] path = s.split("\\s+");
		int pathStart = Integer.parseInt(path[0]);
		int pathEnd = Integer.parseInt(path[1]);					// read the starting node and the ending node.

		if (orderNumber == 0 || orderArrayList.size() <= pathStart || pathEnd >= orderNumber) {	
			output(pathExists, pathStart, pathEnd);							

		} else {
			working(orderArrayList, pathStart, pathEnd);
		}
	}

	private static void working(ArrayList<ArrayList<Integer>> orderArrayList, int pathStart, int pathEnd) {
		Stack<Integer> stack = new Stack<Integer>();
		boolean[] result = new boolean[orderNumber];
		
		result[pathStart] = true;
		stack.push(pathStart);
		int curr = stack.peek();
		
		while (!stack.isEmpty()) {
			if (result[pathEnd] == true) {
				break;
			}

			if (!(orderArrayList.get(curr) == null)) {
				if ((orderArrayList.get(curr).size() == 1) && (orderArrayList.get(curr).get(0) == curr)){
					break;
				} else {
					int loop = orderArrayList.get(curr).size();
					for (int i = 0; i < loop; i++){
						if (!result[orderArrayList.get(curr).get(i)] == true) {
							result[orderArrayList.get(curr).get(i)] = true;
							stack.push(orderArrayList.get(curr).get(i));
							break;

						} else {
							if (i == loop - 1) {
								stack.pop();
								break;
							} else {
								continue;
							}
						}
					}
				}

			} else {
				if(!stack.isEmpty()) {
					stack.pop();
				}
			}

			if (!stack.isEmpty()) {
				curr = stack.peek();	
			}
		}

		if (result[pathEnd] == true) {
			pathExists = true;
		}

		output(pathExists, pathStart, pathEnd);
	}


	private static void output(Boolean pathExists, int pathStart, int pathEnd) {
		System.out.print("s = " + pathStart + "; ");
		System.out.print("t = " + pathEnd + "; ");
		if (pathExists == true) {
			System.out.println("yes");
		} else {
			System.out.println("no");
		}
	}

	public static void main(String[] arguments) throws IOException {	
		//long startTime = System.currentTimeMillis();
		
		DFS.execute();
		
		//long endTime = System.currentTimeMillis();
		//long totalTime = endTime - startTime;
		//System.out.println(totalTime);
	}

}