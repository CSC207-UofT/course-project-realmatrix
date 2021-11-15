package interface_adapter.presenters;

import use_case.output_boundaries.ChangeOutputBoundary;

public class ChangePresenter implements ChangeOutputBoundary {
    private boolean changeResult;

    @Override
    public void setChangeResult(boolean result) {
        this.changeResult = result;
    }

    @Override
    public void presentChangeResult() {
        if (this.changeResult) {
            System.out.println("OK! You have the new username now.");
        } else {
            System.out.println("This name is taken. Please use another username");
        }
    }
}
