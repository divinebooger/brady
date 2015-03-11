package ngordnet;

public class WordLengthProcessor implements YearlyRecordProcessor {

    public double process(YearlyRecord yearlyRecord) {
        double sum = 0;
        double total = 0;
        for (String word : yearlyRecord.words()) {
            sum = sum + (double) word.length() * yearlyRecord.count(word);
            total = total + yearlyRecord.count(word);
        }
        return sum / total;
    }
}
