import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
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
    long unmatched = 0;
    long matched = 0;
    long nulls = 0;
    boolean unmatchedOnly = args.length == 1 && args[0].equals("-u");
    boolean matchedOnly = args.length == 1 && args[0].equals("-m");
    try (Scanner scanner = new Scanner(System.in)) {
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        try {
          if (!line.isEmpty()) {
            ByteArrayInputStream bis = new ByteArrayInputStream(line.getBytes());
            syslogParser.setInputStream(bis);
            List<Object> row = syslogParser.readEvent();
            String matchedOut = "";
            String unmatchedOut = "";
            if (row != null) {
              if (row.get(3) != null) {
                java.sql.Timestamp sqlTs = ((org.apache.hadoop.hive.common.type.Timestamp)row.get(3)).toSqlTimestamp();
                matchedOut += SIMPLE_DATE_FORMAT.format(sqlTs) + " ";
                Map<String, String> data = (Map<String, String>) row.get(8);
                matchedOut += String.format(LOG_LEVEL_FORMAT, data.get("level"));
                matchedOut += " [" + data.get("thread") + "] " + data.get("class") + ": ";
                if (row.get(9) != null) {
                  matchedOut += new String((byte[]) row.get(9));
                }
                matched++;
              } else {
                unmatched++;
                unmatchedOut += new String((byte[]) row.get(10));
              }
            }
            if (matchedOnly) {
              System.out.println(matchedOut);
            } else if (unmatchedOnly) {
              System.out.println(unmatchedOut);
            } else {
              if (!matchedOut.isEmpty()) {
                System.out.println(matchedOut);
              }
              if (!unmatchedOut.isEmpty()) {
                System.out.println(unmatchedOut);
              }
            }
          } else {
            nulls++;
            System.out.println();
          }
        } catch (Exception e) {
          IOException ioe = new IOException("Failed parsing line: " + line, e);
          ioe.printStackTrace();
          System.exit(-1);
        }
      }
    }
    if (matchedOnly) {
      System.out.println("matched: " + matched + " nulls: " + nulls);
    } else if (unmatchedOnly) {
      System.out.println("unmatched: " + unmatched + " nulls: " + nulls);
    } else {
      System.out.println("matched: " + matched + " unmatched: " + unmatched + " nulls: " + nulls);
    }
  }
}
