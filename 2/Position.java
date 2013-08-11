

public class Position 
{
	private int row;
	private int column;
	
	Position()
	{
		this.row = 0;
		this.column = 0;
	}
	
	Position(int row, int column)
	{
		this.row = row;
		this.column = column;
	}

	public int getColumn() 
	{
		return column;
	}

	public void setColumn(int column) 
	{
		this.column = column;
	}

	public int getRow() 
	{
		return row;
	}

	public void setRow(int row) 
	{
		this.row = row;
	}
	
	public boolean equals(Position pos)
	
	
	{
			return this.row == pos.row&&this.column==pos.column;
		
	}
	
	public String toString()
	{
		return "[" + row + "," + column + "]";
	}
}                  //包含坐标点各种信息的类

