package sif.model.policy;

import java.util.TreeMap;
import java.util.UUID;

import sif.model.policy.policyrule.AbstractPolicyRule;

public class Policy {
	private String name;
	private String description;
	private String author;
	TreeMap<String, AbstractPolicyRule> abstractPolicyRules = new TreeMap<String, AbstractPolicyRule>();

	public void add(AbstractPolicyRule abstractPolicyRule) {
		this.abstractPolicyRules.put(abstractPolicyRule.getName(),
				abstractPolicyRule);
	}

	public String getAuthor() {
		return this.author;
	}

	public String getDescription() {
		return this.description;
	}

	public String getName() {
		return this.name;
	}

	public TreeMap<String, AbstractPolicyRule> getPolicyRules() {
		return abstractPolicyRules;
	}

	public AbstractPolicyRule getRuleByName(String name) {
		return abstractPolicyRules.get(name);
	}

	public void remove(AbstractPolicyRule abstractPolicyRule) {
		this.abstractPolicyRules.remove(abstractPolicyRule);
	}

	public void remove(UUID policyRuleId) {
		this.abstractPolicyRules.remove(policyRuleId);
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setName(String name) {
		this.name = name;
	}
}