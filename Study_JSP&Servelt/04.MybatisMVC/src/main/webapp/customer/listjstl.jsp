<%@page import="customer.CustomerDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- JSTL 태그를 이용하기 위한 준비 ↑ -->
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
	#new_btn{
		position: fixed;
		right: 20%;
		top: 20%;
		z-index: 999
	
	}

</style>

<meta charset="UTF-8">
<title>고객관리 모듈(JSTL)</title>
<button type="button" id="new_btn" class="btn btn-primary"  onclick="showModal();">신규 고객 추가</button>
<style type="text/css">
h1{
	padding-top: 30px;
}

table{
	border : 1px solid black;
	border-collapse: collapse;
	width: 80%;
	margin: 2% 10% 2% 10%;
	text-align: center;
}

tr:first-child {
	border : 1px solid black;
	background-color: green;
}

th {
 
  height: 70px;
  width: 100px;
}

td{
	border : 1px solid;
	height: 30px;	
}


</style>


</head>
<body>
<!-- $ <- 동적으로 request에 있는 자원에 접근해서 사용 
-->
	<%@ include file="/include/header.jsp" %>

	<h1 style="text-align: center;">고객관리 모듈(JSTL)</h1>
	
		<table>

		<tr >
			<th>ID</th>
			<th>NAME</th>
			<th>GENDER</th>
			<th>EMAIL</th>
			<th>PHONE</th>
			<th>수정</th>
			
		</tr>
	<c:forEach items="${list}" var="dto">
	<tr>
		<td>${dto.id}</td>
		<td>${dto.name}</td>
		<td>${dto.gender}</td>
		<td>${dto.email}</td>
		<td>${dto.phone}</td>
		<td>
		<button type="button"  class="btn btn-secondary"  onclick="showModal('${dto.id}', '${dto.name}','${dto.email}', '${dto.phone}','${dto.gender}');">정보수정</button>
		</td>
	</tr>
	</c:forEach>
	
	</table>
	



<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">고객 추가</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">

          <div class="mb-3">
            <label for="recipient-name" class="col-form-label">이름</label>
            <input type="text" name="name" class="form-control" id="recipient-name">
          </div>
          <div class="mb-3">
            <label for="recipient-name" class="col-form-label">성별</label>
            <input type="radio" name="gender" value="남"/>남
            <input type="radio" checked="checked" name="gender" value="여" />여
          </div>
           <div class="mb-3">
            <label for="recipient-name" class="col-form-label">이메일</label>
            <input type="text" name="email" class="form-control" id="recipient-name">
          </div>
          <div class="mb-3">
          <input type="hidden" name="id" ><!-- ajax를 통해서 전송 시 id가 필요함-->
          </div>
          <div class="mb-3">
            <label for="recipient-name" class="col-form-label">핸드폰</label>
            <input type="text" name="phone" class="form-control" id="recipient-name">
          </div>
          
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
        <button type="button" class="btn btn-primary" name="submit" onclick="addCustomer();">추가</button>
      </div>
    </div>
  </div>
</div>
	
			
	<%@ include file="/include/footer.jsp" %>
	<script type="text/javascript">
		function showModalInsert() {
			$('#exampleModal').modal('show');//hide 숨김
			$('[name=submit]').attr('onclick', 'addCustomer()');
		}
		
		function showModal(id, name, email, phone, gender) {
			$('[name=submit]').attr('onclick', 'modifyCustomer()');
			$('[name=name]').val(name);
			$('[name=id]').val(id);	//수정시 id정보가 필요함.
			$("[name=gender]").attr('checked',false);
			if(gender =='남'){
				$("input:radio[name=gender]:input[value='남']").attr('checked',true);
			}else{
				$("input:radio[name=gender]:input[value='여']").attr('checked',true);
				
			}
/* 			 if(gender == '남'){	보류
				 $('[name=name]')[0].attr('checked', false);
				 $('[name=name]')[1].attr('checked', false);
			 }else{
				 
			 } */
			 
// 			  $('[name=gender]').val(''),
			  $('[name=email]').val(email);
		      $('[name=phone]').val(phone);
			
		      $('#exampleModal').modal('show');
		}
		
		/* addCustomer 펑션을 만들고 input type에 들어있는 값들을 콘솔 또는 alert이용해서 띄워보기 */
		function addCustomer() {
			/* alert($('[name=name]').val()); jquery를 이용해서 name속성이 name의 값을 가져옴*/
			/* ajax <-통신 이용해서 insert.cu를 만들고 해당하는 Servlet까지 값을 전송해보기 */
			var gender = $('[name=gender]:checked').val();
			alert(gender);
			$.ajax({
				url: 'insert.cu',
				data: {/* request.getParameter로 받아줄것(name) : 넣어줄 실제값 */
					  name:$('[name=name]').val(),
					  gender:gender,
					  email:$('[name=email]').val(),
				      phone:$('[name=phone]').val()
					},
					
				success: function(response){
					/* 페이지전환이 아니라 out 객체를 통해서 값을 바로 써주면 그 값을 가지고 옴 controller에서 콘솔로 syso로 찍어봄 */
					  $('[name=name]').val(''),
					  $('[name=gender]').val(''),
					  $('[name=email]').val(''),
				      $('[name=phone]').val('')
				}, error: function(req,msg){
					alert(msg + " : ");
					
				},
					
	
			});
			
			location.reload();
			$('#exampleModal').modal('hide');
			
		}
	
		/* addCustomer를 재활용(복붙)
			바뀌어야할부분 insert=>update 정보추가x, 정보 수정
			url <= x, insert.cu = >update.cu
			정보추가시 트리거를 이용해서 id가 자동채번되고 있음
			정보수정시 이미 생성된↑ id를 키 값으로 어떤 행이 수정될지를 지정해줘야함.(where 필요)
		*/
		function modifyCustomer() {
			/* alert($('[name=name]').val()); jquery를 이용해서 name속성이 name의 값을 가져옴*/
			/* ajax <-통신 이용해서 insert.cu를 만들고 해당하는 Servlet까지 값을 전송해보기 */
			var gender = $('[name=gender]:checked').val();
			alert(gender);
			$.ajax({
				url: 'update.cu',
				data: {/* request.getParameter로 받아줄것(name) : 넣어줄 실제값 */
					  id:$('[name=id]').val(),
					  name:$('[name=name]').val(),
					  gender:gender,
					  email:$('[name=email]').val(),
				      phone:$('[name=phone]').val()
					},
					
				success: function(response){// 여기는 id는 굳이 써줄 필요없다.
					/* 페이지전환이 아니라 out 객체를 통해서 값을 바로 써주면 그 값을 가지고 옴 controller에서 콘솔로 syso로 찍어봄 */
					alert(response);
					  $('[name=name]').val(''),
					  $('[name=gender]').val(''),
					  $('[name=email]').val(''),
				      $('[name=phone]').val('')
				}, error: function(req,msg){
					alert(msg + " : ");
					
				},
					
	
			});
			
			location.reload();
			$('#exampleModal').modal('hide');
			
		}	
	
	</script>
	
	
</body>
</html>