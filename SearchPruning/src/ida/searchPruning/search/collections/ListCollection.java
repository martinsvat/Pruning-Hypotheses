package ida.searchPruning.search.collections;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 03.05.2017.
 */
public class ListCollection<T> implements MyCollection<T> {
    private final List<T> list;

    public ListCollection() {
        this.list = new ArrayList<T>();
    }

    @Override
    public void add(T t) {
        list.add(t);
    }

    @Override
    public T removeFirst() {
        return list.remove(0);
    }

    public int size(){
        return list.size();
    }

    public boolean isEmpty(){
        return list.isEmpty();
    }
}
