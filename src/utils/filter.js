
export const dateFormat1 = (time) =>  { //YYYY/MM/DD
	const _time = time.toString().length > 10 ? time : time * 1000
	var date = new Date(_time);
	var Y = date.getFullYear();
	var M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1);
	var D = (date.getDate() < 10 ? '0' + date.getDate() : date.getDate());
	var h = (date.getHours() < 10 ? '0' + date.getHours() : date.getHours());
	var m = (date.getMinutes() < 10 ? '0' + date.getMinutes() : date.getMinutes());
	var s = (date.getSeconds() < 10 ? '0' + date.getSeconds() : date.getSeconds());
	let strDate = `${Y}.${M}.${D} ${h}:${m}`
	// let strDate = `${Y}/${M}/${D} ${h}:${m}:${s}`
	return strDate;
}

// 数字补0操作

export const dateFormat2 = (time) =>  { //YYYY/MM/DD
	var date = new Date(time);
	var Y = date.getFullYear();
	var M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1);
	var D = (date.getDate() < 10 ? '0' + date.getDate() : date.getDate());
	var h = (date.getHours() + 8 < 10 ? '0' + (date.getHours()+8) : (date.getHours()+8));
	var m = (date.getMinutes() < 10 ? '0' + date.getMinutes() : date.getMinutes());
	var s = (date.getSeconds() < 10 ? '0' + date.getSeconds() : date.getSeconds());
	let strDate = `${Y}.${M}.${D} ${h}:${m}`
	// let strDate = `${Y}/${M}/${D} ${h}:${m}:${s}`
	return strDate;
}


