<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<script type="text/javascript">
	$(document).ready(function(){


	});

</script>

<div class="container" style="margin-top: 50px">
	<form class="form-horizontal" id="sendForm">
	    <div class="form-group">
	      <label class="col-sm-2 control-label">ID</label>
	      <div class="col-sm-4">
	        <input class="form-control" id="memId" name="memId" type="text" value="" title="ID">
	      </div>

	      <div class="container">
	      	<button type="button" id="idcked" class="btn btn-default" style="display: block;">ID 중복 체크</button>
	      </div>

	    </div>

	    <div class="form-group">
	      <label for="disabledInput " class="col-sm-2 control-label">패스워드</label>
	      <div class="col-sm-4">
	        <input class="form-control" id="pwd" name="" type="password" title="패스워드" >
	      </div>
	      <label for="disabledInput " class="col-sm-2 control-label">패스워드 확인</label>
	      <div class="col-sm-4">
	        <input class="form-control" id="pwdck" name="" type="password" title="패스워드 확인">
	      </div>
	    </div>

	    <div class="form-group">
	      <label for="disabledInput" class="col-sm-2 control-label">이름</label>
	      <div class="col-sm-4">
	        <input class="form-control" id="memName" name="memName" type="text" value="" title="이름" >
	      </div>
	    </div>


	    <div class="col-md-offset-4">
			<button type="button" id="saveBtn"class="btn btn-primary">저장</button>
			<button type="button" id="#"class="btn btn-danger">취소</button>
	    </div>
	</form>
</div>
<script type="text/javascript">

	$('#idcked').click(function(){
		$.ajax({
			url:"<%=request.getContextPath()%>/login/idCkedAjax.do",
			type:"POST",
			data: {"userId" : $('#memId').val()},
			success: function(data){
				if(data.idcnt===1){
					alert("사용 불가능한 ID입니다");
				}else{
					if(data.userId.length < 6){
						alert("ID는 6글자 이상 입력하세요");
						return false;
					}else{
						alert("사용 가능한 ID입니다");
					}
				}				
			},error: function(){
				alert('error');
			}
		});
	});
	
	// 정규표현식 문자 + 숫자 6~12 자리
	var passRule = /^[A-Za-z0-9]{6,12}$/;

	$('#saveBtn').click(function(e){
		e.preventDefault();
		if(!passRule.test($("input[id='pwd']").val())) {
		    alert('비밀번호는 6자리~12자리 영문, 숫자 포함입니다');
		    return false;
		};
		$.ajax({
			url: "<%=request.getContextPath()%>/user/join.do",
			type: "POST",
			data : {"userId":$('#memId').val(),
			"pwd":$('#pwd').val(),
			"userName":$('#memName').val()},
			success: function(data) {
				alert(" 회원가입완료!");
				location.href="http://localhost:9000/boardAjax"; 
			},error: function(request,error) {
				alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			}
			});
	});
</script>

