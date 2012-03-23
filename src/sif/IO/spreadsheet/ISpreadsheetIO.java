package sif.IO.spreadsheet;

import java.io.File;

import sif.model.elements.basic.spreadsheet.Spreadsheet;

/***
 * 
 * 
 * @author Sebastian Zitzelsberger
 * 
 */
public interface ISpreadsheetIO {

	/***
	 * Creates a spreadsheet instance from the given spreadsheet file.
	 * 
	 * @param spreadsheetFile
	 *            The given spreadsheet file from which to create a spreadsheet
	 *            instance
	 * @return The spreadsheet instance from the given spreadsheet file.
	 * @throws InvalidSpreadsheetFileException
	 *             Throws an exception if the spreadsheet file can't be
	 *             transformed to the internal model.
	 */
	Spreadsheet createSpreadsheet(File spreadsheetFile)
			throws InvalidSpreadsheetFileException;

}
