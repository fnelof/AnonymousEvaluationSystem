package gr.unipi.issue.controller;


import java.io.IOException;
import java.io.InputStream;

import gr.unipi.issue.common.Constants;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class DictionaryController {

	private static final Logger logger = LogManager.getLogger(DictionaryController.class);


	/*
	 *  Handles the request for the dictionary 
	 *  needed for the generation of m on front end
	*/
	@RequestMapping(value="/getDictionary")
	public String getDictionary() {
		logger.info("Start getDictionary");
		JSONObject response = new JSONObject();

		try {
			// Reads data.json file with the wordlist
			Resource resource = new ClassPathResource(Constants.DICTIONARY_PATH);
			InputStream resourceInputStream = resource.getInputStream();
			byte[] jsonData = IOUtils.toByteArray(resourceInputStream);
			ObjectMapper objectMapper = new ObjectMapper();
			// Creates the jsonArray of the wordlist
			JsonNode rootNode = objectMapper.readTree(jsonData);
			JSONArray  jsonArray = new JSONArray();
			for(final JsonNode node: rootNode) {
				jsonArray.put(node.toString());
			}
			response.put(Constants.DICTIONARY_RESPONSE_OBJECT, jsonArray);
			logger.info("End getDictionary");
			return response.toString();
			// Handles the exception of fetching the data.json wordlist
		} catch (IOException e) {
			logger.error("There was an error using the dictionary file: ",e);
			response.put(Constants.ERROR_RESPONSE_OBJECT, "Something went wrong on our end. Please contact the administrator of the site for more details.");
			return response.toString();

		} catch (Exception ex){
			logger.error("Unknown error occured when creating the wordlist json object: ", ex);
			response.put(Constants.ERROR_RESPONSE_OBJECT, "Something went wrong on our end. Please contact the administrator of the site for more details.");
			return response.toString();
		}
	}
	
}
