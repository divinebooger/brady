package ngordnet;
import edu.princeton.cs.introcs.In;
import java.util.*;
public class NGramMap {
    private String[] words;
    private String[] counts;
    private HashMap<Integer, YearlyRecord> wordAppear;
    private TimeSeries<Long> yearWords;
    private HashMap<String, TimeSeries<Integer>> wordHistorian;

    public NGramMap(String wordsFilename, String countsFilename){
        In in = new In(wordsFilename);
        In in2 = new In(countsFilename);
        words = in.readAllLines();
        counts = in2.readAllLines();
        wordAppear = new HashMap<Integer, YearlyRecord>();
        yearWords = new TimeSeries<Long>();
        wordHistorian = new HashMap<String, TimeSeries<Integer>>();
        for (String disWord : words){
            String[] splitIt = separateByTab(disWord);
            String chosenWord = splitIt[0];
            Integer disYear = Integer.parseInt(splitIt[1]);
            Integer appearance = Integer.parseInt(splitIt[2]);
            if(wordAppear.get(disYear) == null){
                wordAppear.put(disYear, new YearlyRecord());
            }
            wordAppear.get(disYear).put(chosenWord, appearance);
            if(wordHistorian.get(chosenWord) == null){
                wordHistorian.put(chosenWord, new TimeSeries<Integer>());
            }
            wordHistorian.get(chosenWord).put(disYear, appearance);
        }
        for (String doseWords : counts){
            String[] splitDat = separateByComma(doseWords);
            Integer doseYear = Integer.parseInt(splitDat[0]);
            Long totWords = Long.valueOf(splitDat[1]).longValue();
            yearWords.put(doseYear, totWords);
        }
    }

    private String[] separateByTab(String word){
        return word.split("\t");
    }

    private String[] separateByComma(String word){
        return word.split(",");
    }

    public int countInYear(String word, int year){
        return wordAppear.get(year).count(word);
    }
    
    public YearlyRecord getRecord(int year){
        YearlyRecord temp = new YearlyRecord();
        YearlyRecord notTemp = wordAppear.get(year);
        for (String disWord : notTemp.words()){
            temp.put(disWord, notTemp.count(disWord));
        }
        return temp;
    }

    public TimeSeries<Long> totalCountHistory(){
        return yearWords;
    }

    public TimeSeries<Integer> countHistory(String word, int startYear, int endYear){
        return new TimeSeries<Integer>(wordHistorian.get(word), startYear,endYear);
    }

    public TimeSeries<Integer> countHistory(String word){
        return new TimeSeries<Integer>(wordHistorian.get(word));
    }

    public TimeSeries<Double> weightHistory(String word, int startYear, int endYear){
        TimeSeries<Double> quotient = new TimeSeries<Double>();
        TimeSeries<Long> partHistory = new TimeSeries<Long>(yearWords,startYear,endYear);
        quotient = countHistory(word,startYear, endYear).dividedBy(partHistory);
        return quotient;
    }

    public TimeSeries<Double> weightHistory(String word){
        TimeSeries<Double> quotient = new TimeSeries<Double>();
        quotient = countHistory(word).dividedBy(totalCountHistory());
        return quotient;
    }
/*

    public TimeSeries<Double> summedWeightHistory(Collection<String> words, 
                              int startYear, int endYear){

    }

    public TimeSeries<Double> summedWeightHistory(Collection<String> words){

    }
    /*
    public TimeSeries<Double> processedHistory(int startYear, int endYear,
                                               YearlyRecordProcessor yrp){

    }

    public TimeSeries<Double> processedHistory(YearlyRecordProcessor yrp){

    } 
    */
    public static void main(String[] args) {
        NGramMap ngm = new NGramMap("./ngrams/words_that_start_with_q.csv", 
                                    "./ngrams/total_counts.csv");


        System.out.println(ngm.countInYear("quantity", 1736)); // should print 139
        YearlyRecord yr = ngm.getRecord(1736);
        System.out.println(yr.count("quantity")); // should print 139

        TimeSeries<Integer> countHistory = ngm.countHistory("quantity");
        System.out.println(countHistory.get(1736)); // should print 139

        TimeSeries<Long> totalCountHistory = ngm.totalCountHistory();
        /* In 1736, there were 8049773 recorded words. Note we are not counting
         * distinct words, but rather the total number of words printed that year. */
        System.out.println(totalCountHistory.get(1736)); // should print 8049773

        TimeSeries<Double> weightHistory = ngm.weightHistory("quantity");
        System.out.println(weightHistory.get(1736));  // should print roughly 1.7267E-5
    
        System.out.println((double) countHistory.get(1736) 
                           / (double) totalCountHistory.get(1736)); 
    }
}