package gov.nasa.utils;

import gov.nasa.model.Plateau;
import gov.nasa.model.Rover;
import gov.nasa.model.common.Facing;
import gov.nasa.model.common.Instruction;
import gov.nasa.model.common.Position;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputUtils {

    private static Pattern plateauPattern = Pattern.compile("\\s*([0-9]+)\\s+([0-9]+)\\s*");
    private static Pattern roverPattern = Pattern.compile("\\s*([0-9]+)\\s+([0-9]+)\\s+([NESW])\\s*");


    public static Plateau parsePlateau (String plateauLine) throws Exception {
        // Extracts plateau info from plateau definition line
        Matcher matcher = plateauPattern.matcher(plateauLine);
        if (!matcher.matches()){
            throw new Exception("Wrong plateau definition: " + plateauLine);
        }

        // If the definition matches expected format an object representing the plateau is created
        Position plateauCornerPosition = new Position(
                Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)));
        return new Plateau(plateauCornerPosition);
    }


    public static Map<Rover, Instruction> parseInstructions (String[] lines) throws Exception {
        Map<Rover, Instruction> instructions = new LinkedHashMap<>();

        // Instructions start at second line
        int currentLine = 1;
        while (currentLine < lines.length){
            Rover rover = parseRover(lines[currentLine++]);
            if (currentLine >= lines.length){
                throw new Exception("Missing instruction for rover " + rover);
            }
            instructions.put(rover, new Instruction(lines[currentLine++]));
        }

        return instructions;
    }

    public static Rover parseRover (String roverLine) throws Exception {
        // Extracts rover info from rover definition line
        Matcher matcher = roverPattern.matcher(roverLine);
        if (!matcher.matches()){
            throw new Exception("Wrong rover definition: " + roverLine);
        }

        // If the definition matches expected format an object representing the rover is created
        Position position = new Position(
                Integer.parseInt(matcher.group(1)),
                Integer.parseInt(matcher.group(2)));
        Facing facing = new Facing(Facing.CardinalPoint.valueOf(matcher.group(3)));

        return new Rover(position, facing);
    }
}
