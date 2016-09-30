<%@page import="data.*" %>
<%@page import="controller.*"%> 

<%

String errMessage="";
try
{
	if(request.getParameter("btnSubmit") != null)
	{
		 boolean validData = true;
         String cardtype = "", cardname, cardnumber, transactionpassword;
         int  expmonth, expyear, cvv;
         
         cardnumber = request.getParameter("cardnumber");
         cardname = request.getParameter("cardname");
         transactionpassword = request.getParameter("transactionpassword");
         
         cvv = Integer.parseInt(request.getParameter("cvv"));
         expmonth = Integer.parseInt(request.getParameter("expirationmonth"));
         expyear = Integer.parseInt(request.getParameter("expirationyear"));
         
         
         if(request.getParameter("cardtype") != null ) {
          	if(request.getParameter("cardtype").equals("Credit card")) {
         		 cardtype = "C";
         	 }
          	if(request.getParameter("cardtype").equals("Debit card")) {
     			 cardtype = "D";
     		 }
 		 } 
 		else {
         	 errMessage = "Please enter card details!";
              validData = false;
            }
         
         
         if( cardnumber == null ) {
           errMessage = "Please enter card details ";
           validData = false;
         }
         
         if( cvv < 0 ) {
             errMessage = "Please enter card details ";
             validData = false;
           }
         
         if( cardname == "" ) {
        	 errMessage = "Please enter card details!";
           validData = false;
         }
         
         if( transactionpassword == "" ) {
        	 errMessage = "Please enter card details!";
           validData = false;
         }
       
         out.println(validData);
         if( validData ) {  
       	   BankServer bs = new BankServer();
       	   if(bs.isValidCardNumber(cardnumber))  {
       		   
       	   	if(bs.isValidCardDetails(cardnumber,cardname,cvv,expmonth,expyear,cardtype)) {
       		
           		if(bs.isValidTP(transactionpassword, cardnumber) ) {
           		  session.setAttribute("currCard", cardnumber);
           		  Card c = new Card(); 
           		  c.setCardname(cardname);
           		  c.setCardnumber(cardnumber);
           		  c.setCardtype(cardtype);
                  response.sendRedirect("cardauth.jsp");     
           	}
       	   }
           }else{ 
               errMessage = "Invalid Card Details!!";
            }
         }
	
	}	
} catch (Exception e) {
	response.sendRedirect("failure.jsp");
}

%>

  