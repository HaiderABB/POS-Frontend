package SCD.controllers.cache;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Cache {
  String emp_code;

  public Cache() {
  }

  public Cache(String emp_code) {
    this.emp_code = emp_code;
  }

  public String getEmp_code() {
    return emp_code;
  }

  public void setEmp_code(String emp_code) {
    this.emp_code = emp_code;
  }

  public void clearCache() {
    emp_code = "";
    WriteToFile();
  }

  public void setCache(String emp_code) {
    this.emp_code = emp_code;
    WriteToFile();
  }

  public String getCache() {
    ReadFromFile();
    return emp_code;
  }

  private void ReadFromFile() {
    try {
      Path filePath = Path.of("src/main/java/SCD/controllers/cache/cache.txt");

      String content = Files.readString(filePath);

      emp_code = content.trim();

      System.out.println(emp_code);
    } catch (IOException e) {
      System.err.println("Error reading the file: " + e.getMessage());
    }
  }

  private void WriteToFile() {

    Path filePath = Path.of("src/main/java/SCD/controllers/cache/cache.txt");

    try {
      Files.writeString(filePath, emp_code);

    } catch (IOException e) {
      System.err.println("Error writing to the file: " + e.getMessage());
    }
  }

  public static void main(String[] args) {
    Cache cache = new Cache();
  }

}
