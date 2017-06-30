package ida.searchPruning.search.collections;

import ida.ilp.logic.Clause;
import ida.ilp.logic.HornClause;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * not thread-safe
 *
 * Created by Admin on 03.05.2017.
 */
public class CollectionWithMemory<T extends SearchNodeInfo> {

    private MyCollection<T> collection;
    private final int beamWidth;
    private final Map<T,T> inside;

    public CollectionWithMemory(MyCollection collection, int beamWidth ) {
        this.collection = collection;
        this.beamWidth = beamWidth;
        this.inside = new HashMap<>();
    }

    public CollectionWithMemory(MyCollection collection) {
        this(collection,-1);
    }

    /**
     * Adds t into the collection.
     *
     * @param t
     */
    public void push(T t){
        /*if(t instanceof SearchNodeInfo){
            SearchNodeInfo info = (SearchNodeInfo) t;
            if(Double.isInfinite(info.getAccuracy())){
                return;
            }
        }*/
        if(!inside.containsKey(t)){
            forcePush(t);
        }else{
            T node = inside.get(t);
            node.update(t);
        }
    }

    public void forcePush(T t) {
        inside.put(t,t);
        collection.add(t);
    }

    public void checkSize(){
        if(beamWidth > 0 && collection.size() > beamWidth){
            // hardcoded for queue only
            QueueCollection<T> trimmed = new QueueCollection<T>();
            for (int idx = 0; idx < beamWidth; idx++) {
                trimmed.add(collection.removeFirst());
            }
            this.collection = trimmed;
        }
    }

    public boolean hasContained(T t){
        return inside.containsKey(t);
    }

    public T poll() {
        return collection.removeFirst();
    }

    public boolean isNotEmpty() {
        return !collection.isEmpty();
    }

    public Set<T> getMemory() {
        return inside.keySet();
    }

    public void addMemory(Set<T> outerMemory) {
        // assuming this is done only at the beginning
        outerMemory.forEach(t -> inside.put(t,t));
    }

    public int size() {
        return this.collection.size();
    }


    public static void main(String[] args){
        CollectionWithMemory<SearchNodeInfo> queue = new CollectionWithMemory<>(new QueueCollection<SearchNodeInfo>());
        queue.push(new SearchNodeInfo(new HashSet<Integer>(),new HashSet<Integer>(),new HornClause(Clause.parse("q(Y),p(X)")),1.0,false,new HashSet<Integer>()));
        queue.push(new SearchNodeInfo(new HashSet<Integer>(),new HashSet<Integer>(),new HornClause(Clause.parse("p(X,Y)")),0.0,true,new HashSet<Integer>()));
        queue.push(new SearchNodeInfo(new HashSet<Integer>(),new HashSet<Integer>(),new HornClause(Clause.parse("q(X,Y)")),0.0,false,new HashSet<Integer>()));
        queue.push(new SearchNodeInfo(new HashSet<Integer>(),new HashSet<Integer>(),new HornClause(Clause.parse("p(X)")),1.0,true,new HashSet<Integer>()));

        while(queue.isNotEmpty()){
            System.out.println(queue.size() + " " + queue.poll());
        }
    }
}
