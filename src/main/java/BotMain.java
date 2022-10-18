import java.awt.*;
import java.io.IOException;
import java.util.Random;

import static java.awt.MouseInfo.getPointerInfo;


public class BotMain {
    public static void main(String[] args) throws AWTException, IOException {

        Random random = new Random();

//        String  shellBritnessMin = "(Get-WmiObject -Namespace root/WMI -Class WmiMonitorBrightnessMethods).WmiSetBrightness(1,0)";
//        String  shellBritnessNormal = "(Get-WmiObject -Namespace root/WMI -Class WmiMonitorBrightnessMethods).WmiSetBrightness(1,60)";

        long count = 1;

        Robot robot = new Robot();

        System.out.println("Starting Always On Display Until user interfere....");
        System.out.println("Decreasing brightness to low....");
        BritghnessControll.setBrightness(0);
        System.out.println("!!! Control is taken by machine you take Rest !!!\n");

        robot.delay(3000);

//        robot.setAutoDelay(2000);
//         robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);

        int x1 = (int) getPointerInfo().getLocation().getX();
        int y1 = (int) getPointerInfo().getLocation().getY();

        int lastX = x1;
        int lastY = y1;

        while (true) {
            for (int i = 0; i < 20; i++) {

                x1 = (int) getPointerInfo().getLocation().getX();
                y1 = (int) getPointerInfo().getLocation().getY();

                if (!BotMain.checkDeviation(x1, y1, lastX, lastY)) {

                    System.out.println("\nIncreasing brightness to comfort....");
                    BritghnessControll.setBrightness(70);
                    System.out.println("Position seem to have been moved more than 50 pixel " +
                            "\n Current position at X - " + x1 + " Y - " + y1);

                    System.out.println("!!! Control has been transfer to user !!!");
                    return;
                }
                robot.delay(1000);
            }

            int x = random.ints(250, 1500).findFirst().getAsInt();
            int y = random.ints(200, 800).findFirst().getAsInt();

            for (int i = 0; i < 100; i++) {
                int movX = ((x * i) / 100) + (x1 * (100 - i) / 100);
                int movY = ((y * i) / 100) + (y1 * (100 - i) / 100);
                robot.mouseMove(movX, movY);
                robot.delay(10);

            }
            lastX = x;
            lastY = y;

            System.out.println(count + " :\t Mouse is at position" + "\t X at" + x + "\t Y at" + y);

            count++;
        }

    }


    public static boolean checkDeviation(int x1, int y1, int lastX, int lastY) {
//     Iterator<Integer> integerIterator =  Arrays.stream(x).iterator();
        return ((lastX - 50 < x1) && (x1 < lastX + 50)) && ((lastY - 50 < y1) && (y1 < lastY + 50));
    }
}
