import java.util.*;
import java.io.*;

class RouteFinder	{
	static int[][] arr;
	public static void main(String[] args)	{
		boolean status = read_data();
		if (status != true)
			return;
		HashMap<Integer, String> hm = new HashMap<Integer, String>();
		hm.put(0, "Mumbai");
		hm.put(1, "Chennai");
		hm.put(2, "Singapore");
		hm.put(3, "Japan");
		hm.put(4, "USA");
		// display all nodes from hashmap
		System.out.println("");
		for (int num: hm.keySet())
            System.out.println("\t" + num + " - " + hm.get(num));
		System.out.println("");

		DijShortestPath s = new DijShortestPath(arr, hm);
		s.process();
	}
	static boolean read_data() {
		try {
			Scanner sc = new Scanner(new File("matrix.txt"));
			int rows = 0;
			while (sc.hasNextLine()) {
				rows++;  // count rows
				sc.nextLine();
			}
			int columns = rows;
			arr = new int[rows][columns];
			sc.close();

			// read in the data
			sc = new Scanner(new File("matrix.txt"));
			for (int row = 0; row < rows; row++)
				for (int col = 0; col < columns; col++)
					if (sc.hasNextInt())
						arr[row][col] = sc.nextInt();
			sc.close();

			return true;
		} catch (Exception e) {
			System.out.println("Sorry... matrix file not found");
			return false;
		}
	}
}

class DijShortestPath {
	int NO_PARENT = -1;
	
	int[][] graph;
	int len;
	HashMap<Integer, String> hm;

	DijShortestPath(int[][] graph, HashMap<Integer, String> hm) {
		this.graph = graph;
		len = graph.length;
		this.hm = hm;
	}
	int minDistance(int[] dist, boolean[] sptSet) {
		int min = Integer.MAX_VALUE, min_index = -1;
		for (int v = 0; v < len; v++)
			if (sptSet[v] == false && dist[v] <= min) {
				min = dist[v];
				min_index = v;
			}
		return min_index;
	}
	void process() {
		int dist[] = new int[len];
		boolean sptSet[] = new boolean[len];
		for (int i = 0; i < len; i++) {
			dist[i] = Integer.MAX_VALUE;
			sptSet[i] = false;
		}
		dist[0] = 0;
		for (int count = 0; count < len-1; count++) {
			int u = minDistance(dist, sptSet);
			sptSet[u] = true;
			for (int v = 0; v < len; v++)
				if (!sptSet[v] && graph[u][v] != 0
						&& dist[u] != Integer.MAX_VALUE
						&& dist[u] + graph[u][v] < dist[v])
					dist[v] = dist[u] + graph[u][v];
		}
		System.out.println("City \t\t Vertex \t\t Distance from Source");
		for (int i = 0; i < len; i++)
			System.out.println(hm.get(i) + " \t\t "
				 + i + " \t\t " + dist[i]);
	}
}