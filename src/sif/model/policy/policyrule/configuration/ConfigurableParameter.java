package sif.model.policy.policyrule.configuration;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import sif.model.policy.policyrule.AbstractPolicyRule;

/**
 * Annotation for variables in {@link AbstractPolicyRule}s to indicate that
 * these variables must be made configurable by the framework.
 * 
 * @see ParameterConfiguration
 * @see PolicyRuleConfiguration
 * 
 * @author Sebastian Zitzelsberger
 * 
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
@Inherited
public @interface ConfigurableParameter {
	/**
	 * The description of the configurable parameter, that will be displayed for
	 * the configuration.
	 * 
	 * @return The description of the configurable parameter.
	 */
	String description() default "No description available";

	/**
	 * The name of the the configurable parameter that will be displayed for the
	 * configuration.
	 * 
	 * @return The name of the configurable parameter.
	 */
	String displayedName();

	/**
	 * The class of the configurable parameter.
	 * 
	 * @return
	 */
	Class<?> parameterClass() default String.class;

	/**
	 * The pattern (regular expression) that values for the configurable
	 * parameter must fulfill.
	 * 
	 * @return
	 */
	String pattern() default ".*";
}
