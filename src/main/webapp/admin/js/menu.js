
$(document).ready(function(){
	
	$("#data_div").hide();
	$("#hint_div").show();
	
	$.ajax({		
		
		cache : false, 
		
		type : 'POST',
		
		url : js_path + '/menu/getAll',  // 访问服务器地址
		
		async: false,
		
		dataType : 'json',
		
		ajaxStart:function(){
	        ZENG.msgbox.show(" 正在加载中，请稍后...", 6, 10000);
		},
		
		success : function(result) {
			
			ZENG.msgbox.hide(); //隐藏加载提示
			
			var insetViewData = ""; // 视图数据

			if((result!=undefined || result!=null ) && result!=="") {
				
				$.each(result, function(index,item) {
					 
					insetViewData += createTR(item.id, item.name, item.type, item.menuKey, item.parentId, item.parentName, item.showOrder );
				});
				
				$("#datas").html(insetViewData);  //将得到的字符串放到id为content的标签内（以html形式）
				
				if(result.length>0){
					$("#data_div").show();
					$("#hint_div").hide();
				}
				
			}else{
				var result = String(data);
				eval(result.substring(8,result.length-9));
			}
			
		},
		complete: function (XMLHttpRequest, textStatus) {
			ZENG.msgbox.hide(); //隐藏加载提示
        },
        error: function (e) {
        	ZENG.msgbox.hide(); //隐藏加载提示
        }
			
	});

});



function createTR(id, name, type, key, parentId, parentName, order) {
	
	if(parentId==-1){
		parentName="无";
	}
	
	var kkk = key==null? "": key;
	
	var tr = "<tr>";
	
	tr += "<td style='display:none'>" + id + "</td>";
	tr += "<td>" + name + "</td>";
	tr += "<td>" + type + "</td>";
	tr += "<td>" + kkk + "</td>";
	tr += "<td>" + parentName + "</td>"; 
	tr += "<td>" + order + "</td>"; 
	
	tr += "<td><a  href='" + js_path + "/admin/views/amendMenu.jsp?id=" + id + "'>" + "<img src='" + js_path + "/admin/img/modify.png'>" + "</a></td>";
	tr += "<td><a  href='" + js_path + "/menu/delete/" + id  + "' onclick='return del();'>" + "<img src='" + js_path + "/admin/img/delete.png'>" + "</a></td>";

	tr += "</tr>";
	
	return tr;
}

//提示用户确认删除
function del() {
	return window.confirm("你确认要删除吗?");
}
