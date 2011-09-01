import models.Location;
import play.jobs.Job;
import play.jobs.OnApplicationStart;
import play.test.Fixtures;

@OnApplicationStart
public class Bootstrap extends Job {
	public void doJob() {
		if (Location.count() == 0) {
			Fixtures.loadModels("location.yml");
		}
	}
}
