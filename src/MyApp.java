

/* A WindowSystem class puts up an empty desktop when created 
 * passing in the size of the desktop as two int arguments for
 * the width and the height of the desktop. 
 * The program displays a single line from (0.2, 0.3) to (0.8, 0.7)
 * given in abstract coordinates on this desktop.
 */
public class MyApp {

	public static void main(String[] args) {
			WindowSystem windowSystem = new WindowSystem(1400,800);
			//creating three different windows using simplewindow
			SimpleWindow window1 = new SimpleWindow(100, 100, 300, 200, windowSystem);
			windowSystem.addWindow(window1);
		    SimpleWindow window2 = new SimpleWindow(300, 300, 450, 500, windowSystem);
		    windowSystem.addWindow(window2);
		    SimpleWindow window3 = new SimpleWindow(400, 600, 800, 700, windowSystem);
		    windowSystem.addWindow(window3);
	}

}