package interface_adapter.gateway.datain;

import entity.User;
import interface_adapter.Controller.ProgramState;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

public class UserWriter extends Writer {
    private final User user;

    /**
     * Construct a CardWriter object.
     *
     * @param state the state the program is in
     * @param o     the object (pack) to write/update
     */
    public UserWriter(ProgramState state, Object o) {
        super(state, o);
        this.user = (User) o;
    }

    /**
     * Write or update a user
     *
     * @throws IOException
     */
    @Override
    public void write() throws IOException {
        new File("user_data/users/" + user.getName()).mkdirs();
        BufferedWriter writer =
                new BufferedWriter(new FileWriter("user_data/users/" + user.getName() + "/user_info.txt"));
        writer.write(user.getName() + "," + user.getPassword());
        writer.close();
    }

    /**
     * Archive a user. Effectively, this user is deleted because it won't be loaded next time the program runs.
     *
     * @throws IOException
     */
    @Override
    public void archive() throws IOException {
        new File("user_data/archived_users/").mkdirs();
        Files.move(new File("user_data/users/" + user.getName()).toPath(),
                new File("user_data/archived_users/" + user.getName()).toPath());
    }
}
