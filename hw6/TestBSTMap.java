import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestBSTMap {
    @Test
    public void someTest() {
        BSTMap<Integer, String> um = new BSTMap<Integer, String>();
        um.put(1, "poop");
        System.out.println(um.get(1));
        um.put(2, "cheese");
        System.out.println(um.get(2));

    }

    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestBSTMap.class);
    }
} 