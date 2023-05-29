package services.logs;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AuditService {
    private static final String AUDIT_LOG_FILE = "audit_log.csv";

    public static void writeToAuditLog(String action) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        String logEntry = action + "," + timestamp + "\n";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(AUDIT_LOG_FILE, true))) {
            writer.write(logEntry);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
