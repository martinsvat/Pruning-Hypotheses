package ida.searchPruning.search.collections;

import java.util.PriorityQueue;

/**
 * Created by Admin on 03.05.2017.
 */
public class QueueCollection<T> implements MyCollection<T> {

    private final PriorityQueue<T> queue;

    public QueueCollection() {
        this.queue = new PriorityQueue<T>();
    }

    @Override
    public void add(T t) {
        this.queue.add(t);
    }

    @Override
    public T removeFirst() {
        return this.queue.poll();
    }

    public int size(){
        return queue.size();
    }

    public boolean isEmpty(){
        return queue.isEmpty();
    }
}
