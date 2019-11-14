package pl.devwannabe.domain.user;

public interface SecurityService {

    String findLoggedInUsername();

    void autoLogin(String username, String password);

}