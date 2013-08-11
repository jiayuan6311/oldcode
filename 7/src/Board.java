import java.awt.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class Board extends JPanel
{

	public static final int BOARD_WIDTH = 515;
	public static int[] location = new int[22];
	static
	{
		for (int i = 0, WIDTH = 30; i < location.length; i++, WIDTH += 22)
		{
			location[i] = WIDTH;
		}
	}

	public Board(int x, int y) 
	{
		super(null);
		this.setBounds(x, y, BOARD_WIDTH, BOARD_WIDTH);
		this.setBackground(new Color(255, 255, 255));
	}

	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);

		g.setFont(new Font("ËÎÌå", Font.BOLD, 12));
		// »­ºáÏß
		for (int i = 0, width = 30 + 22 * 21; i < location.length; i++)
		{
			g.setColor(Color.black);
			g.drawLine(30, location[i], width, location[i]);
			g.setColor(Color.blue);
		}
		// »­ÊúÏß
		for (int i = 0, width = 30 + 22 * 21; i < location.length; i++)
		{
			g.setColor(Color.black);
			g.drawLine(location[i], 30, location[i], width);
			g.setColor(Color.blue);
		}

	}
}