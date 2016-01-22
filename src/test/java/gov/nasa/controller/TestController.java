package gov.nasa.controller;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestController {
    @Test
    public void testExecuteInstructions(){
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
}
