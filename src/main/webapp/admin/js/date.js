//年
function yearList(num) {
	
	var date = new Date();
	var t = date.getFullYear() - 1;
	var yStr = "";
	
	for ( var i = t ; i <= t + num; i++) {
		yStr = yStr + "<option value='" + i + (i == t ? "' selected>" : "'>") + i + "</option>";
	}
	return yStr;
}

//月
function monthList() {
	
	var date = new Date();
	var t = date.getMonth() + 1;
	mStr = "";
	
	for ( var i = 1; i <= 12; i++) {
		mStr = mStr + "<option value='" + i + (i == t ? "' selected>" : "'>") + i + "</option>";
	}
	return mStr;
}

//日
function dateList(fName) {
	
	var year = document.all[fName + "_yy"].value;
	var month = document.all[fName + "_mm"].value;
	var d = new Date();
	var t = document.all[fName + "_dd"].value == "" ? d.getDate() : document.all[fName + "_dd"].value;
//	d = new Date(year + "/" + month + "/1");
	dStr = "";
	
	var count = 0;
	
	if(month==1||month==3||month==5||month==7||month==8||month==10||month==12){
		count = 31;
	}else if(month==2){
		
		if((year%4==0 && year%100!=0)||(year%100==0 && year%400==0)){
			count = 29;
		}else{
			count = 28;
		}
		
	}else{
		count = 30;
	}

	for ( var i = 1; i <= count; i++) {
		d.setDate(i);
		if (d.getDate() != i) {
			break;
		}
		dStr = dStr + "<option value='" + i + (i == t ? "' selected>" : "'>") + i + "</option>";
	}
	document.all[fName + "_dx"].innerHTML = "<select name='" + fName
			+ "_dd' onchange='javascript：setDateValue(\"" + fName + "\");'>"
			+ dStr + "</select> &nbsp;日 &nbsp;";
	
//	alert( document.all[fName + "_dd"].value );
}

function setDateValue(name) {
	
	var year = document.all[name + "_yy"].value;
	var month = document.all[name + "_mm"].value;
	var date = document.all[name + "_dd"].value;
	
	if(month>=1&&month<=9){
		month = "0" + month;
	}
	
	if(date>=1&&date<=9){
		date = "0" + date;
	}
	
	var dValue = year + month + date;

	document.all[name].value = dValue;
}


function showDateSelect(name) {
	
	document.write("<select name='" + name + "_yy' id='" + name
			+ "_yy' onchange='javascript: dateList(\"" + name
			+ "\"); setDateValue(\"" + name + "\");'>" + yearList(3)
			+ "</select> &nbsp;年 &nbsp;");
	
	document.write("<select name='" + name + "_mm' id='" + name
			+ "_mm' onchange='javascript: dateList(\"" + name
			+ "\"); setDateValue(\"" + name + "\");'>" + monthList()
			+ "</select> &nbsp;月 &nbsp;");
	
	document.write("<span id='" + name + "_dx'><select name='" + name + "_dd' id='" + name + "_dd'>"
			+ "</select> &nbsp;日 &nbsp;</span>");
	
	dateList(name);
	
	document.write("<input type='hidden' name='" + name + "' value=''>");
	
	setDateValue(name);
}

//--------------------------------------------------------------------------------

//小时
function hourList() {
	
	var date = new Date();
	var hour = date.getHours();
	var hStr = "";

	for ( var i = 0 ; i <= 24; i++) {
		hStr = hStr + "<option value='" + i + (i == hour ? "' selected>" : "'>") + i + "</option>";
	}
	return hStr;
}

//分钟
function minuteList() {
	
	var date = new Date();
	var minute = date.getMinutes();
	mStr = "";

	for ( var i = 0; i <= 59; i=i+5) {
		
		if(i<=minute && minute<i+10){
			
			mStr = mStr + "<option value='" + i + "'>" + i + "</option>";
			i = i + 10;
			if(i==60){
				i = 59;
			}
			mStr = mStr + "<option value='" + i + "' selected>" + i + "</option>";
			
		}else{
			
			mStr = mStr + "<option value='" + i + "'>" + i + "</option>";
		}
		
	}
	return mStr;
}

function setTimeValue(name) {
	
	var hour = document.all[name + "_HH"].value;
	var minute = document.all[name + "_MM"].value;
	
	if(hour>=1&&hour<=9){
		hour = "0" + hour;
	}
	
	if(minute>=1&&minute<=9){
		minute = "0" + minute;
	}
	
	var dValue = hour + ":" + minute;

	document.all[name].value = dValue;
}

function showTimeSelect(name) {
	
	document.write("<select name='" + name + "_HH' id='" + name + "_HH'>" + hourList() + "</select> &nbsp;小时 &nbsp;");
	
	document.write("<select name='" + name + "_MM' id='" + name  + "_MM'>" + minuteList() + "</select> &nbsp;分钟");
	
	document.write("<input type='hidden' name='" + name + "' value=''>");
	
	setTimeValue(name);
}

//--------------------------------------------------------------------------------

function dateList(fName, dd) {
	
	var year = document.all[fName + "_yy"].value;
	var month = document.all[fName + "_mm"].value;
	
	dStr = "";
	
	var count = 0;
	
	if(month==1||month==3||month==5||month==7||month==8||month==10||month==12){
		count = 31;
	}else if(month==2){
		
		if((year%4==0 && year%100!=0)||(year%100==0 && year%400==0)){
			count = 29;
		}else{
			count = 28;
		}
		
	}else{
		count = 30;
	}

	for ( var i = 1; i <= count; i++) {
		dStr = dStr + "<option value='" + i + (i == dd ? "' selected>" : "'>") + i + "</option>";
	}
	document.all[fName + "_dx"].innerHTML = "<select name='" + fName
			+ "_dd' onchange='javascript：setDateValue(\"" + fName + "\");'>"
			+ dStr + "</select> &nbsp;日 &nbsp;";
	
}
