package sif.frontOffice;

import java.io.File;
import java.util.ArrayList;
import java.util.TreeMap;

import sif.model.inspection.InspectionRequest;
import sif.model.policy.Policy;
import sif.model.policy.policyrule.AbstractPolicyRule;
import sif.technicalDepartment.equipment.scanning.ElementScanner;
import sif.technicalDepartment.management.TechnicalManager;

/***
 * The FrontDesk is the access point to use the functionality of SIF. However it
 * does not perform any work, but simply forwards calls to the
 * {@link PolicyManager}, {@link TechnicalManager} or {@link InspectionManager}.
 * Implements the Facade and Singleton pattern.
 * 
 * @author Sebastian Zitzelsberger
 * 
 */
public class FrontDesk {

	/***
	 * Gets the instance of the front desk.
	 * 
	 * @return The instance.
	 */
	public static FrontDesk getInstance() {
		if (theInstance == null) {
			theInstance = new FrontDesk();
		}
		return theInstance;

	}

	private PolicyManager policyManager;
	private InspectionManager inspectionManager;
	private TechnicalManager technicalManager;

	private static FrontDesk theInstance;

	private FrontDesk() {
		policyManager = new PolicyManager();
		inspectionManager = new InspectionManager();
		technicalManager = TechnicalManager.getInstance();
	}

	/***
	 * Creates an Inspection report for the last created inspection request at
	 * the given path.
	 * 
	 * @param path
	 *            the given path.
	 * @throws Exception
	 * 
	 */
	public void createInspectionReport(String path) throws Exception {
		inspectionManager.createInspectionReport(path);
	}

	/***
	 * Gets all available policies that have been registered with SIF and can be
	 * chosen for an inspection request.
	 * 
	 * @return The list of available policies.
	 */
	public TreeMap<String, Policy> getAvailablePolicies() {
		return policyManager.getAvailablePolicies();

	}

	/***
	 * Gets all available policy rules that have been registered with SIF and
	 * can be added to a new policy.
	 * 
	 * @return The list of available policy rules
	 */
	public ArrayList<AbstractPolicyRule> getAvailablePolicyRules() {
		return policyManager.getAvailablePolicyRules();
	}

	/***
	 * Gets the list of all registered element scanners.
	 * 
	 * @return The list of registered element scanners.
	 */
	public TreeMap<ElementScanner<?>, Integer> getScannerList() {
		return technicalManager.getScannerList();
	}

	/***
	 * Register the given policy with SIF so it can be chosen for inspections.
	 * 
	 * @param policy
	 */
	public void register(Policy policy) {
		this.policyManager.register(policy);
	}

	/***
	 * Creates a new inspection request with the given request name for the
	 * given spreadsheet file.
	 * 
	 * @param requestName
	 *            The given request name. May not be null or empty.
	 * @param spreadsheetFile
	 *            The given spreadsheet file.
	 * @return The created inspection request.
	 * @throws Exception
	 *             Throws an exception if the given spreadsheet file is invalid.
	 */
	public InspectionRequest requestNewInspection(String requestName,
			File spreadsheetFile) throws Exception {
		return inspectionManager.createNewInspectionRequest(requestName,
				spreadsheetFile);
	}

	/***
	 * Runs the inspection for the last created inspection request.
	 * 
	 * @throws Exception
	 */
	public void run() throws Exception {
		this.inspectionManager.run();
	}

	/***
	 * Scans the elements the last created inspection request.
	 */
	public void scan() {
		this.inspectionManager.scan();
	}

	/***
	 * Sets the given policy for the last created inspection request.
	 * 
	 * @param policy
	 *            The given policy
	 * @throws Exception
	 */
	public void setPolicy(Policy policy) throws Exception {
		this.inspectionManager.setPolicy(policy);
	}

}
