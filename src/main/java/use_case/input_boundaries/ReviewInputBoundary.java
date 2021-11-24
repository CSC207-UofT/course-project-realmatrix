package use_case.input_boundaries;

import entity.Card;

import java.util.ArrayList;

public interface ReviewInputBoundary {
    Card next();

    public void setShowDefinition();

    public void setCantRecall();
}
