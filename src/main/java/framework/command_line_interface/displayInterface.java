package framework.command_line_interface;

import interface_adapter.Controller.ProgramState;

public interface displayInterface {
    void setState(ProgramState setState);

    ProgramState getState();

    void prompt() throws Exception;

}
