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
					System.out.println(
						map.toStringDirected(el)
					);
					return true;
				}
				if (
					map.getAt(movedPosition) == Cell.Empty ||
					map.getAt(movedPosition) == Cell.End
				)
				{
					map.setAt(movedPosition, Cell.Path);
					cursors.push(moved);
				}
				elDirection = elDirection.rotateCW();
				el.setDirection(elDirection);
			}
		}
		System.out.println(
			map.toStringDirected(el)
		);
		return cursors.isEmpty();
	}
	
}
