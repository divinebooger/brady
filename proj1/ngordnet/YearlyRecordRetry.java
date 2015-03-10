package ngordnet;
import java.util.*;
public class YearlyRecordRetry {
	private int size;
	private HashMap<String,Integer> table;
	private HashMap<Integer,ArrayList<String>> elbat;
	private TreeMap<String,Number> sortedMap;
	    public YearlyRecordRetry(){
	    	size = 0;
	    	table = new HashMap<String,Integer>();
	    	elbat = new HashMap<Integer,ArrayList<String>>();
	    	sortedMap = new TreeMap<String,Number>(new Comparator<String>() {
	    		@Override
	    		public int compare(String string1, String string2){
	    			if (table.get(string1).doubleValue() < table.get(string2).doubleValue())
					    return -1;
					if (table.get(string1).doubleValue() > table.get(string2).doubleValue())
					    return 1;
					return 0;
				}
	    	});
	    }

	    /** Creates a YearlyRecord using the given data. */
	    public YearlyRecordRetry(HashMap<String, Integer> otherCountMap){
	    	table = otherCountMap;
	    	size = table.size();
	    	elbat = new HashMap<Integer,ArrayList<String>>();
	    	sortedMap = new TreeMap<String,Number>(new Comparator<String>() {
	    		@Override
	    		public int compare(String string1, String string2){
	    			if (table.get(string1).doubleValue() < table.get(string2).doubleValue())
					    return -1;
					if (table.get(string1).doubleValue() > table.get(string2).doubleValue())
					    return 1;
					return 0;
				}
	    	});
	    	for (String key : table.keySet()){
	    		sortedMap.put(key, table.get(key));
	    	}

	    	for (String key : otherCountMap.keySet()){
	    		Integer value = otherCountMap.get(key);
	    		if (elbat.get(value) == null){
	    			elbat.put(value, new ArrayList<String>());
	    		}
	    		elbat.get(this.table.get(key)).add(key);
	    	}
	    }


	    /** Returns the number of times WORD appeared in this year. */
	    public int count(String word) {
	    	return table.get(word);
	    }

	    /** Records that WORD occurred COUNT times in this year. */
	    public void put(String word, int count) {
	    	size += 1;
	    	table.put(word, count);
	    	sortedMap.put(word,count);
	    }

	    /** Returns the number of words recorded this year. */
	    public int size(){
	    	return size;
	    }

	    /** Returns all words in ascending order of count. */
	    public Collection<String> words() {
	    	return sortedMap.keySet();
	    }


	    /** Returns all counts in ascending order of count. */
	    public Collection<Number> counts() {
	    	return sortedMap.values();
	    }

	    /** Returns rank of WORD. Most common word is rank 1. 
	      * If two words have the same rank, break ties arbitrarily. 
	      * No two words should have the same rank.
	      */
	    public int rank(String word) {
	    	List<String> disList = new ArrayList<String>(sortedMap.keySet());
	    	return size - disList.indexOf(word);
	 	}
	} 