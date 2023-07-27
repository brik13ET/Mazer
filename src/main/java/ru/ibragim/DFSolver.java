package ru.ibragim;

import java.util.Stack;

public class DFSolver extends BaseSolver
{
	public DFSolver(Map m)
	{
		super(m);
	}

	@Override
	public boolean solve()
	{
		var cursors = new Stack<Cursor>();
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
					movedPositionX < 0 || movedPositionX >= map.getWidth() ||
					movedPositionY < 0 || movedPositionY >= map.getHeigth()
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
		solution = el;
		return cursors.isEmpty();
	}

	@Override
	public String toString()
	{
		return map.toStringDirected(solution);
	}
	
}
