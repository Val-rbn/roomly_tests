package utils;

import models.User;

public class TestUsers {

    public static final User EMPLOYEE_1 =
            new User(
                    "employee@northstar.local",
                    "EmployeePass!2026",
                    "employee"
            );

    public static final User EMPLOYEE_2 =
            new User(
                    "employee2@northstar.local",
                    "EmployeePass!2026",
                    "employee"
            );

    public static final User ADMIN =
            new User(
                    "admin@northstar.local",
                    "AdminPass!2026",
                    "admin"
            );
}