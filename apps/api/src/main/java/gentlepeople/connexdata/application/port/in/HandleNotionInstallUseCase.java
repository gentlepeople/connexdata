package gentlepeople.connexdata.application.port.in;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;

public interface HandleNotionInstallUseCase {
  ResponseEntity<Void> install(HttpServletResponse response);
}
