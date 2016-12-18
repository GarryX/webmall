<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%-- <s:debug></s:debug> --%>
<div class="span5">
	<div class="logo">
		<a href="http://localhost:8080/mango/"> <img
			src="${pageContext.request.contextPath}/image/r___________renleipic_01/logo.gif"
			alt="传智播客"></a>
	</div>
</div>
<div class="span9">
	<div class="headerAd">
		<img src="${pageContext.request.contextPath}/image/header.jpg"
			width="320" height="50" alt="正品保障" title="正品保障">
	</div>
</div>
<div class="span10 last">
	<div class="topNav clearfix">
		<ul>
			<s:if test="#session.loginUser == null">
				<li id="headerLogin" class="headerLogin" style="display: list-item;"><a
					href="${pageContext.request.contextPath }/user_loginPage">登录</a>|</li>
				<li id="headerRegister" class="headerRegister"
					style="display: list-item;"><a
					href="${pageContext.request.contextPath }/user_registPage">注册</a>|</li>
				<li id="headerUsername" class="headerUsername"></li>
			</s:if>
			<s:else>
				<li id="headerLogout" class=""><s:property
						value="#session.loginUser.userName" /> |</li>
				<li id="headerLogout" class=""><a
					href="${pageContext.request.contextPath }/order_getOrdersByUid?uid=
					<s:property value="#session.loginUser.id"/>">我的订单</a>|</li>
				<li id="headerLogout" class=""><a
					href="${pageContext.request.contextPath }/user_logout">[退出]</a></li>
			</s:else>
			<li><a>会员中心</a> |</li>
			<li><a>购物指南</a> |</li>
			<li><a>关于我们</a></li>
		</ul>
	</div>
	<div class="cart">
		<a href="${pageContext.request.contextPath }/cart_getMyCart">购物车</a>
	</div>
	<div class="phone">
		客服热线: <strong>96008/53277764</strong>
	</div>
</div>
<div class="span24">
	<ul class="mainNav">
		<li><a href="${pageContext.request.contextPath }/home">首页</a> |</li>
		<s:iterator value="#session.fclist" var="fc">
			<li><a
				href="${pageContext.request.contextPath }/category_getById?cId=<s:property value="#fc.cId"/>&pageNo=1">
					<s:property value="#fc.cName" />
			</a> |</li>
		</s:iterator>

	</ul>
</div>