<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- saved from url=(0048)http://localhost:8080/mango/product/list/1.jhtml -->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>翊华商城</title>
<link href="${pageContext.request.contextPath}/css/common.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/product.css"
	rel="stylesheet" type="text/css" />

</head>
<body>
	<div class="container header">
		<%@ include file="menu.jsp"%>
	</div>
	<div class="container productList">
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
										value="#sc.scName" /> </a>
							</dd>
						</s:iterator>
					</dl>
				</s:iterator>
			</div>
		</div>
		<div class="span18 last">

			<form id="productForm"
				action="${pageContext.request.contextPath}/image/蔬菜 - Powered By Mango Team.htm"
				method="get">
				<input type="hidden" id="brandId" name="brandId" value="" /> <input
					type="hidden" id="promotionId" name="promotionId" value="" /> <input
					type="hidden" id="orderType" name="orderType" value="" /> <input
					type="hidden" id="pageNumber" name="pageNumber" value="1" /> <input
					type="hidden" id="pageSize" name="pageSize" value="20" />

				<div id="result" class="result table clearfix">
					<ul>
						<s:iterator value="page.list" var="p">
							<li><a
								href="${pageContext.request.contextPath}/product_getById?id=<s:property value="#p.id"/>">
									<img
									src="${pageContext.request.contextPath}/<s:property value="#p.image" />"
									width="170" height="170" style="display: inline-block;" /> <span
									style='color: green'> <s:property value="#p.name" />
								</span> <span class="price"> 商城价： ￥<s:property
											value="#p.shopPrice" />
								</span>
							</a></li>
						</s:iterator>
					</ul>
				</div>
				<div class="pagination">
					<span>第<s:property value="page.pageNo" />/<s:property
							value="page.totalPageNumber" /></span>
					<s:if test="cId != null">
						<s:if test="page.pageNo != 1">
							<a
								href="${pageContext.request.contextPath}/category_getById?cId=<s:property value="cId"/>&pageNo=1"
								class="firstPage">&nbsp;</a>
							<a
								href="${pageContext.request.contextPath}/category_getById?cId=<s:property value="cId"/>&pageNo=<s:property value="page.prevPage"/> "
								class="previousPage">&nbsp;</a>
						</s:if>
						<s:iterator var="i" begin="1" end="page.totalPageNumber">
							<s:if test="page.pageNo != #i">
								<a
									href="${pageContext.request.contextPath}/category_getById?cId=<s:property value="cId"/>&pageNo=<s:property value="#i"/> "
									class=""><s:property value="#i" /></a>
							</s:if>
							<s:else>
								<span class="currentPage"><s:property value="#i" /></span>
							</s:else>
						</s:iterator>
						<s:if test="page.pageNo != page.totalPageNumber">
							<a
								href="${pageContext.request.contextPath}/category_getById?cId=<s:property value="cId"/>&pageNo=<s:property value="page.nextPage"/>"
								class="nextPage"></a>
							<a
								href="${pageContext.request.contextPath}/category_getById?cId=<s:property value="cId"/>&pageNo=<s:property value="page.totalPageNumber"/>"
								class="lastPage"></a>
						</s:if>
					</s:if>
					<s:if test="scId != null">
						<s:if test="page.pageNo != 1">
							<a
								href="${pageContext.request.contextPath}/secondCategory_getById?scId=<s:property value="scId"/>&pageNo=1"
								class="firstPage">&nbsp;</a>
							<a
								href="${pageContext.request.contextPath}/secondCategory_getById?scId=<s:property value="scId"/>&pageNo=<s:property value="page.prevPage"/> "
								class="previousPage">&nbsp;</a>
						</s:if>
						<s:iterator var="i" begin="1" end="page.totalPageNumber">
							<s:if test="page.pageNo != #i">
								<a
									href="${pageContext.request.contextPath}/secondCategory_getById?scId=<s:property value="scId"/>&pageNo=<s:property value="#i"/> "
									class=""><s:property value="#i" /></a>
							</s:if>
							<s:else>
								<span class="currentPage"><s:property value="#i" /></span>
							</s:else>
						</s:iterator>
						<s:if test="page.pageNo != page.totalPageNumber">
							<a
								href="${pageContext.request.contextPath}/secondCategory_getById?scId=<s:property value="scId"/>&pageNo=<s:property value="page.nextPage"/>"
								class="nextPage"></a>
							<a
								href="${pageContext.request.contextPath}/secondCategory_getById?scId=<s:property value="scId"/>&pageNo=<s:property value="page.totalPageNumber"/>"
								class="lastPage"></a>
						</s:if>
					</s:if>
				</div>
			</form>
		</div>
	</div>
	<div class="container footer">
		<div class="span24">
			<div class="footerAd">
				<img src="${pageContext.request.contextPath}/image/footer.jpg"
					width="950" height="52" alt="我们的优势" title="我们的优势" />
			</div>
		</div>
		<div class="span24">
			<ul class="bottomNav">
				<li><a>关于我们</a> |</li>
				<li><a>联系我们</a> |</li>
				<li><a>诚聘英才</a> |</li>
				<li><a>法律声明</a> |</li>
				<li><a>友情链接</a> |</li>
				<li><a target="_blank">支付方式</a> |</li>
				<li><a target="_blank">配送方式</a> |</li>
				<li><a>官网</a> |</li>
				<li><a>论坛</a></li>
			</ul>
		</div>
		<div class="span24">
			<div class="copyright">Copyright©2005-2015 网上商城 版权所有</div>
		</div>
	</div>
</body>
</html>