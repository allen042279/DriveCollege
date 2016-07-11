$(document).ready(function() {

			$.ajax({

				cache : false, 
				
				type : 'POST',
				
				url : js_path + '/menu/getParentMenu',
				
				async: true,
				
				dataType : 'json',
				
				success : function(returnData){
					
					var result = returnData;
					
					var provincecount = "<option  value='-1'>无 </option>";
					
					if (result != "") {
						
						$.each(result, function(index,item) {
							provincecount += "<option  value=" + item.id + ">"	+ item.name + "</option>";
						});
					}
					
					$(provincecount).appendTo($("#parentId"));
				}
				
			});

			

		});

function isNum(item){
	if(isNaN(item.value)){
		item.value = "";
	}
}