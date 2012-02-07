package uk.me.tom_fitzhenry.findbugs.guice.benchmarks;

import com.google.inject.AbstractModule;

public class AModuleWithoutBindings extends AbstractModule {

    @Override
    protected void configure() {
        // empty
    }
}