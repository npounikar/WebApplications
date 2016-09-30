
<html>
    <head>
    <META HTTP-EQUIV="refresh" CONTENT="<%= session.getMaxInactiveInterval() %>; URL=sessiontimeout.jsp" />
    <title>Transaction Success</title>    
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
        <strong style="color:grey;font-style: italic;">|&nbsp;&nbsp;&nbsp;&nbsp; 3. Verify Payment&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </strong>
        <strong style="color:green;font-style: italic;">|&nbsp;&nbsp;&nbsp;&nbsp; 4. Confirmation&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </strong>
        <strong style="color:grey;font-style: italic;">|&nbsp;&nbsp;&nbsp;&nbsp; 5. Thank You   </strong>
 <hr>

<br>
<h2 align="center">Confirmation</h2> 
<table cellpadding="100" align="center">
<tr>
    <td>
    <pre>            <img src="icons/confirm.png" width="100" height="80" align="center"></pre><br>
    <h4 align="center">Your Transaction for $500 was successful!!</h4>
    </td>
   </tr>
</table>
<div align="center"><a href="billsummary.jsp">Go to Summary page</a> </div>
</body>
</html>