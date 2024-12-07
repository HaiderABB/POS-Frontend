package SCD.model.service;

import SCD.model.models.SaleItem;

public class SaleItemJSON {
  SaleItem saleItem;
  boolean success;
  String message;

  public SaleItemJSON(SaleItem saleItem, boolean success, String message) {
    this.saleItem = saleItem;
    this.success = success;
    this.message = message;
  }

  public SaleItem getSaleItem() {
    return saleItem;
  }

  public void setSaleItem(SaleItem saleItem) {
    this.saleItem = saleItem;
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
