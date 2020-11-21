package ex1.tests;

import ex1.src.WGraph_DS;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class myWGraph_DS_test {

    @Test
    //there is an descreption of this graph at the README
    void build() {
        WGraph_DS a = new WGraph_DS();
        assertEquals(0 ,a.edgeSize());
        assertEquals(0 ,a.edgeSize());

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

        a.connect(1, 2, 2);
        a.connect(1, 2, 5);
        a.connect(1, 4, 1);
        a.connect(3, 4, 7);
        a.connect(3, 6, 2);
        a.connect(8, 6, 3);
        a.connect(8, 7, 4);
        a.connect(3, 7, 4);
        a.connect(10, 7, 5);
        a.connect(8, 9, 10);

        assertTrue(a.hasEdge(1,2));
        assertEquals( 5 ,a.getEdge(1,2));
        assertEquals(10 , a.getV().size());
        assertEquals(9 , a.edgeSize());
        assertEquals(20 , a.getMC());
    }

    @Test
    void build1000(){



            long startTime = System.currentTimeMillis();

            WGraph_DS b = new WGraph_DS();
            for (int i = 0; i < 1000000; i++) {
                b.addNode(i);
            }
            for (int i = 0; i < 1000000/4; i++) {

            b.connect(i, i + 1, i);
            b.connect(i, i + 3, 2 * i);
            b.connect(i, i + 7, i / 2);
            b.connect(i, i + 1, i );
            b.connect(i, i +i, i / 2);
            b.connect(i, i + 4, i );
            b.connect(i, i + 1, i);
            b.connect(i, i + 3, 2 * i);
            b.connect(i, i + 7, i / 2);
            b.connect(i, i + 9, i );


            }

        long endTime = System.currentTimeMillis();

        long duration = (endTime - startTime);
        System.out.println("time for build 10^6 vert && 10*(10^6) edges graph: " +duration/1000.0);

    }



}

