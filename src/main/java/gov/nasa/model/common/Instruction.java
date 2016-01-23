package gov.nasa.model.common;

public class Instruction {

    public enum Command {
        L ("Turn left"),
        R ("Turn right"),
        M ("Move forward");

        String description;

        Command (String description){
            this.description = description;
        }
    }

    private Command[] commands;


    public Instruction (String instruction) throws Exception {
        if (null == instruction){throw new Exception("Null instruction received");}

        char[] instructionLetters = instruction.trim().toCharArray();
        commands = new Command[instructionLetters.length];

        for (int i = 0; i < commands.length; i++){
            Character letter = instructionLetters[i];
            try {commands[i] = Command.valueOf(letter.toString());}
            catch (Exception exception){
                throw new Exception("Not supported command \"" + letter + "\"");
            }
        }
    }


    public Command[] getCommands() {
        return commands;
    }
}
