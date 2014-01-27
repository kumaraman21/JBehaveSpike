package behave;

import java.util.Arrays;

import org.jbehave.core.Embeddable;
import org.jbehave.core.annotations.Configure;
import org.jbehave.core.annotations.UsingEmbedder;
import org.jbehave.core.annotations.UsingSteps;
import org.jbehave.core.embedder.Embedder;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.junit.AnnotatedEmbedderRunner;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AnnotatedEmbedderRunner.class)
@Configure(storyLoader = LoadFromClasspath.class, storyReporterBuilder = StoryReporterBuilder.class )
@UsingEmbedder(embedder = Embedder.class, generateViewAfterStories = true, ignoreFailureInStories = true, ignoreFailureInView = true)
@UsingSteps(instances = { HelloSteps.class })
public class HelloStories2 implements Embeddable {
 
    private Embedder embedder;
 
    public void useEmbedder(Embedder embedder) {
        this.embedder = embedder;
    }
 
    @Test
    public void run() {
        embedder.runStoriesAsPaths(new StoryFinder().findPaths(CodeLocations.codeLocationFromClass(this.getClass()).getFile(),
                Arrays.asList("**/*.story"), Arrays.asList("")));
    }
}



