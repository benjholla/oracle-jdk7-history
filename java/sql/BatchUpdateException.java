

package java.sql;

import java.util.Arrays;



public class BatchUpdateException extends SQLException {

  
  public BatchUpdateException( String reason, String SQLState, int vendorCode,
                               int[] updateCounts ) {
      super(reason, SQLState, vendorCode);
      this.updateCounts  = (updateCounts == null) ? null : Arrays.copyOf(updateCounts, updateCounts.length);
  }

  
  public BatchUpdateException(String reason, String SQLState,
                              int[] updateCounts) {
      this(reason, SQLState, 0, updateCounts);
  }

  
  public  BatchUpdateException(String reason, int[] updateCounts) {
      this(reason, null, 0, updateCounts);
  }

  
  public BatchUpdateException(int[] updateCounts) {
      this(null, null, 0, updateCounts);
  }

  
  public BatchUpdateException() {
        this(null, null, 0, null);
  }

    
    public BatchUpdateException(Throwable cause) {
        this((cause == null ? null : cause.toString()), null, 0, null, cause);
    }

    
    public BatchUpdateException(int []updateCounts , Throwable cause) {
        this((cause == null ? null : cause.toString()), null, 0, updateCounts, cause);
    }

    
    public BatchUpdateException(String reason, int []updateCounts, Throwable cause) {
        this(reason, null, 0, updateCounts, cause);
    }

    
    public BatchUpdateException(String reason, String SQLState,
                                int []updateCounts, Throwable cause) {
        this(reason, SQLState, 0, updateCounts, cause);
    }

    
    public BatchUpdateException(String reason, String SQLState, int vendorCode,
                                int []updateCounts,Throwable cause) {
        super(reason, SQLState, vendorCode, cause);
        this.updateCounts  = (updateCounts == null) ? null : Arrays.copyOf(updateCounts, updateCounts.length);
    }

  
  public int[] getUpdateCounts() {
      return (updateCounts == null) ? null : Arrays.copyOf(updateCounts, updateCounts.length);
  }

  
  private final int[] updateCounts;

  private static final long serialVersionUID = 5977529877145521757L;
}
