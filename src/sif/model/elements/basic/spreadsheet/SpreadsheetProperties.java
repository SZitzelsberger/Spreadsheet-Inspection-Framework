package sif.model.elements.basic.spreadsheet;

import java.util.Date;

import sif.model.elements.BasicAbstractElement;

/**
 * Representation of some basic properties of a spreadsheet.
 * 
 * @author Sebastian Zitzelsberger
 * 
 */
public class SpreadsheetProperties extends BasicAbstractElement {
	private String category;
	private Date creationDate;
	private String author;
	private String title;
	private String subject;
	private String revision;
	private String keywords;
	private String comments;

	public String getAuthor() {
		return this.author;
	}

	public String getCategory() {
		return this.category;
	}

	public String getComments() {
		return comments;
	}

	public Date getCreationDate() {
		return this.creationDate;
	}

	public String getKeywords() {
		return this.keywords;
	}

	@Override
	public String getLocation() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getPluralName() {
		return null;
	}

	public String getRevision() {
		return this.revision;
	}

	@Override
	public String getStringRepresentation() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getSubject() {
		return subject;
	}

	public String getTitle() {
		return this.title;
	}

	@Override
	public String getValueAsString() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public void setRevision(String revision) {
		this.revision = revision;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}