package uk.me.tom_fitzhenry.findbugs.guice;

import edu.umd.cs.findbugs.BugInstance;
import edu.umd.cs.findbugs.BugReporter;
import edu.umd.cs.findbugs.OpcodeStack;
import edu.umd.cs.findbugs.ba.ClassContext;
import edu.umd.cs.findbugs.ba.ch.Subtypes2;
import edu.umd.cs.findbugs.classfile.ClassDescriptor;
import edu.umd.cs.findbugs.bcel.OpcodeStackDetector;
import edu.umd.cs.findbugs.internalAnnotations.DottedClassName;

abstract class AbstractBindingImplementationClassDetector extends OpcodeStackDetector {
    static final String MODULE_NAME = "com.google.inject.Module";
    protected final BugReporter bugReporter;
    private boolean isModule = false;

    protected AbstractBindingImplementationClassDetector(final BugReporter bugReporter) {
        this.bugReporter = bugReporter;
    }

    @Override
    public final void visitClassContext(final ClassContext classContext) {
        if (isModule(classContext.getClassDescriptor())) {
            isModule = true;
        }
        super.visitClassContext(classContext);
    }

    protected abstract <T> void sawBindingImplementationClass(@DottedClassName String dottedClassName, Class<T> implementationClass);

    @Override
    public final void sawOpcode(final int seen) {
        if (isModule) {
            switch (seen) {
            case INVOKEVIRTUAL:
            case INVOKEINTERFACE:
                if (isCallingTo()) {
                    OpcodeStack.Item stackItem = stack.getStackItem(0);
                    try {
                        String slashedClassName = (String) stackItem.getConstant();
                        String dottedClassName = slashedClassName.replace("/", ".");
                        Class<?> implementationClass = Class.forName(dottedClassName);
                        sawBindingImplementationClass(dottedClassName, implementationClass);
                    }
                    catch (Exception e) {
                        // ignore
                    }
                }
                break;
            }
        }
    }

    @Override
    public final void report() {
        // empty
    }

    private boolean isCallingTo() {
        return getNameConstantOperand().equals("to");
    }

    protected static final boolean isModule(final ClassDescriptor classDescriptor) {
        return Subtypes2.instanceOf(classDescriptor, MODULE_NAME);
    }
}
