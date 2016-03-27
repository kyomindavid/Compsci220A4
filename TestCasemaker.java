//Made by Vinny Polehwidhi
import java.io.*;

public class TestCaseMaker {;
	public static void main(String[] args) throws IOException {
		
		//set output file name
		String outputName = "testCase.in";
		
		//set number of nodes in this graph
		//int nodes = Integer.parseInt(args[0]);
		int nodes = 20;
		
		//set starting node
		//int start = Integer.parseInt(args[1]);
		int start = 7;
		
		//set target node
		//int target = Integer.parseInt(args[2]);
		int target = 13;
		
		//set true to generate a random path
		//that guarantees a route from start to target
		boolean path = true;
		
		//set true to generate random connections
		//enabling this means that expected output may not be accurate
		boolean random = false;
		
		//max/min number of connections you want on your connections
		//set setRand = true to use this feature
		boolean setRand = false;
		int randMax = 10;
		int randMin = 0;
		
		//set true to generate random connections 
		//that will not lead to target node
		boolean randomJunk = false;
		
		//set true to generate a loop from start and back to start
		boolean loopGen = false;
		
		String[] hasOut = new String[nodes];
		
		for(int x = 0; x < nodes; x++) {
			hasOut[x] = "";
		}
		
		//Start generating output
		PrintWriter output = new PrintWriter(outputName);
		
		output.println(nodes + "");

		//Make random connections
		if(random){
			for(int i = 0; i < nodes; i++) {
				if(!setRand) {
					randMax = nodes;
					randMin = 0;
				}
				
				int outDegrees = randMin + (int)(Math.random()*randMax);
				for(int j = 0; j < outDegrees; j++) {
					int rand = (int)(Math.random()*nodes);
					hasOut[i] += (rand + " ");
				}
			}
		}
		
		//Make random junk connections (will not lead to target)
		if(randomJunk){
			for(int i = 0; i < nodes; i++) {
				if(!setRand) {
					randMax = nodes;
					randMin = 0;
				}
				int outDegrees = randMin + (int)(Math.random()*randMax);
				for(int j = 0; j < outDegrees; j++) {
					int rand = (int)(Math.random()*nodes);
					if(rand != target){
						hasOut[i] += (rand + " ");
					}
				}
			}
		}
		
		//Make a random guaranteed path
		if(path) {
			int curNode = start;
			while(curNode != target){
				int newNode = (int)(Math.random()*nodes);
				hasOut[curNode] += newNode + " ";
				curNode = newNode;
			}
		}
		
		//Generate a loop
		if(loopGen) {
			int curNode = (int)(Math.random()*nodes);
			hasOut[start] += curNode + " ";
			while(curNode != start) {
				int newNode = (int)(Math.random()*nodes);
				if(newNode != target) {
					hasOut[curNode] += newNode + " ";
					curNode = newNode;
				}
			}
		}
		
		//Print hasOut array
		for(int k = 0; k < nodes; k++){
			output.println(hasOut[k]);
		}
		
		//Print closing information
		output.println(start + " " + target);
		output.println("0");
		output.close();

		System.out.print("Expected output: s = " + start + "; t = " + target + "; ");
		if(path){
			System.out.println("yes");
		} else {
			System.out.println("no");
		}
	}
}