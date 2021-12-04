package interface_adapter.gateway.datain;

import entity.Pack;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * A writer class for Pack.
 */
public class PackWriter extends Writer {
    private final Pack pack;

    /**
     * Construct a CardWriter object.
     * @param partialDataPath the state the program is in
     * @param o     the object (pack) to write/update
     */
    public PackWriter(String[] partialDataPath, Object o) {
        super(partialDataPath);
        this.pack = (Pack) o;
    }

    /**
     * Write a new package
     *
     * @throws IOException fail to write
     */
    @Override
    public void write() throws IOException {
        String packPath = "user_data/users/" + this.username + "/packages/" + this.pack.getName();
        new File(packPath).mkdirs();
    }

    /**
     * Write the pack's new name into database by renaming the pack directory.
     *
     * @param oldName the pack's old name
     * @param newO the pack with new name
     * @throws IOException fails to write
     */
    @Override
    public void write(String oldName, Object newO) throws IOException {
        Pack newPack = (Pack) newO;
        Path old = Paths.get("user_data/users/" + this.username + "/packages/" + oldName);
        Files.move(old, old.resolveSibling(newPack.getName()));
    }

    /**
     * Delete a pack.
     */
    @Override
    public void delete() {
        File packFolder = new File("user_data/users/" + this.username + "/packages/" + this.pack.getName());
        File[] cards = packFolder.listFiles();
        if(cards!=null) { // meaning packFolder is non-empty (contains some cards)
            for(File c: cards) {
                c.delete();
            }
        }
        packFolder.delete();
    }
}
