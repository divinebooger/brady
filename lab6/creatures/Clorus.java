package creatures;
import huglife.Creature;
import huglife.Direction;
import huglife.Action;
import huglife.Occupant;
import huglife.HugLifeUtils;
import java.awt.Color;
import java.util.Map;
import java.util.List;


public class Clorus extends Creature {
	private int r;
	private int g;
	private int b;

	public Clorus(double e) {
		super("clorus");
		r = 34;
		g = 0;
		b = 231;
		energy = e;
	}

    public Color color() {
        return color(r, g, b);
    }

    public void attack(Creature c) {
    	energy = energy + c.energy();
    }


    public void move() {
        energy -= 0.03;
    }

    public void stay() {
        energy -= 0.01;
    }


    public Clorus replicate() {
        energy = 0.5 * energy;
        Clorus g = new Clorus(energy);
        return g;
    }

    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        List<Direction> empties = getNeighborsOfType(neighbors, "empty");
        List<Direction> plipz = getNeighborsOfType(neighbors, "plip");
        if (empties.size() == 0){
            return new Action(Action.ActionType.STAY);
        }
        else if (plipz.size() > 0){
            Direction atkDir = HugLifeUtils.randomEntry(plipz);
            return new Action(Action.ActionType.ATTACK, atkDir);
        }
        else if (energy >= 1){
        	Direction repDir = HugLifeUtils.randomEntry(empties);
            return new Action(Action.ActionType.REPLICATE, repDir);
        }
        else{
	        Direction movDir = HugLifeUtils.randomEntry(empties);
	        return new Action(Action.ActionType.MOVE, movDir);
    	}
    }
}
