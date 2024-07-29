package gentlepeople.connexdata.application.port.in;

import jakarta.servlet.http.HttpServletRequest;
import notion.api.v1.model.databases.QueryResults;

public interface QueryNotionDatabaseQuery {
  QueryResults queryNotionDatabase(String databaseId, HttpServletRequest request);
}
