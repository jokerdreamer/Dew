package com.intel.sto.bigdata.dew.utils;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

public class Util {
  // Because posting http stream use header to transfer parameter, add a
  // prefix to differ from http protocol.
  private static String PREFIX = "dew-";

  public static String addPrefix(String s) {
    return PREFIX + s;
  }

  public static String removePrefix(String s) {
    if (s.startsWith(PREFIX)) {
      return s.substring(4);
    }
    return null;
  }

  /**
   * Only used for master and agents, and suppose starting them by shell script.
   */
  public static String findDewClassPath() {
    String dewHome = System.getenv("DEW_HOME");
    if (dewHome != null) {
      return dewHome + "/dew.assembly/dew.jar";
    }
    URL url = Thread.currentThread().getContextClassLoader().getResource("");
    if (url != null) {
      return url.getPath();
    }
    return null;
  }
  
  public static Properties buildProperties(String conf) throws IOException {
    InputStream in = new BufferedInputStream(new FileInputStream(conf));
    Properties props = new Properties();
    props.load(in);
    in.close();
    return props;
  }
}