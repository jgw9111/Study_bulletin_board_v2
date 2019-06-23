<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<form id="sendForm">

	<input type="hidden" id="platform" name="platform" value="">
	<div class="container col-md-offset-2 col-sm-6" style="margin-top: 100px;">
			<div class="input-group">
				<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
				<input id="memId" type="text" class="form-control valiChk" name="memId" placeholder="id" title="ID" value="maeilmilk">
			</div>
			<div class="input-group">
				<span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
				<input id="memPassword" type="password" class="form-control valiChk" name="memPassword" placeholder="Password" title="Password" value="maeil2">
			</div>
			<br />
		<br>
		<div class="col-md-offset-4">
			<button type="button" id="login" class="btn btn-primary">로그인</button>
			<button type="button" id="cancel" class="btn btn-warning" onclick="location.href='<%=request.getContextPath()%>/login/login.do'">취소</button>
			<button type="button" id="join" class="btn btn-info" onclick="location.href='<%=request.getContextPath()%>/user/userInsert.do'">회원가입</button>
		</div>
	</div>
</form>

<script type="text/javascript">
	$('#login').click(function(){
		$.ajax({
			url:"<%=request.getContextPath()%>/login/assign.do",
			type:"POST",
			data: {
				"userId" : $('#memId').val(),
				"pwd" : $('#memPassword').val()
				},
			success: function(data){
				if( data.user == null){
					alert('로그인 실패 \n id와 비밀번호를 확인하세요');
					location.href="http://localhost:9000/boardAjax/";
				}else{
					location.href="http://localhost:9000/boardAjax/account/accountList.do"; 
				}
			},error: function(){
				alert('로그인 실패');
				
			}
		});
	});
	

</script>

			