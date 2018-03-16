function validate() {
	var password = document.getElementById("pwd").value;
	var cpassword = document.getElementById("re-pwd").value;
	if((myform.name.value.length==0) && (myform.email.value.length==0) && (myform.pwd.value.length==0) && (myform.re-pwd.value.length==0) && (myform.address.value.length==0))
  {
		document.getElementById('errfn').innerHTML="Please enter user details";
		return false;
  }
   else if(myform.name.value.length==0)
  {
		document.getElementById('errfn').innerHTML="Please enter valid name";
		return false;
  }
  else if(myform.email.value.length==0)
  {
		document.getElementById('errfn').innerHTML="Please enter valid email";
		return false;
  }
  else if(myform.pwd.value.length==0)
  {
		document.getElementById('errfn').innerHTML="Please enter valid password";
		return false;
  }
  else if(myform.re-pwd.value.length==0)
  {
		document.getElementById('errfn').innerHTML="Please enter valid password";
		return false;
  }
  else if(myform.address.value.length==0)
  {
		document.getElementById('errfn').innerHTML="Please enter valid address";
		return false;
  }
  else if(password!=cpassword)
  {
		document.getElementById('errfn').innerHTML="Passwords do not match";
		return false;
  }
  else if(myform.address.value=="" && myform.email.value=="" && myform.pwd.value=="" && myform.re-pwd.value=="" && myform.name.value=="")
  {
		document.getElementById('errfn').innerHTML="Please enter user details";
		return false;
  }
 }
