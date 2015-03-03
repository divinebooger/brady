import java.util.Set; /* java.util.Set needed only for challenge problem. */
import java.util.Iterator;
import java.util.NoSuchElementException;
/** A data structure that uses a linked list to store pairs of keys and values.
 *  Any key must appear at most once in the dictionary, but values may appear multiple
 *  times. Supports get(key), put(key, value), and contains(key) methods. The value
 *  associated to a key is the value in the last call to put with that key. 
 *
 *  For simplicity, you may assume that nobody ever inserts a null key or value
 *  into your map.
 */ 
public class ULLMap<DatKey,DatVal> implements Map61B<DatKey, DatVal>, Iterable<DatKey>{ //FIX ME
    /** Keys and values are stored in a linked list of Entry objects.
      * This variable stores the first pair in this linked list. You may
      * point this at a sentinel node, or use it as a the actual front item
      * of the linked list. 
      */
    private Entry front;
    private int size = 0;

    @Override
    public DatVal get(DatKey key) { //FIX ME
        Entry g = front.get(key);
        if (g.equals(null)) return null;
        else return g.val;
    }

    @Override
    public void put(DatKey key, DatVal val) { //FIX ME
    //FILL ME IN
        for (Entry x = front; x != null; x = x.next){
            if (key.equals(x.key)) {x.val = val; return;}
        }
        front = new Entry(key, val, front);
        size += 1;
    }

    @Override
    public boolean containsKey(DatKey key) { //FIX ME
    //FILL ME IN
        for (Entry x = front; x != null; x = x.next) {
            if (key.equals(x.key)) {return true;}
        }
        return false; //FIX ME
    }

    @Override
    public int size() {
        return size; // FIX ME (you can add extra instance variables if you want)
    }

    @Override
    public void clear() {
        front.val = null;
        front.next = null;
    }


    /** Represents one node in the linked list that stores the key-value pairs
     *  in the dictionary. */
    private class Entry {
    
        /** Stores KEY as the key in this key-value pair, VAL as the value, and
         *  NEXT as the next node in the linked list. */
        public Entry(DatKey k, DatVal v, Entry n) { //FIX ME
            key = k;
            val = v;
            next = n;
        }

        /** Returns the Entry in this linked list of key-value pairs whose key
         *  is equal to KEY, or null if no such Entry exists. */
        public Entry get(DatKey k) { //FIX ME
            //FILL ME IN (using equals, not ==)
            for (Entry x = this; x != null; x = x.next) {
                if (key.equals(x.key)) return x;
            }
            return null; //FIX ME
        }

        /** Stores the key of the key-value pair of this node in the list. */
        private DatKey key; //FIX ME
        /** Stores the value of the key-value pair of this node in the list. */
        private DatVal val; //FIX ME
        /** Stores the next Entry in the linked list. */
        private Entry next;
    
    }

    private class ULLMapIter implements Iterator<DatKey>{
        private Entry master;

        public ULLMapIter(){
            master = front;
        }

        @Override
        public boolean hasNext() {
            if (master != null){
                return true;
            }
            return false;
        }

        @Override
        public DatKey next(){
            if (hasNext()) {
                DatKey itemToReturn = master.key;
                master = master.next;
                return itemToReturn;
            }
            throw new NoSuchElementException();
        }

        public void remove(){
            throw new UnsupportedOperationException();
        }
    }

    public Iterator<DatKey> iterator(){
        return new ULLMapIter();
    }

    public static <U,V> ULLMap<U,V> invert(ULLMap<V,U> disMap){
        ULLMap<U,V> inverted = new ULLMap<U, V>();
        Iterator<V> disIter = disMap.iterator();
        while (disIter.hasNext()){
            V tempKey = disIter.next();
            inverted.put(disMap.get(tempKey), tempKey);
        }
        return inverted;
    }

    /* Methods below are all challenge problems. Will not be graded in any way. 
     * Autograder will not test these. */
    @Override
    public DatVal remove(DatKey key) { //FIX ME SO I COMPILE
        throw new UnsupportedOperationException();
    }

    @Override
    public DatVal remove(DatKey key, DatVal value) { //FIX ME SO I COMPILE
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<DatKey> keySet() { //FIX ME SO I COMPILE
        throw new UnsupportedOperationException();
    }


}