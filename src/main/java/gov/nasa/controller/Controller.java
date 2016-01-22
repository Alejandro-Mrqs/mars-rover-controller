package gov.nasa.controller;

import gov.nasa.model.Plateau;
import gov.nasa.model.Rover;
import org.apache.log4j.Logger;

public class Controller {

    private static Logger logger = Logger.getLogger(Controller.class);

    public static String executeInstructions (String instructions) throws Exception {
        String[] lines = instructions.split("\\n");
        StringBuilder builder = new StringBuilder();
        Rover rover;
        String newLine = "";

        Plateau plateau = new Plateau(lines[0]);

        for (int roverId = 1; roverId*2 < lines.length; roverId++){
            rover = new Rover(lines[roverId*2-1]);
            plateau.placeObject("Rover " + roverId, rover.getPosition());

            rover.executeCommands(lines[roverId*2]);
            plateau.placeObject("Rover " + roverId, rover.getPosition());

            builder.append(newLine).append(rover.getPositionAndFacingAsString());
            newLine = "\n";
        }
        return builder.toString();
    }
}
