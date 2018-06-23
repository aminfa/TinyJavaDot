package de.upb.o4.tinyjavadot;
import org.junit.Test;

public class LibraryTest {
    @Test public void dotTest() {
        DotGraph g = new DotGraph();
        DotNode n = new DotNode("abc");
        g.addNode(n);
        System.out.println(g.toDot());

    }
}
