package ngordnet;
import java.util.*;
import java.lang.Object.*;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;
import java.util.Hashtable;
public class WordNet {

	private LinkedList<String> nounString;
	private Digraph g;
	private HashMap<Integer, String[]> wordInfo;

	public WordNet(String synsetFilename, String hyponymFilename){
		In in = new In(synsetFilename);
		In in2 = new In(hyponymFilename);
		wordInfo = new HashMap<Integer, String[]>();
		nounString = new LinkedList<String>();
		int q = 0;
		while (in.hasNextLine()){
			q++;
			String[] here = in.readLine().split(",");
			Integer key = Integer.parseInt(here[0]);
			String[] value = here[1].split("\\s");
			wordInfo.put(key, value);
			nounString.addAll(Arrays.asList(value));
		}
		g = new Digraph(q);
		while (in2.hasNextLine()){
			String[] disSet = in2.readLine().split(",");
			int vertex = Integer.parseInt(disSet[0]);
			String [] datSet = new String[disSet.length - 1];
			System.arraycopy(disSet, 1, datSet, 0, disSet.length - 1);
			for (String wordNumber : datSet){
				int num = Integer.parseInt(wordNumber);
				g.addEdge(vertex, num);
			}
		}
	}


	private LinkedList<Integer> findWords(String word){
		LinkedList<Integer> disList = new LinkedList<Integer>();
		for (Integer key : wordInfo.keySet()){
			if (Arrays.asList(wordInfo.get(key)).contains(word)){
				disList.add(key);
			}
		}
		return disList;
	}

	public boolean isNoun(String noun){
		return nounString.contains(noun);
	}

	public Set<String> nouns(){
		Set<String> temp = new HashSet<>(nounString);
		return temp;
	}

	public Set<String> hyponyms(String word){
		LinkedList<Integer> temp = findWords(word);
		Set<Integer> nounSet = new HashSet<>(temp);
		LinkedList<String> temp2 = new LinkedList<String>();
		for (Integer disInt : GraphHelper.descendants(g, nounSet)){
			for (String here : wordInfo.get(disInt)){
				temp2.add(here);
			}
		}
		Set<String> finalone = new HashSet<>(temp2);
		return finalone;
	}   
}