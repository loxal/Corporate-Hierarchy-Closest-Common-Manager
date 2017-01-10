/*
 * Copyright 2017 Alexander Orlov <alexander.orlov@loxal.net>. All rights reserved.
 */

package net.loxal.example;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;

public class DepthFirstSearchTest {
    private static final Logger LOG = Logger.getGlobal();
    static Employee hans = new Employee(0, "Hans");
    static Employee hildegard = new Employee(1, "Hildegard");
    static Employee anna = new Employee(2, "Anna");
    static Employee maria = new Employee(3, "Maria");
    static Employee ludwig = new Employee(4, "Ludwig");
    static Employee sophia = new Employee(5, "Sophia");
    static Employee lena = new Employee(6, "Lena");
    static Employee alex = new Employee(7, "Alex");
    static Employee viktor = new Employee(8, "Viktor");
    static Employee albrecht = new Employee(9, "Albrecht");
    static Employee claudia = new Employee(10, "Claudia");
    static Employee igor = new Employee(11, "Igor");
    static Employee tanja = new Employee(12, "Tanja");
    private final DepthFirstSearch depthFirstSearch = new DepthFirstSearch();

    @BeforeClass
    public static void beforeClass() {

        LOG.info("@BeforeClass");

        hans.addReport(ludwig);
        hans.addReport(hildegard);

        hildegard.addReport(anna);
        hildegard.addReport(maria);

        ludwig.addReport(alex);
        ludwig.addReport(sophia);
        ludwig.addReport(lena);

        lena.addReport(claudia);
        lena.addReport(igor);
        lena.addReport(tanja);

        alex.addReport(viktor);

        viktor.addReport(albrecht);
    }

    @Before
    public void setUp() throws Exception {
        LOG.info("@Before");

        DepthFirstSearch depthFirstSearch = new DepthFirstSearch();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void assureHierarchy() throws Exception {
        assertEquals("Hildegard", depthFirstSearch.closestCommonManager(hans, maria, anna).getName());
        assertEquals("Hildegard", depthFirstSearch.closestCommonManager(hans, hildegard, maria).getName());
        assertEquals("Hildegard", depthFirstSearch.closestCommonManager(hans, hildegard, anna).getName());

        assertEquals("Hans", depthFirstSearch.closestCommonManager(hans, hildegard, ludwig).getName());
        assertEquals("Hans", depthFirstSearch.closestCommonManager(hans, albrecht, anna).getName());
        assertEquals("Hans", depthFirstSearch.closestCommonManager(hans, maria, viktor).getName());

        assertEquals("Viktor", depthFirstSearch.closestCommonManager(hans, viktor, albrecht).getName());
        assertEquals("Viktor", depthFirstSearch.closestCommonManager(hans, albrecht, viktor).getName());

        assertEquals("Lena", depthFirstSearch.closestCommonManager(hans, igor, tanja).getName());
        assertEquals("Lena", depthFirstSearch.closestCommonManager(hans, tanja, claudia).getName());

        assertEquals("Ludwig", depthFirstSearch.closestCommonManager(hans, tanja, albrecht).getName());
        assertEquals("Ludwig", depthFirstSearch.closestCommonManager(hans, sophia, viktor).getName());
        assertEquals("Ludwig", depthFirstSearch.closestCommonManager(hans, sophia, ludwig).getName());

        assertEquals("Alex", depthFirstSearch.closestCommonManager(hans, alex, viktor).getName());
    }
}
