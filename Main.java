import java.util.*;
import java.io.*;

class Main  {
	public static void main(String[] args)  {
		Scanner sc = new Scanner(System.in);
		
		// Create nodes
		Node mumbai = new Node("India_Mumbai");
		Node chennai = new Node("India_Chennai");
		/* India - Mumbai, Chennai, China - HongKong, Chongming
		Singapore, Malaysia, Korea, USA */
		
		HashMap<String, Node> hm = new HashMap();
		hm.add("India_Mumbai", mumbai);
		hm.add("India_Chennai", chennai);

		Node srcNode, dstnNode;
		int dist;
		String[] arr;
		String line;

		File myObj = new File("connections.txt");
		Scanner myReader = new Scanner(myObj);
		while (myReader.hasNextLine())  {
			line = myReader.nextLine();
			arr = line.split(" - ");
			srcNode = hm.get(arr[0]);
			dstnNode = hm.get(arr[1]);
			dist = Integer.valueOf(arr[2]);
			srcNode.connect(dstnNode, dist);
		}
		myReader.close();
		
		// Take user input
		String src = "India_Mumbai";
		String dstn = "Singapore";
		srcNode = hm.get(src);
		dstnNode = hm.get(dstn);
		
		RouteFinder finder = new RouteFinder();
		finder.find(src, dstn);  // prints best route 
	}
}

class Node  {
	String name;
	Node()  { }
	Node(String name)  {
		this.name = name;
	}
	
	ArrayList connections = new ArrayList();
	void connect(Node dstn, int distance)  {
		connections.add(dstn);
	}
}
