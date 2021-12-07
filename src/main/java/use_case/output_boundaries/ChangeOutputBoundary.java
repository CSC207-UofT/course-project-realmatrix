package use_case.output_boundaries;

/**
 * An output boundary that shows the result of changing action,
 * such as changing card term/definition, changing packname.
 * It's a layer between manager and presenter.
 */
public interface ChangeOutputBoundary {
    /**
     * Setter for the result of changing things.
     *
     * @param result true for successfully changing things; false otherwise.
     */
    void setChangeResult(boolean result);

    /**
     * Getter for the result of changing things.
     *
     * @return true for successfully changing things; false otherwise.
     */
    boolean getChangeResult();
}
