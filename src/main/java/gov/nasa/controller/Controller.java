package gov.nasa.controller;

import gov.nasa.model.Rover;
import org.apache.log4j.Logger;

public class Controller {

    private static Logger logger = Logger.getLogger(Controller.class);

    public static String executeInstructions (String instructions){
        String[] lines = instructions.split("\\n");
        StringBuilder builder = new StringBuilder();
        Rover rover;
        String newLine = "";

        for (int lineNumber = 1; lineNumber < lines.length; lineNumber = lineNumber + 2){
            try {
                rover = new Rover(lines[lineNumber]);
                rover.executeCommands(lines[lineNumber + 1]);
                builder.append(newLine).append(rover.getPositionAndFacingAsString());
                newLine = "\n";
            }
            catch (Exception exception){logger.warn(exception.getMessage());}
        }
        return builder.toString();
    }
}
