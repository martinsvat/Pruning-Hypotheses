package ida.searchPruning.search.collections;

/**
 * Created by Admin on 03.05.2017.
 */
public interface MyCollection<T> {
    public void add(T t);
    public T removeFirst();
    public int size();

    boolean isEmpty();
}
