<%@ include file="JSP/cardauthCode.jsp" %> 
<%@page import="controller.*"%>
<html>
<head>
<META HTTP-EQUIV="refresh" CONTENT="<%= session.getMaxInactiveInterval() %>; URL=sessiontimeout.jsp" />
<title>Card Authentication</title>
<script type="text/javascript">
	    
</script>
<style type="text/css">
		html {
				background-color:#C0E6EA;
				border:1px grey solid;
				margin:20px; padding:20px;
			}
</style>        
</head>
<body>
<%@ include file="JSP/header.jsp" %>
        <strong style="color:grey;font-style: italic;">1. Payment type &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </strong>  
        <strong style="color:grey;font-style: italic;">|&nbsp;&nbsp;&nbsp;&nbsp; 2. Enter Card Details  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </strong>
        <strong style="color:green;font-style: italic;">|&nbsp;&nbsp;&nbsp;&nbsp; 3. Verify Payment&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </strong>
        <strong style="color:grey;font-style: italic;">|&nbsp;&nbsp;&nbsp;&nbsp; 4. Confirmation&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </strong>
        <strong style="color:grey;font-style: italic;">|&nbsp;&nbsp;&nbsp;&nbsp; 5. Thank You   </strong>
 <hr>

<br>
<h2 align="center">Please enter the valid digits from your credit card:</h2>
<br></br>
<form action="" method="post">
<table cellpadding="25" width="200" align="center" >
<tr>
<td>Position of the digits in your card number</td>
<%
RandomNumberGenerator rg= new RandomNumberGenerator();
int no1,no2,no3;
rg.printNumbers();
no1=rg.getnum1();
no2=rg.getnum2();
no3=rg.getnum3();

session.setAttribute("currNo1",no1);
session.setAttribute("currNo2",no2);
session.setAttribute("currNo3",no3);
%>
<td><%=no1+1%></td>
<td><%=no2+1%></td>
<td><%=no3+1%></td>
</tr>

<tr>
<td>The digits to be entered</td>
<td><input type="text" maxlength="1" name="firstdigit" id="firstdigit"/></td>
<td><input type="text" maxlength="1" name="seconddigit" id="seconddigit"/></td>
<td><input type="text" maxlength="1" name="thirddigit" id="thirddigit"/></td>
</tr>
</table>
<br><br>
<div align="center" style="color:red;font-weight:bold" id="errMessage"><%=errMessage %></div>
<br></br>
<div style="text-align:center">
    <input type="submit" value="Confirm Payment" name="btnSubmit" />
</div>
</form>
</body>
</html>
