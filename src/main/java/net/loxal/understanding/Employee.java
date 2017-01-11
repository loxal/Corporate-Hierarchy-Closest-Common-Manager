/*
 * Copyright 2017 Alexander Orlov <alexander.orlov@loxal.net>. All rights reserved.
 */

package net.loxal.understanding;

import java.util.ArrayList;
import java.util.List;

class Employee {

    private final int id;
    private final String name;
    private final List<Employee> reports;

    Employee(int id, String name) {
        this.id = id;
        this.name = name;
        this.reports = new ArrayList<>();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Employee{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", reports=").append(reports);
        sb.append('}');
        return sb.toString();
    }

    int getId() {
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