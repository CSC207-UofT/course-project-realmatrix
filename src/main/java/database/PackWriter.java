package database;

import Controller.ProgramState;
import entity.Pack;
import entity.User;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

public class PackWriter extends Writer {

    /**
     * Write or update a package
     * @param state the state the program is in
     * @param o the object (pack) to write/update
     * @throws IOException
     */
    @Override
    public void write(ProgramState state, Object o) throws IOException {
        getName(state);     // get the path name we should write into
        Pack pack = (Pack) o;
        new File("user_data/users/" + this.username + "/packages/" + pack.getName()).mkdirs();
        BufferedWriter writer =
                new BufferedWriter(new FileWriter("user_data/users/" + this.username
                        + "/packages/" + pack.getName() + "/package_info.txt"));
        writer.write(pack.getId() + "," + pack.getName());
        writer.close();
    }

    /**
     * Archive a package. Effectively, this package is deleted because it won't be loaded next time the program runs.
     * @param state the state the program is in
     * @param o the object (pack) to archive
     * @throws IOException
     */
    @Override
    public void archive(ProgramState state, Object o) throws IOException {
        getName(state);     // get the path name we should write into
        Pack pack = (Pack) o;
        new File("user_data/users/" + this.username + "/archived_packages/").mkdirs();
        Files.move(new File("user_data/users/" + this.username + "/packages/" + pack.getName()).toPath(),
                new File("user_data/users/" + this.username + "/archived_packages/" +
                        pack.getName()).toPath());
    }
}
