package GUI.start;

import javax.swing.*;
import java.awt.*;

public class StartButton extends JButton {
    public StartButton(String name) {
        super(name);
        setFont(new Font("verdana", Font.BOLD | Font.ITALIC, 20));
    }
}
