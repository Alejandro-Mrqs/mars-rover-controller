package gov.nasa.model;

import gov.nasa.model.Rover.Command;
import gov.nasa.model.Facing.CardinalPoint;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestRover {
    @Test
    public void testMove(){
        Rover rover = new Rover(new Position(0,0), new Facing(CardinalPoint.N));
        rover.executeCommand(Command.M);
        rover.executeCommand(Command.M);
        rover.executeCommand(Command.M);
        assertEquals(new Position(0,3), rover.getPosition());
        assertEquals(new Facing(CardinalPoint.N), rover.getFacing());
    }

    @Test
    public void testTurnLeft(){
        Rover rover = new Rover(new Position(0,0), new Facing(CardinalPoint.N));
        rover.executeCommand(Command.L);
        rover.executeCommand(Command.L);
        rover.executeCommand(Command.L);
        assertEquals(new Position(0,0), rover.getPosition());
        assertEquals(new Facing(CardinalPoint.E), rover.getFacing());
    }

    @Test
    public void testTurnRight(){
        Rover rover = new Rover(new Position(0,0), new Facing(CardinalPoint.N));
        rover.executeCommand(Command.R);
        rover.executeCommand(Command.R);
        rover.executeCommand(Command.R);
        assertEquals(new Position(0,0), rover.getPosition());
        assertEquals(new Facing(CardinalPoint.W), rover.getFacing());
    }

    @Test
    public void testMoveAndTurn(){
        Rover rover = new Rover(new Position(0,0), new Facing(CardinalPoint.N));
        rover.executeCommand(Command.M);
        rover.executeCommand(Command.R);
        rover.executeCommand(Command.M);
        assertEquals(new Position(1,1), rover.getPosition());
        assertEquals(new Facing(CardinalPoint.E), rover.getFacing());
    }

    @Test
    public void testExecuteCommands() throws Exception {
        Rover rover = new Rover(new Position(1,2), new Facing(CardinalPoint.N));
        rover.executeCommands("LMLMLMLMM");
        assertEquals(new Position(1,3), rover.getPosition());
        assertEquals(new Facing(CardinalPoint.N), rover.getFacing());
    }

    @Test
    public void testGetPositionAndFacingAsString(){
        Rover rover = new Rover(new Position(1,3), new Facing(CardinalPoint.N));
        assertEquals("1 3 N", rover.getPositionAndFacingAsString());
    }
}
