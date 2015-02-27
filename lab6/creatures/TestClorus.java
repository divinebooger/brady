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

public class TestClorus {

    @Test
    public void testChoose() {
        Clorus p = new Clorus(2);
        HashMap<Direction, Occupant> surrounded = new HashMap<Direction, Occupant>();
        surrounded.put(Direction.TOP, new Plip(1));
        surrounded.put(Direction.BOTTOM, new Plip(1));
        surrounded.put(Direction.LEFT, new Plip(1));
        surrounded.put(Direction.RIGHT, new Plip(1));

        Action actual = p.chooseAction(surrounded);
        Action expected = new Action(Action.ActionType.STAY);

        assertEquals(expected, actual);

        Clorus g = new Clorus(3);
        System.out.println(g.energy());
        HashMap<Direction, Occupant> sur = new HashMap<Direction,Occupant>();
        sur.put(Direction.TOP, new Empty());
        sur.put(Direction.BOTTOM, new Empty());
        sur.put(Direction.RIGHT, new Empty());
        sur.put(Direction.LEFT, new Empty());
        //Action a = g.chooseAction(sur);
        //Action ex = new Action(Action.ActionType.REPLICATE, Direction.TOP);
        assertEquals(1.5,g.energy(),0.001);
    }


    public static void main(String[] args) {
        System.exit(jh61b.junit.textui.runClasses(TestClorus.class));
    }
} 
