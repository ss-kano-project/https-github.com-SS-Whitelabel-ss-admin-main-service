package co.deepmindz.adminmainservice.utils;

import org.springframework.stereotype.Service;

import co.deepmindz.adminmainservice.dto.ConfigManagementRequestDto;
import co.deepmindz.adminmainservice.models.ConfigurationManagement;

@Service
public class configUtil {

	public ConfigurationManagement mapDtoToEntity(ConfigManagementRequestDto dto) {
		ConfigurationManagement configurationManagement = new ConfigurationManagement();
		configurationManagement.setConfiguration(dto.getConfiguration());
		configurationManagement.setLocked(dto.isLocked());
		return configurationManagement;
	}

}
