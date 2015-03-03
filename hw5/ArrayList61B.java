import java.util.AbstractList;
import java.lang.IllegalArgumentException;


public class ArrayList61B<E> extends AbstractList<E>{
	int capacity;
	E[] disArray;
	int size;
	
	public ArrayList61B(int initialCapacity){
		if (initialCapacity < 1){
			throw new IllegalArgumentException();
		}
		capacity = initialCapacity;
		disArray = (E[]) new Object[capacity];
		size = 0;
	}

	public ArrayList61B(){
		capacity = 1;
		disArray = (E[]) new Object[capacity];
		size = 0;
	}

	public E get(int i){
		if (i < 0 || i >= size) {
			throw new IllegalArgumentException();
		}
		return disArray[i];
	}

	public boolean add(E item){
		if (size >= capacity){
			capacity = 2 * capacity;
			E[] datArray = (E[]) new Object[capacity];
			for (int i = 0; i < size; i+=1){
				datArray[i] = disArray[i];
			}
			disArray = datArray;
		}
		disArray[size] = item;
		size += 1;
		return true;
	}

	public int size(){
		return size;
	}
}