import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.prasanthj.github.SyslogParser;

/**
 * Created by prasanthj on 2019-01-09.
 */
public class SyslogToConsolePrinter {

  public static void main(String[] args) {
    ClassLoader classloader = Thread.currentThread().getContextClassLoader();
    List<String> inputs = new ArrayList<>();
    inputs.add("test.log");
    for (String file : inputs) {
      InputStream is = classloader.getResourceAsStream(file);
      SyslogParser syslogParser = new SyslogParser();
      int events = 0;
      if (is != null) {
        try (Scanner scanner = new Scanner(is)) {
          while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (!line.isEmpty()) {
              ByteArrayInputStream bis = new ByteArrayInputStream(line.getBytes());
              syslogParser.setInputStream(bis);
              try {
                List<Object> row = syslogParser.readEvent();
                if (row == null) {
                  break;
                }
                events++;
                if (row.get(3) != null) {
                  System.out.print(row.get(3) + " ");
                  Map<String, String> data = (Map<String, String>) row.get(8);
                  System.out.print(data.get("level") + " [" + data.get("thread") + "] " + data.get("class") + ": ");
                  System.out.println(row.get(9));
                } else {
                  System.out.println(row.get(10));
                }
              } catch (Exception e) {
                e.printStackTrace();
              }
            }
          }
        }
      }
      System.out.println("Emitted events: " + events + " file: " + file);
    }
  }
}
