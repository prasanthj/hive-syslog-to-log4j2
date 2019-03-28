package com.prasanthj.github;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import javax.annotation.Nullable;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hive.serde.serdeConstants;
import org.apache.hadoop.hive.serde2.AbstractSerDe;
import org.apache.hadoop.hive.serde2.SerDeException;
import org.apache.hadoop.hive.serde2.SerDeSpec;
import org.apache.hadoop.hive.serde2.SerDeStats;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;

/**
 * Created by prasanthj on 2019-03-20.
 */
@SerDeSpec(schemaProps = {serdeConstants.LIST_COLUMNS})
public class SyslogSerDe extends AbstractSerDe {

  private ObjectInspector inspector;
  private SyslogParser syslogParser;

  @Override
  public void initialize(@Nullable final Configuration configuration, final Properties properties)
    throws SerDeException {
    final List<String> columnNames = Arrays.asList(properties.getProperty(serdeConstants.LIST_COLUMNS).split(","));

    final int numCols = columnNames.size();
    if (numCols != SyslogParser.EXPECTED_COLUMNS) {
      throw new SerDeException("Expected " + SyslogParser.EXPECTED_COLUMNS + " columns but got " + numCols +
        " columns (" + columnNames + ")");
    }

    final List<ObjectInspector> columnOIs = new ArrayList<>(numCols);

    for (int i = 0; i < numCols; i++) {
      columnOIs.add(PrimitiveObjectInspectorFactory.javaStringObjectInspector);
    }

    inspector = ObjectInspectorFactory.getStandardStructObjectInspector(columnNames, columnOIs);
    syslogParser = new SyslogParser();
  }

  @Override
  public Class<? extends Writable> getSerializedClass() {
    return Text.class;
  }

  @Override
  public Writable serialize(final Object o, final ObjectInspector objectInspector) throws SerDeException {
    throw new SerDeException("Serialization is not supported yet");
  }

  @Override
  public SerDeStats getSerDeStats() {
    return null;
  }

  @Override
  public Object deserialize(final Writable writable) throws SerDeException {
    Text rowText = (Text) writable;
    ByteArrayInputStream bis = new ByteArrayInputStream(rowText.getBytes());
    syslogParser.setInputStream(bis);
    try {
      return syslogParser.readEvent();
    } catch (IOException e) {
      throw new SerDeException(e);
    }
  }

  @Override
  public ObjectInspector getObjectInspector() throws SerDeException {
    return inspector;
  }
}
