package sif.IO.spreadsheet.poi;

import org.apache.poi.POIXMLProperties.CoreProperties;
import org.apache.poi.xssf.extractor.XSSFExcelExtractor;
import org.apache.poi.xssf.usermodel.XSSFEvaluationWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import sif.IO.spreadsheet.ISpreadsheetIO;
import sif.model.elements.basic.spreadsheet.SpreadsheetProperties;

/***
 * {@link ISpreadsheetIO} for .xlsx spreadsheet files.
 * 
 * @author Sebastian Zitzelsberger
 * 
 */
public class POISpreadsheetIO_XSSF extends AbstractPOISpreadsheetIO {

	private XSSFWorkbook xssfWorkbook;

	@Override
	public void createCoreProperties() {
		XSSFExcelExtractor extractor = new XSSFExcelExtractor(xssfWorkbook);
		CoreProperties coreProperties = extractor.getCoreProperties();
		SpreadsheetProperties spreadsheetProperties = new SpreadsheetProperties();
		spreadsheetProperties.setAuthor(coreProperties.getCreator());
		spreadsheetProperties.setCategory(coreProperties.getCategory());
		spreadsheetProperties.setCreationDate(coreProperties.getCreated());
		spreadsheetProperties.setSubject(coreProperties.getSubject());
		spreadsheetProperties.setKeywords(coreProperties.getKeywords());
		spreadsheetProperties.setRevision(coreProperties.getRevision());
		spreadsheetProperties.setTitle(coreProperties.getTitle());
		spreadsheet.setProperties(spreadsheetProperties);
	}

	@Override
	public void initialize() {
		this.xssfWorkbook = (XSSFWorkbook) poiWorkbook;
		this.formulaParsingWorkbook = XSSFEvaluationWorkbook
				.create(xssfWorkbook);
	}

}
