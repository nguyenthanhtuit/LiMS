/**
 * @author TuNt
 * create date May 23, 2016
 * LiMS
 */
$(document).ready(function() {
	$(document).on('click','span.checkbox-remenber-me',function(){
		if ($(this).hasClass('checked')) {
			$(this).removeClass('checked');
			$('.checkbox-remenber-me').children().remove();
			
		}
		else{
			$(this).addClass('checked');
			$(this).append('<i class="fa fa-check"></i><input type="checkbox" name="rememberMe" value="Y" checked style="display: none">');
		}
	});
	
	$(document).on('click','#sign-up',function(){
		$('#signup-modal').modal();
	});
	
//	$(document).on('click','#btn-get-sigup',function(){
//		var firstName = $('input[name="firstName"]').val();
//		var lastName = $('input[name="lastName"]').val();
//		var userName = $('input[name="userName"]').val();
//		var password = $('input[name="password"]').val();
//		var confirmPassword = $('input[name="confirmPassword"]').val();
//		var email = $('input[name="email"]').val();
//		
//		var data = new FormData();
//		
//		data.append('firstName',firstName);
//		data.append('lastName',lastName);
//		data.append('password',password);
//		data.append('email',email);
//		
//		var request = new XMLHttpRequest();
//		request.open("POST", "doSignUp");
//		request.send(data);
//				
//		
//	});
	
});

