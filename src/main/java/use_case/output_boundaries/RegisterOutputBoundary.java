package use_case.output_boundaries;

/**
 * An output boundary for registration: between UserManager and registration presenter
 */
public interface RegisterOutputBoundary {
    void setRegisterResult(boolean result);

    boolean getRegisterResult();
}
