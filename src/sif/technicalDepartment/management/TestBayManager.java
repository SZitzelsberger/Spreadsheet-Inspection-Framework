package sif.technicalDepartment.management;

import java.util.Iterator;
import java.util.TreeMap;
import java.util.UUID;

import sif.model.inspection.InspectionRequest;
import sif.model.policy.expression.policyrule.PolicyRuleExpression;
import sif.model.policy.policyrule.AbstractPolicyRule;
import sif.model.policy.policyrule.CompositePolicyRule;
import sif.model.policy.policyrule.MonolithicPolicyRule;
import sif.model.policy.policyrule.implementations.FormulaComplexityPolicyRule;
import sif.model.policy.policyrule.implementations.NoConstantsInFormulasPolicyRule;
import sif.model.policy.policyrule.implementations.ReadingDirectionPolicyRule;
import sif.technicalDepartment.equipment.TestBay;
import sif.technicalDepartment.equipment.testing.facilities.implementations.FormulaComplexityTestFacility;
import sif.technicalDepartment.equipment.testing.facilities.implementations.NoConstantsInFormulasTestFacilitiy;
import sif.technicalDepartment.equipment.testing.facilities.implementations.ReadingDirectionTestFacility;
import sif.technicalDepartment.equipment.testing.facilities.types.AbstractTestFacility;
import sif.technicalDepartment.equipment.testing.facilities.types.CompositeTestFacility;
import sif.technicalDepartment.equipment.testing.facilities.types.MonolithicTestFacility;

/***
 * The TestBayManager is in charge of managing the test equipment and for
 * creating {@link TestBay}s for {@link InspectionRequest}s.
 * 
 * @author Sebastian Zitzelsberger
 * 
 */
class TestBayManager {

	private TreeMap<UUID, TestBay> testBays;
	private TreeMap<String, Class<? extends MonolithicTestFacility>> monolithicTestFacilites;

	protected TestBayManager() {
		monolithicTestFacilites = new TreeMap<String, Class<? extends MonolithicTestFacility>>();
		monolithicTestFacilites.put(
				NoConstantsInFormulasPolicyRule.class.getCanonicalName(),
				NoConstantsInFormulasTestFacilitiy.class);
		monolithicTestFacilites.put(
				ReadingDirectionPolicyRule.class.getCanonicalName(),
				ReadingDirectionTestFacility.class);
		monolithicTestFacilites.put(
				FormulaComplexityPolicyRule.class.getCanonicalName(),
				FormulaComplexityTestFacility.class);
		testBays = new TreeMap<UUID, TestBay>();
	}

	private CompositeTestFacility composeTestFacility(
			PolicyRuleExpression policyExpr) {
		CompositeTestFacility testFacility = policyExpr.interpret();
		return testFacility;
	}

	private AbstractTestFacility getTestFacilityFor(
			AbstractPolicyRule abstractPolicyRule)
			throws InstantiationException, IllegalAccessException {
		AbstractTestFacility abstractTestFacility;
		if ((abstractPolicyRule instanceof CompositePolicyRule))
			abstractTestFacility = composeTestFacility(((CompositePolicyRule) abstractPolicyRule)
					.getPolicyRuleExpression());
		else {
			abstractTestFacility = this.monolithicTestFacilites.get(
					abstractPolicyRule.getClass().getCanonicalName())
					.newInstance();
		}

		return abstractTestFacility;
	}

	/***
	 * 
	 * @param inspection
	 * @throws Exception
	 */
	private void prepareTestBayFor(InspectionRequest inspection)
			throws Exception {
		TestBay testBay = null;
		if (!this.testBays.containsKey(inspection.getId())) {
			testBay = new TestBay(inspection);
			this.testBays.put(inspection.getId(), testBay);
		} else {
			testBay = testBays.get(inspection.getId());
		}

		Iterator<AbstractPolicyRule> policyRuleIterator = inspection
				.getPolicy().getPolicyRules().values().iterator();

		while (policyRuleIterator.hasNext()) {
			AbstractPolicyRule abstractPolicyRule = policyRuleIterator.next();
			try {
				AbstractTestFacility testFacility = getTestFacilityFor(abstractPolicyRule);
				testFacility.setTestBay(testBay);
				testFacility.setTestedPolicyRule(abstractPolicyRule);
				testBay.add(testFacility);
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}

	protected void register(Class<? extends MonolithicPolicyRule> ruleClass,
			Class<? extends MonolithicTestFacility> testFacilityClass) {
		this.monolithicTestFacilites.put(ruleClass.getCanonicalName(),
				testFacilityClass);
	}

	protected void run(InspectionRequest inspection) throws Exception {
		prepareTestBayFor(inspection);
		this.testBays.get(inspection.getId()).runTestFacilities();
	}
}
