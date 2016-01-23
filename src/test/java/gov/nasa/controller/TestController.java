package gov.nasa.controller;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestController {
    @Test
    public void testExecuteInstructions() throws Exception {
        String input =  "5 5\n" +
                        "1 2 N\n" +
                        "LMLMLMLMM\n" +
                        "3 3 E\n" +
                        "MMRMMRMRRM";

        String output = "1 3 N\n" +
                        "5 1 E";

        String result = Controller.executeInstructions(input);
        assertEquals(output, result);
    }

    @Test (expected = Exception.class)
    public void testRoverPlacingOneRoverOverAnother() throws Exception {
        String input =  "5 5\n" +
                "0 0 N\n" +
                "MM\n" +
                "0 0 N\n" +
                "MM\n";

        Controller.executeInstructions(input);
    }

    @Test
    public void testRoverMovingOutsideThePlateau() throws Exception {
        String input =  "5 5\n" +
                        "4 4 N\n" +
                        "MM\n";

        String output = "4 5 N\n" +
                        "ERROR: ";

        String result = Controller.executeInstructions(input);
        assertTrue(result.startsWith(output));
    }

    @Test
    public void testRoverMovingOneRoverIntoAnother() throws Exception {
        String input =  "5 5\n" +
                        "0 0 N\n" +
                        "MM\n" +
                        "0 2 N\n" +
                        "MM\n";

        String output = "0 1 N\n" +
                        "0 2 N\n" +
                        "ERROR: ";

        String result = Controller.executeInstructions(input);
        assertTrue(result.startsWith(output));
    }
}
