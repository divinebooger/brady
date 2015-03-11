package ngordnet;
import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.In;
import java.util.*;
import java.lang.*;
/** Provides a simple user interface for exploring WordNet and NGram data.
 *  @author [brady huynh]
 */
public class NgordnetUI {
    public static void main(String[] args) {
        In in = new In("./ngordnet/ngordnetui.config");
        System.out.println("Reading ngordnetui.config...");
        String wordFile = in.readString();
        String countFile = in.readString();
        String synsetFile = in.readString();
        String hyponymFile = in.readString();
        System.out.println("\nBased on ngordnetui.config, using the following: " + wordFile + ", " + countFile + ", " + synsetFile +", and " + hyponymFile + ".");
        NGramMap ngm = new NGramMap(wordFile, countFile);
        WordNet wn = new WordNet(synsetFile, hyponymFile);
        int startDate = 1400;
        int endDate = 2015;
        while (true) {
            System.out.print(">");
            String line = StdIn.readLine();
            String[] rawTokens = line.split(" ");
            String command = rawTokens[0];
            String[] tokens = new String[rawTokens.length - 1];
            System.arraycopy(rawTokens, 1, tokens, 0, rawTokens.length - 1);
            switch (command) {
              case "quit":
                return;
              case "help":
                System.out.println("quit");
                System.out.println("help");
                System.out.println("range");
                System.out.println("count");
                System.out.println("hyponyms");
                System.out.println("history");
                System.out.println("hypohist");  
                break;  
              case "range":
                try{
                   startDate = Integer.parseInt(tokens[0]);
                   endDate = Integer.parseInt(tokens[1]);
                  } catch (IllegalArgumentException poop) {System.out.println("need integer");}
                 break;
              case "count":
                  String word1 = tokens[0];
                  try{
                    int year = Integer.parseInt(tokens[1]);
                    System.out.println(Integer.toString(ngm.countInYear(word1,year)));
                  } catch (IllegalArgumentException poop) {System.out.println("need integer");}
                  break;
              case "hyponyms":
                  String word2 = tokens[0];
                  System.out.println(wn.hyponyms(word2));
                  break;
              case "history":
                  try{
                    Plotter.plotAllWords(ngm, tokens, startDate, endDate);
                  } catch (IllegalArgumentException poop) {System.out.println("cannot produce");}
                  break;
              case "hypohist":
                  try{
                    Plotter.plotCategoryWeights(ngm, wn, tokens, startDate, endDate);
                  } catch (IllegalArgumentException poop) {System.out.println("cannot produce");}
                  break;
              case "zipf":
                  try{
                    int thisYear = Integer.parseInt(tokens[0]);
                    Plotter.plotZipfsLaw(ngm, thisYear);
                  } catch (IllegalArgumentException poop) {System.out.println("incorrect input");}
                  break;
              default:
                    System.out.println("Invalid command.");  
                    break;                                                                      
            }
        }
    }
} 
