function compare() {
		window.location.href = "#popup2";
	}
	function validate() {
		if(myform.input.value=="")
		{
			document.getElementById('errfn').innerHTML="Please enter input";
			return false;
		}
	}
	function validate1() {
		if(myform.input.value=="")
		{
			document.getElementById('errfn').innerHTML="Please enter input";
			return false;
		}
		if(myform.output.value=="")
		{
			document.getElementById('errfn').innerHTML="Please enter output";
			return false;
		}
	}
