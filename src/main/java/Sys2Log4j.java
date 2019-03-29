import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.prasanthj.github.SyslogParser;

/**
 * Created by prasanthj on 2019-01-09.
 */
public class Sys2Log4j {
  public static void main(String[] args) {
    SyslogParser syslogParser = new SyslogParser();
    try (Scanner scanner = new Scanner(System.in)) {
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        try {
          if (!line.isEmpty()) {
            ByteArrayInputStream bis = new ByteArrayInputStream(line.getBytes());
            syslogParser.setInputStream(bis);
            List<Object> row = syslogParser.readEvent();
            if (row != null) {
              if (row.get(3) != null) {
                System.out.print(row.get(3) + " ");
                Map<String, String> data = (Map<String, String>) row.get(8);
                System.out.print(data.get("level"));
                System.out.print(" [" + data.get("thread") + "] " + data.get("class") + ": ");
                System.out.println(new String((byte[]) row.get(9)));
              } else {
                System.out.println(new String((byte[]) row.get(10)));
              }
            }
          } else {
            System.out.println();
          }
        } catch (Exception e) {
          IOException ioe = new IOException("Failed parsing line: " + line, e);
          ioe.printStackTrace();
          System.exit(-1);
        }
      }
    }
  }
}
