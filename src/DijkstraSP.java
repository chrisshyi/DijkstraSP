import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class DijkstraSP {

    private Map<Edge, Integer> edgeWeights;
    private Map<Integer, Set<Integer>> graph;

    public DijkstraSP(File file) throws IOException {
        this.edgeWeights = new HashMap<>();
        this.graph = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                var splitLine = line.split("\t");
                var vertx = Integer.parseInt(splitLine[0]);
                for (int i = 1; i < splitLine.length; i++) {
                    var splitTuple = splitLine[i].split(",");
                    var neighbor = Integer.parseInt(splitTuple[0]);
                    var edgeWeight = Integer.parseInt(splitTuple[1]);

                    this.edgeWeights.put(new Edge(vertx, neighbor), edgeWeight);
                    if (!this.graph.containsKey(vertx)) {
                        this.graph.put(vertx, new HashSet<>());
                    }
                    this.graph.get(vertx).add(neighbor);
                }
            }
        }
    }

    public Map<Integer, Integer> calcShortestPaths(int sourceVertex) {
        PriorityQueue<Vertex> minHeap = new PriorityQueue<>();
        Map<Integer, Integer> heapScores = new HashMap<>();
        Map<Integer, Integer> shortestPathLengths = new HashMap<>();

        minHeap.add(new Vertex(sourceVertex, 0));
        heapScores.put(sourceVertex, 0);
        // hard coded 200 vertices for the input
        for (int i = 2; i <= 200; i++) {
            minHeap.add(new Vertex(i, Integer.MAX_VALUE));
            heapScores.put(i, Integer.MAX_VALUE);
        }
        while (minHeap.size() != 0) {
            System.out.printf("%d vertices left\n", minHeap.size());
            Vertex minVertx = minHeap.poll();

            shortestPathLengths.put(minVertx.getLabel(), minVertx.getDijkstraScore());
            for (var neighbor : graph.get(minVertx.getLabel())) {
                var oldScore = heapScores.get(neighbor);
                Edge outEdge = new Edge(minVertx.getLabel(), neighbor);

                var candidateScore = minVertx.getDijkstraScore() + this.edgeWeights.get(outEdge);
                if (candidateScore < oldScore) {
                    minHeap.remove(new Vertex(neighbor, 0));
                    minHeap.add(new Vertex(neighbor, candidateScore));
                    heapScores.put(neighbor, candidateScore);
                }
            }
        }
        return shortestPathLengths;
    }

    public static void main(String[] args) throws IOException {
        File file = new File("/home/chris/Workspace/java/DijkstraSP/dijkstraData.txt");
        var dijkstraSP = new DijkstraSP(file);

        var shortestPathLengths = dijkstraSP.calcShortestPaths(1);
        var toReport = List.of(7, 37, 59, 82, 99, 115, 133, 165, 188, 197);

        for (var vertx : toReport) {
            System.out.printf("The shortest path to vertex %d is %d\n", vertx, shortestPathLengths.get(vertx));
        }

    }
}
