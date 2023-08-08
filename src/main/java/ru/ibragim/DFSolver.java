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
		var cursors = new Stack<State>();
		cursors.push(new State(this.Start));
		while (!cursors.isEmpty())
		{
			solution = cursors.pop();
			Direction elDirection = solution.getDirection();
			for (int dirs = 0; dirs < 4; dirs++) {
				var moved = solution.move();
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
					solution = moved;
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
				solution.setDirection(elDirection);
			}
		}
		return cursors.isEmpty();
	}
	
}
