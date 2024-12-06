package SCD.model.service;

import java.util.List;

public class GetResponseClass<T> {

  List<T> data;
  String message;

  public GetResponseClass(String message, List<T> data) {
    this.message = message;
    this.data = data;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public List<T> getData() {
    return data;
  }

  public void setData(List<T> data) {
    this.data = data;
  }
}
