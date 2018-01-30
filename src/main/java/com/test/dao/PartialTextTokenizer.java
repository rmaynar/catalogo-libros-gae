package com.test.dao;

import java.util.logging.Logger;

public class PartialTextTokenizer {

	    private static final Logger LOGGER = Logger.getLogger(PartialTextTokenizer.class.getName());

	    private static final String SEPARATOR = " ";

	    private int size;

	    public PartialTextTokenizer(int size) {
	        if (size < 1) {
	            throw new IllegalArgumentException("Token size must be great than 0");
	        }
	        this.size = size;
	    }

	    public String tokenize(String text) {

	        if (text == null) {
	            return SEPARATOR;
	        }

	        StringBuilder tokens = new StringBuilder();

	        for (String word : text.split(SEPARATOR)) {
	            for (int i = 0; i <= word.length() - size; i++) {
	                tokens.append(word.substring(0, i + size)).append(SEPARATOR);
	            }
	        }

	        LOGGER.info("Tokens: " + tokens.toString());

	        return tokens.toString();
	    }
	}

