package org.jainbooks.ebook.dto;

import java.util.List;

import org.jainbooks.ebook.domain.EBook;

public class EBookLibraryDTO {
	
	public List<EBook> getEbooks() {
		return ebooks;
	}

	public void setEbooks(List<EBook> ebooks) {
		this.ebooks = ebooks;
	}

	private List<EBook> ebooks;
	
}
