package uk.me.tom_fitzhenry.findbugs.guice;

import java.lang.reflect.Modifier;

import edu.umd.cs.findbugs.BugInstance;
import edu.umd.cs.findbugs.BugReporter;
import edu.umd.cs.findbugs.internalAnnotations.DottedClassName;

public final class PublicImplementationClassDetector extends AbstractBindingImplementationClassDetector {

    public PublicImplementationClassDetector(final BugReporter bugReporter) {
        super(bugReporter);
    }

    @Override
    protected <T> void sawBindingImplementationClass(@DottedClassName final String dottedClassName, final Class<T> implementationClass) {
        if (Modifier.isPublic(implementationClass.getModifiers())) {
            bugReporter.reportBug(new BugInstance(this, "GUICE_PUBLIC_IMPLEMENTATION_CLASS", NORMAL_PRIORITY).addClassAndMethod(this).addTypeOfNamedClass(dottedClassName));
        }
    }
}
