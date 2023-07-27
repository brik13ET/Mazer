package ru.ibragim;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Queue;

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
