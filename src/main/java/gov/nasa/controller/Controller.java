package gov.nasa.controller;

import gov.nasa.model.Plateau;
import gov.nasa.model.Rover;
import gov.nasa.model.common.Instruction;
import org.apache.log4j.Logger;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Controller {

    private static Logger logger = Logger.getLogger(Controller.class);

    public static String executeInstructions (String instructionsString) throws Exception {
        String[] lines = instructionsString.split("\\n");
        StringBuilder builder = new StringBuilder();
        String newLine = "";

        Plateau plateau = new Plateau(lines[0]);
        Map<Rover, Instruction> instructions = checkInstructions(lines);

        int roverCount = 0;
        for (Entry<Rover, Instruction> entry : instructions.entrySet()){
            String roverId = "Rover " + roverCount++;
            Rover rover = entry.getKey();
            Instruction instruction = entry.getValue();

            plateau.placeObject(roverId, rover.getPosition());
            rover.executeInstruction(instruction);
            plateau.placeObject(roverId, rover.getPosition());

            builder.append(newLine).append(rover.getPositionAndFacingAsString());
            newLine = "\n";
        }

        return builder.toString();
    }


    private static Map<Rover, Instruction> checkInstructions (String[] lines) throws Exception {
        Map<Rover, Instruction> instructions = new LinkedHashMap<>();

        int line = 1;
        while (line < lines.length){
            instructions.put(
                    new Rover(lines[line++]),
                    new Instruction(lines[line++]));
        }

        return instructions;
    }
}
