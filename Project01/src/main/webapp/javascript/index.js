let userObj = {
	username:"",
	password:"",
	firstName:"",
	lastName:"",
	email:"",
	userID:-1,
	roleID:-1,
};

let verify_login = async function(){
	
	var u = document.getElementById('inputUsername').value;
	var p = document.getElementById('inputPassword').value;
	
	let credentials = {
		username : u,
		password : p
	};
	
	//console.log(credentials.username);
	//console.log(credentials.password);
	
	let response = await fetch("../LoginHandler", {
		method: "POST",
		headers: {"Content-Type": "application/json", "Accept":"application/json"},
		body: JSON.stringify(credentials)
	})
	.then(res => res.json())
	.then(data => {
				
		saveSession(data)
		
		redirect(data)
	})
	.catch(error => console.log('ERROR'));
	
};

function redirect(user){
	console.log("redirecting...")
	if(user.roleID == 0){
		window.location.replace("http://localhost:8085/Project01/html/manager-home.html");
	}
	if(user.roleID == 1){
		window.location.replace("http://localhost:8085/Project01/html/employee-home.html");
	}
};

function saveSession(data){
	userObj.username=data.username;
	userObj.password=data.password;
	userObj.firstName=data.firstName;
	userObj.lastName=data.lastName;
	userObj.email=data.email;
	userObj.userID=data.userID;
	userObj.roleID=data.roleID;
	
	let userStr = JSON.stringify(userObj);
	
	sessionStorage.setItem('currentUser', userStr);
};