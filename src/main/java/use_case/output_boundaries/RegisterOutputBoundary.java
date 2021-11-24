package use_case.output_boundaries;

public interface RegisterOutputBoundary {
    void setRegisterResult(boolean result);

    void setErrorMessage(String);

    String presentRegisterResult();
}
