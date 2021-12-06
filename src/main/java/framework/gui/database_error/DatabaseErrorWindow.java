package framework.gui.database_error;

import javax.swing.*;

/**
 * A window that shows database error message.
 */
public class DatabaseErrorWindow implements IDatabaseErrorWindow {
    /**
     * Present load database error message.
     */
    @Override
    public void presentLoadErrMsg() {
        JOptionPane.showMessageDialog(null,
                "Cannot load database. Try again or restart.",
                "Database failures.",
                JOptionPane.WARNING_MESSAGE);
    }

    /**
     * Present error messages for saving to database.
     */
    @Override
    public void presentWriteErrMsg() {
        JOptionPane.showMessageDialog(null,
                "Cannot save to database. Try again or restart.",
                "Database failures.",
                JOptionPane.WARNING_MESSAGE);
    }
}
