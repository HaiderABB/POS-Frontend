package SCD.model.service.Json;

import java.util.List;

import SCD.model.models.Product;
import SCD.model.models.Sale;

public class GetReportJSON {

  List<Sale> salesData;
  List<Product> products;
  double profit;
  String message;

  public GetReportJSON(List<Sale> salesData, List<Product> products, double profit, String message) {
    this.salesData = salesData;
    this.products = products;
    this.profit = profit;
    this.message = message;
  }

  public List<Sale> getSalesData() {
    return salesData;
  }

  public List<Product> getProducts() {
    return products;
  }

  public double getProfit() {
    return profit;
  }

  public void setSalesData(List<Sale> salesData) {
    this.salesData = salesData;
  }

  public void setProducts(List<Product> products) {
    this.products = products;
  }

  public void setProfit(double profit) {
    this.profit = profit;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

}
