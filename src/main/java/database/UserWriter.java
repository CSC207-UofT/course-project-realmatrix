package database;

import Controller.ProgramState;
import entity.Card;
import entity.Pack;
import entity.User;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

public class UserWriter extends Writer{

    /**
     * Write or update a user
     * @param o the object (user) to write/update
     * @throws IOException
     */
    @Override
    public void write(ProgramState state, Object o) throws IOException {
        getName(state);     // get the path name we should write into
        User user = (User) o;
        new File("user_data/users/" + user.getName()).mkdirs();
        BufferedWriter writer =
                new BufferedWriter(new FileWriter("user_data/users/" + user.getName() + "/user_info.txt"));
        writer.write(user.getId() + "," + user.getName() + "," + user.getPassword());
        writer.close();
    }

    /**
     * Archive a user. Effectively, this user is deleted because it won't be loaded next time the program runs.
     * @param o the object (user) to archive
     * @throws IOException
     */
    @Override
    public void archive(ProgramState state, Object o) throws IOException {
        getName(state);     // get the path name we should write into
        User user = (User) o;
        new File("user_data/archived_users/").mkdirs();
        Files.move(new File("user_data/users/" + user.getName()).toPath(),
                new File("user_data/archived_users/" + user.getName()).toPath());
    }

    // For testing
//    public static void main(String[] args) throws IOException {
//        DataInOutFactory factory = new DataInOutFactory();
//        User user1 = new User("1234567890", "Snoopy", "password123");
//        Pack pack1 = new Pack("1947269283", "greeting101");
//        Pack pack2 = new Pack("1398195", "language101");
//        Card card1 = new Card("1934unsdai", "hello", "word for greeting");
//        Card card2 = new Card("3hwjdwv", "goodbye", "means farewell");
//        Card card3 = new Card("aodangauvou", "english", "a language");
//        Card card4 = new Card("aduvoauo", "Chinese", "another language");
//        factory.write(user1);
//        factory.write(user1, pack1);
//        factory.write(user1, pack2);
//        factory.write(user1, pack1, card1);
//        factory.write(user1, pack1, card2);
//        factory.write(user1, pack2, card3);
//        factory.write(user1, pack2, card4);
////        factory.archive(user1);
////        factory.archive(user1, pack1);
////        factory.archive(user1, pack1, card1);
//
//        User user2 = new User("3owgouapgb", "Pink Panther", "passwordaiogas");
//        factory.write(user2);
//        factory.write(user2, pack1);
//        factory.write(user2, pack2);
//        factory.write(user2, pack1, card1);
//        factory.write(user2, pack1, card2);
//        factory.write(user2, pack2, card3);
//        factory.write(user2, pack2, card4);
//    }
}
