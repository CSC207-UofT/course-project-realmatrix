package use_case.output_boundaries;

/**
 * An output boundary that shows the result of add action, such as add cards/packs.
 */
public interface AddOutputBoundary {
    /**
     * Setter for the result of adding things.
     * @param addResult true for successfully adding things; false otherwise.
     */
    void setAddResult(boolean addResult);

    /**
     * Getter for the result of adding things.
     * @return true for successfully adding things; false otherwise.
     */
    boolean getAddResult();

}
