package database.datain;

import Controller.ProgramState;

import java.io.IOException;

/**
 * An interface for writing and archiving into database.
 */
public interface IDataInFactory {
    // Write the object into database
    public void write() throws IOException;

    // Archive the object in database (store in database but won't load in future)
    public void archive() throws IOException;
}
