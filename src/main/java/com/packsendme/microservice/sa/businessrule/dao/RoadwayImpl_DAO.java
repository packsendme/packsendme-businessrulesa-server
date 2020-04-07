package com.packsendme.microservice.sa.businessrule.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.data.redis.core.RedisTemplate;

import com.packsendme.roadway.bre.rule.model.RoadwayBRE_Model;

public class RoadwayImpl_DAO implements IBusinessRule_DAO<RoadwayBRE_Model> {

	private RedisTemplate<String, RoadwayBRE_Model> redisTemplate;

	@Override
	public void add(RoadwayBRE_Model object) {
		redisTemplate.opsForValue().set(object.id_rule, object);
	}

	@Override
	public void delete(RoadwayBRE_Model object) {
		String key = object.getId_rule();
		redisTemplate.opsForValue().getOperations().delete(key);
	}
	
	@Override
	public RoadwayBRE_Model findOne(String key) {
		return redisTemplate.opsForValue().get(key);
	}

	@Override
	public List<RoadwayBRE_Model> findAll(String id) {
		List<RoadwayBRE_Model> roadwayL = new ArrayList<RoadwayBRE_Model>();
		Set<String> keys = redisTemplate.keys("*");
		Iterator<String> it = keys.iterator();
			
		while(it.hasNext()){
			roadwayL.add(findOne(it.next()));
		}
		return roadwayL;
	}
 
}
