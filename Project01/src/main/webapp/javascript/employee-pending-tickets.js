async function view_pending(){
	
	let response = await fetch("../ViewUserPending", {
		method: "POST",
		headers: {"Content-Type": "application/json", "Accept":"application/json"}
		//body: JSON.stringify(credentials)
	})
	.then(res => res.json())
	.then(data => {
			console.log(data);	
	})
	.catch(error => console.log('ERROR'));
	
};