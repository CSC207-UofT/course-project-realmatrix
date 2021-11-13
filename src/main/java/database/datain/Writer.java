package database.datain;

import Controller.ProgramState;

public abstract class Writer implements IDataWriter {
    protected String username;
    protected String packname;

    /**
     * Get pack/username from the program's current state.
     * This method helps writer classes to determine which path should write to.
     */
    public void getName(ProgramState state) {
        this.username = state.getCurrUser().getName();
        this.packname = state.getCurrPack().getName();
    }
}
