package SCD.model.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "sync_table")
public class SyncTable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "table_name")
  private String tableName;

  @Column(name = "operation_type")
  private String operationType;

  @Column(name = "key_column")
  private String keyColumn;

  public SyncTable() {
  }

  public SyncTable(String tableName, String operationType, String keyColumn) {
    this.tableName = tableName;
    this.operationType = operationType;
    this.keyColumn = keyColumn;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTableName() {
    return tableName;
  }

  public void setTableName(String tableName) {
    this.tableName = tableName;
  }

  public String getOperationType() {
    return operationType;
  }

  public void setOperationType(String operationType) {
    this.operationType = operationType;
  }

  public String getKeyColumn() {
    return keyColumn;
  }

  public void setKeyColumn(String keyColumn) {
    this.keyColumn = keyColumn;
  }

  @Override
  public String toString() {
    return "SyncTable [id=" + id + ", tableName=" + tableName + ", operationType=" + operationType
        + ", keyColumn=" + keyColumn + "]";
  }
}
