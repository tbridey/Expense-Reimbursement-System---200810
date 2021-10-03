async function view_tickets(){

	let response = await fetch("../ViewAllTickets", {
		method: "POST",
		headers: {"Content-Type": "application/json", "Accept":"application/json"},
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
	
	for(var i=1;i<=length;i++){
		//console.log("entered loop");
		var ticket = tickets["ticket"+i];
		//console.log("ticketID: "+ticket.ticketID);
	//get table-body and set the rows and cells with dynamic buttons attached to each ticket
		//console.log("printing table...");	
		var table = document.getElementById('table-body');
		//console.log("creating buttons...");	
		var app = document.createElement('Approve');
		app.type='button';
		app.id=ticket;
		app.className='btn btn-success btn-lg';
		
		var deny = document.createElement('button');
		deny.setAttribute('type','button');
		deny.setAttribute('id', ticket);
		deny.setAttribute('class','btn btn-danger btn-lg');
		
		var row = table.insertRow(i-1);
		//console.log("inserting rows...");
		var cell1 = row.insertCell(0)
		var cell2 = row.insertCell(1)
		var cell3 = row.insertCell(2)
		var cell4 = row.insertCell(3)
		var cell5 = row.insertCell(4)
		var cell6 = row.insertCell(5)
		var cell7 = row.insertCell(6)
		var cell8 = row.insertCell(7)

		//console.log("starting data input...");
		cell1.innerHTML = ticket.ticketID;
		cell2.innerHTML = ticket.author;
		cell3.innerHTML = ticket.resolver;
		cell4.innerHTML = ticket.submitted;
		cell5.innerHTML = ticket.resolved;
		cell6.innerHTML = ticket.type;
		cell7.innerHTML = ticket.amount;
		cell8.innerHTML = ticket.description;
		
		//console.log("... finished printing table");
	}
}