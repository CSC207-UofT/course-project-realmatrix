package use_case.input_boundaries;

public interface ManagerInputBoundary {
    public boolean deleteItem(String name);
    public void write();
    public void archive();
}
