package SCD.model.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "codes") // Specifies the table name in the database
public class Codes {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generated ID
  private Long id;

  @Column(name = "code", nullable = false) // Code column with constraints
  private String code;

  @Column(name = "table_name", nullable = false) // Table name column
  private String tableName;

  // Default constructor (required by JPA)
  public Codes() {
  }

  // Parameterized constructor
  public Codes(String code, String tableName) {
    this.code = code;
    this.tableName = tableName;
  }

  // Getters and setters
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
