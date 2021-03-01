package com.ranjan.javaTestApp.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ranjan.javaTestApp.beans.DateDataBean;
import com.ranjan.javaTestApp.beans.ModelAttributeBean;
import com.ranjan.javaTestApp.beans.ModelObject;
import com.ranjan.javaTestApp.beans.ModelObjectValue;
import com.ranjan.javaTestApp.beans.NumberDataBean;
import com.ranjan.javaTestApp.beans.StringDataBean;
import com.ranjan.javaTestApp.beans.TextDataBean;
import com.ranjan.javaTestApp.error.RecordNotFoundException;
import com.ranjan.javaTestApp.repo.DateDataRepo;
import com.ranjan.javaTestApp.repo.ModelObjectRepo;
import com.ranjan.javaTestApp.repo.ModelObjectValueRepo;
import com.ranjan.javaTestApp.repo.NumberDataRepo;
import com.ranjan.javaTestApp.repo.StringDataRepo;
import com.ranjan.javaTestApp.repo.TextDataRepo;
import com.ranjan.javaTestApp.util.Parser;
import com.ranjan.javaTestApp.util.UuidGenerator;

@Service
public class DataModelServices {

	@Autowired
	DateDataRepo dateRepo;

	@Autowired
	StringDataRepo stringRepo;

	@Autowired
	NumberDataRepo numberRepo;

	@Autowired
	TextDataRepo textRepo;

	@Autowired
	ModelObjectRepo objRepo;

	@Autowired
	ModelObjectValueRepo valueRepo;

	@Autowired
	ModelServices modelServices;

	@SuppressWarnings("unchecked")
	@Transactional(rollbackOn = Exception.class)
	public ModelObject saveDataObjectDetails(Map<String, Object> requestObj) throws ParseException {
		if (requestObj.get("MODEL_ID") == null || requestObj.get("MODEL_ID").toString().equals("")) {
			throw new RecordNotFoundException("Missing Parameter MODEL_ID");
		}
		String model_id = requestObj.get("MODEL_ID").toString();
		Map<String, String> data_obj = (Map<String, String>) requestObj.get("DETAILS");
		String createdBy = data_obj.get("CREATED_BY").toString();
		ModelObject obj = addModelObject(createdBy, model_id);
		List<ModelAttributeBean> attr_list = modelServices.getAllByModel(Long.parseLong(model_id));
		if (!attr_list.isEmpty()) {
			for (ModelAttributeBean modelAttr : attr_list) {
				if (modelAttr.isManditory() && data_obj.get(modelAttr.getDispName()).toString().equals("")) {
					throw new RecordNotFoundException("Manditory Field Missing for :: " + modelAttr.getDispName());
				}
				ModelObjectValue value = null;
				String dataValue = "";
				String type = modelAttr.getDataType();
				if (modelAttr.getDispName().equalsIgnoreCase("DEPARTMENT") && requestObj.get("DEPARTMENT") != null) {
					List<Map<String, String>> dept_list = (List<Map<String, String>>) requestObj.get("DEPARTMENT");
					for (Map<String, String> dept : dept_list) {
						value = addModelValue(createdBy, modelAttr, obj);
						dataValue = dept.get("DEPT_ID");
						splitAndStoreValue(type, value, dataValue);
					}
				} else {
					if (modelAttr.getDispName().equals("EMPLOYEE_ID")) {
						dataValue = obj.getObjectId().toString();
					} else {
						dataValue = data_obj.get(modelAttr.getDispName()).toString();
					}
					value = addModelValue(createdBy, modelAttr, obj);
					splitAndStoreValue(type, value, dataValue);
				}
			}
			return obj;
		}
		throw new RecordNotFoundException("No details found for given MODEL_ID");
	}

	public ModelObject addModelObject(String createdBy, String id) {
		ModelObject obj = new ModelObject();
		obj.setCreatedBy(Long.parseLong(createdBy));
		obj.setCreatedDate(new Date());
		obj.setModel(modelServices.getModelClass(Long.parseLong(id)).get());
		return objRepo.save(obj);
	}

	public ModelObjectValue addModelValue(String createdBy, ModelAttributeBean modelAttr, ModelObject modelObj) {
		ModelObjectValue obj = new ModelObjectValue();
		obj.setCreatedBy(Long.parseLong(createdBy));
		obj.setCreatedDate(new Date());
		obj.setModelAttr(modelAttr);
		obj.setModelObj(modelObj);
		return valueRepo.save(obj);
	}

	public StringDataBean addStringData(ModelObjectValue value, String dataValue) {
		StringDataBean obj = new StringDataBean();
		obj.setId(UuidGenerator.getInstance().getUuid());
		obj.setDataValue(dataValue);
		obj.setObjvalue(value);
		return stringRepo.save(obj);
	}

	public NumberDataBean addNumericData(ModelObjectValue value, String dataValue) {
		NumberDataBean obj = new NumberDataBean();
		obj.setId(UuidGenerator.getInstance().getUuid());
		obj.setDataValue(Long.parseLong(dataValue));
		obj.setObjvalue(value);
		return numberRepo.save(obj);
	}

	public DateDataBean addDateData(ModelObjectValue value, String dataValue) throws ParseException {
		DateDataBean obj = new DateDataBean();
		obj.setId(UuidGenerator.getInstance().getUuid());
		obj.setDataValue(new SimpleDateFormat("dd/MM/yyyy").parse(dataValue));
		obj.setObjvalue(value);
		return dateRepo.save(obj);
	}

	public TextDataBean addTextData(ModelObjectValue value, String dataValue) {
		TextDataBean obj = new TextDataBean();
		obj.setId(UuidGenerator.getInstance().getUuid());
		obj.setDataValue(dataValue);
		obj.setObjvalue(value);
		return textRepo.save(obj);
	}

	public void splitAndStoreValue(String type, ModelObjectValue value, String dataValue) throws ParseException {
		switch (type) {
		case "STRING":
			addStringData(value, dataValue);
			break;
		case "NUMBER":
			addNumericData(value, dataValue);
			break;
		case "DATE":
			addDateData(value, dataValue);
			break;
		case "TEXT":
			addTextData(value, dataValue);
			break;
		}
	}

	public Map<String, Object> getObjectById(Long id) {
		ModelObject obj = objRepo.findById(id).get();
		Map<String, Object> responseList = new HashMap<String, Object>();
		responseList.put("MODEL_NAME", obj.getModel().getDisplayName());
		List<ModelObjectValue> obj_list = valueRepo.findByModelObject(obj.getObjectId());
		for (ModelObjectValue val : obj_list) {
			String value = "";
			if (val.getModelAttr().getDispName().equalsIgnoreCase("Department")) {
				List<String> dept_list = getAllNumberData(val.getValueId());
				for (String dept : dept_list) {
					responseList.put("DEPARTMENT-" + dept, getObjectById(Long.parseLong(dept)));
				}
			} else {
				value = getValue(val.getModelAttr().getDataType(), val.getValueId());
				if (val.getModelAttr().isVisiblity()) {
					responseList.put(val.getModelAttr().getDispName(), value);
				}
			}
		}
		return responseList;
	}

	public String getValue(String type, Long model_obj_id) {
		switch (type) {
		case "STRING":
			return getStringData(model_obj_id);
		case "NUMBER":
			return getNumberData(model_obj_id);
		case "DATE":
			return getDateData(model_obj_id);
		case "TEXT":
			return getTextData(model_obj_id);
		}
		return "";
	}

	public List<String> getAllNumberData(Long id) {
		List<String> result = new ArrayList<String>();
		for (NumberDataBean res : numberRepo.findAllbyModelObject(id)) {
			result.add(res.getDataValue().toString());
		}
		stringRepo.findbyModelObject(id);
		return result;
	}

	public String getStringData(Long id) {
		return stringRepo.findbyModelObject(id).getDataValue().toString();
	}

	public String getDateData(Long id) {
		return Parser.getDate(dateRepo.findbyModelObject(id).getDataValue());
	}

	public String getNumberData(Long id) {
		return numberRepo.findbyModelObject(id).getDataValue().toString();
	}

	public String getTextData(Long id) {
		return textRepo.findbyModelObject(id).getDataValue().toString();
	}

	public void removeObject(Long id) {
		try {
			objRepo.deleteById(id);
		} catch (Exception ex) {
			throw new RecordNotFoundException("No details for given ID");
		}
	}

	public Boolean updateObjectValue(Map<String, String> updateReq) throws ParseException {
		Long obj_id = Long.parseLong(updateReq.get("OBJ_ID").toString());
		List<ModelObjectValue> val_list = valueRepo.findByModelObject(obj_id);
		if (!val_list.isEmpty()) {
			for (ModelObjectValue modelAttr : val_list) {
				if (updateReq.get(modelAttr.getModelAttr().getDispName()) != null) {
					String dataValue = updateReq.get(modelAttr.getModelAttr().getDispName()).toString();
					String type = modelAttr.getModelAttr().getDataType();
					upateValue(type, dataValue, modelAttr.getValueId());
				}

			}
			return true;
		}
		throw new RecordNotFoundException("No details for given ID");
	}

	public void upateValue(String type, String new_val, Long model_obj_id) throws ParseException {
		switch (type) {
		case "STRING":
			updateStringData(new_val, model_obj_id);
			break;
		case "NUMBER":
			updateNumberData(new_val, model_obj_id);
			break;
		case "DATE":
			updateDateData(new_val, model_obj_id);
			break;
		case "TEXT":
			updateTextData(new_val, model_obj_id);
			break;
		}
	}

	public void updateStringData(String dataValue, Long id) {
		StringDataBean bean = stringRepo.findbyModelObject(id);
		bean.setDataValue(dataValue);
		stringRepo.save(bean);
	}

	public void updateDateData(String dataValue, Long id) throws ParseException {
		DateDataBean bean = dateRepo.findbyModelObject(id);
		bean.setDataValue(new SimpleDateFormat("dd/MM/yyyy").parse(dataValue));
		dateRepo.save(bean);
	}

	public void updateNumberData(String dataValue, Long id) {
		NumberDataBean bean = numberRepo.findbyModelObject(id);
		bean.setDataValue(Long.parseLong(dataValue));
		numberRepo.save(bean);
	}

	public void updateTextData(String dataValue, Long id) {
		TextDataBean bean = textRepo.findbyModelObject(id);
		bean.setDataValue(dataValue);
		textRepo.save(bean);
	}

}
