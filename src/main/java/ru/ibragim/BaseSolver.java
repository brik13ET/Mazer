package ru.ibragim;

import java.io.Serializable;

import ru.ibragim.model.Turn;

public abstract class BaseSolver implements Serializable {
	
	protected Map map;

	protected Position Start, End;

	protected Cursor solution;

	public static Position getStart(Map m)
	{
		for (int i = 0; i < m.getWidth(); i++)
		{
			for (int j = 0; j < m.getHeigth(); j++)
			{
				var current = new Position(i, j);
				var cur_cell = m.getAt(current);
				if (cur_cell == Cell.Start)
					return current;
			}
		}
		throw new IllegalArgumentException("Cannot find startpoint");
	}

	public static Position getEnd(Map m)
	{
		for (int i = 0; i < m.getWidth(); i++)
		{
			for (int j = 0; j < m.getHeigth(); j++)
			{
				var current = new Position(i, j);
				var cur_cell = m.getAt(current);
				if (cur_cell == Cell.End)
					return current;
			}
		}
		throw new IllegalArgumentException("Cannot find endpoint");
	}

	public BaseSolver(Map m)
	{
		map = new Map(m);
		Start = getStart(m);
		End = getEnd(m);
	}

	public Turn[] retieveData()
	{
		return Turn.fromCursors(solution.getPath(), map, this.getClass().getName());
	}

	public abstract boolean solve();
	public abstract String toString();
}
