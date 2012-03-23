package sif.IO;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import sif.IO.spreadsheet.ISpreadsheetIO;
import sif.IO.spreadsheet.InvalidSpreadsheetFileException;
import sif.IO.spreadsheet.poi.POISpreadsheetIO_HSSF;
import sif.IO.spreadsheet.poi.POISpreadsheetIO_XSSF;
import sif.model.elements.basic.spreadsheet.Spreadsheet;
import sif.model.inspection.InspectionRequest;
import sif.model.violations.Findings;
import sif.model.violations.ISingleViolation;
import sif.model.violations.IViolation;
import sif.model.violations.IViolationGroup;
import sif.model.violations.lists.ViolationList;

/***
 * DataFacade provides access to IO functions. It can create a
 * {@link Spreadsheet} from a given spreadsheet file and create a html-report
 * for a given inspection request. Implements the Facade and Singleton pattern.
 * 
 * 
 * @author Sebastian Zitzelsberger
 * 
 */
public final class DataFacade {

	private static DataFacade theInstance;

	/***
	 * Gets the instance of the DataFacade.
	 * 
	 * @return The instance.
	 */
	public static DataFacade getInstance() {
		if (theInstance == null) {
			theInstance = new DataFacade();
		}
		return theInstance;
	}

	/***
	 * 
	 * Calls the corresponding {@link ISpreadsheetIO} to create a
	 * {@link Spreadsheet} from the given spreadsheet file.
	 * 
	 * @param spreadsheetFile
	 *            The given spreadsheet file.
	 * @return The spreadsheet for the given spreadsheet spreadsheet file.
	 * @throws InvalidSpreadsheetFileException
	 *             Throws an exception if the spreadsheet file can't be
	 *             transformed to the internal model.
	 */
	public Spreadsheet createSpreadsheet(File spreadsheetFile)
			throws InvalidSpreadsheetFileException {
		ISpreadsheetIO spreadsheetIO = null;

		// .xls Files are handled by the POISpreadsheetIO_HSSF;
		if (spreadsheetFile.getPath().endsWith(".xls")) {
			spreadsheetIO = new POISpreadsheetIO_HSSF();
			// .xlsx files are handled by the POISpreadsheetIO_XSSF;
		} else if (spreadsheetFile.getPath().endsWith(".xlsx")) {
			spreadsheetIO = new POISpreadsheetIO_XSSF();
			// Other files are not supported yet.
		} else {
			throw new InvalidSpreadsheetFileException(
					"Unsupported spreadsheet file format.");
		}

		return spreadsheetIO.createSpreadsheet(spreadsheetFile);

	}

	/***
	 * Creates a hmtl-report for the given inspection request at the given path,
	 * using the request's name as a filename.
	 * 
	 * @param inspectionRequest
	 *            The given inspection request.
	 * @param path
	 *            The given path.
	 * @throws IOException
	 *             Throws an IOExcpetion if the file can't be created at the
	 *             given path.
	 */
	public void export(InspectionRequest inspectionRequest, String path)
			throws IOException {

		String filePath = path;
		if (!filePath.endsWith(File.pathSeparator)) {
			filePath = filePath + File.separator;
		}

		FileWriter outFile = new FileWriter(filePath
				+ inspectionRequest.getName() + ".html");
		PrintWriter out = new PrintWriter(outFile);
		out.append(getFindingsAsHtmlString(inspectionRequest));
		out.close();
	}

	private String getFindingsAsHtmlString(InspectionRequest inspectionRequest) {
		StringBuilder builder = new StringBuilder();
		Findings findings = inspectionRequest.getFindings();
		builder.append("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01//EN\">\n");
		builder.append("<html>\n");
		builder.append("<head>\n");
		builder.append("\n");
		builder.append("<title>\n");
		builder.append("Inspection report:"
				+ inspectionRequest.getSpreadsheetFile().getName() + "\n");
		builder.append("</title>\n");
		builder.append("</head>\n");
		builder.append("<body>\n");

		// CSS- Styles
		builder.append("<style type=\"text/css\">\n");
		builder.append("\n");
		builder.append("label {\n");
		builder.append("font-weight: bold;\n");
		builder.append("}\n");
		builder.append("\n");

		builder.append("td {\n");
		builder.append("align:center;\n");
		builder.append("}\n");
		builder.append("\n");

		builder.append("</style>\n");
		builder.append("\n");

		builder.append("<h1>\n");
		builder.append("Spreadsheet\n");
		builder.append("</h1>\n");
		builder.append("<label>"
				+ inspectionRequest.getInventory().getSpreadsheetFile()
						.getAbsolutePath() + "</label>\n");

		builder.append("<h1>\n");
		builder.append("Configuration\n");
		builder.append("</h1>\n");

		builder.append("<h2>\n");
		builder.append("Policy");
		builder.append("</h2>");

		builder.append("<label>\n");
		builder.append("Name: \n");
		builder.append("</label>\n");
		builder.append(inspectionRequest.getPolicy().getName() + "\n");
		builder.append("<br>\n");
		builder.append("\n");

		builder.append("<label>\n");
		builder.append("Author: \n");
		builder.append("</label>\n");
		builder.append(inspectionRequest.getPolicy().getAuthor() + "\n");
		builder.append("<br>\n");
		builder.append("\n");

		builder.append("<label>\n");
		builder.append("Description: \n");
		builder.append("</label>\n");
		builder.append(inspectionRequest.getPolicy().getDescription() + "\n");
		builder.append("\n");

		// Write Findings
		builder.append("<h1>\n");
		builder.append("Findings\n");
		builder.append("</h1>\n");

		for (ViolationList violationsList : findings.getViolationLists()) {

			builder.append("<h2>\n");
			builder.append(violationsList.getPolicyRule().getName() + "\n");
			builder.append(" (" + violationsList.getNumberOfViolations()
					+ " compounds; "
					+ violationsList.getNumberOfSingleViolations()
					+ " single violations)\n");
			builder.append("</h2>\n");
			builder.append("\n");
			builder.append("<label>Author: </label>"
					+ violationsList.getPolicyRule().getAuthor() + "<br>\n");
			builder.append("<label>Descritption: </label>"
					+ violationsList.getPolicyRule().getDescription()
					+ "<br>\n");
			builder.append("<label>Background: </label>"
					+ violationsList.getPolicyRule().getBackground() + "<br>\n");
			builder.append("<label>Chosen Severity Weight: </label>"
					+ violationsList.getPolicyRule().getSeverityWeight()
					+ "<br>\n");
			builder.append("<label>Possible Solution: </label>"
					+ violationsList.getPolicyRule().getPossibleSolution()
					+ "<br>\n");
			builder.append("<br>\n");
			builder.append("<br>\n");

			builder.append("<table border=\"1px\" style=\" width: 100%; overflow: scroll\" >\n");

			builder.append("<colgroup>\n");
			builder.append("<col width=\"50\">\n");
			builder.append("<col width=\"400\">\n");
			builder.append("<col width=\"200\">\n");
			builder.append("<col width=\"400\">\n");
			builder.append("<col width=\"50\">\n");
			builder.append("</colgroup>\n");

			builder.append("<thead style=\"table-layout:fixed;\">\n");
			builder.append(" <tr style=\"font-family:Tahoma; fontweight=bold; font-size:120%\">\n");
			builder.append("<th>Number</th>\n");
			builder.append("<th>Causing element</th>\n");
			builder.append("<th>Location</th>\n");
			builder.append("<th>Description</th>\n");
			builder.append("<th>Severity</th>\n");
			builder.append(" </tr>\n");
			builder.append("</thead>\n");

			builder.append("<tbody>\n");
			Integer counter = 0;
			for (IViolation violation : violationsList.getViolations()) {
				// Write a single vioaltion.
				if (violation instanceof ISingleViolation) {
					ISingleViolation singleViolation = (ISingleViolation) violation;
					counter++;
					builder.append(" <tr>\n");
					builder.append("<td>\n" + counter + "</td>\n");
					builder.append("<td>\n"
							+ singleViolation.getCausingElement()
									.getStringRepresentation() + "</td>\n");
					builder.append("<td>\n"
							+ singleViolation.getCausingElement().getLocation()
							+ "</td>\n");
					builder.append("<td>" + singleViolation.getDescription()
							+ "</td>\n");

					builder.append("<td>"
							+ singleViolation.getWeightedSeverityValue()
							+ "</td>\n");

					builder.append(" </tr>\n");
				} else {
					// Write a violation group;
					IViolationGroup violationGroup = (IViolationGroup) violation;
					builder.append("</tbody>\n");
					builder.append("<tbody bgcolor=\"lightgrey\">\n");
					builder.append("<tr align=\"left\"\n>");
					counter++;
					builder.append("<th>" + counter + "</th>\n");
					builder.append("<th>\n"
							+ violationGroup.getCausingElement()
									.getStringRepresentation() + "</th>\n");
					builder.append("<th>\n"
							+ violationGroup.getCausingElement().getLocation()
							+ "</th>\n");
					builder.append("<th>" + violationGroup.getDescription()
							+ "</th>\n");
					builder.append("<th>"
							+ violationGroup.getWeightedSeverityValue()
							+ "</th>\n");
					builder.append(" </tr>\n");

					Integer groupCounter = 0;
					for (ISingleViolation singleViolation : violationGroup
							.getMembers()) {

						groupCounter++;
						builder.append(" <tr>\n");
						builder.append("<td>" + counter + "." + groupCounter
								+ "</td>\n");
						builder.append("<td>"
								+ singleViolation.getCausingElement()
										.getStringRepresentation() + "</td\n>");
						builder.append("<td>"
								+ singleViolation.getCausingElement()
										.getLocation() + "</td\n>");
						builder.append("<td>"
								+ singleViolation.getDescription() + "</td>\n");

						builder.append("<td>"
								+ singleViolation.getWeightedSeverityValue()
								+ "</td>\n");

						builder.append(" </tr>\n");

					}
					builder.append("</tbody>\n");
				}
			}

			builder.append("</tbody>\n");
			builder.append("</table>\n");
		}
		builder.append("</body>\n");
		builder.append("</html>\n");

		return builder.toString();
	}
}
