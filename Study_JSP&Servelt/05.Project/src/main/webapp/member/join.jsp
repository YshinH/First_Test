<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	table tr:last-child td input[name=address] { margin-top: 5px } 
	.ui-datepicker table tr th:first-child { border-right:none; }
	.ui-datepicker table tr { border-bottom: none; }
	#delete { position: relative;  right: 30px; display: none }
</style>
<link rel="stylesheet" href="//code.jquery.com/ui/1.13.1/themes/base/jquery-ui.css">
</head>
<body>
<jsp:include page="/include/layout.jsp"/>
<div class="container-fluid px-4">
<h3 class="mt-4">회원가입</h3>
<p>* 는 필수입력 항목입니다</p>
<form method='post' action='member_join.mb'>
<table class='w-px600'>
<tr><th class='w-px140'>* 성명</th>
	<td><input type='text' name='name'></td>
</tr>
<tr><th>* 아이디</th>
	<td><input type='text' name='userid' class='chk'>
		<a class='btn-fill' id='id_check'>아이디 중복확인</a>
		<div class='valid'>아이디를 입력하세요(영문소문자,숫자만 입력가능)</div>
	</td>
</tr>
<tr><th>* 비밀번호</th>
	<td><input type='password' name='userpw' class='chk'>
		<div class='valid'>비밀번호를 입력하세요(영문대/소문자,숫자를 모두 포함)</div>
	</td>
</tr>
<tr><th>* 비밀번호확인</th>
	<td><input type='password' name='userpw_ck' class='chk'>
		<div class='valid'>비밀번호를 다시 입력하세요</div>
	</td>
</tr>
<tr><th>* 성별</th>
	<td>
		<label><input type='radio' name='gender' value='남'>남</label>
		<label><input type='radio' name='gender' value='여' checked>여</label>
	</td>
</tr>
<tr><th>* 이메일</th>
	<td><input type='text' name='email' class='chk'>
		<div class='valid'>이메일을 입력하세요</div>
	</td>
</tr>
<tr><th>생년월일</th>
	<td><input type='text' name='birth' readonly>
		<a id='delete'><i class="font-r fa-solid fa-calendar-xmark"></i></a>
	</td>
</tr>
<tr><th>전화번호</th>
	<td><input type='text' name='phone'></td>
</tr>
<tr><th>주소</th>
	<td>
		<a class='btn-fill' id='post'>우편번호찾기</a>
		<input type='text' name='post' class='w-px80' readonly>
		<input type='text' name='address' readonly>
		<input type='text' name='address' >
	</td>
</tr>
</table>
</form>
<div class='btnSet'>
	<a class='btn-fill' onclick="fn_join()">회원가입</a>
	<a class='btn-empty' href="javascript:history.go(-1)">취소</a>
<!-- 	<a class='btn-empty' onclick="history.go(-1)">취소</a> -->
</div>
</div>

<script src="https://code.jquery.com/ui/1.13.1/jquery-ui.js"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src='js/join_check.js?<%=new java.util.Date()%>'></script>
<script>
//회원가입처리
function fn_join(){
	//필수입력항목에 입력되어있는지 확인
	//특정항목에 대해서는 유효한 입력인지도 확인
	if( $('[name=name]').val() == '' ){
		alert('성명을 입력하세요');
		$('[name=name]').focus();
		return;
	}
	
	//아이디는 중복확인여부에 따라 처리
	//중복확인 한 경우 
	//invalid 이면 회원가입불가
	if( $('[name=userid]').hasClass('checked') ){
		if( $('[name=userid]').siblings('div').hasClass('invalid') ){
			alert('회원가입 불가!\n' + join.id.unUsable.desc );
			$('[name=userid]').focus();
			return;
		}
	}else{
	//중복확인 하지 않은 경우
		if( ! item_check( $('[name=userid]') ) ) return;
		else{
			alert('회원가입 불가!\n' + join.id.valid.desc);
			$('[name=userid]').focus();
			return;
		}
	}
	
	if( ! item_check( $('[name=userpw]') ) ) return;
	if( ! item_check( $('[name=userpw_ck]') ) )  return;
	if( ! item_check( $('[name=email]') ) )  return;
	
	$('form').submit();
}

function item_check( tag ){
	var status = join.tag_status( tag );
	if( status.code=='invalid' ) {
		alert('회원가입 불가!\n' + status.desc );
		tag.focus();
		return false;
	}else
		return true;
}

$('#id_check').on('click', function(){
	id_check();
});
//아이디 중복확인처리
function id_check(){
	var $userid= $('[name=userid]');
	//유효한 입력 아이디에 대해서만 중복확인
	var status = join.tag_status( $userid );
	if( status.code=='invalid' ){
		alert( '아이디 중복확인 불필요\n' +  status.desc );
		$userid.focus();
		return;
	}
	
	$.ajax({
		url: 'id_check.mb',
		data: { id: $userid.val() },
		success: function( response ){
			$userid.addClass('checked');
			response = join.id_usable( response );
			$userid.siblings('div').text( response.desc )
									.removeClass().addClass( response.code );
		},error: function(req, text){
			alert(text +':' +req.status);
		}
	});
	
}

//입력의 유효성을 판단
$('.chk').on('keyup', function(e){
	if( $(this).attr('name')=='userid' ){
		if( e.keyCode==13 ) { id_check(); return; }
		else $(this).removeClass('checked');
	}
	var status = join.tag_status( $(this) );
	$(this).siblings('div').text( status.desc ).removeClass().addClass( status.code );
});


//만13세까지만 가입가능
var today = new Date();
var start = today.getFullYear()-100;
today.setFullYear( today.getFullYear() - 13 );
today.setDate( today.getDate()-1 );
var defaultDay = new Date();
defaultDay.setFullYear(1990); //1990-07-13 이 있는 달력이 기본으로 보이게

$( "[name=birth]" ).datepicker({ 
	dateFormat: 'yy-mm-dd',
	dayNamesMin: [ '일', '월', '화', '수', '목', '금', '토' ],
	showMonthAfterYear: true,
	monthNamesShort: ['1월', '2월', '3월', '4월', '5월', '6월'
					, '7월', '8월', '9월', '10월', '11월', '12월'],
	changeYear: true,
	changeMonth: true,
	//beforeShowDay: before,
	maxDate: today,
	yearRange: start +':'+today.getFullYear(),
	defaultDate: defaultDay,
});


//특정날짜(오늘)까지만 선택할수 있게
function before(date){
	if( date > new Date() ) return [false];
	else 					return [true];
}

//생년월일을 변경되면 삭제이미지가 보이게
$('[name=birth]').change(function(){
	$('#delete').css('display', 'inline');
});

//달력삭제 클릭시 생년월일 없애기, 삭제이미지도 안보이게
$('#delete').click(function(){
	$('[name=birth]').val('');
	$('#delete').css('display', 'none');
});

// $('#post').click(function(){
$('#post').on('click', function(){	
    new daum.Postcode({
        oncomplete: function(data) {
			console.log( data );
        	$('[name=post]').val( data.zonecode );
        	//R:도로명주소, J:지번주소
        	var address = data.userSelectedType == 'R' 
        					? data.roadAddress : data.jibunAddress;
        	if(data.buildingName!='') address += ' ('+data.buildingName+')';
        	$('[name=address]').eq(0).val( address );
        }
    }).open();
});

</script>
<jsp:include page="/include/footer.jsp"/>
</body>
</html>