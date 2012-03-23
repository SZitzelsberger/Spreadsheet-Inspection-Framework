package sif.model.policy.policyrule.configuration;

import java.lang.reflect.Array;
import java.util.regex.Pattern;

/**
 * Stores the configuration for a {@link ConfigurableParameter}.
 * 
 * @author Sebastian Zitzelsberger
 * 
 * @param <ParameterClass>
 */
public class ParameterConfiguration<ParameterClass> {

	public static <ParameterType> ParameterConfiguration<ParameterType> createParameterConfiguationFor(
			Class<ParameterType> parameterClass, String parameterPattern) {
		return new ParameterConfiguration<ParameterType>(parameterClass,
				parameterPattern);
	}

	private Class<ParameterClass> parameterClass;
	private ParameterClass defaultValue;
	protected ParameterClass parameterValue;
	private String fieldName;
	private String displayedParameteName;

	private String description;

	private String pattern;

	/**
	 * Creates a new configuration for a parameter of the given class whose
	 * value must fulfill the given pattern.
	 * 
	 * @param paramaterClass
	 *            The given class.
	 * @param pattern
	 *            The given pattenr.
	 */
	public ParameterConfiguration(Class<ParameterClass> paramaterClass,
			String pattern) {
		this.parameterClass = paramaterClass;
		this.pattern = pattern;
	}

	public ParameterClass getDefaultValue() {
		return defaultValue;
	}

	public String getDescription() {
		return description;
	}

	public String getFieldName() {
		return this.fieldName;
	}

	public String getParamaterName() {
		return displayedParameteName;
	}

	public ParameterClass getParameterValue() {
		ParameterClass returnValue = parameterValue;
		if (returnValue == null) {
			returnValue = defaultValue;
		}
		return returnValue;
	}

	/***
	 * Checks if the given value matches the required pattern.
	 * 
	 * @param value
	 *            The given value.
	 * @return Whether the value matches the required pattern.
	 */
	private Boolean matchesPattern(ParameterClass value) {
		Boolean matchesPattern = true;

		if (value.getClass().isArray()) {
			for (int i = 0; i < Array.getLength(value) & matchesPattern; i++) {
				matchesPattern = Pattern.matches(pattern, Array.get(value, i)
						.toString());
			}

		} else {
			matchesPattern = Pattern.matches(pattern, value.toString());
		}
		return matchesPattern;
	}

	public void setDefaultValue(Object value) throws Exception {

		ParameterClass castedValue = this.parameterClass.cast(value);
		Boolean matchesPattern = matchesPattern(castedValue);

		if (matchesPattern) {
			this.defaultValue = castedValue;
			this.parameterValue = defaultValue;
		} else {
			throw new Exception("Value doesn't match pattern: " + pattern);
		}

	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public void setParamaterName(String paramaterName) {
		this.displayedParameteName = paramaterName;
	}

	/***
	 * Sets the given value for this configuration. The value must be of the
	 * required class and match the required pattern.
	 * 
	 * @param value
	 *            The given value.
	 * @throws Exception
	 *             Thrown if the given value does not match.
	 */
	public void setParameterValue(Object value) throws Exception {
		if (value.getClass().equals(parameterClass)) {
			@SuppressWarnings("unchecked")
			ParameterClass parameterValue = (ParameterClass) value;
			Boolean matchesPattern = matchesPattern(parameterValue);

			if (matchesPattern) {
				this.parameterValue = parameterValue;
			} else {
				throw new Exception("Value doesn't match pattern: " + pattern);
			}

		} else {
			throw new Exception("Wrong value class.");
		}

	}

}
