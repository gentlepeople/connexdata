package gentlepeople.connexdata.adapter.in.web;

import gentlepeople.connexdata.application.port.in.HandleNotionCallbackUseCase;
import gentlepeople.connexdata.application.port.in.HandleNotionInstallUseCase;
import gentlepeople.connexdata.application.port.in.QueryNotionDatabaseQuery;
import gentlepeople.connexdata.common.WebAdapter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import notion.api.v1.model.databases.QueryResults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
class NotionController {
  private final QueryNotionDatabaseQuery queryNotionDatabaseQuery;
  private final HandleNotionInstallUseCase handleNotionInstallUseCase;
  private final HandleNotionCallbackUseCase handleNotionCallbackUseCase;

  @GetMapping("/notion/install")
  public ResponseEntity<Void> install(HttpServletResponse response) {
    return handleNotionInstallUseCase.install(response);
  }

  @GetMapping("/notion/callback")
  public ResponseEntity<String> callback(@RequestParam("code") String code, @RequestParam("state") String state, HttpServletResponse response, HttpServletRequest request) {
    return handleNotionCallbackUseCase.callback(code, state, response, request);
  }

  @GetMapping(path = "/notion/databases/{databaseId}/query")
  public QueryResults queryNotionDatabase(@PathVariable("databaseId") String databaseId, HttpServletRequest request) {
    return queryNotionDatabaseQuery.queryNotionDatabase(databaseId, request);
  }
}
