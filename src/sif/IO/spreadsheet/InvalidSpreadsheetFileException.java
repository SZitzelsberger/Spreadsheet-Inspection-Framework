package sif.IO.spreadsheet;

/***
 * Indicates that a spreadsheet instance could not be created from a spreadsheet
 * file, because the file was invalid.
 * 
 * @author Sebastian Zitzelsberger
 * 
 */
public class InvalidSpreadsheetFileException extends Exception {

	private static final long serialVersionUID = -4386440206999821735L;

	public InvalidSpreadsheetFileException(String message) {
		super(message);
	}

}
