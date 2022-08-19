package mostafa.mu.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

  private long amount;
  private long dateTime;
  private TransactionType transactionType;
  private String destAccno;
  private String srcAccno;
}
