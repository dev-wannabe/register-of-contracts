package pl.devwannabe.domain.User;

public interface SecurityService {

    String findLoggedInUsername();

    void autoLogin(String username, String password);

}