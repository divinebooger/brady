package creatures;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.HashMap;
import java.awt.Color;
import huglife.Direction;
import huglife.Action;
import huglife.Occupant;
import huglife.Impassible;
import huglife.Empty;

/** Tests the plip class   
 *  @authr FIXME
 */

public class TestPlip {

    @Test
    public void testBasics() {
        Plip p = new Plip(2);
        assertEquals(2, p.energy(), 0.01);
        assertEquals(new Color(99, 255, 76), p.color());
        p.move();
        assertEquals(1.85, p.energy(), 0.01);
        p.move();
        assertEquals(1.70, p.energy(), 0.01);
        p.stay();
        assertEquals(1.90, p.energy(), 0.01);
        p.stay();
        assertEquals(2.00, p.energy(), 0.01);
    }

    @Test
    public void testReplicate() {
        Plip p = new Plip(2);
        Plip g = p.replicate();
        assertNotSame(p,g);
        assertEquals(1, p.energy(), 0.001);
        assertEquals(1, g.energy(), 0.001);

    }

    @Test
    public void testChoose() {
        Plip p = new Plip(1);
        HashMap<Direction, Occupant> surrounded = new HashMap<Direction, Occupant>();
        surrounded.put(Direction.TOP, new Clorus(1));
        surrounded.put(Direction.BOTTOM, new Clorus(1));
        surrounded.put(Direction.LEFT, new Clorus(1));
        surrounded.put(Direction.RIGHT, new Empty());

        //You can create new empties with new Empty();
        //Despite what the spec says, you cannot test for Cloruses nearby yet.
        //Sorry!  

        Action actual = p.chooseAction(surrounded);
        Action expected = new Action(Action.ActionType.MOVE, Direction.RIGHT);

        assertEquals(expected, actual);

        HashMap<Direction, Occupant> sur = new HashMap<Direction,Occupant>();
        sur.put(Direction.TOP, new Impassible());
        sur.put(Direction.BOTTOM, new Impassible());
        sur.put(Direction.RIGHT, new Impassible());
        sur.put(Direction.LEFT, new Clorus(1));
        Action a = p.chooseAction(sur);
        Action ex = new Action(Action.ActionType.MOVE, Direction.LEFT);
    }

    public static void main(String[] args) {
                Plip g = new Plip(2);
        System.out.println(g.energy());
        System.exit(jh61b.junit.textui.runClasses(TestPlip.class));
    }
} 
