package gentlepeople.connexdata.application.service;


import gentlepeople.connexdata.adapter.out.notion.adapter.NotionClientAdapter;
import gentlepeople.connexdata.application.port.in.HandleNotionInstallUseCase;
import gentlepeople.connexdata.common.UseCase;
import gentlepeople.connexdata.domain.entity.StateManager;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import notion.api.v1.NotionClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@UseCase
@RequiredArgsConstructor
@Slf4j
public class HandleNotionInstallService implements HandleNotionInstallUseCase {

  private final NotionClientAdapter notionClientAdapter;
  private final StateManager stateManager;

  @Override
  public ResponseEntity<Void> install(HttpServletResponse response) {
    try {
      String state = stateManager.generate();
      String authorizeUrl = getAuthorizeUrl(state);
      response.addCookie(new Cookie("notion-installation-session", state));
      response.sendRedirect(authorizeUrl);
      return new ResponseEntity<>(HttpStatus.TEMPORARY_REDIRECT);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  private String getAuthorizeUrl(String state) {
    NotionClient client = notionClientAdapter.getNotionClient();
    return "https://api.notion.com/v1/oauth/authorize?owner=user" +
      "&client_id=" + client.getClientId() +
      "&redirect_uri=" + URLEncoder.encode(client.getRedirectUri(), StandardCharsets.UTF_8) +
      "&state=" + state +
      "&response_type=code";
  }
}
