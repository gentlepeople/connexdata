package gentlepeople.connexdata.application.service;

import gentlepeople.connexdata.application.port.in.GetNotionPageQuery;
import gentlepeople.connexdata.application.port.out.notion.LoadNotionPort;
import lombok.RequiredArgsConstructor;
import notion.api.v1.model.pages.Page;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class GetNotionPageService implements GetNotionPageQuery {

  private final LoadNotionPort loadNotionPort;

  @Override
  public Optional<Page> getNotionPage(String pageId) {
    return loadNotionPort.loadPage(pageId);
  }
}
