<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
	<title>网上商城</title>
	<link href="${pageContext.request.contextPath}/css/common.css"
		rel="stylesheet" type="text/css"/>
		<link href="${pageContext.request.contextPath}/css/product.css"
			rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.3.js"></script>
<script type="text/javascript">
	$(function(){
		$("#addToCart").click(function(){
			var form = $("#cartForm");
			form.submit();
		})
		//判断商品数量修改值的合法性
		var val = $("#quantity").val();
		$("#quantity").change(function(){
			var value = $(this).val().trim();
			//合法数值的正规表达式，即全为数字
			var reg = /^\d+$/g;
			//如果不是数字，弹出警告，并恢复最初的数值
			if(!reg.test(value)){
				alert("输入的数量为非法字符！");
				$(this).val(val);
			}
		})
	})
</script>
</head>
<body>
	<div class="container header">
		<%@ include file="menu.jsp"%>
	</div>
	<div class="container productContent">
		<div class="span6">
			<div class="hotProductCategory">
				<s:iterator value="#session.fclist" var="fc">
					<dl>
						<dt>
							<a
								href="${pageContext.request.contextPath}/category_getById?cId=<s:property value="#fc.cId"/>&pageNo=1"><s:property
									value="#fc.cName" /></a>
						</dt>
						<s:iterator value="#fc.secondCategorys" var="sc">
							<dd>
								<a
									href="${pageContext.request.contextPath}/secondCategory_getById?scId=<s:property value="#sc.scId"/>&pageNo=1"><s:property
										value="#sc.scName" /></a>
							</dd>
						</s:iterator>
					</dl>
				</s:iterator>
			</div>


		</div>
		<div class="span18 last">

			<div class="productImage">
				<a title="" style="outline-style: none; text-decoration: none;"
					id="zoom"
					href="http://image/r___________renleipic_01/bigPic1ea8f1c9-8b8e-4262-8ca9-690912434692.jpg"
					rel="gallery">
					<div class="zoomPad">
						<img style="opacity: 1;" title="" class="medium"
							src="${pageContext.request.contextPath }/<s:property value="model.image" />" />
						<div
							style="display: block; top: 0px; left: 162px; width: 0px; height: 0px; position: absolute; border-width: 1px;"
							class="zoomPup">
							<div
								style="position: absolute; z-index: 5001; left: 312px; top: 0px; display: block;"
								class="zoomWindow">
								<div style="width: 368px;" class="zoomWrapper">
									<div style="width: 100%; position: absolute; display: none;"
										class="zoomWrapperTitle"></div>
									<div style="width: 0%; height: 0px;" class="zoomWrapperImage">
										<img
											src="%E5%B0%9A%E9%83%BD%E6%AF%94%E6%8B%89%E5%A5%B3%E8%A3%852013%E5%A4%8F%E8%A3%85%E6%96%B0%E6%AC%BE%E8%95%BE%E4%B8%9D%E8%BF%9E%E8%A1%A3%E8%A3%99%20%E9%9F%A9%E7%89%88%E4%BF%AE%E8%BA%AB%E9%9B%AA%E7%BA%BA%E6%89%93%E5%BA%95%E8%A3%99%E5%AD%90%20%E6%98%A5%E6%AC%BE%20-%20Powered%20By%20Mango%20Team_files/6d53c211-2325-41ed-8696-d8fbceb1c199-large.jpg"
											style="position: absolute; border: 0px none; display: block; left: -432px; top: 0px;" />
									</div>
								</div>
							</div>
							<div
								style="visibility: hidden; top: 129.5px; left: 106px; position: absolute;"
								class="zoomPreload">Loading zoom</div>
						</div>
					</div>
				</a>

			</div>
			<div class="name">
				<s:property value="model.name" />
			</div>
			<div class="sn">
				<div>
					编号：
					<s:property value="model.id" />
				</div>
			</div>
			<div class="info">
				<dl>
					<dt>翊华价:</dt>
					<dd>
						<strong><s:property value="model.shopPrice" />元</strong> 参 考 价：
						<del>
							<s:property value="model.marketPrice" />
							元
						</del>
					</dd>
				</dl>
				<dl>
					<dt>促销:</dt>
					<dd>
						<a target="_blank" title="限时抢购 (2014-07-30 ~ 2015-01-01)">限时抢购</a>
					</dd>
				</dl>
				<dl>
					<dt></dt>
					<dd>
						<span> </span>
					</dd>
				</dl>
			</div>
			<form id="cartForm" action="cart_addToCart" method="post">
				<div class="action">
					<input type="hidden" value="<s:property value="model.id"/>" name="id"/>
					<dl class="quantity">
						<dt>购买数量:</dt>
						<dd>
							<input id="quantity" name="quantity" value="1" maxlength="4"
								onpaste="return false;" type="text" />
							<div>
								<span id="increase" class="increase">&nbsp;</span> <span
									id="decrease" class="decrease">&nbsp;</span>
							</div>
						</dd>
						<dd>件</dd>
					</dl>
					<div class="buy">
						<input id="addToCart" class="addCart" value="加入购物车"
							type="button" />
					</div>
				</div>
			</form>
			<div id="bar" class="bar">
				<ul>
					<li id="introductionTab"><a href="#introduction">商品介绍</a></li>

				</ul>
			</div>

			<div id="introduction" name="introduction" class="introduction">
				<div class="title">
					<strong><s:property value="model.desc" /></strong>
				</div>
				<div>
					<img
						src="${pageContext.request.contextPath }/<s:property value="model.image"/>" />
				</div>
			</div>



		</div>
	</div>
	<div class="container footer">
		<div class="span24">
			<div class="footerAd">
				<img src="image\r___________renleipic_01/footer.jpg" alt="我们的优势"
					title="我们的优势" height="52" width="950">
			</div>
		</div>
		<div class="span24">
			<ul class="bottomNav">
				<li><a href="#">关于我们</a> |</li>
				<li><a href="#">联系我们</a> |</li>
				<li><a href="#">诚聘英才</a> |</li>
				<li><a href="#">法律声明</a> |</li>
				<li><a>友情链接</a> |</li>
				<li><a target="_blank">支付方式</a> |</li>
				<li><a target="_blank">配送方式</a> |</li>
				<li><a>SHOP++官网</a> |</li>
				<li><a>SHOP++论坛</a></li>
			</ul>
		</div>
		<div class="span24">
			<div class="copyright">Copyright © 2005-2015 网上商城 版权所有</div>
		</div>
	</div>
</body>
</html>