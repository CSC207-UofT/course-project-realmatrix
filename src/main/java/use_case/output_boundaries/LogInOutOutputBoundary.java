package use_case.output_boundaries;

/**
 * An output boundary for login: between login manager and login presenter.
 */
public interface LogInOutOutputBoundary {
    void setLogInOutResult(boolean loginResult);

    boolean getLogInOutResult();

}
