package sif.technicalDepartment.equipment;

import java.util.ArrayList;

import sif.model.inspection.InspectionRequest;
import sif.model.violations.Findings;
import sif.technicalDepartment.equipment.testing.facilities.types.AbstractTestFacility;

public class TestBay {
	private Findings findings;
	private InspectionRequest inspection;
	private ArrayList<AbstractTestFacility> testFacilities;

	public TestBay(InspectionRequest inspection) {
		this.testFacilities = new ArrayList<AbstractTestFacility>();
		this.inspection = inspection;
		this.findings = new Findings();
	}

	public void add(AbstractTestFacility testFacility) {
		this.testFacilities.add(testFacility);
	}

	public InspectionRequest getInspection() {
		return this.inspection;
	}

	public void runTestFacilities() {
		for (AbstractTestFacility testFacility : testFacilities) {
			findings.add(testFacility.run());

		}
		this.inspection.setFindings(findings);
	}

	public void setInspection(InspectionRequest currentSession) {
		this.inspection = currentSession;
	}

}