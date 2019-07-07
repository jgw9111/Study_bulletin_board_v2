package com.lime.account.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor.HSSFColorPredefined;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.lime.account.service.AccountService;
import com.lime.board.vo.BoardVO;
import com.lime.common.service.CommonService;
import com.lime.login.service.UserService;
import com.lime.user.vo.UserVO;
import com.lime.util.CommUtils;

import egovframework.rte.psl.dataaccess.util.EgovMap;


@Controller
public class AccountController {


	@Resource(name = "jsonView")
	private MappingJackson2JsonView jsonView;

	@Resource(name="accountService")
	private AccountService accountService;

	@Resource(name="commonService")
	private CommonService commonService;
	
	@Resource(name = "userService")
	private UserService userService;
	

	/**
	 *
	 * @param searchVO - 조회할 정보가 담긴 SampleDefaultVO
	 * @param model
	 * @return "egovSampleList"
	 * @exception Exception
	 */
	@RequestMapping(value = "/account/accountList.do")
	public ModelAndView selectSampleList(@ModelAttribute("user") UserVO user, 
			HttpServletRequest request, ModelMap model) throws Exception {
		// 리스트 가져오기
		System.out.println("List");
		
		ModelAndView mav = new ModelAndView();
		Map<String, Object> inOutMap  = CommUtils.getFormParam(request);
		
		UserVO login = (UserVO) request.getSession().getAttribute("user");
		System.out.println(login.toString());
		inOutMap.put("userName",login.getUserName());
		model.put("inOutMap", inOutMap);
		
		List<EgovMap> result = commonService.selectAccountList(inOutMap);
		System.out.println("result ::  "+result);
		model.put("result", result);
		
		mav.setViewName( "/account/accountList");
		mav.addObject("accountList",result);
		
		return mav;
	}

	@RequestMapping(value="/account/selectListOne.do",method= {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView selectListOne(HttpServletRequest request, ModelMap model) throws Exception{
		System.out.println("selectListOne  ");
		ModelAndView mav = new ModelAndView();
		Map<String, Object> inOutMap  = CommUtils.getFormParam(request);
		System.out.println("---->"+inOutMap);
		
		inOutMap.put("category", "A000000");
		List<EgovMap> resultMap= commonService.selectCombo(inOutMap);
		mav.addObject("resultMap", resultMap);
		
		BoardVO resultMap2 = commonService.selectAccountListOne(inOutMap);
		
		System.out.println(resultMap2);
		
		inOutMap.put("boardVO", resultMap2);
//		mav.addObject("resultMap", resultMap);
		mav.setViewName("/account/accountInsert");
		return mav;
		
	}


	/**
	 *
	 * @param request
	 * @return
	 * @throws Exception
	 */
	
	@RequestMapping(value="/account/accountInsert.do")
	public String accountInsert(HttpServletRequest request, ModelMap model) throws Exception{
		System.out.println("-----accountInsert---->");
		Map<String, Object> inOutMap = CommUtils.getFormParam(request);
		inOutMap.get("mode");
		inOutMap.get("resultMap");
		inOutMap.put("category", "A000000");
		System.out.println("inOutMap ?? ---------->"+inOutMap);
		List<EgovMap> resultMap= commonService.selectCombo(inOutMap);

		System.out.println(resultMap);
		model.put("resultMap", resultMap);

		return "/account/accountInsert";
	}


	/**
	 *
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/account/selectCombo.do")
	public ModelAndView ajaxtest(HttpServletRequest request, ModelMap model) throws Exception{
		System.out.println("/account/selectCombo.do");

		Map<String, Object> inOutMap  = CommUtils.getFormParam(request);
		
		List<EgovMap> resultMap = commonService.selectCombo(inOutMap);
		System.out.println("resultMap ? "+resultMap+"code ? ");
		model.put("resultMap", resultMap);
		
		return new ModelAndView(jsonView, inOutMap);
	}
	
	
	@RequestMapping(value="/account/insertCombo.do")
	public ModelAndView insertCombo(HttpServletRequest request, ModelMap model) throws Exception{
		System.out.println("=======insertCombo====");
		Map<String, Object> inOutMap  = CommUtils.getFormParam(request);
		
		HttpSession session = request.getSession();
		UserVO user = (UserVO) session.getAttribute("user");
		session.setAttribute("userName",user.getUserName());
		System.out.println(session.getAttribute("userName"));
		
		inOutMap.put("userName", session.getAttribute("userName"));
		commonService.insertCombo(inOutMap);
		
		System.out.println(inOutMap);
		return new ModelAndView(jsonView, inOutMap);
		
	}

	@RequestMapping(value="/account/excelDownload.do")
	public void excelDownload(HttpServletResponse response,HttpServletRequest request) throws Exception {
		// poi를 활용한 엑셀다운로드
		Map<String, Object> inOutMap  = CommUtils.getFormParam(request);
		
		// id
		HttpSession session = request.getSession();
		UserVO user = (UserVO) session.getAttribute("user");
		session.setAttribute("userName", user.getUserName());
		
		inOutMap.put("userName", session.getAttribute("userName"));
	
		
		// 게시판 목록 조회
		List<EgovMap> result = commonService.selectAccountList(inOutMap);
		
		// HSSFWorkbook .xls 파일
		Workbook wb = new HSSFWorkbook();
		// 엑셀 탭 이름 AccountExcel
		Sheet sheet = wb.createSheet("AccountExcel");
		Row row = null;
		Cell cell = null;
		int rowNo = 0;
		
		// 엑셀 스타일 적용
		
		// 테이블 헤더 
		CellStyle headStyle = wb.createCellStyle();
		headStyle.setBorderTop(BorderStyle.THIN);
		headStyle.setBorderBottom(BorderStyle.THIN);
		headStyle.setBorderLeft(BorderStyle.THIN);
		headStyle.setBorderRight(BorderStyle.THIN);
		
		// 배경색
		headStyle.setFillForegroundColor(HSSFColorPredefined.YELLOW.getIndex());;
		headStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		// 데이터 정렬
		headStyle.setAlignment(HorizontalAlignment.CENTER);
		
		// 데이터 스타일
		CellStyle bodyStyle = wb.createCellStyle();
		bodyStyle.setBorderTop(BorderStyle.THIN);
		bodyStyle.setBorderBottom(BorderStyle.THIN);
		bodyStyle.setBorderLeft(BorderStyle.THIN);
		bodyStyle.setBorderRight(BorderStyle.THIN);
		
		// 헤더
		row = sheet.createRow(rowNo++);
		cell = row.createCell(0);
		cell.setCellStyle(headStyle);
		cell.setCellValue("수익/비용");
		cell = row.createCell(1);
		cell.setCellStyle(headStyle);
		cell.setCellValue("관");
		cell = row.createCell(2);
		cell.setCellStyle(headStyle);
		cell.setCellValue("항");
		cell = row.createCell(3);
		cell.setCellStyle(headStyle);
		cell.setCellValue("목");
		cell = row.createCell(4);
		cell.setCellStyle(headStyle);
		cell.setCellValue("과");
		cell = row.createCell(5);
		cell.setCellStyle(headStyle);
		cell.setCellValue("금액");
		cell = row.createCell(6);
		cell.setCellStyle(headStyle);
		cell.setCellValue("등록일");
		cell = row.createCell(7);
		cell.setCellStyle(headStyle);
		cell.setCellValue("작성자");
		
		// 데이터
		for(EgovMap vo : result) {
	        row = sheet.createRow(rowNo++);
	        cell = row.createCell(0);
	        cell.setCellStyle(bodyStyle);
	        cell.setCellValue((String) vo.get("profitCost"));
	        cell = row.createCell(1);
	        cell.setCellStyle(bodyStyle);
	        cell.setCellValue((String) vo.get("bigGroup"));
	        cell = row.createCell(2);
	        cell.setCellStyle(bodyStyle);
	        cell.setCellValue((String) vo.get("middleGroup"));
	        cell = row.createCell(3);
	        cell.setCellStyle(bodyStyle);
	        cell.setCellValue((String) vo.get("smallGroup"));
	        cell = row.createCell(4);
	        cell.setCellStyle(bodyStyle);
	        cell.setCellValue((String) vo.get("detailGroup"));
	        cell = row.createCell(5);
	        cell.setCellStyle(bodyStyle);
	        cell.setCellValue(String.valueOf(vo.get("transactionMoney")));
	        cell = row.createCell(6);
	        cell.setCellStyle(bodyStyle);
	        cell.setCellValue(String.valueOf(vo.get("regDate")));
	        cell = row.createCell(7);
	        cell.setCellStyle(bodyStyle);
	        cell.setCellValue((String) vo.get("writer"));
	    }
		
		// 컨텐츠 타입 + 파일명 (한글 안됨)
		response.setContentType("ms-vnd/excel");
		response.setHeader("Content-Disposition", "attachment;filename=AccountList.xls");
		
		// 엑셀 출력
		wb.write(response.getOutputStream());
		wb.close();
	}


}// end of calss
