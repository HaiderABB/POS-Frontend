package SCD.model.service;

public class AddResponseClass {

  boolean success;
  String message;

  public AddResponseClass(String message, boolean success) {
    this.success = success;
    this.message = message;
  }

  public boolean isSuccess() {
    return success;
  }

  public void setSuccess(boolean success) {
    this.success = success;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

}
