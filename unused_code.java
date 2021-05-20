import java.util.HashMap;

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