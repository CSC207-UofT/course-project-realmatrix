package gateway.datain;

import Controller.ProgramState;

import java.io.IOException;

public abstract class Writer {
    protected String username;
    protected String packname;

    /**
     * Get pack/username from the program's current state.
     * This method helps writer classes to determine which path should write to.
     */
    public Writer(ProgramState state, Object o) {
        this.username = state.getCurrUser().getName();
        if (state.getCurrPack() != null) {
            this.packname = state.getCurrPack().getName();
        }
    }

    /** Write the object into database
     * @throws IOException
     */
    public abstract void write() throws IOException;

    /** Archive the object in database (store in database but won't load in future)
     * @throws IOException
     */
    public abstract void archive() throws IOException;
}
