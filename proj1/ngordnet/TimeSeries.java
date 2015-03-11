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
}