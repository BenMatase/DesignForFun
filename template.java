import java.awt.Robot;
import com.leapmotion.leap.*;
import java.awt.event.*;
import com.leapmotion.leap.Gesture.State;
import java.awt.Dimension;
import java.awt.AWTException;


class CustomListener extends Listener {
  // The automator. More in the information document under "Leap Motion 101"
  public Robot robot;

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
    } catch(Exception e) {}
    Frame frame = controller.frame();
    /*––––––––––––*/
    /* CODE HERE. */
    /*––––––––––––*/
  }

} // end of listener class



// The "main()" function
// NAME THIS CLASS WHATEVER YOUR FILE IS NAMED
class MarioKart {
  public static void main(String[] args){
    // initializes our detector
    CustomListener listener = new CustomListener();
    Controller controller = new Controller();
    controller.addListener(listener);
    System.out.println("Press Enter to quit...");

    try {
      System.in.read();
    } catch(Exception e) {}
    controller.removeListener(listener);
  }
}
