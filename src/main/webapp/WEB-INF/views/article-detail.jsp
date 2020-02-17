<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${article.title }</title>
<link href="/public/css/bootstrap.min.css" rel="stylesheet">
<link href="/public/css/index.css" rel="stylesheet">
</head>
<body>
	<body>
	<jsp:include page="./common/user/head-top.jsp"></jsp:include>
	<div class="container-fluid" style="margin-top: 6px;">
		<div class="row offset-1">
			<div class="col-6">
				<h1>${article.title }</h1>
				<h3 style="color: #777;">${article.nickname }    发布时间：<fmt:formatDate value="${article.created }" pattern="yyyy-MM-dd日"/></h3>
				<div>
					<div class="article-content">
						${article.content }
					</div>
				</div>
				<form id="commentForm">
				<c:if test="${userInfo==null }">
					<div>
						<span><a href="/user/login">请登录后，发表评论</a></span>
					</div>
				</c:if>
				<c:if test="${userInfo!=null }">
					<div class="row" style="margin-top: 20px;">
							<input type="hidden" id="articleId" name="articleId" value="${article.id }">
							<div class="col-8 form-group">
							    <!-- <label for="exampleFormControlTextarea1">评论</label> -->
							    <textarea class="form-control" id="content" name="content" rows="1" placeholder="请输入评论"></textarea>
							</div>
							<div class="col-4">
								<button type="button" class="btn btn-primary" onclick="submitComment();">发布</button>
							</div>
					</div>
				</c:if>
				<div>
					<c:forEach items="${pageInfo.list }" var="item">
						<div class="media">
						  <img src="${item.headimg }" class="mr-3" alt="..." style="width: 32px;">
						  <div class="media-body">
						    <h5 class="mt-0">${item.nickname }  <fmt:formatDate value="${item.created }" pattern="yyyy-MM-dd HH:mm"></fmt:formatDate></h5>
						     ${item.content }
						  </div>
						</div>
					</c:forEach>
				</div>
				<div>
					
					<jsp:include page="./common/page.jsp"></jsp:include>
				</div>
				
				</form>
			</div>
			
			<div class="col-3">
				<div class="right">
					<div>相关文章</div>
					<ul class="list-unstyled">
						<c:forEach items="${relArticleList }" var="item">
							<li class="media">
								<a href="/article/detail/${item.id }.html" target="_blank"><img src="${item.picture }" style="height: 72px; width: 72px;" class="mr-3" alt="..."></a>
								<div class="media-body">
									<h5 class="mt-0 mb-1"><a href="/article/detail/${item.id }.html" target="_blank">${item.title }</a></h5>
								</div>
							</li>
						</c:forEach>
					</ul>
				</div>
			</div>

		</div>
	</div>
	<script src="/public/js/jquery.min.1.12.4.js"></script>
	<script src="/public/js/bootstrap.min.js"></script>
	<script type="text/javascript">
		function submitComment(){
			var formData = $("#commentForm").serialize();
			console.log(formData);
			$.post('/comment/add',formData,function(res){
				if(res.result){
					console.log("评论成功");
					var href = location.href;
					location.href=href.substring(0,href.indexOf('?'));
				}else if(res.errorCode==10000){
					alert(res.message);
					location.href="/user/login"
				}else{
					console.log("评论失败");
				}
			})
		}
		
		function gotoPage(pageNo){
			var href = location.href;
			href = href.substring(0,href.indexOf('?'));
			console.log(href);
			location.href=href+'?pageNum='+pageNo;
		}
	</script>
</body>
</html>