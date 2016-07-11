
$(document).ready(function(){
	
	var idval = $("#id").val();
	
	$.ajax({		
		
		cache : false, 
		
		type : 'POST',
		
		url : js_path + '/user/get',
		
		async: true,
		
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
			var username = result.username;
			var password = result.password;
			var role = result.role;
			var isEnabled = result.enabled;
			var isAccountNonExpired = !result.accountNonExpired;
			var isAccountNonLocked = !result.accountNonLocked;
			var isCredentialsNonExpired = !result.credentialsNonExpired;
			
			$("#username").val(username);
			$("#password").val(password);
			
			$("#role option").each(function() {
				if ($(this).val() == role) {
					$(this).prependTo($("role"));
					$(this).attr("selected", "selected");
				}
			});
			
			$("#isEnabled option").each(function() {
				if ($(this).val() == isEnabled) {
					$(this).prependTo($("isEnabled"));
					$(this).attr("selected", "selected");
				}
			});
			
			$("#isAccountNonLocked option").each(function() {
				if ($(this).val() == isAccountNonLocked) {
					$(this).prependTo($("isAccountNonLocked"));
					$(this).attr("selected", "selected");
				}
			});
			
			$("#isAccountNonExpired option").each(function() {
				if ($(this).val() == isAccountNonExpired) {
					$(this).prependTo($("isAccountNonExpired"));
					$(this).attr("selected", "selected");
				}
			});
			
			$("#isCredentialsNonExpired option").each(function() {
				if ($(this).val() == isCredentialsNonExpired) {
					$(this).prependTo($("isCredentialsNonExpired"));
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