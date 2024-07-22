package gentlepeople.connexdata.application.port.out.notion;

import notion.api.v1.model.pages.Page;

import java.util.Optional;

public interface LoadNotionPort {
  Optional<Page> loadPage(String pageId);
}
