package com.lime.common.service;

import java.util.List;
import java.util.Map;

import com.lime.board.vo.BoardVO;

import egovframework.rte.psl.dataaccess.util.EgovMap;

public interface CommonService {

	List<EgovMap> selectCombo(Map<String, Object> inOutMap) throws Exception;

	void insertCombo(Map<String, Object> inOutMap);
	
	List<EgovMap> selectAccountList(Map<String, Object> inOutMap);

	BoardVO selectAccountListOne(Map<String, Object> inOutMap);

}
