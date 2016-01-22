package gov.nasa.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import gov.nasa.model.Facing.CardinalPoint;

public class Rover {
    enum Command {
        L,R,M
    }

    private Position position;
    private Facing facing;

    public Rover (Position position, Facing facing){
        this.position = position;
        this.facing = facing;
    }

    public Rover (String id) throws Exception {
        Pattern pattern = Pattern.compile("\\s*([0-9]+)\\s+([0-9]+)\\s+([NESW])\\s*");
        Matcher matcher = pattern.matcher(id);
        if (!matcher.matches()){
            throw new Exception("Wrong id format");
        }
        position = new Position(
                Integer.parseInt(matcher.group(1)),
                Integer.parseInt(matcher.group(2)));
        facing = new Facing(CardinalPoint.valueOf(matcher.group(3)));
    }

    public void executeCommand (Command command){
        switch (command){
            case M:
                position.move(facing.getCardinalPoint());
                break;
            case L:
                facing.rotateLeft();
                break;
            case R:
                facing.rotateRight();
                break;
        }
    }

    public void executeCommands (String commands) throws Exception {
        for (Character commandName : commands.toCharArray()){
            Command command;
            try {command = Command.valueOf(commandName.toString());}
            catch (Exception exception){
                throw new Exception("Unsupported command \"" + commandName + "\"");
            }
            executeCommand(command);
        }
    }

    public String getPositionAndFacingAsString(){
        return position.getX() + " " + position.getY() + " " + facing.getCardinalPoint();
    }

    public Position getPosition() {
        return position;
    }

    public Facing getFacing() {
        return facing;
    }
}