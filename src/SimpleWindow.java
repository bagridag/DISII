

import java.awt.Color;
import java.awt.Rectangle;

public class SimpleWindow {
	//declaring window cordinates
	private int x1, y1, x2, y2;
	private WindowSystem windowSystem;
	private TitleBar titleBar;
	
	public TitleBar getTitleBar() {
		return titleBar;
	}
	
	//setting title bar
	public void setTitleBar(TitleBar titleBar) {
		this.titleBar = titleBar;
	}

	
	public SimpleWindow(int X1, int Y1, int X2, int Y2, WindowSystem windowSystem) {
		x1 = X1;
		y1 = Y1;
		x2 = X2;
		y2 = Y2;
		this.windowSystem = windowSystem;
	}
	
	//storing window cordinates in an array to draw windows in handle paint
	public int[] getWindowCoordinates() {
		int[] coordinates = new int[4];
		coordinates[0] = x1;
		coordinates[1] = y1;
		coordinates[2] = x2;
		coordinates[3] = y2;
		return coordinates;
	}
	
	//move function for moving the window around
	public void moveWindow(int xDiff, int yDiff) {
		x1 += xDiff;
		y1 += yDiff;
		x2 += xDiff;
		y2 += yDiff;
	}
	
	
	public Rectangle getWindowRectangle() {
		Rectangle rectangle = new Rectangle(x1, y1, x2-x1, y2-y1);
		return rectangle;
	}
	
	//drawing window using an instance of the class windowsystem.
	public void drawWindow() {
		windowSystem.setColor(Color.GRAY);
		windowSystem.drawRect(x1,y1,x2,y2);
		windowSystem.fillRect(x1,y1,x2,y2);
		windowSystem.requestRepaint(new Rectangle(x1, y1, x2-x1, y2-y1));
	}
	
	//boundary for fancy window dressing which represents active window on click
	public void drawBoundary() {
		windowSystem.setColor(Color.GREEN);
		windowSystem.drawRect(x1,y1,x2,y2);
		windowSystem.requestRepaint(new Rectangle(x1, y1, x2-x1, y2-y1));
	}
	
	//checking bounds of the mouse click on window
	public boolean isWithinWindowBounds(int x, int y) {
		if(x >= x1 && x <=x2 && y >= y1 && y <= y2){
			return true;
		}
		return false;
	}
}
