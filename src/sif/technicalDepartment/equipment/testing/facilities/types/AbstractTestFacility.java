package sif.technicalDepartment.equipment.testing.facilities.types;

import java.lang.reflect.Field;

import sif.model.inspection.SpreadsheetInventory;
import sif.model.policy.policyrule.AbstractPolicyRule;
import sif.model.policy.policyrule.configuration.ParameterConfiguration;
import sif.model.violations.lists.ViolationList;
import sif.technicalDepartment.equipment.TestBay;

public abstract class AbstractTestFacility {

	private TestBay testBay;

	private AbstractPolicyRule testedPolicyRule;

	protected SpreadsheetInventory inventory;

	private void configure() throws Exception {
		for (ParameterConfiguration<?> pconfg : getTestedPolicyRule()
				.getConfiguration().getParameters()) {

			Field f = this.getClass().getDeclaredField(pconfg.getFieldName());
			f.setAccessible(true);
			f.set(this, pconfg.getParameterValue());
			f.setAccessible(false);
		}
	}

	protected SpreadsheetInventory getInventory() {
		return inventory;
	}

	public TestBay getTestBay() {
		return this.testBay;
	}

	public AbstractPolicyRule getTestedPolicyRule() {
		return testedPolicyRule;
	}

	public abstract ViolationList run();

	public void setTestBay(TestBay testBay) {
		this.testBay = testBay;
		this.inventory = testBay.getInspection().getInventory();
	}

	public void setTestedPolicyRule(AbstractPolicyRule testedPolicyRule)
			throws Exception {
		this.testedPolicyRule = testedPolicyRule;
		this.configure();
	}
}