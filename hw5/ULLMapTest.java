import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Iterator;
import java.util.Arrays;

/** ULLMapTest. You should write additional tests.
 *  @author Josh Hug
 */

public class ULLMapTest {
    @Test
    public void testBasic() {
        ULLMap<String, String> um = new ULLMap<String, String>();
        um.put("Gracias", "Dios Basado");
        assertEquals(um.get("Gracias"), "Dios Basado");
    }

    
    @Test
    public void testIterator() {
        ULLMap<Integer, String> um = new ULLMap<Integer, String>();
        um.put(0, "zero");
        um.put(1, "one");
        um.put(2, "two");
        System.out.println(um.get(0));
        System.out.println(um.get(1));
        System.out.println(um.get(2));
        Iterator<Integer> umi = um.iterator();
        System.out.println(umi.next());
        System.out.println(umi.next());
        System.out.println(umi.next());
    }

    @Test
    public void myTest() {
        ULLMap<Integer, String> um = new ULLMap<Integer, String>();
        um.put(0, "zero");
        um.put(1, "one");
        um.put(2, "two");
        /*Iterator<String> umi = ULLMap.invert(um).iterator();
        System.out.println(umi.next());
        System.out.println(umi.next());
        System.out.println(umi.next());*/

       // System.out.println(Arrays.toString(i))   ;  
    }
    

    /** Runs tests. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(ULLMapTest.class);
    }
} 