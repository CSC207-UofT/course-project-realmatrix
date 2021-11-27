package interface_adapter.gateway.datain;

import entity.User;
import use_case.constants.Exceptions;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;

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
                new BufferedWriter(new FileWriter(userFilePath + "/user_info.txt"));
        writer.write(user.getPassword());
        writer.close();
    }

    /**
     * Write the user's new name into database by renaming the user directory.
     *
     * @param oldName the user's old name
     * @param newO the user with new name
     * @throws Exception if the path of oldName is invalid or the new path exits
     */
    @Override
    public void write(String oldName, Object newO) throws Exception {
        User newUser = (User) newO;
        try{
            Path old = Paths.get("user_data/users/" + oldName);
            Files.move(old, old.resolveSibling(newUser.getName()));
        } catch (InvalidPathException e){
            throw new Exception(Exceptions.InvalidPath);
        } catch (IOException e){
            throw new Exception(Exceptions.WritePathExist);
        }


    }

    /**
     * Archive a user. Effectively, this user is deleted because it won't be loaded next time the program runs.
     *
     * @throws Exception if the archive rout exists.
     */
    @Override
    public void archive() throws Exception {
        try{
            new File("user_data/archived_users/").mkdirs();
            Files.move(new File("user_data/users/" + user.getName()).toPath(),
                    new File("user_data/archived_users/" + user.getName()).toPath());
        } catch (IOException e){
            throw new Exception(Exceptions.ArchivePathExist);
        }

    // FIXME: may be deleted completely without archiving, cuz move throws exception if target path exists.
    }
}
