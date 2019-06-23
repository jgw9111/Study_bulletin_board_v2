<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<script>
$(document).ready(function(){



});




</script>

<!-- 비용 START -->
<div class="container" style="margin-top: 50px">
	<div class="col-sm-12"><label for="disabledInput" class="col-sm-12 control-label"></label></div>
	<div class="col-sm-12"><label for="disabledInput" class="col-sm-12 control-label"></label></div>
	<div class="col-sm-12"><label for="disabledInput" class="col-sm-12 control-label"></label></div>
	<div class="col-sm-12"><label for="disabledInput" class="col-sm-12 control-label"></label></div>



	<div class="col-sm-11" id="costDiv">
		<div>
			<div class="col-sm-11">
			 		<div class="col-sm-12">
				      <div class="col-sm-3">
						<select class="form-control" id="profitCost" name="profitCost" title="비용">
				        	<option value="">선택</option>
				        	<c:forEach var="list" items="${resultMap}" varStatus="cnt">
					        	<option value="${list.code}">${list.comKor}</option>
				        	</c:forEach>
				        </select>
				      </div>

				      <div class="col-sm-3">
						<select class="form-control" id="bigGroup"  name="bigGroup" title="관">
				        	<option value="">선택</option>
				        </select>
				      </div>

				      <div class="col-sm-3">
						<select class="form-control " id="middleGroup" name="middleGroup"  title="항">
					        	<option value="0">해당없음</option>
				        </select>
				      </div>

				      <div class="col-sm-3">
						<select class="form-control " id="smallGroup" name="smallGroup" title="목">
					        	<option value="0">해당없음</option>
				        </select>
				      </div>
			 		</div>

			 		<div class="col-sm-12">  <label for="disabledInput" class="col-sm-12 control-label"> </label></div>
			 		<div class="col-sm-12">
			 			  <div class="col-sm-3">
								<select class="form-control " id="detail_group" name="comment1" title="과">
							        	<option value="0">해당없음</option>
						        </select>
					      </div>
				      <div class="col-sm-9">
				      		<input class="form-control " id="comment" name="comment" type="text" value="" placeholder="비용 상세 입력" title="비용 상세">
				      </div>
			 		</div>

				<div class="col-sm-12">  <label for="disabledInput" class="col-sm-12 control-label"> </label></div>
			 		<div class="col-sm-12">
			 		  <label for="disabledInput" class="col-sm-1 control-label" ><font size="1px">금액</font></label>
				      <div class="col-sm-3">
				        	<input class="form-control"  name="transactionMoney" id="transactionMoney" type="text" value="" title="금액">
				      </div>
			 		  <label for="disabledInput" class="col-sm-1 control-label" ><font size="1px">거래일자</font></label>
				      <div class="col-sm-3">
				       	 <input class="form-contro col-sm-2" id="transactionDate" name="transactionDate" type="text" value="" style="width: 80%" title="거래일자">
				      </div>
			 		</div>

					<div class="col-sm-12"><label for="disabledInput" class="col-sm-12 control-label"></label></div>
					<div class="col-sm-12"><label for="disabledInput" class="col-sm-12 control-label"></label></div>
					
				<div class="col-md-offset-4">
					<button type="button" id="register" class="btn btn-primary">등록</button>
					<button type="button" id="cancel" class="btn btn-warning" onclick="location.href='<%=request.getContextPath()%>/account/accountList.do'">취소</button>
				</div>
			 </div>
		</div>
	</div>
</div>

<!-- 비용 END -->

<script>
	
	$(function(){
		$('#transactionDate').datepicker();	
	});
	
	var cost = /^[0-9,]+$/;
	$('#transactionMoney').change(function(){
		
		if(!cost.test($("input[id='transactionMoney']").val())){
			alert('숫자만 입력 가능합니다');
			$('#transactionMoney').val('');
		};
		
	});
	
	
	$('#profitCost').change(function(){
		// selectCombo 2
		
		$.ajax({
			url:"<%=request.getContextPath()%>/account/selectCombo.do",
			type:"get",
			data:{category : $("#profitCost option:selected").val()},
			success: function(data){
				$.each(data.resultMap,function(index,res){
					$('<option value="'+res.code+'">'+res.comKor+'</option>').appendTo('#bigGroup');
				});
				$('#bigGroup').change(function(){

					// selectCombo 2
					$.ajax({
						url:"<%=request.getContextPath()%>/account/selectCombo.do",
						type:"get",
						data:{category : $("#bigGroup option:selected").val()},
						success: function(data){
							$.each(data.resultMap,function(index,res){
								$('<option value="'+res.code+'">'+res.comKor+'</option>').appendTo('#middleGroup');
							});
							
							$('#middleGroup').change(function(){
								
								// selectCombo 3
								$.ajax({
									url:"<%=request.getContextPath()%>/account/selectCombo.do",
									type:"get",
									data:{category : $("#middleGroup option:selected").val()},
									success: function(data){
										$.each(data.resultMap,function(index,res){
											$('<option value="'+res.code+'">'+res.comKor+'</option>').appendTo('#smallGroup');
										});
										
										$('#smallGroup').change(function(){
											
											// selectCombo 4
											$.ajax({
												url:"<%=request.getContextPath()%>/account/selectCombo.do",
												type:"get",
												data:{category : $("#smallGroup option:selected").val()},
												success: function(data){
													$.each(data.resultMap,function(index,res){
														$('<option value="'+res.code+'">'+res.comKor+'</option>').appendTo('#detail_group');
													});
													
												},error: function(error){
													alert('실패');
												}
												
											});
										});
									},error: function(error){
										alert('실패');
									}
									
								});
							});
							
						},error: function(error){
							alert('실패');
						}
						
					});
					
				});

			},error: function(error){
				alert('실패');
			}
			
		});
		
	});
	
	
	$('#register').click(function(){
		alert('등록하기');
		
		$.ajax({
			url: "<%=request.getContextPath()%>/account/insertCombo.do",
			type: "POST",
			data : {"profit_cost":$("#profitCost option:selected").val(),
			"big_group":$("#bigGroup option:selected").val(),
			"middle_group":$("#middleGroup option:selected").val(),
			"small_group":$("#smallGroup option:selected").val(),
			"detail_group":$("#detail_group option:selected").val(),
			"comments":$('#comment').val(),
			"transaction_money":$('#transactionMoney').val(),
			"transaction_date":$('#transactionDate').val(),
			},
			success: function(data) {
				alert("등록이 완료되었습니다");
				location.href="http://localhost:9000/boardAjax/account/accountList.do";
			},error: function(request,error) {
				alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			}
		});
	});
	
</script>
<script	src="<%=request.getContextPath()%>/js/common.js"></script>