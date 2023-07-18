package ru.ibragim;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class BFSolver extends BaseSolver
{
	public BFSolver(Map m) {
		super(m);
	}

	private Cursor finalPath;

	@Override
	public boolean solve()
	{
		Queue<Cursor> cursors = new LinkedList<Cursor>();
		cursors.add(new Cursor(this.Start));
		Cursor el = null;
		while (!cursors.isEmpty())
		{
			el = cursors.poll();
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
					cursors.clear();
					el = moved;
					break;
				}
				if (map.getAt(movedPosition) == Cell.Empty)
				{
					map.setAt(movedPosition, Cell.Path);
					cursors.add(moved);
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
