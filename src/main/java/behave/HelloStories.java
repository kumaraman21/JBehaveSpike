package behave;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.Properties;

import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.io.LoadFromRelativeFile;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.CandidateSteps;
import org.jbehave.core.steps.InstanceStepsFactory;

public class HelloStories extends JUnitStories {
	
	@Override
	public Configuration configuration() {
		Properties viewResources = new Properties();
        viewResources.put("decorateNonHtml", "true");
//        viewResources.put("reports", "stories/ftl/jbehave-views-custom.ftl");
		
        URL storyURL = null;
        try {
            storyURL = new URL("file://" + System.getProperty("user.dir")
                    + "/src/main/resources/stories");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        
		return new MostUsefulConfiguration()
			.useStoryLoader(new LoadFromRelativeFile(storyURL))
			.useStoryReporterBuilder(new StoryReporterBuilder()
					.withCodeLocation(CodeLocations.codeLocationFromClass(this.getClass()))
					.withViewResources(viewResources)
					.withFormats(Format.STATS, Format.CONSOLE, Format.HTML));
	}
	
	@Override
	public List<CandidateSteps> candidateSteps() {
		return new InstanceStepsFactory(configuration(), new HelloSteps()).createCandidateSteps();
	}

	@Override
	protected List<String> storyPaths() {
		//String codeLocation = CodeLocations.codeLocationFromClass(this.getClass()).getFile();
		
		String storiesPath = null;
        
            try {
				storiesPath = new File(getClass().getClassLoader().getResource("stories").toURI()).getAbsolutePath();
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
               
        return new StoryFinder().findPaths(storiesPath, "**/*.story", "");
	}

}
