package com.ranjan.javaTestApp.controller;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.ranjan.javaTestApp.dto.GenericResponse;
import com.ranjan.javaTestApp.error.RecordNotFoundException;
import com.ranjan.javaTestApp.error.ResourceNotFoundException;
import com.ranjan.javaTestApp.services.DataModelServices;
import com.ranjan.javaTestApp.services.ModelServices;

@Validated
@RestController
@RequestMapping(path = "/", method = { RequestMethod.GET, RequestMethod.POST })
public class DataModelController {

	@Autowired
	ModelServices modelService;

	@Autowired
	DataModelServices dataServices;

	@RequestMapping(path = "/model-object.add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public GenericResponse addModelAttribute(@RequestBody Map<String, Object> requestObj) throws ResourceNotFoundException, ParseException {
		
		if (dataServices.saveDataObjectDetails(requestObj) != null) {
			return new GenericResponse("0", "Successfully Inserted", "true");
		} else {
			return new GenericResponse("1", "Failed", "false");
		}
	}

	@RequestMapping(path = "/model-object-list.add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public GenericResponse addModelAttributeList(@RequestBody List<Map<String, Object>> requestJsonList,
			HttpServletRequest request, RedirectAttributes attributes)
			throws ResourceNotFoundException, ParseException {
		if (!requestJsonList.isEmpty()) {
			int count = 0;
			for (Map<String, Object> requestJson : requestJsonList) {
				dataServices.saveDataObjectDetails(requestJson);
				count++;
			}
			if (count == requestJsonList.size()) {
				return new GenericResponse("0", "Successfully Inserted", "true");
			}
		}
		return new GenericResponse("1", "Failed", "false");
	}
	@RequestMapping(path = "/model-object.update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public GenericResponse updateObjectValue(@RequestBody Map<String, String> requestObj) throws ResourceNotFoundException, ParseException {
		
		if (dataServices.updateObjectValue(requestObj)) {
			return new GenericResponse("0", "Successfully Inserted", "true");
		} else {
			return new GenericResponse("1", "Failed", "false");
		}
	}
	
	@RequestMapping(path = "/object.delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public GenericResponse deleteModelClass(@NotNull @NotEmpty @RequestParam("obj_id") Long model_id) {
		dataServices.removeObject(model_id);
		return new GenericResponse("0", "Successfully Deleted", "true");
	}

	@JsonInclude(value = Include.NON_NULL)
	@RequestMapping(path = "/object.get", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> getObjectById(@NotNull @NotEmpty @RequestParam("search_id") Long id) {
		Map<String, Object> response = dataServices.getObjectById(id);
		if (!response.isEmpty()) {
			return response;
		} else {
			throw new RecordNotFoundException("No details for given ID");
		}
	}
}
