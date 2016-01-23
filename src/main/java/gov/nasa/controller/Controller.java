package gov.nasa.controller;

import gov.nasa.model.Plateau;
import gov.nasa.model.Rover;
import gov.nasa.model.common.Instruction;
import gov.nasa.utils.InputUtils;
import gov.nasa.utils.OutputUtils;
import org.apache.log4j.Logger;

import java.util.Map;
import java.util.Map.Entry;

public class Controller {

    private static Logger logger = Logger.getLogger(Controller.class);

    public static String executeInstructions (String input) throws Exception {
        logger.debug("Received input: " + input);

        // In order to avoid wasting time by transmitting malformed instructions, input is parsed by the controller
        if (null == input) {throw new Exception("Null input received");}

        String[] inputLines = input.split("\\n");

        // Plateau definition is in the first input line
        Plateau plateau = InputUtils.parsePlateau(inputLines[0]);
        // Then every rover and its associated instruction are parsed
        Map<Rover, Instruction> instructions = InputUtils.parseInstructions(inputLines);

        // It is assumed that every rover is already placed on the plateau, so the plateau model is set up with every
        // rover on its starting position so it can be checked that their positions are valid ones
        for (Rover rover : instructions.keySet()){
            plateau.placeObject(rover.getId(), rover.getPosition());
        }

        // If the instructions and set up is valid the controller starts to send the instructions to the rovers
        for (Entry<Rover, Instruction> entry : instructions.entrySet()) {
            Rover rover = entry.getKey();
            Instruction instruction = entry.getValue();

            // After moving the rover its position is updated in the plateau model
            rover.executeInstruction(instruction);
            plateau.placeObject(rover.getId(), rover.getPosition());
        }

        // Every rover final point is retrieved and written in the expected format
        return OutputUtils.getRoversOutput(instructions.keySet());
    }
}