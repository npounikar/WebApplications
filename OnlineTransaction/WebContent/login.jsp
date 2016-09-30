<%@ page import="data.*" %>

<%@ page import="controller.*" %>

<%@ include file="JSP/loginCode.jsp" %>
 
<html>
    <head>
       
        <title>Login</title>
        
        <script type="text/javascript">
            <%@include  file="Javascript/loginJs.js" %>
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

        <div align="right">
        	<h3>Sign in with your account information below </h3>
            <!-- Login Form Starts here -->            
            <form action="" method="post">
            <table>
            	<tr>
            		<td>  UserID  </td>
                	<td><input type="text" name="txtUserId" value="" id="userid"/></td>
                </tr>
                <tr>
                	<td></td>
                	<td><span style="font-style: italic; color: red;" id="errUserid"><%=errUserid%></span></td>
                </tr>
                
                <tr>
            		<td>  Password  </td>
            		<td><input type="password" name="txtPassword" value="" id="pwd"/></td>
            	</tr>
                <tr>
                	<td></td>	
                	<td><span style="font-style: italic; color: red;" id="errPassword"><%=errPassword%></span></td>
                </tr>
                </tr>
                <tr>
                	<td></td>
                	<td><span style="font-style:italic; color:green;"> <b><%= errMessage %> </b></span></td>
                </tr>
                <tr>
                	<td></td>	
                	<td><input type="submit" value="Login" name="btnSubmit" onclick="return validateForm()"/><br/>
                		<a href="#">Forgot Password ?</a>
                	</td>
                </tr>
           	</table>
           	
            </form>
            
            <!-- Login Form ends here -->
         </div>

</body>
</html>

          
