package interface_adapter.gateway.datain;

import java.io.IOException;

/**
 * An abstract writer.
 */
public abstract class Writer {
    protected final String username;
    protected String packname;

    /**
     * Get pack/username from the program's current state.
     * This method helps writer classes to determine which path should write to.
     */
    public Writer(String[] partialDataPath) {
        this.username = partialDataPath[0];
        if (partialDataPath[1] != null) {
            this.packname = partialDataPath[1];
        }
    }

    /**
     * Write the object into database
     */
    public abstract void write() throws IOException;

    /**
     * Rename the object with new name.
     *
     * @param oldName the old object's name which should be replaced
     * @param newO    the new object
     * @throws IOException fails to write
     */
    public abstract void write(String oldName, Object newO) throws IOException;

    /**
     * Delete the object in database.
     */
    public abstract void delete() throws IOException;
}