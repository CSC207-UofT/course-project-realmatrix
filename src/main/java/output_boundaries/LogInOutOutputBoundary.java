package output_boundaries;

import use_case.manager.LogInOutManager;

public interface LogInOutOutputBoundary {
    public void setLogInOutResult(LogInOutManager.LoggedIn loginResult);
    public void presentLogInOutResult();
}
