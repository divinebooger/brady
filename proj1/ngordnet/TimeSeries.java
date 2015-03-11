package ngordnet;
import java.util.*;
import java.lang.IllegalArgumentException;
public class TimeSeries<T extends Number> extends TreeMap<Integer, T>{

    public TimeSeries(){
    	super();
    }

    private NavigableSet<Integer> validYears(int startYear, int endYear){
    	NavigableSet<Integer> temp = new TreeSet<Integer>();
    	for (int i = startYear; i <= endYear; i++){
    		temp.add(i);
    	}
    	return temp;
    }

    public TimeSeries(TimeSeries<T> ts, int startYear, int endYear){
    	super();
    	for (int i : validYears(startYear, endYear)){
    		if (ts.keySet().contains(i)){
    			this.put(i, ts.get(i));
    		}
    	}
    }


    public TimeSeries(TimeSeries<T> ts){
    	super();
    	for(Integer i : ts.keySet()){
    		this.put(i, ts.get(i));
    	}
    }


    public TimeSeries<Double> dividedBy(TimeSeries<? extends Number> ts){
    	TimeSeries<Double> cheese = new TimeSeries<Double>();
    	if (this.keySet().equals(ts.keySet())){
    		for (Integer i : this.keySet()){
    			cheese.put(i, this.get(i).doubleValue() / ts.get(i).doubleValue());
    		}
    		return cheese;
    	}
    	else{
    		throw new IllegalArgumentException();
    	}
    }

    public TimeSeries<Double> plus(TimeSeries<? extends Number> ts){
    	TimeSeries<Double> cheese = new TimeSeries<Double>();
    	Set<Integer> newSet = new HashSet<>(this.keySet());
    	newSet.addAll(ts.keySet());
    	for (Integer i : newSet){
    		if (this.get(i) == null && ts.get(i) != null){
    			cheese.put(i, ts.get(i).doubleValue());
    		}
    		else if (ts.get(i) == null && this.get(i) != null){
    			cheese.put(i, this.get(i).doubleValue());
    		}
    		else if (ts.get(i) != null && this.get(i) != null){
    			cheese.put(i, this.get(i).doubleValue() + ts.get(i).doubleValue());
    		}
    	}
    	return cheese;
    }


    public Collection<Number> years() {
    	LinkedList<Number> temp = new LinkedList<Number>();
    	temp.addAll(this.keySet());
    	return temp;
    }


    public Collection<Number> data() {
    	LinkedList<Number> temp = new LinkedList<Number>();
    	temp.addAll(this.values());
    	return temp;
    }
    public static void main(String[] args) {
        //Create a new time series that maps to Double 
        TimeSeries<Double> ts = new TimeSeries<Double>();

        /* You will not need to implement the put method, since your
           TimeSeries class should extend the TreeMap class. */
        ts.put(1992, 3.6);
        ts.put(1993, 9.2);
        ts.put(1994, 15.2);
        ts.put(1995, 16.1);
        ts.put(1996, -15.7);

        /* Gets the years and data of this TimeSeries. 
         * Note, you should never cast these to another type, even
         * if you happen to know how the Collection<Number> is implemented. */
        Collection<Number> years = ts.years();
        Collection<Number> data = ts.data();

        for (Number yearNumber : years) {
            /* This awkward conversion is necessary since you cannot
             * do yearNumber.get(yearNumber), since get expects as
             * Integer since TimeSeries always require an integer
             * key. 
             *
             * Your output may be in any order. */
            int year = yearNumber.intValue();
            double value = ts.get(year);
            System.out.println("In the year " + year + " the value was " + value);
        }

        for (Number dataNumber : data) {
             /* Your dataNumber values must print out in the same order as the
              * they did in the previous for loop. */
            double datum = dataNumber.doubleValue();
            System.out.println("In some year, the value was " + datum);
        }        

        TimeSeries<Integer> ts2 = new TimeSeries<Integer>();
        ts2.put(1991, 10);
        ts2.put(1992, -5);
        ts2.put(1993, 1);

        TimeSeries<Double> tSum = ts.plus(ts2);
        System.out.println(tSum.get(1991)); // should print 10
        System.out.println(tSum.get(1992)); // should print -1.4

        TimeSeries<Double> ts3 = new TimeSeries<Double>();
        ts3.put(1991, 5.0);
        ts3.put(1992, 1.0);
        ts3.put(1993, 100.0);

        TimeSeries<Double> tQuotient = ts2.dividedBy(ts3);

        System.out.println(tQuotient.get(1991)); // should print 2.0

        TimeSeries<Double> ts4 = new TimeSeries<Double>(ts3);
         Collection<Number> years4 = ts4.years();
        Collection<Number> data4 = ts4.data();

        for (Number yearNumber : years4) {
            /* This awkward conversion is necessary since you cannot
             * do yearNumber.get(yearNumber), since get expects as
             * Integer since TimeSeries always require an integer
             * key. 
             *
             * Your output may be in any order. */
            int year = yearNumber.intValue();
            double value = ts4.get(year);
            System.out.println("In the year " + year + " the value was " + value);
        }
    }
}