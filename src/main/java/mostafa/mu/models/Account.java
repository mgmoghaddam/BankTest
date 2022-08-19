package mostafa.mu.models;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Account {

  private String accno;
  private Currency currency;
  private User user;
  private long balance;
  private List<Transaction> transactions;
}
