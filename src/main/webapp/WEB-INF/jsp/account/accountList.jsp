<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<script type="text/javascript">

</script>

<form name="sendForm" id="sendForm" method="post" onsubmit="return false;">

<input type="hidden" id="situSeq" name="situSeq" value="">
<input type="hidden" id="mode" name="mode" value="Cre">

<div id="wrap"  class="col-md-offset-1 col-sm-10" >
		<div align="center"><h2>회계정보리스트</h2></div>
		<div class="form_box2 col-md-offset-7" align="right" >
			<div class="right" >
				<button class="btn btn-primary" onclick="location.href='<%=request.getContextPath()%>/account/accountInsert.do'">등록</button>
				<button class="btn btn-primary" onclick="location.href='<%=request.getContextPath()%>/account/excelDownload.do'">엑셀 다운</button>
			</div>
		</div>
	    <br/>
		<table class="table table-hover" id="boardList">
			    <thead>
			      <tr align="center">
			        <th style="text-align: center;" >수익/비용</th>
			        <th style="text-align: center;" >관</th>
			        <th style="text-align: center;" >항</th>
			        <th style="text-align: center;" >목</th>
			        <th style="text-align: center;" >과</th>
			        <th style="text-align: center;" >금액</th>
			        <th style="text-align: center;" >등록일</th>
			        <th style="text-align: center;" >작성자</th>
			      </tr>
			    </thead>
			    <tbody>
				    <c:forEach var="result" items="${accountList}" varStatus="status">
				   		 <tr align="center"  style="cursor: pointer;">
							<td style="display:none;"><c:out value="${result.accountSeq}"/></td>
							<td> <c:out value="${result.profitCost}"/></td>
							<td> <c:out value="${result.bigGroup}"/></td>
							<td> <c:out value="${result.middleGroup}"/></td>
							<td> <c:out value="${result.smallGroup}"/></td>
							<td> <c:out value="${result.detailGroup}"/></td>
							<td> <c:out value="${result.transactionMoney}"/></td>
							<td> <c:out value="${result.regDate}"/></td>
							<td> <c:out value="${result.writer}"/></td>
						</tr>
				    </c:forEach> 
			    </tbody>
			</table>

</div>
</form>

<div>
	<form id="seq" action="/account/selectListOne.do" method="post">
		<input type="hidden" name="accountSeq" id="accountSeq"/>	
	</form>
</div>

<script>
	$('#boardList tr').click(function(){
		$.trim($('#accountSeq').val($(this).find("td").eq(0).text())); 
		$('#seq').submit();
	});
</script>
