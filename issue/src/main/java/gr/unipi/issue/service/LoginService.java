package gr.unipi.issue.service;

public interface LoginService {
    public int getUserLoginAttempts(String username);
    public void updateLoginAttempts(String username);
    public void successfulLogin(String username);
}
