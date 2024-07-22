package gentlepeople.connexdata.application.port.in;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;

public interface HandleNotionCallbackUseCase {
  ResponseEntity<String> callback(String code, String state, HttpServletResponse response, HttpServletRequest request);
}
