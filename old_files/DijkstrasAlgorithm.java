
// A Java program for Dijkstra's
// single source shortest path
// algorithm. The program is for
// adjacency matrix representation
// of the graph.

class DijkstrasAlgorithm {

static int NO_PARENT = -1;

// Function that implements Dijkstra's
// single source shortest path
// algorithm for a graph represented
// using adjacency matrix representation
static void dijkstra(int[][] matrix,
                                    int startVertex)  {
    int numNodes = matrix[0].length;

    // shortestDists[i] will hold the
    // shortest distance from src to i
    int[] shortestDists = new int[numNodes];

    // added[i] will true if vertex i is
    // included / in shortest path tree
    // or shortest distance from src to
    // i is finalized
    boolean[] added = new boolean[numNodes];

    // Initialize all distances as
    // INFINITE and added[] as false
    for (int vertexIndex = 0;
            vertexIndex < numNodes; vertexIndex++) {
        shortestDists[vertexIndex] = Integer.MAX_VALUE;
        added[vertexIndex] = false;
    }
    
    // Distance of source vertex from itself is always 0
    shortestDists[startVertex] = 0;

    // Parent array to store shortest path tree
    int[] parentArr = new int[numNodes];

    // The starting vertex does not have a parent
    parentArr[startVertex] = NO_PARENT;

    // Find shortest path for all vertices
    for (int i = 1; i < numNodes; i++) {

        // Pick the minimum distance vertex
        // from the set of vertices not yet
        // processed. nearestVertex is
        // always equal to startNode in
        // first iteration.
        int nearestVertex = -1;
        int shortestDist = Integer.MAX_VALUE;
        for (int vertexIndex = 0;
                vertexIndex < numNodes;
                vertexIndex++)
        {
            if (!added[vertexIndex]
                    && shortestDists[vertexIndex] <
                    shortestDist) {
                nearestVertex = vertexIndex;
                shortestDist = shortestDists[vertexIndex];
            }
        }

        // Mark the picked vertex as processed
        added[nearestVertex] = true;

        // Update dist value of the adjacent
        // vertices of the picked vertex.
        for (int vertexIndex = 0;
                vertexIndex < numNodes;
                vertexIndex++)
        {
            int edgeDistance = matrix[nearestVertex][vertexIndex];
            
            if (edgeDistance > 0
                && ((shortestDist + edgeDistance) <
                    shortestDists[vertexIndex]))
            {
                parentArr[vertexIndex] = nearestVertex;
                shortestDists[vertexIndex] = shortestDist +
                                                edgeDistance;
            }
        }
    }

    printSolution(startVertex, shortestDists, parentArr);
}

// A utility function to print the constructed
// distances array and shortest paths
static void printSolution(int startVertex,
                                int[] distances,
                                int[] parentArr)
{
    int numNodes = distances.length;
    System.out.print("Vertex\t Distance\tPath");
    
    for (int vertexIndex = 0;
            vertexIndex < numNodes;
            vertexIndex++)
    {
        if (vertexIndex != startVertex)
        {
            System.out.print("\n" + startVertex + " -> ");
            System.out.print(vertexIndex + " \t\t ");
            System.out.print(distances[vertexIndex] + "\t\t");
            printPath(vertexIndex, parentArr);
        }
    }
}

// Function to print shortest path from 
// source to curNode using parentArr array
static void printPath(int curNode,
                            int[] parentArr) {
    // Source node has been processed
    if (curNode == NO_PARENT)
        return;
    printPath(parentArr[curNode], parentArr);
    System.out.print(curNode + " ");
}

public static void main(String[] args) {
    int[][] matrix = {
        { 0, 4, 0, 0, 0, 0, 0, 8, 0 },
        { 4, 0, 8, 0, 0, 0, 0, 11, 0 },
        { 0, 8, 0, 7, 0, 4, 0, 0, 2 },
        { 0, 0, 7, 0, 9, 14, 0, 0, 0 },
        { 0, 0, 0, 9, 0, 10, 0, 0, 0 },
        { 0, 0, 4, 0, 10, 0, 2, 0, 0 },
        { 0, 0, 0, 14, 0, 2, 0, 1, 6 },
        { 8, 11, 0, 0, 0, 0, 1, 0, 7 },
        { 0, 0, 2, 0, 0, 0, 6, 7, 0 }
    };
    dijkstra(matrix, 0);
}
}
