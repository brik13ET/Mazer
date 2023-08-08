package ru.ibragim;

import java.util.LinkedList;
import java.util.Queue;

public class BFSolver extends BaseSolver
{
	public BFSolver(Map m) {
		super(m);
	}

	@Override
	public boolean solve()
	{
		Queue<State> cursors = new LinkedList<State>();
		cursors.add(new State(this.Start));
		while (!cursors.isEmpty())
		{
			solution = cursors.poll();
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
					cursors.clear();
					solution = moved;
					break;
				}
				if (map.getAt(movedPosition) == Cell.Empty)
				{
					map.setAt(movedPosition, Cell.Path);
					cursors.add(moved);
				}

				elDirection = elDirection.rotateCW();
				solution.setDirection(elDirection);
			}
		}
		return cursors.isEmpty();
	}
}
