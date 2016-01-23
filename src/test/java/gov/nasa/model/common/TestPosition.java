package gov.nasa.model.common;

import gov.nasa.model.common.Facing.CardinalPoint;
import gov.nasa.model.common.Position;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestPosition {
    @Test
    public void testMoveNorth(){
        Position position = new Position(1,2);
        position.move(CardinalPoint.N);
        assertEquals(position, new Position(1,3));
    }

    @Test
    public void testMoveSouth(){
        Position position = new Position(1,0);
        position.move(CardinalPoint.S);
        assertEquals(position, new Position(1,-1));
    }

    @Test
    public void testMoveEast(){
        Position position = new Position(1,0);
        position.move(CardinalPoint.E);
        assertEquals(position, new Position(2,0));
    }

    @Test
    public void testMoveWest(){
        Position position = new Position(1,0);
        position.move(CardinalPoint.W);
        assertEquals(position, new Position(0,0));
    }
}
