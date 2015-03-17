import java.util.Set;
import java.util.HashSet;

public class BSTMap<K extends Comparable, V> implements Map61B<K, V>{
    private int size;
    private Entry _list;
    private HashSet<K> keyList = new HashSet<K>();

    public void clear() {
        size = 0;
        _list = null;
        keyList = new HashSet<K>();
    }

    @Override
    public boolean containsKey(K key) {
        if (_list == null) return false;
        return _list.get(key) != null;
    }

    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key. 
     */
    public V get(K key) {
        if (_list == null) return null;
        Entry lookup = _list.get(key);
        if (lookup == null) return null;
        return lookup._val;
    }

   /* Returns the number of key-value mappings in this map. */
    public int size(){
        return size;
    }

    /* Associates the specified value with the specified key in this map. */
    public void put(K key, V value) {
        if (_list == null) {
            _list = insert(_list, key, value);
            size = size + 1;
            keyList.add(key);
        }
        else if (_list.get(key) == null) {
            _list = insert(_list, key, value);
            size = size + 1;
            keyList.add(key);
        } else {
            _list.get(key)._val = value;
        }
    }

    private Entry insert (Entry t, K dk, V dv) {
        if (t == null) {
            return new Entry(dk, dv, null, null);
        } else if (t._key.compareTo(dk) == 1) {
            t._left = insert(t._left, dk, dv);
        } else if (t._key.compareTo(dk) == -1) {
            t._right = insert(t._right, dk, dv);
        }
        return t;
    }

    private class Entry {
        private K _key;
        private V _val;
        private Entry _left;
        private Entry _right;
        public Entry(K key, V val, Entry left, Entry right) {
            _key = key;
            _val = val;
            _left = left;
            _right = right;
        }
        public Entry get(K key) {
            if (key != null && key.equals(_key)) return this;
            if (_right != null && key.compareTo(_key) == 1) return _right.get(key);
            if (_left != null && key.compareTo(_key) == -1) return _left.get(key);
            return null;
        }
    }


    public V remove(K key) {
        return null;
    }

    public V remove(K key, V value){
        return null;
    }

    public Set<K> keySet(){
        return keyList;
    }

    public void printInOrder() {
        for (K keyz : keySet()) {
            System.out.println(get(keyz));
        }
    }

    public static void main(String[] args) {
        BSTMap<Integer, String> um = new BSTMap<Integer, String>();
        um.put(1,"poop");
        um.put(3,"soup");
        um.put(2,"cheese");
        System.out.println(um.keySet());
        um.printInOrder();
        System.out.println(um.containsKey(1));
        System.out.println(um.containsKey(4));
    }

}