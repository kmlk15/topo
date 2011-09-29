package dependencies;

import service.api.LocationSearchService;
import service.api.ScoringService;
import service.impl.LocationSearchServiceImpl;
import service.impl.ScoringServiceImpl;

import com.google.inject.AbstractModule;

public class AppModule extends AbstractModule {

	public void configure() {
		bind(LocationSearchService.class).to(LocationSearchServiceImpl.class);
		bind(ScoringService.class).to(ScoringServiceImpl.class);
	}
}
