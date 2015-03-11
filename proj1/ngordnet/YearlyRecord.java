package ngordnet;
import java.util.*;
public class YearlyRecord {
	private int size;
	private HashMap<String,Integer> table;
	private boolean booWord;
	private boolean booCount;
	private LinkedList<String> wordTable;
	private LinkedList<Number> countTable;
	private HashMap<String, Integer> booTable;
	    public YearlyRecord(){
	    	size = 0;
	    	booWord = true;
	    	booCount = true;
	    	wordTable = new LinkedList<String>();
	    	countTable = new LinkedList<Number>();
	    	table = new HashMap<String,Integer>();
	    	booTable = null;
	    }

	    /** Creates a YearlyRecord using the given data. */
	    public YearlyRecord(HashMap<String, Integer> otherCountMap){
	    	table = otherCountMap;
	    	size = table.size();
	    	booWord = false;
	    	booCount = false;
	    	booTable = null;
	    }


	    /** Returns the number of times WORD appeared in this year. */
	    public int count(String word) {
	    	return table.get(word);
	    }

	    /** Records that WORD occurred COUNT times in this year. */
	    public void put(String word, int count) {
	    	size += 1;
	    	table.put(word, count);
	    	booCount = false;
	    	booWord = false;
	    	booTable = null;
	    }

	    /** Returns the number of words recorded this year. */
	    public int size(){
	    	return size;
	    }

	    /** Returns all words in ascending order of count. */
	    public Collection<String> words() {
	    	if (!booWord) {
	    		wordTable = new LinkedList<String>();
	    		for (String word : table.keySet()){
		    		wordTable.add(word);
		    	}
	    		Collections.sort(wordTable, new Comparator<String>() {
		    		@Override
		    		public int compare(String string1, String string2){
		    			if (table.get(string1).doubleValue() < table.get(string2).doubleValue())
						    return -1;
						if (table.get(string1).doubleValue() > table.get(string2).doubleValue())
						    return 1;
						return 0;
					}
		    	});
	    		booWord = true;
	    	}
	    	return wordTable;
	    }


	    /** Returns all counts in ascending order of count. */
	    public Collection<Number> counts() {
	    	if (!booCount){
	    		countTable = new LinkedList<Number>();
	    			for (Integer i : table.values()){
	    				countTable.add(i);
	    			}
	    		Collections.sort(countTable, new Comparator<Number>() {
			    		@Override
			    		public int compare(Number number1, Number number2){
			    			if (number1.doubleValue() < number2.doubleValue())
							    return -1;
							if (number1.doubleValue() > number2.doubleValue())
							    return 1;
							return 0;
						}
			    	});
	    		booCount = true;
	    	}
	    	return countTable;
	    }

	    /** Returns rank of WORD. Most common word is rank 1. 
	      * If two words have the same rank, break ties arbitrarily. 
	      * No two words should have the same rank.
	      */
	    public int rank(String word) {
	    	if (booTable == null) {
	    		booTable = new HashMap<String,Integer>();
	    		int i = 1;
	    		for (String disWord : words()){
	    			booTable.put(disWord, i);
	    			i++;
	    		}
	    	}
	    	return size - booTable.get(word) + 1;
		}
}