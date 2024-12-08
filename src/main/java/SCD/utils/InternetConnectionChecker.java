package SCD.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InternetConnectionChecker {

  public static boolean isInternetAvailable() {
    try {
      InetAddress address = InetAddress.getByName("www.google.com");
      return address != null;
    } catch (UnknownHostException e) {
      return false;
    }
  }
}
