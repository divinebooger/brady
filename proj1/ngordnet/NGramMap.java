package ngordnet;
import edu.princeton.cs.introcs.In;
import java.util.*;
public class NGramMap {
    private String[] counts;
    private HashMap<Integer, YearlyRecord> wordAppear;
    private TimeSeries<Long> yearWords;

    public NGramMap(String wordsFilename, String countsFilename){
        In in = new In(wordsFilename);
        In in2 = new In(countsFilename);
        wordAppear = new HashMap<Integer, YearlyRecord>();
        yearWords = new TimeSeries<Long>();
        while (in.hasNextLine()){
            String[] splitIt = separateByTab(in.readLine());
            String chosenWord = splitIt[0];
            Integer disYear = Integer.parseInt(splitIt[1]);
            Integer appearance = Integer.parseInt(splitIt[2]);
            if(wordAppear.get(disYear) == null){
                wordAppear.put(disYear, new YearlyRecord());
            }
            wordAppear.get(disYear).put(chosenWord, appearance);
        }
        while(in2.hasNextLine()){
            String[] splitDat = separateByComma(in2.readLine());
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
        if (wordAppear.get(year) != null){
            for (String word : wordAppear.get(year).words()){
                temp.put(word, wordAppear.get(year).count(word));
            }
        }
        return temp;
    }

    public TimeSeries<Long> totalCountHistory(){
        return yearWords;
    }

    public TimeSeries<Integer> countHistory(String word, int startYear, int endYear){
        TimeSeries<Integer> temp = new TimeSeries<Integer>();
        for (int i = startYear; i <= endYear; i++){
            if(getRecord(i) != null){
                Collection<String> words = getRecord(i).words();
                if (words.contains(word)){
                    temp.put(i, getRecord(i).count(word));
                }
            }
        }
        return temp;
    }

    public TimeSeries<Integer> countHistory(String word){
        TimeSeries<Integer> temp = new TimeSeries<Integer>();
        for (int i : wordAppear.keySet()){
            if(getRecord(i) != null){
                Collection<String> words = getRecord(i).words();
                if (words.contains(word)){
                    temp.put(i, getRecord(i).count(word));
                }
            }
        }
        return temp;
    }

    public TimeSeries<Double> weightHistory(String word, int startYear, int endYear){
        TimeSeries<Double> quotient = new TimeSeries<Double>();
        TimeSeries<Integer> wordHistory = countHistory(word, startYear, endYear);
        TimeSeries<Long> partHistory = new TimeSeries<Long>();
        for (int i : wordHistory.keySet()){
            partHistory.put(i, yearWords.get(i));
        }
        quotient = wordHistory.dividedBy(partHistory);
        return quotient;
    }

    public TimeSeries<Double> weightHistory(String word){
        TimeSeries<Double> quotient = new TimeSeries<Double>();
        TimeSeries<Integer> wordHistory = countHistory(word);
        TimeSeries<Long> partHistory = new TimeSeries<Long>();
        for (int i : wordHistory.keySet()){
            partHistory.put(i, yearWords.get(i));
        }
        quotient = wordHistory.dividedBy(partHistory);
        return quotient;
    }


    public TimeSeries<Double> summedWeightHistory(Collection<String> words, 
                              int startYear, int endYear){
        TimeSeries<Double> temp = new TimeSeries<Double>();
        for (String disWord : words){
            temp = temp.plus(weightHistory(disWord, startYear, endYear));
            }
        return temp;
    }

    public TimeSeries<Double> summedWeightHistory(Collection<String> words){
        TimeSeries<Double> temp = new TimeSeries<Double>();
        for (String disWord : words){
            temp = temp.plus(weightHistory(disWord));
            }
        return temp;
    }
    
    public TimeSeries<Double> processedHistory(int startYear, int endYear,
                                               YearlyRecordProcessor yrp){
        TimeSeries<Double> processD = new TimeSeries<Double>();
        for (int i = startYear; i <= endYear; i++) {
            if (wordAppear.keySet().contains(i)){
                processD.put(i, yrp.process(wordAppear.get(i)));
            }
        }
        return processD;
    }

    public TimeSeries<Double> processedHistory(YearlyRecordProcessor yrp){
        TimeSeries<Double> processD = new TimeSeries<Double>();
        for (Integer i : wordAppear.keySet()) {
            if (wordAppear.keySet().contains(i)){
                processD.put(i, yrp.process(wordAppear.get(i)));
            }
        }
        return processD;
    } 
    
    public static void main(String[] args) {
        NGramMap ngm = new NGramMap("./ngrams/very_short.csv", 
                                    "./ngrams/total_counts.csv");
        YearlyRecordProcessor yrp = new WordLengthProcessor();
        TimeSeries<Double> yo = ngm.processedHistory(2007, 2008, yrp);
        System.out.println(ngm.wordAppear.get(2007).words());
        System.out.println(ngm.wordAppear.get(2007).counts());
        System.out.println(yo.years());
        System.out.println(yo.data());

    }
}