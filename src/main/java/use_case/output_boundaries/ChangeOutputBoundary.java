package use_case.output_boundaries;


public interface ChangeOutputBoundary {
    void setChangeResult(boolean result);

    boolean getChangeResult();

    String presentChangeResult();
}
