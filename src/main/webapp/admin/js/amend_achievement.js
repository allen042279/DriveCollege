
$(document).ready(function(){
	
	var idval = $("#achievementId").val();
	
	$.ajax({		
		
		cache : false, 
		
		type : 'POST',
		
		url : js_path + '/achievement/get',
		
		async: false,
		
		data : {
			id : idval
		},
		
		dataType : 'json',
		
		ajaxStart:function(){
	        ZENG.msgbox.show(" 正在加载中，请稍后...", 6, 10000);
		},
		
		complete: function(){
			ZENG.msgbox.hide();  //隐藏加载提示
		},
		
		success : function(returnData) {
			
			var result = returnData;
			
			var title = result.title;
			var typeId = result.achievementType.id;
			var url = result.url;
			var order = result.showOrder;
			
			$("#name").val(title);
			$("#url").val(url);
			$("#showOrder").val(order);
			
			$("#achievement_type option").each(function() {
				if ($(this).val() == typeId) {
					$(this).prependTo($("achievement_type"));
					$(this).attr("selected", "selected");
				}
			});
			
		}
		
	});

});

