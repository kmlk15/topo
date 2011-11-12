package service.api;

import models.app.SearchQuery;

public interface SearchQueryParser {

	SearchQuery parseQuery(String query);
}
