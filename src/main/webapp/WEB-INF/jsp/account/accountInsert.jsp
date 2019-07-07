<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<script>
$(document).ready(function(){
	
	console.log('${boardVO}');
	var cost = /^[0-9,]+$/;
	$('#transactionMoney').change(function(){
		
		if(!cost.test($("input[id='transactionMoney']").val())){
			alert('숫자만 입력 가능합니다');
			$('#transactionMoney').val('');
		};
		
	});
	
	getList(0);
	
	
	$('#register').click(function(){
// 		$('#transaction_money').val($('#transaction_money').val().replace(/,/g,''));

		var param = $('#accountForm').serialize();
		
		$.ajax({
			url: "/account/insertCombo.do",
			type: "POST",
			data : param,
			contentType: "application/x-www-form-urlencoded; charset=UTF-8",
// 			{"profit_cost":$("#profitCost option:selected").val(),
// 			"big_group":$("#bigGroup option:selected").val(),
// 			"middle_group":$("#middleGroup option:selected").val(),
// 			"small_group":$("#smallGroup option:selected").val(),
// 			"detail_group":$("#detail_group option:selected").val(),
// 			"comments":$('#comment').val(),
// 			"transaction_money":$('#transactionMoney').val(),
// 			"transaction_date":$('#transactionDate').val(),
// 			},
			success: function(data) {
				alert("등록이 완료되었습니다");
				location.href="/account/accountList.do";
			},error: function(request,error) {
				alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			}
		});
	});
	
	$('#modify').click(function() {
		alert('수정버튼클릭');

	});
});

var idArr = ["profit_cost","big_group","middle_group","small_group","detail_group"]; // 이전 데이터
var valueArr = ["profit_cost","big_group","middle_group","small_group","detail_group"]; // 다음 데이터

/* 리스트 가져오는 function */	
function getList(idx){
	/* 이전 값 넣어서 다음 값 조회 */
	
	var category = $("#"+idArr[idx]).find("option:selected").val();
	
	$.ajax({
		url : "/account/selectCombo.do",
		type : "get",
		data : {category:category},
		async : false, /* 동기식 */
		success : function(data){
			setList(data, idx+1);	
		},
		error : function(error){
			alert('실패');
		}
		}
	);
	
}

/* 가져온 리스트 값 세팅 */
function setList(data, idx){
	$('#'+idArr[idx]).empty();
	if(data.resultMap.length == 0){
		
		$('#'+idArr[idx]).append('<option value="">해당없음</option>');
		
	}else{
		
		$('#'+idArr[idx]).append('<option value="">선택</option>');
		$.each(data.resultMap,function(index,res){
			var option = $('<option value="'+res.code+'">'+res.comKor+'</option>');
			$('#'+idArr[idx]).append(option);
		});
	}
	
	if(idx != 4){
		getList(idx);
	}
	
}

</script>

<!-- 비용 START -->
<div class="container" style="margin-top: 50px">
	<div class="col-sm-12"><label for="disabledInput" class="col-sm-12 control-label"></label></div>
	<div class="col-sm-12"><label for="disabledInput" class="col-sm-12 control-label"></label></div>
	<div class="col-sm-12"><label for="disabledInput" class="col-sm-12 control-label"></label></div>
	<div class="col-sm-12"><label for="disabledInput" class="col-sm-12 control-label"></label></div>


<form id="accountForm" enctype="application/x-www-form-urlencoded">
	<div class="col-sm-11" id="costDiv">
		<div>
			<div class="col-sm-11">
			 		<div class="col-sm-12">
				      <div class="col-sm-3">
						<select class="form-control" id="profit_cost" name="profit_cost" title="비용" onchange="getList(0)">
				        	<option value="">선택</option>
<%-- 				        	<c:choose> --%>
<%-- 				        		<c:when test="${accountList != null}"> --%>
<%-- 				        			<c:forEach var="result" items="${accountList}" varStatus="cnt"> --%>
<%-- 							        	<option><%= request.getParameter("profitCost") %></option> --%>
<%-- 						        	</c:forEach> --%>
<%-- 				        		</c:when> --%>
<%-- 				        		<c:otherwise> --%>
<%-- 				        		</c:otherwise> --%>
<%-- 				        	</c:choose> --%>
						        	<c:forEach var="list" items="${resultMap}" varStatus="cnt">
							        	<option value="${list.code}">${list.comKor}</option>
						        	</c:forEach>
				        </select>
				      </div>

				      <div class="col-sm-3">
						<select class="form-control" id="big_group"  name="big_group" title="관" onchange="getList(1)">
				        	<option value="">선택</option>
				        </select>
				      </div>

				      <div class="col-sm-3">
						<select class="form-control " id="middle_group" name="middle_group"  title="항" onchange="getList(2)">
					        	<option value="0">해당없음</option>
				        </select>
				      </div>

				      <div class="col-sm-3">
						<select class="form-control " id="small_group" name="small_group" title="목" onchange="getList(3)">
					        	<option value="0">해당없음</option>
				        </select>
				      </div>
			 		</div>

			 		<div class="col-sm-12">  <label for="disabledInput" class="col-sm-12 control-label"> </label></div>
			 		<div class="col-sm-12">
			 			  <div class="col-sm-3">
								<select class="form-control " id="detail_group" name="detail_group" title="과" >
							        	<option value="0">해당없음</option>
						        </select>
					      </div>
				      <div class="col-sm-9">
				      		<input class="form-control " id="comments" name="comments" type="text" value="" placeholder="비용 상세 입력" title="비용 상세">
				      </div>
			 		</div>

				<div class="col-sm-12">  <label for="disabledInput" class="col-sm-12 control-label"> </label></div>
			 		<div class="col-sm-12">
			 		  <label for="disabledInput" class="col-sm-1 control-label" ><font size="1px">금액</font></label>
				      <div class="col-sm-3">
				        	<input class="form-control"  name="transaction_money" id="transaction_money" type="text" value="" title="금액">
				      </div>
			 		  <label for="disabledInput" class="col-sm-1 control-label" ><font size="1px">거래일자</font></label>
				      <div class="col-sm-3">
				       	 <input class="form-contro col-sm-2 datepicker" id="transaction_date" name="transaction_date" type="text" value="" style="width: 80%" title="거래일자">
				      </div>
			 		</div>

					<div class="col-sm-12"><label for="disabledInput" class="col-sm-12 control-label"></label></div>
					<div class="col-sm-12"><label for="disabledInput" class="col-sm-12 control-label"></label></div>
					
				<div class="col-md-offset-4">
					<c:choose>
						<c:when test="${mode == null}">
							<button type="button" id="register" class="btn btn-primary">등록</button>
						</c:when>
						<c:otherwise>
							<button type="button" id="modify" class="btn btn-primary">수정</button>
							<button type="button" id="delete" class="btn btn-primary">삭제</button>
						</c:otherwise>
					</c:choose>
					
					
					<button type="button" id="cancel" class="btn btn-warning" onclick="location.href='<%=request.getContextPath()%>/account/accountList.do'">취소</button>
				</div>
			 </div>
		</div>
	</div>
</div>
</form>

<!-- 비용 END -->
<script	src="<%=request.getContextPath()%>/js/common.js"></script>