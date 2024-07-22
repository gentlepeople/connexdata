package gentlepeople.connexdata.application.service;


import gentlepeople.connexdata.adapter.out.notion.adapter.NotionClientAdapter;
import gentlepeople.connexdata.application.port.in.HandleNotionCallbackUseCase;
import gentlepeople.connexdata.common.UseCase;
import gentlepeople.connexdata.domain.entity.StateManager;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import notion.api.v1.NotionClient;
import notion.api.v1.exception.NotionOAuthAPIError;
import notion.api.v1.model.oauth.OAuthTokenResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

@UseCase
@RequiredArgsConstructor
@Slf4j
public class HandleNotionCallbackService implements HandleNotionCallbackUseCase {

  private final NotionClientAdapter notionClientAdapter;
  private final StateManager stateManager;

  @Override
  public ResponseEntity<String> callback(String code, String state, HttpServletResponse response, HttpServletRequest request) {
    try {
      Map<String, String> cookies = Arrays.stream(request.getCookies())
        .collect(Collectors.toMap(Cookie::getName, Cookie::getValue));
      if (stateManager.consume(state, cookies)) {
        response.addCookie(expireCookie("notion-installation-session"));
        OAuthTokenResult token = exchangeAuthCode(code, state);
        log.info("Token API response: {}", token);
        return new ResponseEntity<>("OK!", HttpStatus.OK);
      } else {
        return new ResponseEntity<>("Error: the state value is no longer valid", HttpStatus.BAD_REQUEST);
      }
    } catch (NotionOAuthAPIError e) {
      return new ResponseEntity<>("Error: " + e, HttpStatus.valueOf(e.getHttpResponse().getStatus()));
    } catch (Exception e) {
      return new ResponseEntity<>("Error: invalid redirection", HttpStatus.BAD_REQUEST);
    }
  }

  private Cookie expireCookie(String name) {
    Cookie cookie = new Cookie(name, null);
    cookie.setMaxAge(0);
    cookie.setPath("/");
    return cookie;
  }

  private OAuthTokenResult exchangeAuthCode(String code, String state) throws NotionOAuthAPIError {
    NotionClient client = notionClientAdapter.getNotionClient();
    return client.exchangeAuthCode(code, state);
  }
}
