
async function search(){
	
	var num = document.getElementById('emp-num').value;
	//console.log(emp_num);
	let employee_number = {
		emp_num : num,
	}
	
	//console.log(credentials.username);
	
	let response = await fetch("../SearchEmployee", {
		method: "POST",
		headers: {"Content-Type": "application/json", "Accept":"application/json"},
		body: JSON.stringify(employee_number)
	})
	.then(res => res.json())
	.then(data => {
		//console.log(data);
		printEmp(data);
	})
	.catch(error => console.log('ERROR'));
	
};

function printEmp(data){
	console.log(data);
	var name = `${data.firstName} ${data.lastName}`
	document.getElementById('employee-name').innerHTML = name;
	document.getElementById('employee-username').innerHTML = data.username;
	document.getElementById('employee-email').innerHTML = data.email;
	document.getElementById('employee-title').innerHTML = data.userRole;
	document.getElementById('employee-id').innerHTML = data.userID;
}