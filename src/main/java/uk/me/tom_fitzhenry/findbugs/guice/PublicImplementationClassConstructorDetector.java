package uk.me.tom_fitzhenry.findbugs.guice;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

import edu.umd.cs.findbugs.BugInstance;
import edu.umd.cs.findbugs.BugReporter;
import edu.umd.cs.findbugs.OpcodeStack;
import edu.umd.cs.findbugs.ba.ClassContext;
import edu.umd.cs.findbugs.ba.ch.Subtypes2;
import edu.umd.cs.findbugs.classfile.ClassDescriptor;
import edu.umd.cs.findbugs.bcel.OpcodeStackDetector;

public final class PublicImplementationClassConstructorDetector extends OpcodeStackDetector {
    static final String MODULE_NAME = "com.google.inject.Module";
    private final BugReporter bugReporter;
    private boolean isModule = false;

    public PublicImplementationClassConstructorDetector(final BugReporter bugReporter) {
        this.bugReporter = bugReporter;
    }

    @Override
    public void visitClassContext(final ClassContext classContext) {
        if (isModule(classContext.getClassDescriptor())) {
            isModule = true;
        }
        super.visitClassContext(classContext);
    }

    @Override
    public void sawOpcode(final int seen) {
        if (isModule) {
            switch (seen) {
            case INVOKEVIRTUAL:
            case INVOKEINTERFACE:
                if (isCallingTo()) {
                    OpcodeStack.Item stackItem = stack.getStackItem(0);
                    try {
                        String implementationClassName = (String) stackItem.getConstant();
                        Class<?> implementationClass = Class.forName(implementationClassName.replace("/", "."));
                        for (Constructor<?> constructor : implementationClass.getDeclaredConstructors()) {
                            if (Modifier.isPublic(constructor.getModifiers())) {
                                bugReporter.reportBug(new BugInstance(this, "GUICE_PUBLIC_IMPLEMENTATION_CLASS_CONSTRUCTOR", NORMAL_PRIORITY).addClassAndMethod(this));
                            }
                        }
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
    public void report() {
        // empty
    }

    private boolean isCallingTo() {
        return getNameConstantOperand().equals("to");
    }

    static boolean isModule(final ClassDescriptor classDescriptor) {
        return Subtypes2.instanceOf(classDescriptor, MODULE_NAME);
    }
}
