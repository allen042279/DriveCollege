
$(document).ready(function() {

	$.ajax({

		cache : false, 
		
		type : 'POST',
		
		url : js_path + '/achievement/getAchievementType',
		
		async: true,
		
		dataType : 'json',
		
		success : function(returnData){
			
//			var blockViewData = "";
			var stripViewData = "";
			
//			var count = returnData.count;
			var result = returnData;
			
			if((result!=undefined || result!=null ) && result!=="") {
				
				$.each(result, function(index,items) {
					
//					blockViewData += createBlock(count, index, items.id, items.name, items.picPath, items.urlLink, items.status );
					stripViewData += createStrip(index+1, items.id, items.name);
				});

//				$("#block_datas").html(blockViewData);  //将得到的字符串放到id为content的标签内（以html形式）
				$("#strip_datas").html(stripViewData);  //将得到的字符串放到id为content的标签内（以html形式）
				
			}
		}
		
	});

});

function createBlock(count, index, id, name, picPath, urlLink, status) {

	var blockText = "";
	var statusStr = "";
	
	var shortName = "";
	if(getStringLength(name)>7){
		shortName = name.substr(0,7) + "...";
	}else{
		shortName = name;
	}
	
	if(1==status){
		statusStr += "style='color: red;'"; 
	} else if(2==status){
		statusStr += "style='color: yellow;'"; 
	} else if(3==status){
		statusStr += "style='color: green;'"; 
	}
	
	switch(index%3){
	
		case 0:
			blockText = "<tr>";
			blockText += "<td onclick='javascript: return is_Login(\"" + urlLink + "\")'>";
			blockText += "<div><img src='" + base_path + picPath + "' ></div>";
			blockText += "<div><span " + statusStr +">" + shortName + "</span></div>";
			blockText += "<p>&nbsp;</p>";
//			blockText += "<td style='display:none'>" + urlLink + "</td>";
			blockText += "</td>";
			
			if(count==index+1){
				blockText += "</tr>";
			}
			
			break;
		  
		case 1:
			blockText = "<td onclick='javascript: return is_Login(\"" + urlLink + "\")'>";
			blockText += "<div><img src='" + base_path + picPath + "' ></div>";
			blockText += "<div><span  " + statusStr +">" + shortName + "</span></div>";
			blockText += "<p>&nbsp;</p>";
//			blockText += "<td style='display:none'>" + urlLink + "</td>";
			blockText += "</td>";
			
			if(count==index+1){
				blockText += "</tr>";
			}
			
			break;
		  
		case 2:
			blockText = "<td onclick='javascript: return is_Login(\"" + urlLink + "\")'>";
			blockText += "<div><img src='" + base_path + picPath + "' ></div>";
			blockText += "<div><span  " + statusStr +">" + shortName + "</span></div>";
			blockText += "<p>&nbsp;</p>";
//			blockText += "<td style='display:none'>" + urlLink + "</td>";  onclick='javascript: return window.location.href=&quot;" + urlLink + "&quot;'"
			blockText += "</td>";
			
			blockText += "</tr>";
			
			break;

	}
	
	return blockText;
}

function createStrip(index, id, name) {

	var shortName = "";
	if(getStringLength(name)>50){
		shortName = name.substr(0,46) + "...";
	}else{
		shortName = name;
	}
	
	var tr = "<tr onclick=\"window.location.href='" + js_path + "/views/showAchievements.jsp?typeId=" + id + "'\" >";
	
	tr += "<td>" + index + ". " + shortName + "</td>";
	tr += "<td style='text-align:right;'><img src='" + base_path + "img/arrow.png" + "' style='width:20px;height:20px;vertical-align:middle;'></td>";
	
	tr += "</tr>";

	return tr;
	
}

function getStringLength(str) {
    ///<summary>获得字符串实际长度，中文2，英文1</summary>
    ///<param name="str">要获得长度的字符串</param>
    var realLength = 0, len = str.length, charCode = -1;
    
    for (var i = 0; i < len; i++) {
        charCode = str.charCodeAt(i);
        if (charCode >= 0 && charCode <= 128) realLength += 1;
        else realLength += 2;
    }
    
    return realLength;
}

function is_Login(url){
	
	if(isLogin=="1"){
		
		window.location.href= url;
		
	}else{
		
		var start = openIdString.lastIndexOf("?openId=");
		var openId = openIdString.substring(start + 8);
		
		$.ajax({
		
				cache : false, 
				
				type : 'POST',
				
				url : js_path + '/specialist',
				
				async: true,
				
				data : {
					actionMethod : 'is_login',
					openId : openId
				},
				
				dataType : 'json',
				
				success : function(returnData){
					
					var result = returnData.result;
					
					if (result != "") {
						
						if(result=="1") {
							window.location.href = url;
						}else{
							window.location.href = js_path + "/Html/mobile/loginSpecialist.jsp?nextUrl=" + url + "&openId=" + openId ;
						}
					}
					
				}
				
			});
		
	}

}
