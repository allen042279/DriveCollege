
$(document).ready(function(){
	
	var idval = $("#menuid").val();
	
	$.ajax({		
		
		cache : false, 
		
		type : 'POST',
		
		url : js_path + '/subscribe/get',
		
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
		
		success : function(result) {
			
//			var id = result.id;
			var title = result.title;
			var description = result.description;
			var linkurl = result.url;
//			var contentUrl = result.contentUrl;
			var picPath = result.picPath;
			var is_top;
			var is_used;
			var order = result.showOrder;
			
			if(result.top==true){
				is_top = 1; 
			}else{
				is_top = 0;
			}
			
			if(result.used==true){
				is_used = 1;
			}else{
				is_used = 0;
			}
			
			
			$("#title").val(title);
			$("#description").html(description);
			
			$("#oldimg").attr("src", base_path + picPath);
			$("#oldPicPath").val(picPath);
			
			$("#url").val(linkurl);
//			$("#contentUrl").val(contentUrl);
			$("#showOrder").val(order);
			
			$("#top option").each(function() {
				if ($(this).val() == is_top) {
					$(this).prependTo($("top"));
					$(this).attr("selected", "selected");
				}
			});
			
			$("#used option").each(function() {
				if ($(this).val() == is_used) {
					$(this).prependTo($("used"));
					$(this).attr("selected", "selected");
				}
			});
			
		}
		
	});

});

