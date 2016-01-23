package gov.nasa.utils;

import gov.nasa.model.Rover;
import gov.nasa.model.common.Facing;
import gov.nasa.model.common.Position;
import org.junit.Test;

import java.util.LinkedHashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class TestOutputUtils {

    @Test
    public void testCreate () throws Exception {
        String expectedOutput = "1 2 N\n2 3 E";
        Set<Rover> rovers = new LinkedHashSet<>();
        rovers.add(new Rover(new Position(1, 2), new Facing(Facing.CardinalPoint.N)));
        rovers.add(new Rover(new Position(2, 3), new Facing(Facing.CardinalPoint.E)));

        assertEquals(expectedOutput, OutputUtils.getRoversOutput(rovers));
    }
}