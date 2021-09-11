package com.example.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.BooksModel;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional
public class CustomBookInformationService {

	private static final Logger logger = LoggerFactory.getLogger(CustomBookInformationService.class);

	@Autowired
	private BooksInformationService booksInformationService;

	public BooksModel updateBooks(BooksModel booksModel) {
		logger.info("We are in update book method");

		Optional<BooksModel> bookModelOptional = booksInformationService.findById(booksModel.getId());

		if (bookModelOptional.get() != null) {
			booksInformationService.save(booksModel);
			return bookModelOptional.get();
		}

		return null;
	}

	public BooksModel updateBooksForPatch(BooksModel booksModel) {
		logger.info("We are in Patch book method");

		Optional<BooksModel> bookModelOptional = booksInformationService.findById(booksModel.getId());
		BooksModel booksModelOld = bookModelOptional.get();
		if (bookModelOptional.get() != null) {
			if (booksModel.getAuthor() != null) {
				booksModelOld.setAuthor(booksModel.getAuthor());
			}
			if (booksModel.getName() != null) {
				booksModelOld.setName(booksModel.getName());
			}
			if (booksModel.getPrice() > 0) {
				booksModelOld.setPrice(booksModel.getPrice());
			}
			booksInformationService.save(booksModelOld);
			return booksModelOld;
		}
		return null;

	}

}
