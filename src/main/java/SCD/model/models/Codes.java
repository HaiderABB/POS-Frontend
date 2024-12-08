package SCD.model.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "codes")
public class Codes {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "code", nullable = false)
  private String code;

  @Column(name = "table_name", nullable = false)
  private String tableName;

  public Codes() {
  }

  public Codes(String code, String tableName) {
    this.code = code;
    this.tableName = tableName;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getTableName() {
    return tableName;
  }

  public void setTableName(String tableName) {
    this.tableName = tableName;
  }

  @Override
  public String toString() {
    return "Codes{" +
        "id=" + id +
        ", code='" + code + '\'' +
        ", tableName='" + tableName + '\'' +
        '}';
  }
}
