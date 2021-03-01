package com.ranjan.javaTestApp.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ranjan.javaTestApp.beans.ModelAttributeBean;
import com.ranjan.javaTestApp.beans.ModelClassBean;
import com.ranjan.javaTestApp.dto.ModelAttributeDTO;
import com.ranjan.javaTestApp.dto.ModelClassDTO;
import com.ranjan.javaTestApp.error.RecordNotFoundException;
import com.ranjan.javaTestApp.repo.ModelAttributeRepo;
import com.ranjan.javaTestApp.repo.ModelClassRepo;

@Service
public class ModelServices {

	@Autowired
	ModelClassRepo classRepo;

	@Autowired
	ModelAttributeRepo attrRepo;

	/*
	 * The following method will behave as for both INSERT and UPDATE based of
	 * MODEL_ID With no ID present or invalid ID, it will INSERT. With Valid ID
	 * present It will UPDATE
	 * 
	 * @Consumes: ModelClassDTO
	 * 
	 * @Produces: ModelClassBean
	 */

	@Transactional(rollbackOn = RecordNotFoundException.class)
	public ModelClassBean addModelClass(ModelClassDTO modelDto) throws RecordNotFoundException {
		ModelClassBean model = null;
		if (modelDto.getModelId() == null || !classRepo.existsById(Long.parseLong(modelDto.getModelId()))) {
			model = new ModelClassBean();
		} else {
			model = classRepo.findById(Long.parseLong(modelDto.getModelId())).get();
		}
		model.setCreatedBy(Long.parseLong(modelDto.getCreatedBy()));
		model.setCreatedDate(new Date());
		model.setDescription(modelDto.getDescription());
		model.setDisplayName(modelDto.getDisplayName().toUpperCase());
		model.setEntityName(modelDto.getEntityName().toUpperCase());
		model.setStatus(modelDto.isStatus());
		if (modelDto.getParent() != null) {
			model.setParent(classRepo.findById(Long.parseLong(modelDto.getParent())).get());
		}
		if (classRepo.save(model) != null) {
			if (modelDto.getAttribute() != null && !modelDto.getAttribute().isEmpty()) {
				for (ModelAttributeDTO attrDto : modelDto.getAttribute()) {
					addModelAttribute(attrDto, model);
				}
			}
			return model;
		}
		throw new RecordNotFoundException();
	}

	/*
	 * The following method will behave as for both INSERT and UPDATE based of
	 * ModelClassBean With no ID present or invalid ID, it will INSERT. With Valid
	 * ID present It will UPDATE
	 * 
	 * @Consumes: ModelClassDTO,ModelClassBean
	 * 
	 * @Produces: ModelAttributeBean
	 */

	public ModelAttributeBean addModelAttribute(ModelAttributeDTO attrDto, ModelClassBean model) {
		ModelAttributeBean attr = null;
		if (attrDto.getModelAttributeId() == null
				|| !attrRepo.existsById(Long.parseLong(attrDto.getModelAttributeId()))) {
			attr = new ModelAttributeBean();
		} else {
			attr = attrRepo.findById(Long.parseLong(attrDto.getModelAttributeId())).get();
		}
		attr.setAttrName(attrDto.getAttrName().toUpperCase());
		attr.setCreatedBy(Long.parseLong(attrDto.getCreatedBy()));
		attr.setCreatedDate(new Date());
		attr.setDataType(attrDto.getDataType().toUpperCase());
		attr.setDescription(attrDto.getDescription());
		attr.setDispName(attrDto.getDispName().toUpperCase());
		attr.setHasChild(attrDto.isHasChild());
		attr.setManditory(attrDto.isManditory());
		attr.setUnique(attrDto.isUnique());
		attr.setVisiblity(attr.isVisiblity());
		attr.setModelClass(model);
		if (attrRepo.save(attr) != null) {
			return attr;
		}
		throw new RecordNotFoundException();
	}

	/*
	 * The following method will behave as for both INSERT and UPDATE based of
	 * MODEL_ATTRIBUTE_ID With no ID present or invalid ID, it will INSERT. With
	 * Valid ID present It will UPDATE
	 * 
	 * @Consumes: ModelClassDTO
	 * 
	 * @Produces: ModelAttributeBean
	 */

	@Transactional(rollbackOn = RecordNotFoundException.class)
	public ModelAttributeBean addModelAttribute(ModelAttributeDTO attrDto) throws RecordNotFoundException {
		if (classRepo.existsById(Long.parseLong(attrDto.getModelClass()))) {
			Optional<ModelClassBean> model = classRepo.findById(Long.parseLong(attrDto.getModelClass()));
			if (model.isPresent()) {
				return addModelAttribute(attrDto, model.get());
			}
		}
		throw new RecordNotFoundException();
	}

	/*
	 * The following method will fetch all the ModelClassBean objects
	 * 
	 * @Produces: List of ModelClassBean
	 */
	public List<ModelClassBean> getAllModelClass() {
		try {
			return classRepo.findAll();
		} catch (Exception ex) {
			throw new RecordNotFoundException("Orphan node Found. Please Delete the Orphan Node");
		}
	}

	/*
	 * The following method will fetch all the ModelAttributeBean objects
	 * 
	 * @Produces: List of ModelAttributeBean
	 */
	public List<ModelAttributeBean> getAllModelAttribute() {
		try {
			return attrRepo.findAll();
		} catch (Exception ex) {
			throw new RecordNotFoundException("Orphan node Found. Please Delete the Orphan Node");
		}
	}

	/*
	 * The following method will fetch ModelClassBean object with given MODEL_ID
	 * 
	 * @Consumes: Long MODEL_ID
	 * 
	 * @Produces: ModelClassBean
	 */
	public Optional<ModelClassBean> getModelClass(Long id) {
		return classRepo.findById(id);
	}

	/*
	 * The following method will fetch ModelAttributeBean object with given
	 * MODEL_ATTRIBUTE_ID
	 * 
	 * @Consumes: Long MODEL_ATTRIBUTE_ID
	 * 
	 * @Produces: ModelAttributeBean
	 */
	public Optional<ModelAttributeBean> getModelAttribute(Long id) {
		return attrRepo.findById(id);
	}

	/*
	 * The following method will DELETE ModelClassBean object with given ID
	 * 
	 * @Consumes: Long MODEL_ID
	 */
	public void removeModelClass(Long id) {
		try {
			classRepo.deleteById(id);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RecordNotFoundException("No details for given ID");
		}
	}

	/*
	 * The following method will DELETE ModelAttributeBean object with given ID
	 * 
	 * @Consumes: Long MODEL_ID
	 */
	public void removeModelAttribute(Long id) {
		try {
			attrRepo.deleteById(id);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RecordNotFoundException("No details for given ID");
		}
	}

	/*
	 * The following method will fetch ModelAttributeBean object with given MODEL_ID
	 * 
	 * @Consumes: Long MODEL_ID
	 * 
	 * @Produces: ModelAttributeBean
	 */
	public List<ModelAttributeBean> getAllByModel(Long model_id) {
		try {
			return attrRepo.getAllByModel(model_id);
		} catch (Exception ex) {
			throw new RecordNotFoundException("Orphan node Found. Please Delete the Orphan Node");
		}
	}

	/*
	 * The following method will fetch ModelAttributeBean object with given
	 * DISPLAY_NAME
	 * 
	 * @Consumes: Long DISPLAY_NAME
	 * 
	 * @Produces: ModelAttributeBean
	 */
	public ModelAttributeBean findByDisplayName(String dispName) {
		try {
			return attrRepo.findByDispName(dispName);
		} catch (Exception ex) {
			throw new RecordNotFoundException("Orphan node Found. Please Delete the Orphan Node");
		}
	}
}
