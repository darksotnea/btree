import java.util.concurrent.atomic.AtomicInteger;

public class BTree <T extends Comparable<T>> implements IBTree<T> {
    private IBTree left = null;
    private IBTree right = null;
    private AtomicInteger count = new AtomicInteger(1);
    private T value;
}
