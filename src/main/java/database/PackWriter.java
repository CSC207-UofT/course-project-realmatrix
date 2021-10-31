package database;

import entity.Pack;
import entity.User;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

public class PackWriter {

    /**
     * Write or update a package
     * @param user the user whose package to write/update
     * @param pack the package to write/update
     * @throws IOException
     */
    public void writePack(User user, Pack pack) throws IOException {
        new File("user_data/users/" + user.getName() + "/packages/" + pack.getName()).mkdirs();
        BufferedWriter writer =
                new BufferedWriter(new FileWriter("user_data/users/" + user.getName()
                        + "/packages/" + pack.getName() + "/package_info.txt"));
        writer.write(pack.getId() + "," + pack.getName());
        writer.close();
    }

    /**
     * Archive a package. Effectively, this package is deleted because it won't be loaded next time the program runs.
     * @param user the user whose package to archive
     * @param pack the package to archive
     * @throws IOException
     */
    public void archivePack(User user, Pack pack) throws IOException {
        new File("user_data/users/" + user.getName() + "/archived_packages/").mkdirs();
        Files.move(new File("user_data/users/" + user.getName() + "/packages/" + pack.getName()).toPath(),
                new File("user_data/users/" + user.getName() + "/archived_packages/" +
                        pack.getName()).toPath());
    }
}
