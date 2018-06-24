package de.upb.o4.tinyjavadot;

public final class DotNode implements DotEntity {

	private static Shape defaultShape = Shape.box;

	private static long globalId = 0;

	private final long id;

	String label;

	Shape shape;

	String link;

	{
		shape = defaultShape;
		link = null;
		id = globalId;
		globalId = globalId == Long.MAX_VALUE ? 0 : globalId+1;
	}

	public DotNode(String label_) {
		label = label_;
	}

	public String reference() {
		return "node_" + id;
	}

	public String declaration() {
		String declaration = "";
		declaration += reference();
		declaration += " [";
		declaration += "label=\"" + label() + "\"";
		declaration += ",shape=" + shape.name() + "";
		if(link !=null) {
			declaration += ",href=" + link + "";
		}
		declaration += "];";
		return declaration;
	}

	public String label() {
		return label;
	}

	public void setShape(Shape shape_) {
		shape = shape_;
	}

	public static void setDefaultShape(Shape shape_) {
		DotNode.defaultShape = shape_;
	}

	void setLink(String link) {
		this.link = link;
	}

	public static enum Shape {
		diamond, box, ellipse, oval, circle, none, plain
	}

	@Override
	public boolean equals(Object o) {
		return this == o;
	}

	@Override
	public int hashCode() {
		return Long.hashCode(id);
	}
}
