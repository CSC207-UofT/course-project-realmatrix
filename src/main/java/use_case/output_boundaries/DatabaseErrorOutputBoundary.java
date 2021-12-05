package use_case.output_boundaries;

/**
 * An output boundary for database writers and loaders
 */
public interface DatabaseErrorOutputBoundary {
    void presentWriteErrMsg();
    void presentLoadErrMsg();
}
