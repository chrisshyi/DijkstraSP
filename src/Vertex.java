import java.util.Objects;

public class Vertex implements Comparable<Vertex> {

    private int label;
    private int dijkstraScore;

    public Vertex(int label, int dijkstraScore) {
        this.label = label;
        this.dijkstraScore = dijkstraScore;
    }

    public int getLabel() {
        return label;
    }

    public int getDijkstraScore() {
        return dijkstraScore;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertex vertex = (Vertex) o;
        return label == vertex.label;
    }

    @Override
    public int hashCode() {

        return Objects.hash(label);
    }

    @Override
    public int compareTo(Vertex o) {
        if (this.dijkstraScore < o.dijkstraScore) {
            return -1;
        } else if (this.dijkstraScore == o.dijkstraScore) {
            return 0;
        }
        return 1;
    }


}
