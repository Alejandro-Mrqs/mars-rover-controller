package gov.nasa.model;

import gov.nasa.model.common.Facing;
import gov.nasa.model.common.Instruction;
import gov.nasa.model.common.Instruction.Command;
import gov.nasa.model.common.Position;
import org.apache.log4j.Logger;

public class Rover {

    private Logger logger = Logger.getLogger(this.getClass());

    private String id;

    private Position position;
    private Facing facing;

    private Plateau environment;

    public Rover (Position position, Facing facing){
        this.position = position;
        this.facing = facing;
        // Initial position and facing are used as rover's id
        this.id = getRoverAsString();
        logger.info("Rover \"" + id + "\" created");
    }

    public void executeCommand (Command command) throws Exception {
        // Depending on the received command the associated method is executed
        logger.debug("Rover \"" + id + "\" received \"" + command.getDescription() + "\" command");
        logger.debug("Rover \"" + id + "\" initial position: [" + getRoverAsString() + "]");
        switch (command){
            case M:
                safeMove();
                break;
            case L:
                facing.rotateLeft();
                break;
            case R:
                facing.rotateRight();
                break;
        }
        logger.debug("Rover \"" + id + "\" final position: [" + getRoverAsString() + "]");
    }

    public void executeInstruction (Instruction instruction) throws Exception {
        logger.info("Rover \"" + id + "\" received an instruction: " + instruction);
        logger.info("Rover \"" + id + "\" initial position: [" + getRoverAsString() + "]");

        int commandNumber = 0;
        // Every command in the instruction is executed in sequential order. If any command fails the instruction is
        // interrupted
        for (Command command : instruction.getCommands()) {
            commandNumber++;
            try {executeCommand(command);}
            catch (Exception exception){
                String message = "Rover \"" + id + "\" aborting instruction on command " + command +
                        " (Index " + commandNumber + "). Reason: " + exception.getMessage() + ". " +
                        "Last position: [" + getRoverAsString() + "]";
                logger.error(message);
                throw new Exception(message);
            }
        }
        logger.info("Rover \"" + id + "\" final position: [" + getRoverAsString() + "]");
    }

    private void safeMove () throws Exception {
        // If enviroment information is present it is checked before moving in order to know if it is an allowed movement
        if (null != environment) {
            // Before moving the rover calculates the next position
            Position newPosition = new Position(position.getX(), position.getY());
            newPosition.move(facing.getCardinalPoint());

            // If the new position is outside the plateau
            if (environment != null && !environment.isInsideLimits(newPosition)) {
                throw new Exception("Next move will take the rover outside the plateau");
            }
            // Or any other object is blocking the new position
            if (environment != null && environment.isBlockedPosition(newPosition)) {
                throw new Exception("Next move will make the rover collide");
            }
            // The instruction is aborted and the rover remains in the previous position. Otherwise the rover moves.
            position = newPosition;
            // And the new position is updated in the environment
            environment.placeObject(id, position);
        }
        else {
            // If no environment information is provided the movement is applied directly
            position.move(facing.getCardinalPoint());
        }
    }

    public String getRoverAsString(){
        return position.getX() + " " + position.getY() + " " + facing.getCardinalPoint();
    }

    public void updateEnvironment(Plateau plateau){
        environment = plateau;
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
