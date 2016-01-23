package gov.nasa.model.common;

import org.junit.Test;
import gov.nasa.model.common.Instruction.Command;

import static org.junit.Assert.assertEquals;

public class TestInstruction {
    @Test
    public void testRightInstruction() throws Exception {
        Instruction instruction = new Instruction("LMRM");
        Command[] commands = instruction.getCommands();

        assertEquals(Command.L, commands[0]);
        assertEquals(Command.M, commands[1]);
        assertEquals(Command.R, commands[2]);
        assertEquals(Command.M, commands[3]);
    }

    @Test (expected = Exception.class)
    public void testWrongInstruction() throws Exception {
        new Instruction("LM2M");
    }
}
