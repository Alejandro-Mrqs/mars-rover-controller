package gov.nasa.controller;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

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
    public void testRoverMovingOutsideThePlateau() throws Exception {
        String input =  "5 5\n" +
                        "4 4 N\n" +
                        "MM\n";

        Controller.executeInstructions(input);
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
}
