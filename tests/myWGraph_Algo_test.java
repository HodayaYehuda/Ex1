package ex1;


import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class myWGraph_Algo_test {


    //non connected graph
    public static WGraph_DS build10() {
        WGraph_DS a = new WGraph_DS();
        a.addNode(1);
        a.addNode(2);
        a.addNode(3);
        a.addNode(4);
        a.addNode(5);
        a.addNode(6);
        a.addNode(7);
        a.addNode(8);
        a.addNode(9);
        a.addNode(10);


        a.addNode(10);
        a.removeEdge(1, 3);
        a.removeNode(12);

        a.connect(1, 2, 5);
        a.connect(1, 4, 1);
        a.connect(3, 4, 7);
        a.connect(3, 6, 2);
        a.connect(8, 6, 3);
        a.connect(8, 7, 4);
        a.connect(3, 7, 4);
        a.connect(10, 7, 5);
        a.connect(8, 9, 10);

        return a;

    }


    // 10^6 nodes & edges
    public static WGraph_DS build1000000() {
        long startTime = System.currentTimeMillis();

        WGraph_DS b = new WGraph_DS();
        for (int i = 0; i < 1000000; i++) {
            b.addNode(i);
        }
        for (int i = 0; i < 1000000/4; i++) {
            b.connect(i, i + 1, i);
            // b.connect(i, i + 3, 2 * i);
            //   b.connect(i, i + 7, i / 2);
            //     b.connect(i, i + 9, i );
        }

        long endTime = System.currentTimeMillis();

        long duration = (endTime - startTime);
        System.out.println("time for build 10^6 vert && edges graph: " +duration/1000.0);

        return b;
    }
    @Test
    void isConnected() {
        WGraph_DS a = build10();

        weighted_graph_algorithms b = new WGraph_Algo();
        b.init(a);
        assertEquals(false , b.isConnected());

        //empty graph
        weighted_graph_algorithms c = new WGraph_Algo();
        assertEquals(true , c.isConnected());
    }

    // check the constructors && copy constructor
    @Test
    void copy() {
        WGraph_DS a = build10();
        WGraph_DS b = new WGraph_DS(a);

        WGraph_DS c = build1000000();

        long startTime = System.currentTimeMillis();
        WGraph_DS d = new WGraph_DS(c);

        long endTime = System.currentTimeMillis();
        long duration = (endTime - startTime);
        System.out.println("time for copy 10^6 vert && edges graph: " + duration / 1000.0);

        for (int i = 1; i < 10; i++) {
            for (int j = 1; j < 10; j++) {
                assertEquals(a.getEdge(i, j), b.getEdge(i, j));
            }}


        assertTrue(a.equals(b));
        // assertTrue(c.equals(d));
    }

    @Test
    void shortPath(){
        WGraph_DS a = build10();

        weighted_graph_algorithms b = new WGraph_Algo();
        b.init(a);

        Double ans = b.shortestPathDist(3,9);
        assertEquals(15 ,ans);

        Double ans2 = b.shortestPathDist(4,7);
        assertEquals(11 ,ans2);

        Double ans3 = b.shortestPathDist(5,7);
        assertEquals(-1 ,ans3);


    }

    @Test
    void shortPathList() {

        WGraph_DS a = build10();

        weighted_graph_algorithms b = new WGraph_Algo();
        b.init(a);
       List l = b.shortestPath(2 ,3);
       List<node_info> ans = new LinkedList<node_info>();
       ans.add(a.getNode(2));
        ans.add(a.getNode(1));
        ans.add(a.getNode(4));
        ans.add(a.getNode(3));
        assertEquals(l , ans);

        //path from node to himself
        List l2 = b.shortestPath(2 ,2);
        List<node_info> ans2 = new LinkedList<node_info>();
        ans2.add(a.getNode(2));
        assertEquals(l2 , ans2);

        //there is no path
        List l3 = b.shortestPath(2 ,5);
        assertEquals(l3 , null);


    }


    void save_load() {
        weighted_graph g0 = build10();
        weighted_graph_algorithms ag0 = new WGraph_Algo();
        ag0.init(g0);
        String name = "myGraph";
        ag0.save(name);

        weighted_graph g1 = build10();
        ag0.load(name);

        assertNotEquals(g0,g1);
    }
}
