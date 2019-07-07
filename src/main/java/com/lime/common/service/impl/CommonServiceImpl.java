package com.lime.common.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lime.board.vo.BoardVO;
import com.lime.common.service.CommonService;

import egovframework.rte.psl.dataaccess.util.EgovMap;

@Service("commonService")
public class CommonServiceImpl implements CommonService {


	@Resource(name="commonDAO")
	private CommonDAO commonDAO;

	@Override
	public List<EgovMap> selectCombo(Map<String, Object> inOutMap) throws Exception {
		return commonDAO.selectCombo(inOutMap);
	}

	@Override
	public void insertCombo(Map<String, Object> inOutMap) {
		commonDAO.insertCombo(inOutMap);
	}

	@Override
	public List<EgovMap> selectAccountList(Map<String, Object> inOutMap) {
		return commonDAO.selectAccountList(inOutMap);
	}

	@Override
	public BoardVO selectAccountListOne(Map<String, Object> inOutMap) {
		// TODO Auto-generated method stub
		return commonDAO.selectAccountListOne(inOutMap);
	}




}
