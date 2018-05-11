<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<SPAN id=pagelink>
		<DIV
			style="LINE-HEIGHT: 20px; HEIGHT: 20px; TEXT-ALIGN: right">
			共[<B>${page.totalCount}</B>]条记录,[<B>${page.totalPage}</B>]页
			,每页显示
			<select name="pageSize">
			
			<option value="5" <c:if test="${page.pageSize==5 }">selected</c:if>>5</option>
			<option value="10" <c:if test="${page.pageSize==10 }">selected</c:if>>10</option>
			</select>
			条
			<c:if test="${page.pageCode > 1}">
			[<A href="javascript:to_page(${page.pageCode-1})">前一页</A>]
			</c:if>
			<B>${page.pageCode}</B>
			<c:if test="${page.pageCode < page.totalPage }">
			[<A href="javascript:to_page(${page.pageCode+1})">后一页</A>]
			</c:if> 
			到
			<input type="text" size="3" id="page" name="pageCode" />
			页
			
			<input type="button" value="Go" onclick="to_page()"/>
		</DIV>
</SPAN>
