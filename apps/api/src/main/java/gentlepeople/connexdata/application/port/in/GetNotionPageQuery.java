package gentlepeople.connexdata.application.port.in;

import notion.api.v1.model.pages.Page;

import java.util.Optional;

public interface GetNotionPageQuery {
  Optional<Page> getNotionPage(String pageId);
}
