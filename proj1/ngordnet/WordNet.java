package ngordnet;
import java.util.*;
import java.lang.Object.*;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;
import java.util.Hashtable;
public class WordNet {
	private String[] synInfo;
	private String[] hypInfo;
	private LinkedList<String> nounString;
	private Digraph g;

	public WordNet(String synsetFilename, String hyponymFilename){
		In in = new In(synsetFilename);
		In in2 = new In(hyponymFilename);
		synInfo = in.readAllLines();
		hypInfo = in2.readAllLines();
		g = new Digraph(synInfo.length);
		nounString = new LinkedList<String>();
		for (String index : hypInfo){
			String[] disSet = index.split(",");
			int vertex = Integer.parseInt(disSet[0]);
			String [] datSet = new String[disSet.length - 1];
			System.arraycopy(disSet, 1, datSet, 0, disSet.length - 1);
			for (String wordNumber : datSet){
				int num = Integer.parseInt(wordNumber);
				g.addEdge(vertex, num);
			}
		}
		for (int p = 0; p < synInfo.length; p++){
			String[] multiSet = deCode(p);
			for (int q = 0; q < multiSet.length; q++){
				nounString.add(multiSet[q]);
			}
		}
	}

	private String[] deCode(int i){
		return cut(synInfo[i].split(",")[1]);
	}

	private LinkedList<Integer> findWords(String word){
		LinkedList<Integer> disList = new LinkedList<Integer>();
		for (int i = 0; i < synInfo.length; i++){
			if (Arrays.asList(deCode(i)).contains(word)){
				disList.add(i);
			}
		}
		return disList;
	}

	private String[] cut(String i){
		return i.split("\\s");
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
			for (String here : deCode(disInt)){
				temp2.add(here);
			}
		}
		Set<String> finalone = new HashSet<>(temp2);
		return finalone;
	}

	public static void main(String[] args) {
		WordNet wn = new WordNet("./wordnet/synsets.txt", "./wordnet/hyponyms.txt");
		/*System.out.println("All nouns:");
        for (String noun : wn.nouns()) {
            System.out.println(noun);
        }*/
        System.out.println("Hypnoyms of animal:");
        for (String noun : wn.hyponyms("animal")) {
            System.out.println(noun);
        }
	}

}