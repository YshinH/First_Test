<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
 <%@ include file="/include/layout.jsp"%>
<div class="container-fluid px-4">
<h3 class="mt-4">공지글수정</h3>
<!-- 파일첨부시 주의사항
	: 1. form태그의 전송방식은 post
	  2. 파일첨부형식을 지정 : enctype="multipart/form-data" 
 -->
<form method="post" enctype="multipart/form-data" action="update.no">	
<input type="hidden" name="id" value="${dto.id}">
	<table class="table">
		<tr><th class="w-px140">제목</th>
			<td><input type="text" name="title" value='${dto.title}' class="chk" title="제목"> </td>
		</tr>
		<tr><th>내용</th>
			<td><textarea name="content" class="chk" title="내용">${dto.content}</textarea></td>
		</tr>
		<tr><th>첨부파일</th>
			<td>
				<input type="file" name="file" id="attach-file">
				<span id="filename">${dto.filename }</span>
				<a id='delete-file' ><i class="font-r fa-solid fa-trash-can"></i></a>
<%-- 				style="display: ${dto.filename==null ? 'none' : 'inline'}"" --%>
			 </td>
		</tr>
	
	</table>
	
	
<input type="hidden" name="filename"> 
</form>
<div class="btnSet">
<!-- 	<a class="btn-fill" onclick="if(emptyCheck()) $('form').submit()">저장</a> -->
	<a class="btn-fill" onclick="save()">저장</a>
	<a class="btn-empty" href="javascript:history.go(-1)">취소</a>
<!-- 	<a class="btn-empty" href="list.no">취소</a> -->


</div>
</div>
<script >
function save() {
	if( emptyCheck() ){
		var name = $('#filename').text();
		$('[name=filename]').val(name);
		$("form").submit();
		
		
	}
}


if( ${dto.filename!=null} )
	$('#delete-file').css('display', 'inline');

</script>	
<%@ include file="/include/footer.jsp"%>

</body>
</html>