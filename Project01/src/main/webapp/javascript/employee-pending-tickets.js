async function view_pending(){
	
	let response = await fetch("../ViewUserPending", {
		method: "POST",
		headers: {"Content-Type": "application/json", "Accept":"application/json"}
		//body: JSON.stringify(credentials)
	})
	.then(res => res.json())
	.then(data => {
		console.log(data)
		printTable(data)
	})
	.catch(error => console.log('ERROR'));
	
};

function printTable(tickets){
	var length = Object.keys(tickets).length;
	console.log("JSON length: "+length);
	
	for(var i=1;i<length;i++){
		console.log("entered loop");
		var ticket = tickets["ticket"+i];
		//console.log("ticketID: "+ticket.ticketID);
	//get table-body and set the rows and cells
		console.log("printing table...");	
		var table = document.getElementById('table-body');
		var row = table.insertRow(i-1);
		var cell1 = row.insertCell(0)
		var cell2 = row.insertCell(1)
		var cell3 = row.insertCell(2)
		var cell4 = row.insertCell(3)
		var cell5 = row.insertCell(4)
		cell1.innerHTML = ticket.ticketID;
		cell2.innerHTML = ticket.submitted;
		cell3.innerHTML = ticket.type;
		cell4.innerHTML = ticket.amount;
		cell5.innerHTML = ticket.description;
		console.log("... finished printing table");
		

	}
}