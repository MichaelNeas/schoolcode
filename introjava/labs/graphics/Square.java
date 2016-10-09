import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
public class Square implements Shape
{
	private Color _color = Color.BLACK;
	private int _x, _y, _size;
	public Square(int x, int y, int size)
	{
		_x = x;
		_y = y;
		_size = size;
	}
	@Override
	public void paint(Graphics g)
	{
		g.setColor(_color);
		g.drawRect(_x, _y, _size, _size);
	}
	@Override
	public void mousePressed(MouseEvent mev)
	{
		if(_color.equals(Color.BLACK))
			_color = Color.GREEN;
		else
			_color = Color.BLACK;
	}
	@Override //checks for error
	public boolean contains(Point p)
	{
		return p.x >= _x && p.x < _x+_size && p.y >= _y && p.y < _y+_size;
	}
}