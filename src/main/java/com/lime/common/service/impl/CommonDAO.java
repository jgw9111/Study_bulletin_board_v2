package com.lime.common.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.fdl.cmmn.exception.EgovBizException;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

@Repository("commonDAO")
public class CommonDAO extends EgovAbstractMapper{


	public List<EgovMap> selectCombo(Map<String, Object> inOutMap) throws EgovBizException{
		return selectList("Common.selectCombo", inOutMap);
	}
	public void insertCombo(Map<String, Object> inOutMap){
		insert("Common.insertCombo", inOutMap);
	}
	
	public List<EgovMap> selectAccountList(Map<String, Object> inOutMap){
		return selectList("Common.selectAccountList",inOutMap);
		
	};
	
	public List<EgovMap> selectAccountListOne(Map<String, Object> inOutMap){
		return selectList("Common.selectAccountListOne",inOutMap);
		
	};
}
