import java.util.concurrent.atomic.AtomicInteger;

public class BTree <T extends Comparable<T>> implements IBTree<T> {
    private IBTree<T> left = null;
    private IBTree<T> right = null;
    private AtomicInteger count = new AtomicInteger(1);
    private T value;

    public BTree(T value) {
        this.value = value;
    }

    @Override
    public IBTree<T> getLeft() {
        return left;
    }

    @Override
    public IBTree<T> getRight() {
        return right;
    }

    @Override
    public T getValue() {
        return this.value;
    }

    @Override
    public AtomicInteger getCount() {
        return count;
    }

    @Override
    public void add(T value) {
        if (value.equals(getValue())) {
            this.count.incrementAndGet();
        } else if (value.compareTo(getValue()) < 0) {
            addLeft(value);
        } else {
            addRight(value);
        }
    }

    private void addLeft(T value){
        if(getLeft() != null) {
            getLeft().add(value);
        } else {
            setLeft(new BTree(value));
        }
    }

    private void addRight(T value) {
        if(getRight() != null) {
            getRight().add(value);
        } else {
            setRight(new BTree(value));
        }
    }

    private void setLeft(IBTree<T> left) {
        this.left = left;
    }

    private void setRight(IBTree<T> right) {
        this.right = right;
    }

/*    public void printValues() {
        if (this.left != null) {
            left.printValues();
        }
        if (right != null) {
            right.printValues();
        }

        System.out.println("Значение " + getValue() + ", колличество: " + getCount());
    }*/

    @Override
    public void foreach(Process<T> process) {
        new Thread(){
            @Override
            public void run() {
                process.process(getValue());
            }
        }.start();
        if (getLeft() != null) {
            getLeft().foreach(process);
        }
        if (getRight() != null) {
            getRight().foreach(process);
        }
    }

    public static void main(String[] args) {
        BTree<Integer> integerBTree = new BTree<>(5);
        for (int i = 0; i < 10; i++) {
            int a = (int)(Math.random()* 10);
            integerBTree.add(a);
        }
        Process<Integer> p = new Process<Integer>() {
            @Override
            public void process(Integer value) {
                System.out.println("Значение : " + value);
            }
        };
        integerBTree.foreach(p);
    }
}
