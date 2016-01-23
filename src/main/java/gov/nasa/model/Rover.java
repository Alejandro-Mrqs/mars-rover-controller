package gov.nasa.model;

import gov.nasa.model.common.Facing;
import gov.nasa.model.common.Instruction;
import gov.nasa.model.common.Instruction.Command;
import gov.nasa.model.common.Position;

public class Rover {

    private Position position;
    private Facing facing;
    private String id;

    public Rover (Position position, Facing facing){
        this.position = position;
        this.facing = facing;
        // Initial position and facing are used as rover's id
        this.id = getRoverAsString();
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

    public void executeInstruction (Instruction instruction){
        for (Command command : instruction.getCommands()){
            executeCommand(command);
        }
    }

    public String getRoverAsString(){
        return position.getX() + " " + position.getY() + " " + facing.getCardinalPoint();
    }

    public Position getPosition() {
        return position;
    }

    public Facing getFacing() {
        return facing;
    }

    public String getId() {
        return id;
    }
}
