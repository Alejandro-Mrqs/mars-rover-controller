package gov.nasa.utils;

import gov.nasa.model.Plateau;
import gov.nasa.model.Rover;
import gov.nasa.model.common.Facing.CardinalPoint;
import gov.nasa.model.common.Instruction;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;

public class TestInputUtils {
    @Test
    public void testParseRightPlateau () throws Exception {
        Plateau plateau = InputUtils.parsePlateau("4 7");
        assertEquals(4, plateau.getxLimit());
        assertEquals(7, plateau.getyLimit());
    }

    @Test (expected = Exception.class)
    public void testParseWrongNegativePlateau () throws Exception {
        InputUtils.parsePlateau("-4 7");
    }

    @Test (expected = Exception.class)
    public void testParseWrongLetterPlateau () throws Exception {
        InputUtils.parsePlateau("A 7");
    }

    @Test (expected = Exception.class)
    public void testParseWrongFormatPlateau () throws Exception {
        InputUtils.parsePlateau("A");
    }

    @Test
    public void testParseRightRover () throws Exception {
        Rover rover = InputUtils.parseRover("1 3 N");
        assertEquals(1, rover.getPosition().getX());
        assertEquals(3, rover.getPosition().getY());
        assertEquals(CardinalPoint.N, rover.getFacing().getCardinalPoint());
    }

    @Test (expected = Exception.class)
    public void testParseWrongPositionRover () throws Exception {
        InputUtils.parsePlateau("A 7 N");
    }

    @Test (expected = Exception.class)
    public void testParseWrongFacingRover () throws Exception {
        InputUtils.parsePlateau("1 7 P");
    }

    @Test (expected = Exception.class)
    public void testParseWrongFormatRover () throws Exception {
        InputUtils.parsePlateau("17");
    }

    @Test
    public void testParseRightInstructions () throws Exception {
        String[] input = new String[]{"5 5", "1 3 N", "LMMRM", "4 3 E", "MR"} ;
        Map<Rover, Instruction> instructions = InputUtils.parseInstructions(input);
        assertEquals(2, instructions.size());
    }

    @Test (expected = Exception.class)
    public void testParseWrongMissingInstructions () throws Exception {
        String[] input = new String[]{"5 5", "1 3 N", "LMMRM", "4 3 E"} ;
        InputUtils.parseInstructions(input);
    }

    @Test (expected = Exception.class)
    public void testParseWrongMissingRoverInstructions () throws Exception {
        String[] input = new String[]{"5 5", "LMMRM", "4 3 E", "MR"} ;
        InputUtils.parseInstructions(input);
    }
}