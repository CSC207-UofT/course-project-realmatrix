package use_case.input_boundaries;

import java.io.IOException;

public interface ManagerInputBoundary {
    public boolean deleteItem(String name);
    public void write() throws IOException;
    public void archive() throws IOException;
}
