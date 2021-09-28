let userStr = sessionStroage.getItem('currentUser');
let userObj = JSON.parse(userStr);
alert("here it is: "+userObj.firstName); 

document.getElementById('welcome-div').addEventListener('load', printName()); 

function printName() {
	
	alert("here it is: "+userObj.firstName); 
	
	document.getElementById('emp-name').innerHTML = "poop hole";
}

	

