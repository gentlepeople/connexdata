package gentlepeople.connexdata.application.service;

import gentlepeople.connexdata.application.port.in.QueryNotionDatabaseQuery;
import gentlepeople.connexdata.application.port.out.notion.LoadNotionPort;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import notion.api.v1.NotionClient;
import notion.api.v1.model.databases.QueryResults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

@RequiredArgsConstructor
@Service
public class QueryNotionDatabaseService implements QueryNotionDatabaseQuery {

  private static final Logger log = LoggerFactory.getLogger(QueryNotionDatabaseService.class);
  private final LoadNotionPort loadNotionPort;

  @Override
  public QueryResults queryNotionDatabase(String databaseId, HttpServletRequest request) {
    String authToken = request.getHeader("Authorization");
    if (authToken != null && authToken.startsWith("Bearer ")) {
      String token = authToken.substring(7);
      NotionClient notionClient = loadNotionPort.getNotionClient(token);
      return notionClient.queryDatabase(databaseId, null, null, null, null);
    } else {
      throw new UnauthorizedException("Error: Unauthorized access.");
    }
  }

  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  private static class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(String message) {
      super(message);
    }
  }
}
