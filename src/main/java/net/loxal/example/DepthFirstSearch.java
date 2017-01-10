/*
 * Copyright 2017 Alexander Orlov <alexander.orlov@loxal.net>. All rights reserved.
 */

package net.loxal.example;

import java.util.Objects;
import java.util.Stack;
import java.util.logging.Logger;

public class DepthFirstSearch {
    public static final Logger LOG = Logger.getGlobal();

    public DepthFirstSearch() {
    }

    public static void main(String... args) {
        new DepthFirstSearch();
    }

    Employee closestCommonManager(Employee ceo, Employee one, Employee other) {
        Stack<Employee> onePath = new Stack<>();
        Stack<Employee> otherPath = new Stack<>();

        Employee root = ceo;

        depthFirstSearch(root, one, onePath);
        depthFirstSearch(root, other, otherPath);

        if (Objects.equals(onePath.peek().getId(), one.getId()) && Objects.equals(otherPath.peek().getId(), other.getId())) {
            int oneSize = onePath.size();
            int otherSize = otherPath.size();
            int difference = Math.abs(otherSize - oneSize);

            if (oneSize > otherSize) {
                moveUp(onePath, difference);
            } else {
                moveUp(otherPath, difference);
            }

            while (!Objects.equals(onePath.peek().getId(), otherPath.peek().getId())) {
                onePath.pop();
                otherPath.pop();
            }

            if (onePath.size() > 0) {
                return onePath.pop();
            }
        }
        return null;
    }

    private boolean depthFirstSearch(Employee root, Employee target, Stack<Employee> path) {
        path.push(root);
        if (Objects.equals(root.getId(), target.getId())) {
            return true;
        }

        for (Employee employee : root.getReports()) {
            boolean result = depthFirstSearch(employee, target, path);
            if (result) {
                return true;
            }
        }

        path.pop();

        return false;
    }

    private void moveUp(Stack<Employee> path, int difference) {
        while (difference > 0 && !path.isEmpty()) {
            path.pop();
            difference--;
        }
    }
}
