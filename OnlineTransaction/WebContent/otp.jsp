<%@ include file="JSP/otpCode.jsp" %>
<%@page import="controller.*"%> 
<html>
<head>
<META HTTP-EQUIV="refresh" CONTENT="<%= session.getMaxInactiveInterval() %>; URL=sessiontimeout.jsp" />
<title>OTP Authentication</title>
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

        <strong style="color:grey;font-style: italic;">1. Payment type &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </strong>  
        <strong style="color:grey;font-style: italic;">|&nbsp;&nbsp;&nbsp;&nbsp; 2. Enter Card Details  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </strong>
        <strong style="color:green;font-style: italic;">|&nbsp;&nbsp;&nbsp;&nbsp; 3. Verify Payment&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </strong>
        <strong style="color:grey;font-style: italic;">|&nbsp;&nbsp;&nbsp;&nbsp; 4. Confirmation&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </strong>
        <strong style="color:grey;font-style: italic;">|&nbsp;&nbsp;&nbsp;&nbsp; 5. Thank You   </strong>
 <hr>

<br>
<h3 align="center">Please enter the valid OTP values:</h3> 
<br></br>
<form action="" method="post">

<%
BankServer server = new BankServer();
String otp  = server.getOTP((String)session.getAttribute("currUser"));
session.removeAttribute("otpStr");
session.setAttribute("otpStr",otp);
%>

<table cellpadding="25" width="300" align="center" >
<tr>
<td><img src="icons/mail.png" width=80 height="60"></td>
<td><h4> The OTP has been sent to the mail that is registered with us.</h4></td>
</tr>

<tr>
<td></td>
<td><h5> If you did not receive the OTP mail yet, please <a href="otp.jsp" >click here</a> </h5></td>
</tr>

<tr>
<td>Enter OTP : </td>
<td><input type="password" maxlength="6" name="otp" id="otp" required/></td>
</tr>

</table>
<br><br>
<span style="color:red;font-weight:bold" ></span> 
<br></br>
<div style="text-align:center">
    <input type="submit" value="Confirm OTP" name="btnSubmit"/>
</div>
</form>
</body>
</html>
