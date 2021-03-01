package com.ranjan.javaTestApp.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ranjan.javaTestApp.beans.ModelAttributeBean;
import com.ranjan.javaTestApp.beans.ModelClassBean;
import com.ranjan.javaTestApp.dto.GenericResponse;
import com.ranjan.javaTestApp.dto.ModelAttributeDTO;
import com.ranjan.javaTestApp.dto.ModelClassDTO;
import com.ranjan.javaTestApp.error.RecordNotFoundException;
import com.ranjan.javaTestApp.error.ResourceNotFoundException;
import com.ranjan.javaTestApp.services.ModelServices;

@Validated
@RestController
@RequestMapping(path = "/", method = { RequestMethod.GET, RequestMethod.POST })
public class ModelController {

	@Autowired
	ModelServices modelService;


	/*
	 * The following method will perform
	 * @Action: Add Model Class only
	 * @Action: Add Model Class with Attribute
	 * @Action: Update Model Class only
	 * @Action: Update Model Class with Attribute
	 */
	@RequestMapping(path = "/model-class.add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public GenericResponse addModelClass(@RequestBody ModelClassDTO requestJson, HttpServletRequest request,
			RedirectAttributes attributes) throws ParseException, ResourceNotFoundException {
		ModelClassBean model = modelService.addModelClass(requestJson);
		if (model != null) {
			return new GenericResponse("0", "Successfully Inserted", "true",model.getModelId().toString());
		} else {
			return new GenericResponse("1", "Failed", "false");
		}
	}
	/*
	 * The following method will perform
	 * @Action: Add List of Model Class only
	 * @Action: Add List of Model Class with Attribute
	 * @Action: Update List of Model Class only
	 * @Action: Update List of Model Class with Attribute
	 */
	@RequestMapping(path = "/model-class.addList", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public GenericResponse addModelClassList(@RequestBody List<ModelClassDTO> requestJsonList,
			HttpServletRequest request, RedirectAttributes attributes)
			throws ParseException, ResourceNotFoundException {
		if (!requestJsonList.isEmpty()) {
			int count = 0;
			for (ModelClassDTO requestJson : requestJsonList) {
				modelService.addModelClass(requestJson);
				count++;
			}
			if (count == requestJsonList.size()) {
				return new GenericResponse("0", "Successfully Inserted", "true");
			}
		}
		return new GenericResponse("1", "Failed", "false");

	}

	/*
	 * The following method will perform
	 * @Action: Add Model Attribute Class for Model Class
	 * @Action: Update Attribute Class for Model Class
	 */
	@RequestMapping(path = "/model-attribute.add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public GenericResponse addModelAttribute(@RequestBody ModelAttributeDTO requestJson, HttpServletRequest request,
			RedirectAttributes attributes) throws ParseException, ResourceNotFoundException {
		if (modelService.addModelAttribute(requestJson) != null) {
			return new GenericResponse("0", "Successfully Inserted", "true");
		} else {
			return new GenericResponse("1", "Failed", "false");
		}
	}

	/*
	 * The following method will perform
	 * @Action: Add List of Model Attribute Class for Model Class
	 * @Action: Update List of Model Attribute Class for Model Class
	 */
	@RequestMapping(path = "/model-attribute.addList", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public GenericResponse addModelAttributeList(@RequestBody List<ModelAttributeDTO> requestJsonList,
			HttpServletRequest request, RedirectAttributes attributes)
			throws ParseException, ResourceNotFoundException {
		if (!requestJsonList.isEmpty()) {
			int count = 0;
			for (ModelAttributeDTO requestJson : requestJsonList) {
				modelService.addModelAttribute(requestJson);
				count++;
			}
			if (count == requestJsonList.size()) {
				return new GenericResponse("0", "Successfully Inserted", "true");
			}
		}
		return new GenericResponse("1", "Failed", "false");

	}
	/*
	 * The following method will perform
	 * @Action: Delete Model Class for given ID
	 */
	@RequestMapping(path = "/model-class.delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public GenericResponse deleteModelClass(@NotNull @NotEmpty @RequestParam("model_id") Long model_id) {
		modelService.removeModelClass(model_id);
		return new GenericResponse("0", "Successfully Deleted", "true");
	}
	/*
	 * The following method will perform
	 * @Action: Delete Model Attribute Class for given ID
	 */
	@RequestMapping(path = "/model-attribute.delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public GenericResponse deleteModelAttribute(@NotNull @NotEmpty @RequestParam("attr_id") Long attr_id) {
		modelService.removeModelAttribute(attr_id);
		return new GenericResponse("0", "Successfully Deleted", "true");
	}

	/*****************************************************************************************************/

	@RequestMapping(path = "/model-class-list.get", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ModelClassBean> getModelClassList() {
		return modelService.getAllModelClass();
	}

	@RequestMapping(path = "/model-attribute-list.get", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ModelAttributeBean> getModelAttributeList() {
		return modelService.getAllModelAttribute();
	}

	@RequestMapping(path = "/model-class.get", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ModelClassBean getModelClass(@NotNull @NotEmpty @RequestParam("model_id") Long model_id) {
		Optional<ModelClassBean> response = modelService.getModelClass(model_id);
		if (response.isPresent()) {
			return response.get();
		} else {
			throw new RecordNotFoundException("No details for given ID");
		}
	}

	@RequestMapping(path = "/model-attribute.get", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ModelAttributeBean getModelAttribute(@NotNull @NotEmpty @RequestParam("attr_id") Long attr_id) {
		Optional<ModelAttributeBean> response = modelService.getModelAttribute(attr_id);
		if (response.isPresent()) {
			return response.get();
		} else {
			throw new RecordNotFoundException("No details for given ID");
		}
	}

	@RequestMapping(path = "/model-class.dummy", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ModelClassDTO getModelClassDummy() {
		List<ModelAttributeDTO> list = new ArrayList<ModelAttributeDTO>();
		list.add(new ModelAttributeDTO());
		return new ModelClassDTO(list);
	}

	@RequestMapping(path = "/model-parameter.dummy", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Map<String, String>> getModelParameter(
			@NotNull @NotEmpty @RequestParam("model_id") Long model_id) {
		List<ModelAttributeBean> attr = modelService.getAllByModel(model_id);
		Map<String, Map<String, String>> map_resp = new HashMap<String, Map<String, String>>();
		Map<String, String> map = new HashMap<String, String>();
		for (ModelAttributeBean atb : attr) {
			map.put(atb.getDispName(), atb.getDataType());
			map_resp.put(atb.getModelClass().getDisplayName(), map);
		}
		return map_resp;
	}

	@RequestMapping(path = "/model-parameter-list.dummy", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Map<String, Map<String, String>>> getModelParameter() {
		List<ModelClassBean> model_list = modelService.getAllModelClass();
		List<Map<String, Map<String, String>>> response = new ArrayList<Map<String, Map<String, String>>>();
		for (ModelClassBean model : model_list) {
			List<ModelAttributeBean> attr = modelService.getAllByModel(model.getModelId());
			Map<String, Map<String, String>> map_resp = new HashMap<String, Map<String, String>>();
			Map<String, String> map = new HashMap<String, String>();
			for (ModelAttributeBean atb : attr) {
				map.put(atb.getDispName(), atb.getDataType());
				map_resp.put(atb.getModelClass().getDisplayName(), map);
			}
			response.add(map_resp);
		}
		return response;
	}
}
