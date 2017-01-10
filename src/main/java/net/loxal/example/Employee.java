/*
 * Copyright 2017 Alexander Orlov <alexander.orlov@loxal.net>. All rights reserved.
 */

package net.loxal.example;

import java.util.ArrayList;
import java.util.List;

class Employee {

    private final Integer id;
    private final String name;
    private final List<Employee> reports;

    Employee(Integer id, String name) {
        this.id = id;
        this.name = name;
        this.reports = new ArrayList<>();
    }

    Integer getId() {
        return id;
    }

    String getName() {
        return name;
    }

    List<Employee> getReports() {
        return reports;
    }

    void addReport(Employee employee) {
        reports.add(employee);
    }
}