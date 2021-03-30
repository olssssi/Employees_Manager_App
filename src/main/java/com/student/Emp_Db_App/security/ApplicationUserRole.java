package com.student.Emp_Db_App.security;

import com.google.common.collect.Sets;

import java.util.Set;

import static com.student.Emp_Db_App.security.ApplicationUserPermission.*;

public enum ApplicationUserRole {
    MANAGER(Sets.newHashSet(EMPLOYEES_READ)),
    BOSS(Sets.newHashSet(EMPLOYEES_READ,EMPLOYEES_ADD,EMPLOYEES_UPTADE)),
    SECRETARY(Sets.newHashSet(EMPLOYEES_READ,EMPLOYEES_ADD));

    private final Set<ApplicationUserPermission> permissions;

    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }
}
