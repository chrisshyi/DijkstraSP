import java.util.Objects;

public class Edge {
    private int tail;
    private int head;

    public Edge(int tail, int head) {
        this.tail = tail;
        this.head = head;
    }

    public int getTail() {
        return tail;
    }

    public int getHead() {
        return head;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge edge = (Edge) o;
        return tail == edge.tail &&
                head == edge.head;
    }

    @Override
    public int hashCode() {

        return Objects.hash(tail, head);
    }
}
