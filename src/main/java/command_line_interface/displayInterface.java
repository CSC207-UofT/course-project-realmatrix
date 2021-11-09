package command_line_interface;
import Controller.ProgramState;

public interface displayInterface {
    void setState(ProgramState setState);
    ProgramState getState();
    void prompt() throws Exception;

}
