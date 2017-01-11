/*
 * Copyright 2017 Alexander Orlov <alexander.orlov@loxal.net>. All rights reserved.
 */

package net.loxal.understanding;

import java.util.Stack;
import java.util.logging.Logger;

class DepthFirstSearch {
    private static final Logger LOG = Logger.getGlobal();

    Employee closestCommonManager(Employee ceo, Employee white, Employee black) {
        Stack<Employee> whiteHierarchy = new Stack<>();
        Stack<Employee> blackHierarchy = new Stack<>();

        buildUpHierarchies(ceo, white, black, whiteHierarchy, blackHierarchy);

        int whiteHierarchyHeight = whiteHierarchy.size();
        int blackHierarchyHeight = blackHierarchy.size();
        int hierarchyDelta = Math.abs(blackHierarchyHeight - whiteHierarchyHeight);

        // reduce to the lowest height which is the highest hierarchy
        if (whiteHierarchyHeight > blackHierarchyHeight) {
            neutralizeHierarchyDelta(whiteHierarchy, hierarchyDelta);
        } else {
            neutralizeHierarchyDelta(blackHierarchy, hierarchyDelta);
        }

        reduceHierarchyUntilCommonManager(whiteHierarchy, blackHierarchy);

        return whiteHierarchy.pop();
    }

    private void reduceHierarchyUntilCommonManager(Stack<Employee> whiteHierarchy, Stack<Employee> blackHierarchy) {
        while (whiteHierarchy.peek().getId() != blackHierarchy.peek().getId()) {
            LOG.info("POP from “white” hierarchy: " + whiteHierarchy.pop().getName());
            LOG.info("POP from “black” hierarchy: " + blackHierarchy.pop().getName());
        }
    }

    private void buildUpHierarchies(Employee ceo, Employee white, Employee black, Stack<Employee> whiteHierarchy, Stack<Employee> blackHierarchy) {
        depthFirstSearch(ceo, white, whiteHierarchy);
        depthFirstSearch(ceo, black, blackHierarchy);
    }

    private boolean depthFirstSearch(Employee root, Employee target, Stack<Employee> hierarchy) {
        LOG.info("PUSHed root: " + hierarchy.push(root).getName());
        if (root.getId() == target.getId()) {
            return true;
        }

        for (Employee employee : root.getReports()) {
            boolean targetIsRoot = depthFirstSearch(employee, target, hierarchy);
            if (targetIsRoot) {
                return true;
            }
        }

        LOG.info("POPed: " + hierarchy.pop().getName());

        return false;
    }

    private void neutralizeHierarchyDelta(Stack<Employee> hierarchyToReduce, int hierarchyDelta) {
        while (hierarchyDelta-- > 0) {
            LOG.info("POPed to neutralize: " + hierarchyToReduce.pop().getName());
        }
    }
}
