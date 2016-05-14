

import java.awt.Color;
import java.awt.Rectangle;

public class TitleBar {
	private WindowSystem windowSystem;
	private SimpleWindow window;
	
	//coordinates of title bar
	private int titleBarTopLeftX;
	private int titleBarTopLeftY;
	private int titleBarBottomRightX;
	private int titleBarBottomRightY;
	
	//coordinates of the close button
	private int closeButtonTopLeftX;
	private int closeButtonTopLeftY;
	private int closeButtonBottomRightX;
	private int closeButtonBottomRightY;
	
	//coordinates of minimize button
	private int minimizeButtonTopLeftX;
	private int minimizeButtonTopLeftY;
	private int minimizeButtonBottomRightX;
	private int minimizeButtonBottomRightY;
	
	//setting height and width
	private int titleBarWidth;
	private final int titleBarHeight = 20;
	private final int closeButtonHeight = 20;
	private final int closeButtonWidth = 20;
	private final int minimizeButtonWidth = 20;
	private final int minimizeButtonHeight = 20;
	
	//setting color
	private final Color titleBarColor = Color.BLUE;
	private final Color closeButtonColor = Color.RED;
	private final Color highlightColor = Color.GREEN;
	private final Color minimizeButtonColor = Color.YELLOW;
	

	public TitleBar(WindowSystem system, SimpleWindow simpleWindow) {
		windowSystem = system;
		window = simpleWindow;
		int[] windowCoordinates = window.getWindowCoordinates();
		// top-left (x1, y1-h) bottom-right (x2,y1)
		titleBarTopLeftX = windowCoordinates[0];
		titleBarTopLeftY = windowCoordinates[1] - titleBarHeight;
		titleBarBottomRightX = windowCoordinates[2];
		titleBarBottomRightY = windowCoordinates[1];
		titleBarWidth = titleBarBottomRightX - titleBarTopLeftX;
		
		// top-left (x1, y1-h) bottom-right (x2,y1)
		closeButtonTopLeftX = windowCoordinates[2] - closeButtonWidth;
		closeButtonTopLeftY = windowCoordinates[1] - closeButtonHeight;
		closeButtonBottomRightX = titleBarBottomRightX;
		closeButtonBottomRightY = titleBarBottomRightY;
		
		// top-left (x1, y1-h) bottom-right (x2,y1)
		minimizeButtonTopLeftX = closeButtonTopLeftX - closeButtonWidth;
		minimizeButtonTopLeftY = closeButtonTopLeftY;
		minimizeButtonBottomRightX = titleBarBottomRightX - closeButtonWidth ;
		minimizeButtonBottomRightY = closeButtonBottomRightY ;
		
	}
	public Rectangle getRectangle() {
		Rectangle rectangle = new Rectangle(titleBarTopLeftX, titleBarTopLeftY, titleBarWidth
				, titleBarHeight);
		return rectangle;
	}
	public void moveTitleBar(int xDiff, int yDiff) {
		//setting the new cordinates of the title bar, close button and minimizebutton
		titleBarTopLeftX += xDiff;
		titleBarTopLeftY += yDiff;
		titleBarBottomRightX += xDiff;
		titleBarBottomRightY += yDiff;
		closeButtonTopLeftX += xDiff;
		closeButtonTopLeftY += yDiff;
		closeButtonBottomRightX += xDiff;
		closeButtonBottomRightY += yDiff;
		minimizeButtonTopLeftX += xDiff;
		minimizeButtonTopLeftY += yDiff;
		minimizeButtonBottomRightX += xDiff;
		minimizeButtonBottomRightY += yDiff;
		
	}

	public SimpleWindow getWindow() {
		return window;
	}
	
	//drawing titlebar using an instance of the WindowSystem class, since drawing is only done in handle paint 
	public void drawTitleBar() {
		windowSystem.setColor(titleBarColor);
		windowSystem.drawRect(titleBarTopLeftX, titleBarTopLeftY, titleBarBottomRightX, titleBarBottomRightY);
		windowSystem.fillRect(titleBarTopLeftX, titleBarTopLeftY, titleBarBottomRightX, titleBarBottomRightY);
		windowSystem.requestRepaint(new Rectangle(titleBarTopLeftX, titleBarTopLeftY, titleBarWidth, titleBarHeight));
	}

	//drawing closebutton using an instance of the WindowSystem class, since drawing is only done in handle paint 
	public void drawCloseButton() {
		windowSystem.setColor(closeButtonColor);
		windowSystem.drawRect(closeButtonTopLeftX, closeButtonTopLeftY, closeButtonBottomRightX, closeButtonBottomRightY);
		windowSystem.fillRect(closeButtonTopLeftX, closeButtonTopLeftY, closeButtonBottomRightX, closeButtonBottomRightY);
		windowSystem.requestRepaint(new Rectangle(closeButtonTopLeftX, closeButtonTopLeftY, closeButtonWidth, closeButtonHeight));
	}
	
	//drawing minimizebutton using an instance of the WindowSystem class, since drawing is only done in handle paint 
	public void drawMinimizeButton(){
		windowSystem.setColor(minimizeButtonColor);
		windowSystem.drawRect(minimizeButtonTopLeftX, minimizeButtonTopLeftY, minimizeButtonBottomRightX, minimizeButtonBottomRightY);
		windowSystem.fillRect(minimizeButtonTopLeftX, minimizeButtonTopLeftY, minimizeButtonBottomRightX, minimizeButtonBottomRightY);
		windowSystem.requestRepaint(new Rectangle(minimizeButtonTopLeftX, minimizeButtonTopLeftY, minimizeButtonWidth, minimizeButtonHeight));
		
	}

	//drawing titlebar bondary for fancy window dressing
	public void drawBoundary() {
		windowSystem.setColor(highlightColor);
		windowSystem.drawRect(titleBarTopLeftX, titleBarTopLeftY, titleBarBottomRightX, titleBarBottomRightY);
		windowSystem.requestRepaint(new Rectangle(titleBarTopLeftX, titleBarTopLeftY, titleBarWidth, titleBarHeight));
	}
	
	//checking the bounds for minimize button
	public boolean isWithinMinimizeButtonBounds(int x, int y){
		if (x >= minimizeButtonTopLeftX && x <= minimizeButtonBottomRightX 
				&& y >= minimizeButtonTopLeftY && y <= minimizeButtonBottomRightY){
			return true;
	}
		return false;
		
	}
	
	//checking the bounds for close button
	public boolean isWithinCloseButtonBounds(int x, int y) {
		if (x >= closeButtonTopLeftX && x <= closeButtonBottomRightX 
				&& y >= closeButtonTopLeftY && y <= closeButtonBottomRightY) {
			return true;
		}
		return false;
	}

	//checking the bounds for tilte bar
	public boolean isWithinBounds(int x, int y) {
		if (x >= titleBarTopLeftX && x <= titleBarBottomRightX 
				&& y >= titleBarTopLeftY && y <= titleBarBottomRightY) {
			return true;
		}
		return false;
	}
}
