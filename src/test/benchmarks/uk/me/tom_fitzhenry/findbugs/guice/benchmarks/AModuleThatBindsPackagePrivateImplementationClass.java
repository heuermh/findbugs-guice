package uk.me.tom_fitzhenry.findbugs.guice.benchmarks;

import com.google.inject.AbstractModule;

public class AModuleThatBindsPackagePrivateImplementationClass extends AbstractModule {

    @Override
    protected void configure() {
        bind(Service.class).to(PackagePrivateImplementationClass.class);
    }
}