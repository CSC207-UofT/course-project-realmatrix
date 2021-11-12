package database;

import Controller.ProgramState;

import java.io.IOException;

/**
 * An interface for writing and archiving into database.
 */
public interface IDataWriter {
    // Write the object into database
    public void write(ProgramState state, Object o) throws IOException;

    // Archive the object in database (store in database but won't load in future)
    public void archive(ProgramState state, Object o) throws IOException;
}
