package SCD.model.service;

public class AddResponseJSON {

  boolean success;
  String message;

  public AddResponseJSON(String message, boolean success) {
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
