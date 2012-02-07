package uk.me.tom_fitzhenry.findbugs.guice;

import edu.umd.cs.findbugs.BugReporter;
import edu.umd.cs.findbugs.Detector;
import edu.umd.cs.findbugs.ba.ClassContext;

public final class PublicImplementationClassDetector implements Detector {

    public PublicImplementationClassDetector(final BugReporter bugReporter) {
        // empty
    }

    @Override
    public void visitClassContext(final ClassContext classContext) {
        // empty
    }

    @Override
    public void report() {
        // empty
    }
}
