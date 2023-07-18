package ru.ibragim;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

import ru.ibragim.BaseSolver.Cursor;

public class Map implements Serializable {
	Cell[] data;
	int width;
	int heigth;

	public Map(int width, int heigth, float fill, int seed)
	{
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

	public Map(Collection<Cell> col, int width, int heigth)
	{
		if (width * heigth != col.size())
			throw new IllegalArgumentException("Width * Height != col.size()");
		data = new Cell[col.size()];
		var i = col.iterator();
		int j = 0;
		Object el;
		while (i.hasNext())
		{
			el = i.next();
			data[j] = (Cell)el;
			j ++;
		}
	}

	public Map(Map from)
	{
		this.width = from.width;
		this.heigth = from.heigth;
		data = new Cell[width * heigth];
		System.arraycopy(from.data, 0, data, 0, width*heigth);
	}

	public Cell getAt(Position p)
	{
		return data[p.getX() + p.getY() * this.width];
	}
	
	public void setAt(Position p, Cell c)
	{
		data[p.getX() + p.getY() * this.width] = c;
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
		curPosition = new Position(curPosition.getX()- 1, curPosition.getY()+ 1);

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
}