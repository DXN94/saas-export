<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<body>
<form id="pageForm" action="${param.pageUrl}" method="post">
<div class="pull-left">
    <div class="form-group form-inline">
        总共${page.pages} 页，共${page.total} 条数据。每页
        <select class="form-control" name="pageSize" onchange="javascript:goPage()">
            <option value="2" ${page.size==2? "selected":""}>2</option>
            <option value="3" ${page.size==3? "selected":""}>3</option>
            <option value="6" ${page.size==6? "selected":""}>6</option>
            <option value="7" ${page.size==7? "selected":""}>7</option>
            <option value="10" ${page.size==10? "selected":""}>10</option>
        </select> 条
    </div>
</div>

<div class="box-tools pull-right">
    <ul class="pagination" style="margin: 0px;">
        <li >
            <a href="javascript:goPage(1)" aria-label="Previous">首页</a>
        </li>
        <li><a href="javascript:goPage(${page.prePage})">上一页</a></li>
        <c:forEach begin="${page.navigateFirstPage}" end="${page.navigateLastPage}" var="i">
            <li class="paginate_button ${page.pageNum==i ? 'active':''}"><a href="javascript:goPage(${i})">${i}</a></li>
        </c:forEach>
        <li><a href="javascript:goPage(${page.nextPage})">下一页</a></li>
        <li>
            <a href="javascript:goPage(${page.pages})" aria-label="Next">尾页</a>
        </li>
    </ul>
</div>

    <input type="hidden" name="page" id="pageNum">
</form>
<script>
    function goPage(page){
        if (page){
            $("#pageNum").val(page);
        }
        $("#pageForm").submit();
    }
</script>
</body>
</html>
