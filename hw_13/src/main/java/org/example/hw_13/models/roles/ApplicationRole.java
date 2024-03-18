package org.example.hw_13.models.roles;

import java.util.HashSet;
import java.util.Set;

public enum ApplicationRole implements Role {
    ADMIN, USER;

    private final Set<Role> childRoles = new HashSet<>();

    static {
        ADMIN.childRoles.add(USER);
    }

    @Override
    public boolean hasRole(Role role) {
        return this.equals(role) || childRoles.stream().anyMatch(r -> r.hasRole(role));
    }
}
