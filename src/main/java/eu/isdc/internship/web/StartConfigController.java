package eu.isdc.internship.web;

import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import eu.isdc.internship.db.adapters.StartConfAdapter;
import eu.isdc.internship.db.dao.StartConfigDAO;
import eu.isdc.internship.db.dto.StartConfigDTO;

@Controller
@TransactionConfiguration(transactionManager="transactionManager")
public class StartConfigController {
	
	@Autowired
	private StartConfigDAO startConfigDAO;
	
	@Autowired
	private StartConfAdapter startConfAdapter;
	
	@RequestMapping(method=RequestMethod.POST, value="startConfig/add")
	@ResponseBody
	@Transactional
	public StartConfigDTO createStartConfig(@RequestParam("startConfigJson") String startConfigJson) throws JsonParseException, JsonMappingException, IOException {
		//This has not been tested and most likely does not work
		StartConfigDTO startConfigDTO = new ObjectMapper().readValue(startConfigJson, StartConfigDTO.class);
		startConfigDAO.save(startConfAdapter.toModel(startConfigDTO));
		
		return startConfigDTO;
	}
}
