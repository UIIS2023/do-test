package geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

public class Donut extends Circle {

	private int innerRadius;

	public Donut() {

	}

	public Donut(Point center, int radius, int innerRadius) {
		super(center, radius);
		this.innerRadius = innerRadius;
	}

	public Donut(Point center, int radius, int innerRadius, boolean selected) {
		this(center, radius, innerRadius);
		this.setSelected(selected);
	}
	
	public Donut(Point center, int radius, int innerRadius, boolean selected, Color color) {
		this(center, radius, innerRadius, selected);
		this.setColor(color);
	}
	
	public Donut(Point center, int radius, int innerRadius, boolean selected, Color color, Color innerColor) {
		this(center, radius, innerRadius, selected, color);
		this.setInnerColor(innerColor);
	}
	
	@Override
	public int compareTo(Object o) {
		if (o instanceof Donut) {
			return (int) (this.area() - ((Donut) o).area());
		}
		return 0;
	}

	@Override
	public void fill(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        // Create the outer circle shape
        Ellipse2D outerCircle = new Ellipse2D.Double(
            this.getCenter().getX() - this.getRadius(),
            this.getCenter().getY() - this.getRadius(),
            this.getRadius() * 2, this.getRadius() * 2
        );

        // Create the inner circle shape
        Ellipse2D innerCircle = new Ellipse2D.Double(
            this.getCenter().getX() - this.getInnerRadius(),
            this.getCenter().getY() - this.getInnerRadius(),
            this.getInnerRadius() * 2, this.getInnerRadius() * 2
        );

        // Create an Area for the outer circle
        Area outerArea = new Area(outerCircle);

        // Create an Area for the inner circle
        Area innerArea = new Area(innerCircle);

        // Subtract the inner circle from the outer circle area
        outerArea.subtract(innerArea);

        // Set the inner color for the donut shape
        g2d.setColor(getInnerColor());
        g2d.fill(outerArea);
    }

	@Override
	public void draw(Graphics g) {
		super.draw(g);
		g.setColor(getColor());
		g.drawOval(this.getCenter().getX() - this.innerRadius, this.getCenter().getY() - this.innerRadius,
				this.innerRadius * 2, this.innerRadius * 2);
	}

	public double area() {
		return super.area() - innerRadius * innerRadius * Math.PI;
	}

	public boolean contains(int x, int y) {
		double dFromCenter = this.getCenter().distance(x, y);
		return super.contains(x, y) && dFromCenter > innerRadius;
	}

	public boolean contains(Point p) {
		double dFromCenter = this.getCenter().distance(p.getX(), p.getY());
		return super.contains(p.getX(), p.getY()) && dFromCenter > innerRadius;
	}

	public boolean equals(Object obj) {
		if (obj instanceof Donut) {
			Donut pomocni = (Donut) obj;
			if (this.getCenter().equals(pomocni.getCenter()) && this.getRadius() == pomocni.getRadius()
					&& this.innerRadius == pomocni.innerRadius) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public int getInnerRadius() {
		return innerRadius;
	}

	public void setInnerRadius(int innerRadius) {
		this.innerRadius = innerRadius;
	}
	public Donut clone() {			
		Donut donut= null;
		
		donut = new Donut(this.getCenter(), this.getRadius(),this.getInnerRadius(),this.isSelected(),this.getColor(),this.getInnerColor());
		donut.setSelected(this.isSelected());
			
		return donut;
	}

	public String toString() {
		return super.toString() + ", inner radius=" + innerRadius;
	}
}