package bearmaps.proj2ab;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class KDTreeTest {

    @Test
    public void testNearestOnebyOne() {
        List<Point> points = new ArrayList<>();
        Point p1 = new Point(0, 0);
        points.add(p1);
        KDTree tree = new KDTree(points);
        assertEquals(p1, tree.nearest(1, 1));

        Point p2 = new Point(.5, .5);
        points.add(p2);
        KDTree tree2 = new KDTree(points);
        assertEquals(p2, tree2.nearest(1, 1));

        Point p3 = new Point(2, 2);
        points.add(p3);
        KDTree tree3 = new KDTree(points);
        assertEquals(p2, tree2.nearest(1, 1));

        Point p4 = new Point(.75, .75);
        points.add(p4);
        KDTree tree4 = new KDTree(points);
        assertEquals(p4, tree4.nearest(1, 1));
    }

    @Test
    public void testAwholeLotohStuff() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(100, 0);
        Point p3 = new Point(0, 100);
        Point p4 = new Point(23, 25);
        Point p5 = new Point(64, 32.5);
        Point p6 = new Point(254, 0);
        Point p7 = new Point(0, 5);
        Point p8 = new Point(98, 440);
        Point p9 = new Point(1, 1);
        List<Point> points = new ArrayList<>();
        points.add(p1);
        points.add(p2);
        points.add(p3);
        points.add(p4);
        points.add(p5);
        points.add(p6);
        points.add(p7);
        points.add(p8);
        points.add(p9);
        KDTree tree = new KDTree(points);
        assertEquals(p9, tree.nearest(2, 2));
    }

    @Test
    public void allAlongXaxis() {
        Point p1 = new Point(1, 0);
        Point p2 = new Point(2, 0);
        Point p3 = new Point(3, 0);
        Point p4 = new Point(4, 0);
        Point p5 = new Point(5, 0);
        Point p6 = new Point(6, 0);
        Point p7 = new Point(7, 0);
        Point p8 = new Point(8, 0);
        Point p9 = new Point(9, 0);
        List<Point> points = new ArrayList<>();
        points.add(p1);
        points.add(p2);
        points.add(p3);
        points.add(p4);
        points.add(p5);
        points.add(p6);
        points.add(p7);
        points.add(p8);
        points.add(p9);
        KDTree tree = new KDTree(points);

        assertEquals(p1, tree.nearest(-1, 0));
        assertEquals(p1, tree.nearest(0, 0));
        assertEquals(p1, tree.nearest(0, 1));

        assertEquals(p2, tree.nearest(1.75, 0));
        assertEquals(p2, tree.nearest(2, 0));
        assertEquals(p2, tree.nearest(2, 1));

        assertEquals(p3, tree.nearest(2.75, 0));
        assertEquals(p3, tree.nearest(3, 0));
        assertEquals(p3, tree.nearest(3, 1));

        assertEquals(p4, tree.nearest(3.75, 0));
        assertEquals(p4, tree.nearest(4, 0));
        assertEquals(p4, tree.nearest(4, 1));
    }


    @Test
    public void allAlongYaxis() {
        Point p1 = new Point(0, 1);
        Point p2 = new Point(0, 2);
        Point p3 = new Point(0, 3);
        Point p4 = new Point(0, 4);
        Point p5 = new Point(0, 5);
        Point p6 = new Point(0, 6);
        Point p7 = new Point(0, 7);
        Point p8 = new Point(0, 8);
        Point p9 = new Point(0, 9);
        List<Point> points = new ArrayList<>();
        points.add(p1);
        points.add(p2);
        points.add(p3);
        points.add(p4);
        points.add(p5);
        points.add(p6);
        points.add(p7);
        points.add(p8);
        points.add(p9);
        KDTree tree = new KDTree(points);

        assertEquals(p1, tree.nearest(-1, 0));
        assertEquals(p1, tree.nearest(0, 0));
        assertEquals(p1, tree.nearest(0, 1));
        assertEquals(p1, tree.nearest(100, 1));


        assertEquals(p2, tree.nearest(-1, 2));
        assertEquals(p2, tree.nearest(0, 2));
        assertEquals(p2, tree.nearest(-100, 2));

        assertEquals(p3, tree.nearest(0, 3));
        assertEquals(p3, tree.nearest(-1, 3));
        assertEquals(p3, tree.nearest(100, 3));

        assertEquals(p4, tree.nearest(0, 4));
        assertEquals(p4, tree.nearest(-1, 4));
        assertEquals(p4, tree.nearest(1, 4));

        assertEquals(p9, tree.nearest(0, 100));
        assertEquals(p1, tree.nearest(0, -1));
        assertEquals(p9, tree.nearest(100, 100));
    }

    @Test
    public void testWithRandomInputsandOneCalculatedinput() {
        List<Point> points = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            points.add(new Point(StdRandom.uniform(-1000, 1000), StdRandom.uniform(-1000, 1000)));
        }
        Point p1 = new Point(666, 666);
        Point p2 = new Point(-234, -546);
        points.add(p1);
        points.add(p2);
        KDTree tree = new KDTree(points);
        assertEquals(p1, tree.nearest(666.1, 665.9));
        assertEquals(p2, tree.nearest(-234.1, -546.1));

    }
}
