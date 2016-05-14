

import java.awt.Color;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.logging.Logger;

import de.rwth.hci.Graphics.GraphicsEventSystem;

public class WindowSystem extends GraphicsEventSystem {
	// Create the Hashtable-windowTable to store the SimpleWindow objects
	public Hashtable<Integer, SimpleWindow> windowTable = new Hashtable<Integer, SimpleWindow>();

	// Initialize the parameters for window size
	private int desktopWidth, desktopHeight;
	private WindowManager windowManager;

	private int nextKey = 0;
	private Logger logger = Logger.getLogger("WindowSystem");

	public WindowSystem(int width, int height) {
		super(width, height);
		setBackground(Color.BLACK);
		// Set the width and height of the desktop
		// coordinates as given by the user
		desktopWidth = width;
		desktopHeight = height;
		windowManager = new WindowManager(this);
	}

	// Determine the X coordinates in the "Desktop"
	private int convertCoordinateX(float i_X) {
		int NewX;
		NewX = (int) (desktopWidth * i_X);
		return NewX;

	}

	// Determine the Y coordinates in the "Desktop"
	private int convertCoordinateY(float i_Y) {
		int NewY;
		NewY = (int) (desktopHeight * i_Y);
		return NewY;

	}

	public Hashtable<Integer, SimpleWindow> getWindows() {
		return windowTable;
	}
	
	public Logger getLogger(){
		return logger;
	}

	public void addWindow(SimpleWindow simpleWindow) {
		windowTable.put(nextKey, simpleWindow);
		windowManager.addTitleBar(nextKey, simpleWindow);
		nextKey++;
		windowManager.setActiveWindow(simpleWindow);
	}

	public void removeWindow(int key) {
		logger.info("Removing window");
		windowTable.remove(key);
		logger.info("Windowtable removed");
		this.requestRepaint();
		logger.info("Repaint called");
	
	}

	void drawLine(float StartX, float StartY, float EndX, float EndY) {
		// Initialize and set the relative X and Y coordinates
		// of the line end points
		int NewStartX = convertCoordinateX(StartX);
		int NewStartY = convertCoordinateY(StartY);
		int NewEndX = convertCoordinateX(EndX);
		int NewEndY = convertCoordinateY(EndY);

		drawLine(NewStartX, NewStartY, NewEndX, NewEndY);
	}

	@Override
	protected void handlePaint() {
		// Draw all windows in the hash table
		
		Iterator<Entry<Integer, SimpleWindow>> iterator = windowTable.entrySet().iterator();
		while(iterator.hasNext()) {
			Entry<Integer, SimpleWindow> entry = iterator.next();
			SimpleWindow window = entry.getValue();
				window.drawWindow();
				window.getTitleBar().drawTitleBar();
				window.getTitleBar().drawCloseButton();
				window.getTitleBar().drawMinimizeButton();
				requestRepaint(window.getWindowRectangle());
				requestRepaint(window.getTitleBar().getRectangle());
		}
		if(windowManager.getActiveWindow()!= null){
			windowManager.getActiveWindow().drawWindow();
			windowManager.getActiveWindow().getTitleBar().drawTitleBar();
			windowManager.getActiveWindow().getTitleBar().drawCloseButton();;
			windowManager.getActiveWindow().getTitleBar().drawMinimizeButton();
     		windowManager.getActiveWindow().drawBoundary();
			windowManager.getActiveWindow().getTitleBar().drawBoundary();
			requestRepaint();

		}
	}

	@Override
	public void handleMouseClicked(int x, int y) {
		super.handleMouseClicked(x, y);
		logger.info("Clicked");
		windowManager.handleMouseClicked(x, y);
	}

	@Override
	public void handleMouseDragged(int x, int y) {
		super.handleMouseDragged(x, y);
		windowManager.handleMouseDragged(x, y);
	}

	@Override
	public void handleMouseMoved(int x, int y) {
		super.handleMouseMoved(x, y);
		windowManager.handleMouseMoved(x, y);
	}

	@Override
	public void handleMousePressed(int x, int y) {
		super.handleMousePressed(x, y);
		windowManager.handleMousePressed(x, y);
	}
}