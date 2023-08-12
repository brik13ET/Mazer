package ru.ibragim.base;

public class Position {
    private int x, y;

    public Position(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    
    public Position()
    {
        this.x = 0;
        this.y = 0;
    }

    public int getX()
    {
        return x;
    }

    
    public int getY()
    {
        return y;
    }

    public void setX(int value)
    {
        this.x = value;
    }

    
    public void setY(int value)
    {
        this.y = value;
    }
}
