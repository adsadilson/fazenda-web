function formatoMoeda(id){
	$("#"+id).maskMoney({
		showSymbol : true,
		symbol : "R$ ",
		decimal : ",",
		thousands : ".",
		allowZero : true,
		symbolStay: true
	});
}