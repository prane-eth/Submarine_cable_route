import java.util.*;
import java.io.*;

class RouteFinder	{
	static int[][] arr;
	public static void main(String[] args)	{
		boolean status = read_data();
		if (status != true)
			return;
		HashMap<Integer, String> hm = new HashMap<Integer, String>();
		hm.put(0, "India_Mumbai");
		hm.put(1, "India_Chennai");
		hm.put(2, "Singapore");
		hm.put(3, "Japan");
		hm.put(4, "USA");
		DijShortestPath s = new DijShortestPath(arr, hm);
		s.process();
	}
	static boolean read_data()	{
		try {
			Scanner sc = new Scanner(new File("matrix.txt"));
			int rows = 0;
			int columns = 0;
			while (sc.hasNextLine()) {
				rows++;
				Scanner colReader = new Scanner(sc.nextLine());
				while (colReader.hasNextInt())
					columns++;
			}
			int[][] arr = new int[rows][columns];
			sc.close();

			// read in the data
			sc = new Scanner(new File("array.txt"));
			for (int i = 0; i < rows; i++)
				for (int j = 0; j < columns; j++)
					if (sc.hasNextInt())
						arr[i][j] = sc.nextInt();
			return true;
		} catch (FileNotFoundException e) {
			System.out.println("Sorry. File not found");
			return false;
		}
	}
}


class DijShortestPath {
	int[][] graph;
	int V;
	HashMap<Integer, String> hm;

	DijShortestPath(int[][] graph, HashMap hm) {
		this.graph = graph;
		V = graph.length;
		this.hm = hm;
	}
	int minDistance(int dist[], Boolean sptSet[]) {
		int min = Integer.MAX_VALUE, min_index = -1;
		for (int v = 0; v < V; v++)
			if (sptSet[v] == false && dist[v] <= min) {
				min = dist[v];
				min_index = v;
			}
		return min_index;
	}
	void process() {
		int dist[] = new int[V];
		Boolean sptSet[] = new Boolean[V];
		for (int i = 0; i < V; i++) {
			dist[i] = Integer.MAX_VALUE;
			sptSet[i] = false;
		}
		dist[0] = 0;
		for (int count = 0; count < V - 1; count++) {
			int u = minDistance(dist, sptSet);
			sptSet[u] = true;
			for (int v = 0; v < V; v++)
				if (!sptSet[v] && graph[u][v] != 0
						&& dist[u] != Integer.MAX_VALUE
						&& dist[u] + graph[u][v] < dist[v])
					dist[v] = dist[u] + graph[u][v];
		}
		System.out.println("City \t\t Vertex \t\t Distance from Source");
		for (int i = 0; i < V; i++)
			System.out.println(hm.get(i) + " \t\t " + i + " \t\t " + dist[i]);
	}
}