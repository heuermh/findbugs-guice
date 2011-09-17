package uk.me.tom_fitzhenry.findbugs.guice;

import static org.junit.Assert.assertFalse;

import org.junit.Test;

import uk.me.tom_fitzhenry.findbugs.guice.benchmarks.ClassContainingAStaticFieldAnnotatedWithInject;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class StaticFieldInjectionTest {
	
	@Test
	public void guiceDoesNotInjectStaticFields() {
		
		Injector injector = Guice.createInjector(new AbstractModule() {
			@Override
			protected void configure() {
				bind(String.class).toInstance("injectedString");
			}
			
		});
		
		ClassContainingAStaticFieldAnnotatedWithInject foo = injector.getInstance(ClassContainingAStaticFieldAnnotatedWithInject.class);
		
		assertFalse("injectedString".equals(foo.getFoo()));
	}
}
		
		
