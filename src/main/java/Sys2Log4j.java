import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.apache.commons.lang3.StringEscapeUtils;

import com.prasanthj.github.SyslogParser;

/**
 * Created by prasanthj on 2019-01-09.
 */
public class Sys2Log4j {
  private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
  private static final String LOG_LEVEL_FORMAT = "%5s";

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
                System.out.print(SIMPLE_DATE_FORMAT.format(row.get(3)) + " ");
                Map<String, String> data = (Map<String, String>) row.get(8);
                System.out.print(String.format(LOG_LEVEL_FORMAT, data.get("level")));
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
