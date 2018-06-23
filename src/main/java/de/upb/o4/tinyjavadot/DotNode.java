package de.upb.o4.tinyjavadot;

public class DotNode {

	String label;

	Shape shape = Shape.diamond;

	String link = null;

	DotNode(String label_) {
		label = label_;
	}

	public String reference() {
		return super.toString();
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

	void setLink(String link) {
		this.link = link;
	}

	public static enum Shape {
		diamond, box, ellipse, oval, circle, none, plain
	}
}
