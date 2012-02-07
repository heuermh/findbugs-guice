package uk.me.tom_fitzhenry.findbugs.guice;

import static com.youdevise.fbplugins.tdd4fb.DetectorAssert.ofType;

import org.junit.Before;
import org.junit.Test;

import uk.me.tom_fitzhenry.findbugs.guice.benchmarks.AModuleThatBindsPackagePrivateImplementationClass;
import uk.me.tom_fitzhenry.findbugs.guice.benchmarks.AModuleThatBindsPublicImplementationClass;

import com.youdevise.fbplugins.tdd4fb.DetectorAssert;

import edu.umd.cs.findbugs.BugReporter;
import edu.umd.cs.findbugs.Detector;

public class PublicImplementationClassDetectorTest {

    private BugReporter bugReporter;
    private Detector detector;
    
    @Before
    public void setUp() {
        bugReporter = DetectorAssert.bugReporterForTesting();
        detector = new PublicImplementationClassDetector(bugReporter);
    }
    
    @Test
    public void publicImplementationClassIsReported() throws Exception {
    	assertBugReportedAgainstClass(AModuleThatBindsPublicImplementationClass.class);
    }
    
    @Test
    public void packagePrivateImplementationClassIsNotReported() throws Exception {
    	assertNoBugsReportedForClass(AModuleThatBindsPackagePrivateImplementationClass.class);
    }
    
    private void assertBugReportedAgainstClass(Class<?> classToTest) throws Exception {
        DetectorAssert.assertBugReported(classToTest, detector, bugReporter, ofType("GUICE_PUBLIC_IMPLEMENTATION_CLASS"));
    }
    
    private void assertNoBugsReportedForClass(Class<?> classToTest) throws Exception {
        DetectorAssert.assertNoBugsReported(classToTest, detector, bugReporter);
    }
	
}
