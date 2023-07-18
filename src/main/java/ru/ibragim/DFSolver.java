package ru.ibragim;

import java.io.Serializable;
import java.util.Stack;

public class DFSolver extends BaseSolver implements Serializable
{
	private Cursor finalPath;
	public DFSolver(Map m)
	{
		super(m);
	}

	@Override
	public boolean solve()
	{
		var cursors = new Stack<BaseSolver.Cursor>();
		cursors.push(new Cursor(this.Start));
		Cursor el = null;
		while (!cursors.isEmpty())
		{
			el = cursors.pop();
			Direction elDirection = el.getDirection();
			for (int dirs = 0; dirs < 4; dirs++) {
				var moved = el.move();
				var movedPosition = moved.getPosition();
				int
					movedPositionX = movedPosition.getX(),
					movedPositionY = movedPosition.getY();
				if (
					movedPositionX < 0 || movedPositionX >= map.width ||
					movedPositionY < 0 || movedPositionY >= map.heigth
				)
					continue;
				if (
					map.getAt(movedPosition) == Cell.End
				)
				{
					cursors.clear();;
					el = moved;
					break;
				}
				if (
					map.getAt(movedPosition) == Cell.Empty
				)
				{
					map.setAt(movedPosition, Cell.Path);
					cursors.push(moved);
				}
				elDirection = elDirection.rotateCW();
				el.setDirection(elDirection);
			}
		}
		finalPath = el;
		return cursors.isEmpty();
	}

	@Override
	public String toString()
	{
		return map.toStringDirected(finalPath);
	}
	
}
