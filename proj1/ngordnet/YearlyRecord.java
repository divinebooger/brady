package ngordnet;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class YearlyRecord {
    private int size;
    private HashMap<String, Integer> table;
    private boolean booWord;
    private boolean booCount;
    private LinkedList<String> wordTable;
    private LinkedList<Number> countTable;
    private HashMap<String, Integer> booTable;
    public YearlyRecord() {
        size = 0;
        booWord = true;
        booCount = true;
        wordTable = new LinkedList<String>();
        countTable = new LinkedList<Number>();
        table = new HashMap<String, Integer>();
        booTable = null;
    }

    public YearlyRecord(HashMap<String, Integer> otherCountMap) {
        table = otherCountMap;
        size = table.size();
        booWord = false;
        booCount = false;
        booTable = null;
    }

    public int count(String word) {
        return table.get(word);
    }

    public void put(String word, int count) {
        if (table.get(word) == null) {
            size += 1;
        }
        table.put(word, count);
        booCount = false;
        booWord = false;
        booTable = null;
    }

    public int size() {
        return size;
    }

    public Collection<String> words() {
        if (!booWord) {
            wordTable = new LinkedList<String>();
            for (String word : table.keySet()) {
                wordTable.add(word);
            }
            Collections.sort(wordTable, new Comparator<String>() {
                @Override
                public int compare(String string1, String string2) {
                    if (table.get(string1).doubleValue() < table.get(string2).doubleValue()) {
                        return -1;
                    }
                    if (table.get(string1).doubleValue() > table.get(string2).doubleValue()) {
                        return 1;
                    }
                    return 0;
                }
                });
            booWord = true;
        }
        return wordTable;
    }

    public Collection<Number> counts() {
        if (!booCount) {
            countTable = new LinkedList<Number>();
            for (Integer i : table.values()) {
                countTable.add(i);
            }
            Collections.sort(countTable, new Comparator<Number>() {
                    @Override
                    public int compare(Number number1, Number number2) {
                        if (number1.doubleValue() < number2.doubleValue()) {
                            return -1;
                        }
                        if (number1.doubleValue() > number2.doubleValue()) {
                            return 1;
                        }
                        return 0;
                    }
                });
            booCount = true;
        }
        return countTable;
    }

    public int rank(String word) {
        if (booTable == null) {
            booTable = new HashMap<String, Integer>();
            int i = 1;
            for (String disWord : words()) {
                booTable.put(disWord, i);
                i++;
            }
        }
        return size - booTable.get(word) + 1;
    }
}
