package service.impl;

import models.app.SearchQuery;

import org.apache.commons.lang.StringUtils;

import service.api.SearchQueryParser;

public class SearchQueryParserImpl implements SearchQueryParser {

	public SearchQuery parseQuery(String query) {
		SearchQuery searchQuery = new SearchQuery();
		String[] keys = StringUtils.split(query, ",");
		for (String key : keys) {
			if (StringUtils.contains(key, SearchQuery.CITY)) {
				searchQuery.addCity(StringUtils.remove(key, SearchQuery.CITY));
			} else if (StringUtils.contains(key, SearchQuery.CATEGORY)) {
				searchQuery.addCategory(StringUtils.remove(key, SearchQuery.CATEGORY));
			}
		}
		return searchQuery;
	}
}
