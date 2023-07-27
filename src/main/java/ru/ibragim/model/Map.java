package ru.ibragim.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "Maps")
public class Map implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "Width", nullable = false)
	private int width;

	@Column(name = "Heigth", nullable = false)
	private int heigth;

	@Column(name = "Fill", nullable = false)
	private float fill;

	@Column(name = "Seed", nullable = false)
	private int seed;

	public Map() {  this(20, 20, .2f, 0); }

	public Map(int width, int heigth, float fill, int seed)
	{
		if (
			width <= 0 ||
			heigth <= 0 ||
			fill < 0 ||
			fill > 100
		)
			throw new IllegalArgumentException();
			
		this.width = width;
		this.heigth = heigth;
		this.fill = fill;
		this.seed = seed;
	}
	
	public int	getWidth()	{ return width; }
	public int	getHeigth()	{ return heigth; }
	public float getFill()	{ return fill; }
	public long	getId()  	{ return id; }
	public int	getSeed()	{ return seed; }

	@Override
	public String toString() {
		return "Map { .id=" + id + "\t.width=" + width + "\t.heigth=" + heigth + "\t.fill=" + fill + "\t.seed=" + seed + " }";
	}
}
