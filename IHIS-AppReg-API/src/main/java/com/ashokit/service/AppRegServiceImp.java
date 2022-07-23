package com.ashokit.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ashokit.binding.CitizenApp;
import com.ashokit.entity.CitizenAppsEntity;
import com.ashokit.repository.CitizenAppsRepository;

@Service
public class AppRegServiceImp implements AppRegService {

	@Autowired
	private CitizenAppsRepository cityapprepo;
	
	@Override
	public String createapp(CitizenApp citiapp) {
		String ssaurl="https://ssa-web-api.herokuapp.com/ssn/{ssn}";
		RestTemplate rt=new RestTemplate();
		ResponseEntity<String> entity = rt.getForEntity(ssaurl, String.class, citiapp.getZzn());
		String statename= entity.getBody();
		if("NewJersey".equals(statename)) {
		citiapp.setStatename(statename);
		CitizenAppsEntity cityentity=new CitizenAppsEntity();
		BeanUtils.copyProperties(cityentity, citiapp);
		cityapprepo.save(cityentity);
		return "NewGersey citizen created successfully";
		}
		return "citizen can't applied for other state";
	}

	@Override
	public List<CitizenApp> viewapp() {
		List<CitizenAppsEntity> entities = cityapprepo.findAll();
		List<CitizenApp> li=new ArrayList<>();
		entities.forEach(entity->{
			CitizenApp app=new CitizenApp();
			BeanUtils.copyProperties(entity, app);
			li.add(app);
		});
		return li;
	}

}
