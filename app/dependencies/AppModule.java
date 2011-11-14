package dependencies;

import service.api.LocationSearchService;
import service.api.ScoringService;
import service.api.SearchAutoCompleteService;
import service.api.SearchQueryParser;
import service.impl.LocationSearchServiceImpl;
import service.impl.ScoringServiceImpl;
import service.impl.SearchAutoCompleteServiceImpl;
import service.impl.SearchQueryParserImpl;

import com.google.inject.AbstractModule;

public class AppModule extends AbstractModule {

	public void configure() {
		bind(LocationSearchService.class).to(LocationSearchServiceImpl.class);
		bind(ScoringService.class).to(ScoringServiceImpl.class);
		bind(SearchQueryParser.class).to(SearchQueryParserImpl.class);
		bind(SearchAutoCompleteService.class).to(SearchAutoCompleteServiceImpl.class);
	}
}
