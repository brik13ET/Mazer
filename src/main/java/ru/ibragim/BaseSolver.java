package ru.ibragim;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Queue;

public abstract class BaseSolver {
	
	protected Map map;

	protected Position Start, End;

	public static Position getStart(Map m)
	{
		for (int i = 0; i < m.width; i++)
		{
			for (int j = 0; j < m.heigth; j++)
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
		for (int i = 0; i < m.width; i++)
		{
			for (int j = 0; j < m.heigth; j++)
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

	public class Cursor implements Serializable
	
	{
		private Position currentPosition;
		private Direction currentDirection;
		private Queue<Direction> path = new LinkedList<>();

		public Cursor(Position pos)
		{
			currentPosition = pos;
			currentDirection = Direction.Up;
		}

		public Queue<Direction> getPath()
		{
			return path;
		}

		public Direction getDirection()
		{
			return currentDirection;
		}

		public Position getPosition()
		{
			return currentPosition;
		}

		public void setDirection(Direction value)
		{
			this.currentDirection = value;
		}

		public void setPosition(Position value)
		{
			this.currentPosition = value;
		}
		
		public Cursor move()
		{
			Cursor ret = new Cursor(
				 currentDirection.apply(
					currentPosition
				)
			);
			ret.path = new LinkedList<>(this.path);
			ret.path.add(currentDirection);
			ret.currentDirection = currentDirection;
			return ret;
		}

		@Override
		public String toString()
		{
			return currentDirection.toString();
		}
	}

	public abstract boolean solve();
}
