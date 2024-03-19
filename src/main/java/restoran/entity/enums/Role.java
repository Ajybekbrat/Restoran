package restoran.entity.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ADMIN,
    CHEF,
    USER,
    WAITER;

    @Override
    public String getAuthority() {
        return name();
    }
}
