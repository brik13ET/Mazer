package ru.ibragim;

public class AStarSolver extends BaseSolver
{

	public AStarSolver(Map m)
	{
		super(m);
	}

	protected class CostedCursor extends Cursor
	{

		

		public CostedCursor(Position pos)
		{
			super(pos);
		}
		
	}

	@Override
	public boolean solve()
	{
		
		return false;
	}

	@Override
	public String toString()
	{
		return null;
	}
	
}
