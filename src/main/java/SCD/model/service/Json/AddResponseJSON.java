package SCD.model.service.Json;

public class AddResponseJSON {

  boolean success;
  String message;
  String code;

  public AddResponseJSON(String message, boolean success, String code) {
    this.success = success;
    this.message = message;
    this.code = code;
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

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

}
