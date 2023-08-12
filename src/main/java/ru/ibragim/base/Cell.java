package ru.ibragim.base;

public enum Cell {
	
	Empty	(' '),
	Block	('▓'),
	Start	('S'),
	End 	('E'),
	Path	('░');


	Cell(char ch)
	{
		visual = ch;
	}
	
	private char visual;

	public char asChar()
	{
		return visual;
	}

	@Override
	public String toString()
	{
		return "" + visual;
	}
}
