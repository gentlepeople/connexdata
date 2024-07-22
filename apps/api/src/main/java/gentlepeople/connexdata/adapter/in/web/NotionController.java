package gentlepeople.connexdata.adapter.in.web;

import gentlepeople.connexdata.application.port.in.GetNotionPageQuery;
import gentlepeople.connexdata.application.port.in.HandleNotionCallbackUseCase;
import gentlepeople.connexdata.application.port.in.HandleNotionInstallUseCase;
import gentlepeople.connexdata.common.WebAdapter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import notion.api.v1.model.pages.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
class NotionController {
  private final GetNotionPageQuery getNotionPageQuery;
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

  @GetMapping(path = "/notion/database/{pageId}")
  public ResponseEntity<Page> getNotionPage(@PathVariable("pageId") String pageId) {
    return getNotionPageQuery.getNotionPage(pageId)
      .map(ResponseEntity::ok)
      .orElseGet(() -> ResponseEntity.notFound().build());
  }
}
