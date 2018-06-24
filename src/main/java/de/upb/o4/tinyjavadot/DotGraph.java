package de.upb.o4.tinyjavadot;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;

public class DotGraph implements DotEntity {
	Set<DotEntity> entities = new HashSet<>();
	Set<Edge> kanten = new HashSet<>();

	public String toDot() {
		return "digraph" + createDotString();
	}

	private String createDotString() {
		String dot = "{\n\t";
		for (DotEntity entity : entities) {
			dot += entity.declaration().replaceAll("\n", "\n\t") + "\n\t";
		}
		for (Edge kante : kanten) {
			dot += kante.declaration() + "\n\t";
		}
		dot = dot.substring(0, dot.length()-1) +  "}\n";
		return dot;
	}

	public void addNode(DotNode knote) {
		entities.add(Objects.requireNonNull(knote));
	}
	public void addGraph(DotGraph dotGraph) {
		if(this == dotGraph) {
			throw new RuntimeException();
		}
		entities.add(Objects.requireNonNull(dotGraph));
	}

	public Edge connect(DotEntity e1, DotEntity e2) {
		Edge e = new Edge(e1, e2).directed();
		kanten.add(e);
		return e;
	}

	public Edge connectStyle(DotNode knoten1, DotNode knoten2, EdgeStyle style) {
		return connect(knoten1, knoten2).style(style);
	}

	@Override
	public String reference() {
		return String.valueOf(super.hashCode());
	}

	@Override
	public String declaration() {
		return "subgraph " + reference() + " " + createDotString();
	}

	public enum EdgeStyle {
		dashed,
		dotted,
		solid,
		invis,
		bold,
		filled,
		radial,
		diagonals,
		rounded;
	}
	public static class Edge {
		DotEntity a, b;

		DotGraph.EdgeStyle style = DotGraph.EdgeStyle.filled;

		boolean directed = false;

		Edge(DotEntity a_, DotEntity b_) {
			a = Objects.requireNonNull(a_);
			b = Objects.requireNonNull(b_);
		}

		public Edge directed() {
			directed = true;
			return this;
		}

		public Edge undirected() {
			directed = false;
			return this;
		}

		public Edge style(DotGraph.EdgeStyle style) {
			this.style = style;
			return this;
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
			String arrow = (directed ? "->" : "--");
			declaration += a.reference() + arrow + b.reference() + " [";
			declaration += "style=\"" + style.name() + "\"";
			declaration += "];";
			return declaration;
		}
	}


}