package output_boundaries;

import manager.LogInOutManager;

public interface LogInOutOutputBoundary {
    public void setLogInOutResult(LogInOutManager.LoggedIn loginResult);
    public void presentLogInOutResult();
}
