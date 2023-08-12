package ru.ibragim.base;

public enum Direction
{
	Up  	('v'),
	Right	('>'),
	Down	('^'),
	Left	('<');
	
	private char asChar;	
	
	Direction(char ch)
	{
		asChar = ch;
	}
	
	public Direction rotateCW()
	{
		switch(this)
		{
			case Down:
				return Left;
			case Left:
				return Up;
			case Up:
				return Right;
			case Right:
				return Down;
			default:
				throw new IllegalArgumentException("Inconsistent previus enum value");
			
		}
	}
	
	public Position apply(Position pos)
	{
		switch(this)
		{
			case Up:
				return new Position(pos.getX(), pos.getY() + 1);
			case Right:
				return new Position(pos.getX() + 1, pos.getY());
			case Down:
				return new Position(pos.getX(), pos.getY() - 1);
			case Left:
				return new Position(pos.getX() - 1, pos.getY());
			default:
				throw new IllegalArgumentException("Inconsistent previus enum value");
			
		}
	}
	
	@Override
	public String toString()
	{
		return "" + asChar;
	}
}
