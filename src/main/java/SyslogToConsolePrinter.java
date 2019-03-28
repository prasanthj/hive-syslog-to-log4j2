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
    //    inputs.add("classpath.log");
    //    inputs.add("dwdc-2019-03-22-01-00.log");
    //    inputs.add("dwdl-2019-03-22-01-00.log");
    //    inputs.add("dwdl-2019-03-22-01-05.log");
    //    inputs.add("dwdl-2019-03-22-01-10.log");
    //    inputs.add("hive.syslog.log");
    //    inputs.add("hs2-2019-03-22-01-00.log");
    //    inputs.add("hs2-2019-03-22-01-05-2.log");
    //    inputs.add("hs2-2019-03-22-01-05.log");
    //    inputs.add("hs2-2019-03-22-01-10.log");
    //    inputs.add("hs2-syslog-k8s.log");
    //    inputs.add("llap-syslog-cr.log");
    //    inputs.add("llap-syslog-k8s.log");
    //    inputs.add("m-2019-03-22-01-00.log");
    //    inputs.add("m-2019-03-22-01-05.log");
    //    inputs.add("m-2019-03-22-01-10.log");
    //    inputs.add("mc-2019-03-22-01-00.log");
    //    inputs.add("mix.log");
    //    inputs.add("qc-2019-03-22-01-00.log");
    //    inputs.add("qe-2019-03-22-01-00.log");
    //    inputs.add("qe-2019-03-22-01-05.log");
    //    inputs.add("syslog-hs2-cr.log");
    //    inputs.add("syslog-hs2-cr2.log");
    //    inputs.add("syslog-llap-2.log");
    //    inputs.add("syslog-llap.log");
    inputs.add("hs2-linebreak-after-structured-data-4.log");
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
                // System.out.println(row);
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
