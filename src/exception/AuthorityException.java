package exception;

import service.Role;

/**
 * Created by junyuan on 03/01/2017.
 */
public class AuthorityException extends Exception {
    private Role role;

    public AuthorityException(Role role) {
        this.role = role;
    }

    @Override
    public String getMessage() {
        return "Permission Denied. Present Role: " + this.role;
    }
}
