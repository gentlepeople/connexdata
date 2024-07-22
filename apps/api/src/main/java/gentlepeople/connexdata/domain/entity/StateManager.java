package gentlepeople.connexdata.domain.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class StateManager {

  private final Map<String, Long> values = new HashMap<>();

  public String generate() {
    String state = UUID.randomUUID().toString();
    values.put(state, new Date().getTime());
    return state;
  }

  public boolean consume(String state, Map<String, String> cookies) {
    Long createdAt = values.remove(state);
    if (createdAt == null || new Date().getTime() >= (createdAt + 10 * 60 * 1000)) {
      return false;
    }
    String sessionValue = cookies.get("notion-installation-session");
    return sessionValue != null && sessionValue.equals(state);
  }
}
