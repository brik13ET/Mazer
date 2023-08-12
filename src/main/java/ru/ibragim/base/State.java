package ru.ibragim.base;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Queue;

public class State implements Serializable
	
	{
		private Position currentPosition;
		private Direction currentDirection;
		private Queue<Direction> path = new LinkedList<>();

		public State(Position pos)
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
		
		public State move()
		{
			State ret = new State(
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
