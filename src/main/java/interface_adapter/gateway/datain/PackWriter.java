package interface_adapter.gateway.datain;

import entity.Pack;
import use_case.constants.Exceptions;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;

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
     * @throws IOException
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
     * @throws Exception if the path of oldName is invalid or the new path exits
     */
    @Override
    public void write(String oldName, Object newO) throws Exception {
        Pack newPack = (Pack) newO;
        try{
            Path old = Paths.get("user_data/users/" + this.username + "/packages/" + oldName);
            Files.move(old, old.resolveSibling(newPack.getName()));
        } catch (InvalidPathException e){
            throw new Exception(Exceptions.InvalidPath);
        } catch (IOException e){
            throw new Exception(Exceptions.WritePathExist);
        }

    }

    /**
     * Archive a package. Effectively, this package is deleted because it won't be loaded next time the program runs.
     *
     * @throws Exception if the archive route exits.
     */
    @Override
    public void archive() throws Exception {
        new File("user_data/users/" + this.username + "/archived_packages/").mkdirs();
        try {
            Files.move(new File("user_data/users/" + this.username + "/packages/" + this.pack.getName()).toPath(),
                    new File("user_data/users/" + this.username + "/archived_packages/" +
                            this.pack.getName()).toPath());
        } catch (IOException e){
            throw new Exception(Exceptions.ArchivePathExist);
        }

    } // FIXME: same problem as UserWriter.archive.
}
