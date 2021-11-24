package interface_adapter.gateway.datain;

import entity.User;
import interface_adapter.gateway.DataInOut;
import use_case.manager.ProgramStateManager;
import use_case.manager.UserManager;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class UserWriter extends Writer {
    private final User user;

    /**
     * Construct a CardWriter object.
     * @param partialDataPath including username and packname (if it's not null)
     * @param o     the object (pack) to write/update
     */
    public UserWriter(String[] partialDataPath, Object o) {
        super(partialDataPath);
        this.user = (User) o;
    }

    /**
     * Write a new user into database.
     * @throws IOException
     */
    @Override
    public void write() throws IOException {
        String userFilePath = "user_data/users/" + user.getName();
        new File(userFilePath).mkdirs();
        BufferedWriter writer =
                new BufferedWriter(new FileWriter(userFilePath + "/password.txt"));
        writer.write(user.getName() + "," + user.getPassword());
        writer.close();
    }

    /**
     * Write the user's new name into database by renaming the user directory.
     *
     * @param oldName the user's old name
     * @param newName the user's new name
     * @throws IOException fails to write
     */
    @Override
    public void write(String oldName, String newName) throws IOException {
        Path old = Paths.get("user_data/users/" + oldName);
        Files.move(old, old.resolveSibling(newName));
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
    // FIXME: may be deleted completely without archiving, cuz move throws exception if target path exists.
    }
}
