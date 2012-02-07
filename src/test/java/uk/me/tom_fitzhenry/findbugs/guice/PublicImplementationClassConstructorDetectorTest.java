package uk.me.tom_fitzhenry.findbugs.guice;

import static com.youdevise.fbplugins.tdd4fb.DetectorAssert.ofType;

import org.junit.Before;
import org.junit.Test;

import uk.me.tom_fitzhenry.findbugs.guice.benchmarks.AModuleThatBindsPackagePrivateImplementationClass;
import uk.me.tom_fitzhenry.findbugs.guice.benchmarks.AModuleThatBindsPackagePrivateImplementationClassPackagePrivateConstructor;
import uk.me.tom_fitzhenry.findbugs.guice.benchmarks.AModuleThatBindsPublicImplementationClass;
import uk.me.tom_fitzhenry.findbugs.guice.benchmarks.AModuleThatBindsPublicImplementationClassPackagePrivateConstructor;
import uk.me.tom_fitzhenry.findbugs.guice.benchmarks.AModuleWithoutBindings;

import com.youdevise.fbplugins.tdd4fb.DetectorAssert;

import edu.umd.cs.findbugs.BugReporter;
import edu.umd.cs.findbugs.Detector;

public class PublicImplementationClassConstructorDetectorTest {

    private BugReporter bugReporter;
    private Detector detector;
    
    @Before
    public void setUp() {
        bugReporter = DetectorAssert.bugReporterForTesting();
        detector = new PublicImplementationClassConstructorDetector(bugReporter);
    }

    @Test
    public void publicImplementationClassConstructorIsReported() throws Exception {
    	assertBugReportedAgainstClass(AModuleThatBindsPublicImplementationClass.class);
    	assertBugReportedAgainstClass(AModuleThatBindsPackagePrivateImplementationClass.class);
    }

    @Test
    public void packagePrivateImplementationClassConstructorIsNotReported() throws Exception {
    	assertNoBugsReportedForClass(AModuleThatBindsPublicImplementationClassPackagePrivateConstructor.class);
    	assertNoBugsReportedForClass(AModuleThatBindsPackagePrivateImplementationClassPackagePrivateConstructor.class);
    }

    @Test
    public void notModuleIsNotReported() throws Exception {
        assertNoBugsReportedForClass(Object.class);
    }

    @Test
    public void moduleWithoutBindingsIsNotReported() throws Exception {
        assertNoBugsReportedForClass(AModuleWithoutBindings.class);
    }

    private void assertBugReportedAgainstClass(Class<?> classToTest) throws Exception {
        DetectorAssert.assertBugReported(classToTest, detector, bugReporter, ofType("GUICE_PUBLIC_IMPLEMENTATION_CLASS_CONSTRUCTOR"));
    }

    private void assertNoBugsReportedForClass(Class<?> classToTest) throws Exception {
        DetectorAssert.assertNoBugsReported(classToTest, detector, bugReporter);
    }
	
}
