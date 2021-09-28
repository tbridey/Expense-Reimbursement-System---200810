
async function new_ticket(){
	
	var session = sessionStorage;
	var userStr = session.getItem('currentUser');
	var userObj = JSON.parse(userStr);
	var x = Math.floor((Math.random() * 100) + 1);
	var now = new Date();
	
	var a = document.getElementById('amountInput').value
	var d = document.getElementById('descriptionInput').value
	var r = document.getElementById('recieptAttachment').value
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
		ticketID : x,
		amount : a,
		submitted : now.getDate(),
		description : d,
		reciept : r,
		author : userObj.firstName,
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
	.catch(error => console.log('ERROR'));	
};

let hello = function(){
	alert("hey boi");
}
