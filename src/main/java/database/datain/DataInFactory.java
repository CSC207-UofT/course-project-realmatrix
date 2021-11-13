package database.datain;

import Controller.ProgramState;
import entity.*;

import java.io.IOException;

/**
 * A class that determines generating which Writer class to write/archive objects.
 * Usecase classes should only make use of this factory, not specific Writer class.
 */
public class DataInFactory implements IDataInFactory{
    private Writer writer;  // The Writer we should generate

    /**
     * Construct a DataInFactory according to the object needs to be written/archived.
     * @param state the current state the program is in
     * @param o the object needs to write/archive
     */
    // TODO: does checking data type follow clean architecture principles?
    public DataInFactory(ProgramState state, Object o) {
        if (o instanceof Card) {
            this.writer = new CardWriter(state, o);
        }

        else if (o instanceof Pack) {
            this.writer = new PackWriter(state, o);
        }

        else if (o instanceof User) {
            this.writer = new UserWriter(state, o);
        }
    }

    /**
     * Use the writer class to write the object into database.
     * @throws IOException
     */
    @Override
    public void write() throws IOException {
        this.writer.write();
    }

    /**
     * Use the writer class to write archive object into database.
     * @throws IOException
     */
    @Override
    public void archive() throws IOException {
        this.writer.archive();
    }
}
