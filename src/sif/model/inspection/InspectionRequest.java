package sif.model.inspection;

import java.io.File;
import java.util.UUID;

import sif.model.policy.Policy;
import sif.model.violations.Findings;

/**
 * Represents a request from a user of the Spreadsheet Inspection Framework to
 * inspect a spreadsheet.
 * 
 * @author Sebastian Zitzelsberger
 * 
 */
public class InspectionRequest {
	private UUID id;
	private String name;
	private SpreadsheetInventory inventory;
	private Policy policy;
	private Findings findings;
	private File spreadsheetFile;

	public InspectionRequest() {
		this.id = UUID.randomUUID();
	}

	public Findings getFindings() {
		return findings;
	}

	public UUID getId() {
		return this.id;
	}

	public SpreadsheetInventory getInventory() {
		return this.inventory;
	}

	public String getName() {
		return this.name;
	}

	public Policy getPolicy() {
		return this.policy;
	}

	public File getSpreadsheetFile() {
		return spreadsheetFile;
	}

	public void setFindings(Findings findings) {
		this.findings = findings;
	}

	public void setInventory(SpreadsheetInventory inventory) {
		this.inventory = inventory;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPolicy(Policy policy) {
		this.policy = policy;
	}

	public void setSpreadsheetFile(File spreadsheetFile) {
		this.spreadsheetFile = spreadsheetFile;
	}

}