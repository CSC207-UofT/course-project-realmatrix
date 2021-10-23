package database;

import entity.Pack;
import entity.User;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class PackWriter {

    public void writePack(User user, Pack pack) throws IOException {
        new File("user_data/" + user.getName() + "/packages/" + pack.getName()).mkdirs();
        BufferedWriter writer =
                new BufferedWriter(new FileWriter("user_data/" + user.getName()
                        + "/packages/" + pack.getName() + "/package_info.txt"));
        writer.write(pack.getId() + "," + pack.getName());
        writer.close();
    }

    public void erasePack(User user, Pack pack) throws IOException {}

    public void updatePack(User user, Pack pack) throws IOException {}
}
