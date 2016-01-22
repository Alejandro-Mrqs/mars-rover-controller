package gov.nasa.model;

import org.junit.Test;
import gov.nasa.model.Facing.CardinalPoint;

import static org.junit.Assert.assertEquals;

public class TestFacing {
    @Test
    public void testRotateRight(){
        Facing facing = new Facing(Facing.CardinalPoint.S);
        facing.rotateRight();
        assertEquals (CardinalPoint.W, facing.getCardinalPoint());
        facing.rotateRight();
        assertEquals (CardinalPoint.N, facing.getCardinalPoint());
        facing.rotateRight();
        assertEquals (CardinalPoint.E, facing.getCardinalPoint());
        facing.rotateRight();
        assertEquals (CardinalPoint.S, facing.getCardinalPoint());
    }
    @Test
    public void testRotateLeft(){
        Facing facing = new Facing(Facing.CardinalPoint.N);
        facing.rotateLeft();
        assertEquals (CardinalPoint.W, facing.getCardinalPoint());
        facing.rotateLeft();
        assertEquals (CardinalPoint.S, facing.getCardinalPoint());
        facing.rotateLeft();
        assertEquals (CardinalPoint.E, facing.getCardinalPoint());
        facing.rotateLeft();
        assertEquals (CardinalPoint.N, facing.getCardinalPoint());
    }
    @Test
    public void testRotate(){
        Facing facing = new Facing(Facing.CardinalPoint.N);
        facing.rotate(5);
        assertEquals (CardinalPoint.E, facing.getCardinalPoint());
        facing.rotate(-6);
        assertEquals (CardinalPoint.W, facing.getCardinalPoint());
    }
}
