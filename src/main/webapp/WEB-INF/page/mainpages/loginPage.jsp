<div class="modal fade" id="signup-modal" role="dialog">
	<div class="modal-dialog">
<form action="doSignUp" method="POST">
		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header" style="text-align: center;">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h3 class="modal-title">Sign up</h3>
			</div>
			<div class="dialog-sigup-content">
				<div class="dialog-sigup-item">
						<p>First Name :</p>
						<input type="text" name="firstName" class="form-control"
							placeholder="First Name" value="${user.firstName}">
				</div>
				<div class="dialog-sigup-item">
						<p>Last Name :</p>
						<input type="text" name="lastName" class="form-control"
							placeholder="Last Name" value="${user.lastName}">
				</div>
				<div class="dialog-sigup-item">
						<p>User Name :</p>
						<input type="text" name="userName" class="form-control"
							placeholder="User Name" value="${user.userName}">
				</div>
				<div class="dialog-sigup-item">
						<p>Password :</p>
						<input type="password" name="password" class="form-control"
							placeholder="Password">
				</div>
				<div class="dialog-sigup-item">
						<p>Confirm password :</p>
						<input type="password" name="confirmPassword" class="form-control"
							placeholder="Confirm password">
				</div>
				<div class="dialog-sigup-item">
						<p>Email :</p>
						<input type="text" name="email" class="form-control"
							placeholder="Email" value="${user.lastName}">
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				<button type="submit" class="btn btn-success" id="btn-get-sigup">Sig
					up</button>
			</div>
		</div>
</form>
	</div>
</div>

<form action="doLogin" method="post" accept-charset="utf-8">
	<div class="login-form">
	<p class="notify">${mess }</p>
		<div class="login-item-logo">
			<img src="${pageContext.request.contextPath}/html/images/logo.png" alt="">
		</div>
		
		<div class="login-item-input">
			<input type="text" name="userName" class="form-control login-input"
				placeholder="Username" value="${user.userName}">
		</div>
		<div class="login-item-input">
			<input type="password" name="password"
				class="form-control login-input" placeholder="Password">
		</div>
		<div class="login-item-input">
			<button type="submit" class="form-control login-button btn">
				Sign in</button>
		</div>
		<div class="login-extend">
			<p>
				<span class="checkbox-remenber-me checked"> <i
					class="fa fa-check"></i> <input type="checkbox" name="rememberMe"
					value="Y" checked style="display: none">
				</span>
			</p>
			<span class="content-remenber">Remember</span> <a href="#"
				class="content-orther" id="sign-up">Sign up</a> <span class="content-orther">|</span> <a href=""
				class="content-orther">Forgot password</a>
		</div>
		<strong>${erroString }</strong>
		
	</div>
</form>
