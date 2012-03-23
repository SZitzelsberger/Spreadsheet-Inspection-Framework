package sif.model.policy.policyrule.configuration;

import java.util.ArrayList;
import java.util.TreeMap;

import sif.model.policy.policyrule.AbstractPolicyRule;

/***
 * Stores the individual {@link ParameterConfiguration}s as the configuration
 * for a policy rule ({@link AbstractPolicyRule}).
 * 
 * @author Sebastian Zitzelsberger
 * 
 */
public class PolicyRuleConfiguration {

	private AbstractPolicyRule policyRule;
	private TreeMap<String, ParameterConfiguration<?>> parameters;

	public PolicyRuleConfiguration() {
		this.parameters = new TreeMap<String, ParameterConfiguration<?>>();
	}

	/**
	 * Adds the given parameter configuration.
	 * 
	 * @param parameterConfiguration
	 *            The given parameter configuration.
	 */
	public void add(ParameterConfiguration<?> parameterConfiguration) {
		this.parameters.put(parameterConfiguration.getFieldName(),
				parameterConfiguration);
	}

	/**
	 * Gets the parameter configuration, which matches the given field name, as
	 * a parameter configuration of the given parameter class. Returns null if
	 * no configuration matches.
	 * 
	 * @param fieldName
	 *            The given field name.
	 * @param parameterClass
	 *            The given parameter class.
	 * @return The parameter configuration for the given field.
	 */
	@SuppressWarnings("unchecked")
	public <ParameterClass> ParameterConfiguration<ParameterClass> getParameterBy(
			String fieldName, Class<ParameterClass> parameterClass) {
		return (ParameterConfiguration<ParameterClass>) parameters
				.get(fieldName);
	}

	public ParameterConfiguration<?> getParameterByFieldName(String fieldName) {
		return parameters.get(fieldName);
	}

	public ArrayList<ParameterConfiguration<?>> getParameters() {
		return new ArrayList<ParameterConfiguration<?>>(parameters.values());
	}

	public AbstractPolicyRule getPolicyRule() {
		return policyRule;
	}

	public void setPolicyRule(AbstractPolicyRule policyRule) {
		this.policyRule = policyRule;
	}

}
