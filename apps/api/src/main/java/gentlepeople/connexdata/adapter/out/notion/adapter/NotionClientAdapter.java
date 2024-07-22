package gentlepeople.connexdata.adapter.out.notion.adapter;

import gentlepeople.connexdata.application.port.out.notion.LoadNotionPort;
import gentlepeople.connexdata.common.ExternalApiAdapter;
import lombok.Getter;
import notion.api.v1.NotionClient;
import notion.api.v1.model.pages.Page;
import org.springframework.beans.factory.annotation.Value;

import java.util.Optional;

@Getter
@ExternalApiAdapter
public class NotionClientAdapter implements LoadNotionPort {

  private final NotionClient notionClient;

  public NotionClientAdapter(
    @Value("${NOTION_CLIENT_ID}") String clientId,
    @Value("${NOTION_CLIENT_SECRET}") String clientSecret,
    @Value("${NOTION_REDIRECT_URI}") String redirectUri) {
    this.notionClient = new NotionClient(clientId, clientSecret, redirectUri);
  }

  @Override
  public Optional<Page> loadPage(String pageId) {
    return Optional.of(this.notionClient.retrievePage(pageId, null));
  }

}
