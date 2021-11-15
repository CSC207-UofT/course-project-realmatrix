package interface_adapter.gateway.datain;

import interface_adapter.Controller.ProgramState;
import entity.Pack;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

public class PackWriter extends Writer {
    private final Pack pack;

    /**
     * Construct a CardWriter object.
     *
     * @param state the state the program is in
     * @param o     the object (pack) to write/update
     */
    public PackWriter(ProgramState state, Object o) {
        super(state, o);
        this.pack = (Pack) o;
    }

    /**
     * Write or update a package
     *
     * @throws IOException
     */
    @Override
    public void write() throws IOException {
        new File("user_data/users/" + this.username + "/packages/" + this.pack.getName()).mkdirs();
        BufferedWriter writer =
                new BufferedWriter(new FileWriter("user_data/users/" + this.username
                        + "/packages/" + this.pack.getName() + "/package_info.txt"));
        writer.write(this.pack.getId() + "," + this.pack.getName());
        writer.close();
    }

    /**
     * Archive a package. Effectively, this package is deleted because it won't be loaded next time the program runs.
     *
     * @throws IOException
     */
    @Override
    public void archive() throws IOException {
        new File("user_data/users/" + this.username + "/archived_packages/").mkdirs();
        Files.move(new File("user_data/users/" + this.username + "/packages/" + this.pack.getName()).toPath(),
                new File("user_data/users/" + this.username + "/archived_packages/" +
                        this.pack.getName()).toPath());
    }
}
