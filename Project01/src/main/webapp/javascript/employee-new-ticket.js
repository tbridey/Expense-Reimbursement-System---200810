
async function new_ticket(){
	
	var session = sessionStorage;
	var userStr = session.getItem('currentUser');
	var userObj = JSON.parse(userStr);
	var now = new Date();
	
	var a = document.getElementById('amountInput').value
	var d = document.getElementById('descriptionInput').value
	//var r = document.getElementById('recieptAttachment').value
	var t = document.getElementById('type').value
	var tID;
	if(t=='Lodging'){
		tID=1;
	}
	if(t=='Travel'){
		tID=2;
	}
	if(t=='Food'){
		tID=3;
	}
	if(t=='Other'){
		tID=4;
	}
	
	let newTicket = {
	
		amount : a,
		submitted : now.getDate(),
		description : d,
		//reciept : r,
		author : userObj.userID,
		resolver:-1,
		statusID : 0,
		typeID : tID
	};
	
	//console.log(credentials.username);
	//console.log(credentials.password);
	
	let response = await fetch("../NewTicket", {
		method: "POST",
		headers: {"Content-Type": "application/json", "Accept":"application/json"},
		body: JSON.stringify(newTicket)
	})
	.then(res => res.json())
	.then(data => {
		if(data==true){
			alert("Ticket submition succeeded");
			window.location.replace("http://localhost:8085/Project01/html/employee-home.html");
		}
		else{
			alert("Ticket submition failed");
			window.location.replace("http://localhost:8085/Project01/html/employee-home.html");
		}
	})
	.catch(error => console.log('ERROR'));	
};

let hello = function(){
	alert("hey boi");
}
