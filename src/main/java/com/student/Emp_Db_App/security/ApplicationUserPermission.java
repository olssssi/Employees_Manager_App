package com.student.Emp_Db_App.security;

public enum ApplicationUserPermission {
    EMPLOYEES_READ("employees:read"),
    EMPLOYEES_ADD("employees:add"),
    EMPLOYEES_UPTADE("employees:uptade");

    private final String perrmision;

    ApplicationUserPermission(String perrmision) {
        this.perrmision = perrmision;
    }

    public String getPerrmision() {
        return perrmision;
    }
}
