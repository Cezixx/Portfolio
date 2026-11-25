package Bank_savings;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Operation {
   private final String id;
   private final String type;
   private final double amount;
   private final LocalDateTime  timestamp;

   public Operation(String id, String type, double amount, LocalDateTime timestamp) {
        this.id = id;
        this.type = type;
        this.amount = amount;
        this.timestamp = timestamp;
   }

   public String getId() {
    return this.id;
   }

   public String getType() {
    return this.type;
   }

   public double getAmount() {
    return this.amount;
   }

   public LocalDateTime getDateTime() {
    return this.timestamp;
   }

   @Override
   public String toString() {
       DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
       String formattedDate = (timestamp != null) ? timestamp.format(formatter) : "brak daty";
       return String.format("[%s] %s | %.2f zł (ID: %s)", formattedDate, type, amount, id);
   }
}