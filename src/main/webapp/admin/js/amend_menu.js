
$(document).ready(function(){
	
	$.ajax({		
		
		cache : false, 
		
		type : 'POST',
		
		url : js_path + '/menu/get',
		
		async: true,
		
		data : {
			id : $("#id").val()
		},
		
		dataType : 'json',
		
		ajaxStart:function(){
	        ZENG.msgbox.show(" 正在加载中，请稍后...", 6, 10000);
		},
		
		complete: function(){
			ZENG.msgbox.hide();  //隐藏加载提示
		},
		
		success : function(result) {
			
//			var id = result.id;
			var name = result.name;
//			var type = result.type;
			var key = result.menuKey;
			var parentId = result.parentId;
			var parentName = result.parentName;
			var showOrder = result.showOrder;
			
			$("#name").val(name);
			$("#menuKey").val(key);
			$("#showOrder").val(showOrder);
			
			if(parentId==-1){
				$("#old_parentId").val("无");
			}else{
				$("#old_parentId").val(parentName);
			}
			
			
			$("#old_parentId option").each(function() {
				if ($(this).val() == parentId) {
					$(this).prependTo($("old_parentId"));
					$(this).attr("selected", "selected");
				}
			});
			

			
		}
		
	});

});

//function checkType(){
//	
//	var choose = $("#menu_type").value;
//	if(choose=="click"){
//		alert("不能定义单击型菜单！");
//	}
//}