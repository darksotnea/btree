import java.util.concurrent.atomic.AtomicInteger;

public interface IBTree <T extends Comparable<T>> {

    public IBTree<T> getLeft();

    public IBTree<T> getRight();

    public T getValue();

    public AtomicInteger getCount();

    public void add(T value);
}
