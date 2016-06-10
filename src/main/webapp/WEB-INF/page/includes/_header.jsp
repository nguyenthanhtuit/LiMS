
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<header id="header">
	<nav class="navbar navbar-default">
		<div class="container header-wapper">
			<div class="pull-left logo">
				<a href="#"><img src="html/images/logo.png" alt=""></a>
			</div>
			<div class="pull-right menu">
				<div class="header-menu">
					<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
    						<c:set var="userName" value="${sessionScope.loginedUser.userName }" />
    						<c:choose>
    							<c:when test="${userName==null }">
    							<ul class="nav navbar-nav ul-menu" style="margin-top: 15px">
    								<a href="${pageContext.request.contextPath }/login">Sign in</a>
    							</c:when>
    							<c:when test="${userName!=null }">
    					<ul class="nav navbar-nav ul-menu">
       			 		<li class="dropdown">
						<a href="${request.getconte }" data-toggle="dropdown" class="btn dropdown-toggle" style="min-width: 105px">
							<i class="fa fa-user"></i> <c:out value="${userName}"></c:out>
							<span class="caret"></span>
						</a>
						<ul class="dropdown-menu account">
						
							<li class="dropdown-menu-title">
									<span>Account Settings</span>
							</li>
							<li><a href="#"><i class="fa fa-user"></i> Profile</a></li>
							<li><a href="${pageContext.request.contextPath }/logout"><i class="fa fa-power-off"></i> Logout</a></li>
						</ul>
					</li>
					</c:when>
    						</c:choose>
    						
						</ul>
					</div>	
				</div>	
			</div>
			<div class="input-group col-lg-6 col-lg-offset-3">
                <div class="input-group-btn">
                    <button type="button" class="btn btn-default dropdown-toggle show-all-button" data-toggle="dropdown">
                        <span class="filter_box" data-category="">ALL</span><span class="caret"></span>
                    </button>
                    <ul class="dropdown-menu">
                        <li><a>Tất cả</a></li>
                        <li><a>Sách Tiếng Việt</a></li>
                        <li><a>Sách Tiếng Anh</a></li>
                        <li><a>Điện Thoại - Máy Tính Bảng</a></li>
                        <li><a>Thiết Bị Số - Phụ Kiện Số</a></li>
                    </ul>
                </div>
                <input type="text" name="q" autocomplete="off" class="form-control" value="" placeholder="Search...">
                <span class="input-group-btn">
                    <button class="btn btn-default bnt-search" type="submit">
                        <span class="hidden-sm hidden-xs" >Search</span>
                        <i class="fa fa-search hidden-lg hidden-md"></i>
                    </button>
                </span>
            </div>
		</div>
	</nav>
</header><!-- /header -->