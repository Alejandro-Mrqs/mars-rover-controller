package gov.nasa.utils;

import gov.nasa.model.Rover;

import java.util.Set;

public class OutputUtils {

    public static String getRoversOutput (Set<Rover> rovers){
        StringBuilder builder = new StringBuilder();
        String linePrefix = "";
        for (Rover rover : rovers){
            builder.append(linePrefix).append(rover.getRoverAsString());
            linePrefix = "\n";
        }
        return builder.toString();
    }
}
