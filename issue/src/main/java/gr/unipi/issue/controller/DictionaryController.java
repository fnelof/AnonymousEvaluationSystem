package gr.unipi.issue.controller;


import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.compress.utils.IOUtils;
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
	
	/*
	 *  Handles the request for the dictionary 
	 *  needed for the generation of m on front end
	*/
	@RequestMapping(value="/getDictionary")
	public String getDictionary() {
		
		JSONObject response = new JSONObject();

		try {
			// Reads data.json file with the wordlist
			Resource resource = new ClassPathResource("data.json");
			InputStream resourceInputStream = resource.getInputStream();
			byte[] jsonData = IOUtils.toByteArray(resourceInputStream);
			ObjectMapper objectMapper = new ObjectMapper();
			// Creates the jsonArray of the wordlist
			JsonNode rootNode = objectMapper.readTree(jsonData);
			JSONArray  jsonArray = new JSONArray();
			for(final JsonNode node: rootNode) {
				jsonArray.put(node.toString());
			}
			response.put("dictionary", jsonArray);
			
			return response.toString();
			// Handles the exception of fetching the data.json wordlist
		} catch (IOException e) {
			
			e.printStackTrace();
			response.put("error", "Something went wrong");
			return response.toString();

		}
	}
	
}
