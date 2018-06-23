package de.upb.o4.tinyjavadot;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class DotGraph {
	Set<DotNode> knoten = new HashSet<>();
	Set<Edge> kanten = new HashSet<>();

	public String toDot() {
		String dot ="{";
		for(DotNode knote : knoten) {
			dot += knote.declaration() + "\n";
		}
		for(Edge kante : kanten) {
			dot += kante.declaration() + "\n";
		}
		 dot += "}";
		return dot;
	}

	public void addNode(DotNode knote) {
		knoten.add(knote);
	}

	public void connect(DotNode knoten1, DotNode knoten2) {
		kanten.add(new Edge(knoten1, knoten2));
	}

}

class Edge {
	DotNode a, b;

	boolean directed = false;

	Edge(DotNode a_, DotNode b_) {
		a = Objects.requireNonNull(a_);
		b = Objects.requireNonNull(b_);
	}

	boolean has(DotNode knoten) {
		return a == knoten || b == knoten;
	}



	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Edge edge = (Edge) o;

		if (a != null ? !a.equals(edge.a) : edge.a != null) return false;
		return b != null ? b.equals(edge.b) : edge.b == null;
	}

	@Override
	public int hashCode() {
		int result = a != null ? a.hashCode() : 0;
		result = 31 * result + (b != null ? b.hashCode() : 0);
		return result;
	}

	public String declaration() {
		String declaration = "";
		String arrow = (directed? "->" : "--");
		declaration += a.reference() + arrow + b.reference() + " [";
		declaration += "];";
		return declaration;
	}
}

