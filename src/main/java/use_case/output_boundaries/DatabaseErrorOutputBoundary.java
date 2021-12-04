package use_case.output_boundaries;

/**
 * An output boundary for
 */
public interface DatabaseErrorOutputBoundary {
    void presentWriteErrMsg();
    void presentLoadErrMsg();
}
