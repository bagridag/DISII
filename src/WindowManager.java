

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.logging.Logger;

public class WindowManager {

	private static final int DEFAULT_SETTING = 0;
	private int pressedMouseX;
	private int pressedMouseY;
	private int xDiff;
	private int yDiff;


	private WindowSystem windowSystem;
	private Hashtable<Integer, TitleBar> titleBars;
	private Hashtable<Integer, SimpleWindow> windows;

	private SimpleWindow activeWindow;

	private Logger logger;

	public WindowManager(WindowSystem system) {
		windowSystem = system;
		logger = windowSystem.getLogger();
		titleBars = new Hashtable<Integer, TitleBar>();
	}

	public void addTitleBar(int key, SimpleWindow simpleWindow) {
		TitleBar titleBar = new TitleBar(windowSystem, simpleWindow);
		titleBars.put(key, titleBar);
		simpleWindow.setTitleBar(titleBar);
	}

	public void drawTitleBars() {

		Iterator<Entry<Integer, TitleBar>> iterator = titleBars.entrySet().iterator();

		while(iterator.hasNext()) {
			Entry<Integer, TitleBar> entry = iterator.next();
			TitleBar titleBar = entry.getValue();
			titleBar.drawTitleBar();
			titleBar.drawCloseButton();
			titleBar.drawMinimizeButton();
			windowSystem.requestRepaint();
		}
	}

	public void handleMouseClicked(int x, int y) {
		logger.info("Handle clicked called");
		Iterator<Entry<Integer, TitleBar>> iterator = titleBars.entrySet().iterator();

		while(iterator.hasNext()) {
			Entry<Integer, TitleBar> entry = iterator.next();
			TitleBar titleBar = entry.getValue();
			Integer key = entry.getKey();

			if((titleBar != null) && (titleBar.isWithinBounds(x,y) || titleBar.getWindow().isWithinWindowBounds(x, y))){
				if(titleBar.isWithinCloseButtonBounds(x, y)){
					if(titleBar.getWindow() == activeWindow){
						if(key + 1 <= titleBars.size()){
							setActiveWindow(windows.get(key+1));	
						}
						else{
							setActiveWindow(windows.get(DEFAULT_SETTING));
						}
					} 
					logger.info("Close button clicked");
					removeTitleBar(key);
					windowSystem.removeWindow(key); // window and titleBar keys correspond
					windowSystem.requestRepaint();
					return;
				}
				else{
					logger.info("After close bound if");
					setActiveWindow(titleBar.getWindow());
					windowSystem.requestRepaint(activeWindow.getWindowRectangle());
				}
			}
		}
	}

	protected void removeTitleBar(Integer key) {
		titleBars.remove(key);
		logger.info("Title bar removed from Hash table");
	}

	protected void setActiveWindow(SimpleWindow window) {
		logger.info("Active window being set...");
		activeWindow = window;
	}

	public void handleMouseDragged(int x, int y) {
		windows = windowSystem.getWindows();
		xDiff= x-pressedMouseX;
		yDiff= y-pressedMouseY;
		pressedMouseX= x;
		pressedMouseY= y;

		if(activeWindow != null && activeWindow.getTitleBar().isWithinBounds(x, y)){
			activeWindow.moveWindow(xDiff, yDiff);
			activeWindow.getTitleBar().moveTitleBar(xDiff, yDiff);
		}

	}

	public void handleMouseMoved(int x, int y) {
		windows = windowSystem.getWindows();
	}

	public void handleMousePressed(int x, int y) {
		windows = windowSystem.getWindows();

		Iterator<Entry<Integer, TitleBar>> iterator = titleBars.entrySet().iterator();

		while(iterator.hasNext()) {
			Entry<Integer, TitleBar> entry = iterator.next();
			TitleBar titleBar = entry.getValue();
			if(titleBar.isWithinBounds(x,y)){
				setActiveWindow(titleBar.getWindow());
			}
		}

		pressedMouseX = x;
		pressedMouseY = y;
	}

	public SimpleWindow getActiveWindow() {
		return activeWindow;
	}
}
