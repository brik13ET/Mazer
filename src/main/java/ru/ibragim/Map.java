package ru.ibragim;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public class Map implements Serializable {
	Cell[] data;
	ru.ibragim.model.Map Db;
	private int width;
	private int heigth;

	public Map(int width, int heigth, float fill, int seed)
	{
		Db = new ru.ibragim.model.Map(width,heigth,fill,seed);
		this.width = width;
		this.heigth = heigth;
		data = new Cell[width * heigth];
			 if (fill < 0  ) fill  = 0;
		else if (fill > 100) fill  = 1;
		else				 fill /= 100;

		int BlockCount = (int)(width * heigth * fill) + 2; // Reserve 2 for `Start` and `End`

		var preIndexes = new ArrayList<Integer>(width*heigth);
		for (int i = 0; i < width*heigth; i++)
			preIndexes.add(i);
		Collections.shuffle(preIndexes, new Random(seed));
		int[] indexes = new int[BlockCount];
		var it = preIndexes.toArray();
		for (int i = 0; i < BlockCount; i++)
			indexes[i] = (Integer)it[i];

		// Fill empty BG
		for (int i = 0; i < data.length; i++)
			data[i] = Cell.Empty;
		
		for (int i = 0; i < indexes.length - 2; i++)
			data[indexes[i]] = Cell.Block;

		data[indexes[indexes.length - 2]] = Cell.Start;
		data[indexes[indexes.length - 1]] = Cell.End;
	}

	public Map(Map from)
	{
		this.width = from.width;
		this.heigth = from.heigth;
		data = new Cell[width * heigth];
		Db = from.Db;
		System.arraycopy(from.data, 0, data, 0, width*heigth);
	}

	
	public int	getWidth()	{ return width; }
	public int	getHeigth()	{ return heigth; }

	public Cell getAt(Position pos)
	{
		return data[pos.getX() + pos.getY() * width];
	}

	
	public void setAt(Position pos, Cell c)
	{
		data[pos.getX() + pos.getY() * width] = c;
	}

	

	@Override
	public String toString()
	{
		var sb = new StringBuilder();
		for (int i = 0; i < width + 2; i++)
			sb.append("█");
		sb.append("\r\n");

		for (int i = 0; i < data.length; i++) {
			if (i == 0)
				sb.append('█');
			else if (i % width == 0)
				sb.append("█\r\n█");
			sb.append(data[i].asChar());
		}

		sb.append("█\r\n");
		for (int i = 0; i < width + 2; i++)
			sb.append("█");
		return sb.toString();
	}

	public String toStringDirected(Cursor cur)
	{
		var sb = new StringBuilder();
		
		sb.append(toString());

		Position curPosition = BaseSolver.getStart(this);
		curPosition = new Position(curPosition.getX(), curPosition.getY()+ 1);

		for (Direction step : cur.getPath())
		{
			int x = curPosition.getX(), y = curPosition.getY();
			int sbPos = 1 + x + y * (width + 4);
			// System.out.printf("Cursor extracted: %s\tpos: (%d,%d)\t%d\n", step.toString(), x, y, sbPos);
			sb.setCharAt(
				sbPos, 
				step.toString().charAt(0)
			);
			curPosition = step.apply(curPosition);
		}
		return sb.toString();
	}

	public ru.ibragim.model.Map toDbMap()
	{
		return Db;
	}
}