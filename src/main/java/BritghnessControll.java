import java.io.IOException;

public class BritghnessControll {


        public static void setBrightness(int brightness)
                throws IOException {

            String s = String.format("$brightness = %d;", brightness)
                    + "$delay = 0;"
                    + "$myMonitor = Get-WmiObject -Namespace root\\wmi -Class WmiMonitorBrightnessMethods;"
                    + "$myMonitor.wmisetbrightness($delay, $brightness)";
            String command = "powershell.exe  " + s;

            Process powerShellProcess = Runtime.getRuntime().exec(command);

            powerShellProcess.getOutputStream().close();
        }
    }

