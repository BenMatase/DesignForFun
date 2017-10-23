import java.awt.Robot;
import com.leapmotion.leap.*;
import java.awt.event.*;
import com.leapmotion.leap.Gesture.State;
import java.awt.Dimension;
import java.awt.AWTException;

class CustomListener extends Listener {
	// The automator. More in the information document under "Leap Motion 101"
	// http://www.arcadespot.com/game/mario-kart-64/
	public Robot robot;
	public int counter = 0;
	public int previous_hand_is_left = 0;
	// 0 is not left hand, 1 is left hand

	// Executes once your program and detector are connected, are there any specific gestures you're going
	// to look for later on? Uncomment the ones you want to enable below
	public void onConnect(Controller controller) {
		System.out.println("Connected");
		//  controller.enableGesture(Gesture.Type.TYPE_CIRCLE);
		//  controller.enableGesture(Gesture.Type.TYPE_SWIPE);
		//  controller.enableGesture(Gesture.Type.TYPE_SCREEN_TAP);
		//  controller.enableGesture(Gesture.Type.TYPE_KEY_TAP);
		controller.setPolicy(Controller.PolicyFlag.POLICY_BACKGROUND_FRAMES); // allows the program to run in places other than the terminal
	}

	// Executes when detector stops working/is no longer connected to computer
	public void onDisconnect(Controller controller) {
		System.out.println("Disconnected");
	}

	// Executes when you manually stop running the detector/program
	public void onExit(Controller controller) {
		System.out.println("Exited");
	}

	// More info in the Leap Motion 101 section
	public void onFrame(Controller controller) {
		try {
			// Creating our robot friend. Feel free to name it anything you'd like
			robot = new Robot();
		} catch (Exception e) {
		}
		Frame frame = controller.frame();
		/*––––––––––––*/
		/* CODE HERE. */
		/*––––––––––––*/
		// javac -classpath LeapJava.jar MarioKart.java
		// java -classpath "LeapJava.jar;." MarioKart
		for (Hand hand : frame.hands()) {
			int fCount = 0;
			String handType = hand.isLeft() ? "left" : "right";
			// get the normal vector of the hand. Used to detect tilt
			Vector normal = hand.palmNormal();
			Vector direction = hand.direction();
			double roll = Math.toDegrees(normal.roll());
			double pitch = Math.toDegrees(direction.pitch());
			double hand_angle = Math.toDegrees(hand.grabStrength());
			double yaw = Math.toDegrees(direction.yaw());
			Vector handSpeed = hand.palmVelocity();
			if (hand.isLeft()) {
				System.out.println(hand_angle);				
				if (hand_angle > 55){
					robot.keyPress(KeyEvent.VK_K);
					try {
						Thread.sleep(50);
					} catch (InterruptedException ex) {
						Thread.currentThread().interrupt();
					}
					robot.keyRelease(KeyEvent.VK_K);

				}
				if (roll > 25) {
					previous_hand_is_left = 1;					
					System.out.println('1');
					// if (previous_hand_is_left == 1 && counter > 0) {
					// 	counter--;
					// } else {
					// 	counter = 0;
					// 	robot.keyPress(KeyEvent.VK_A);
					// 	try {
					// 		Thread.sleep(50);
					// 	} catch (InterruptedException ex) {
					// 		Thread.currentThread().interrupt();
					// 	}
					// 	robot.keyRelease(KeyEvent.VK_A);
					// }

					robot.keyPress(KeyEvent.VK_A);
					try {
						Thread.sleep(50);
					} catch (InterruptedException ex) {
						Thread.currentThread().interrupt();
					}
					robot.keyRelease(KeyEvent.VK_A);

				} else if (roll < -25) {
					previous_hand_is_left = 0;					
					System.out.println('2');
					// if (previous_hand_is_left == 0 && counter > 0) {
					// 	counter--;
					// } else {
					// 	counter = 0;
					// 	robot.keyPress(KeyEvent.VK_D);
					// 	try {
					// 		Thread.sleep(50);
					// 	} catch (InterruptedException ex) {
					// 		Thread.currentThread().interrupt();
					// 	}
					// 	robot.keyRelease(KeyEvent.VK_D);
					// }
					robot.keyPress(KeyEvent.VK_D);
					try {
						Thread.sleep(50);
					} catch (InterruptedException ex) {
						Thread.currentThread().interrupt();
					}
					robot.keyRelease(KeyEvent.VK_D);
				}
			} else {
				if (handSpeed.getY() > 1000) {
					System.out.println('y');
					System.out.println(handSpeed.getY());
				}
				if ((handSpeed.getZ() < -700)) {
					robot.keyPress(KeyEvent.VK_SPACE);
					try {
						Thread.sleep(50);
					} catch (InterruptedException ex) {
						Thread.currentThread().interrupt();
					}
					robot.keyRelease(KeyEvent.VK_SPACE);
				}
			}
		}

	} // end of listener class
}

// The "main()" function
// NAME THIS CLASS WHATEVER YOUR FILE IS NAMED
class MarioKart {
	public static void main(String[] args) {
		// initializes our detector
		CustomListener listener = new CustomListener();
		Controller controller = new Controller();
		controller.addListener(listener);
		System.out.println("Press Enter to quit...");

		try {
			System.in.read();
		} catch (Exception e) {
		}
		controller.removeListener(listener);
	}
}
