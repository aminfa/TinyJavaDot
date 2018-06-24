package de.upb.o4.tinyjavadot;

import org.junit.Test;

public class LibraryTest {
    @Test public void dotTest() {
        DotGraph g = new DotGraph();

        DotNode n1 = new DotNode("abc");


        DotNode n2 = new DotNode("def");

        DotNode n3 = new DotNode("def");

        DotNode.setDefaultShape(DotNode.Shape.diamond);

        g.addNode(n1);
        g.addNode(n2);
        g.addNode(n3);
        g.connect(n1, n2);
        g.connect(n2, n3);
        g.connect(n3, n1);

        g.connectStyle(n1, n3, DotGraph.EdgeStyle.dotted);

        DotGraph g2 = new DotGraph();
        DotNode n11 = new DotNode("abc");


        DotNode n21 = new DotNode("def");

        DotNode n31 = new DotNode("def");


        g2.addNode(n11);
        g2.addNode(n21);
        g2.addNode(n31);
        g2.connect(n11, n2);
        g2.connect(n21, n3);
        g2.connect(n31, n1);

        g.addGraph(g2);


        DotGraph g3 = new DotGraph();
        DotNode n12 = new DotNode("abc");


        DotNode n22 = new DotNode("def");

        DotNode n32 = new DotNode("def");


        g3.addNode(n12);
        g3.addNode(n22);
        g3.addNode(n32);
        g3.connect(n12, n2);
        g3.connect(n22, n3);
        g3.connect(n32, n1);

        g.addGraph(g3);

        System.out.println(g.toDot());
//        System.out.println(g3.toDot());
    }
}
