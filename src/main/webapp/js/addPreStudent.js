
function chkName(js_path, flag){
	
	var name = $("#name").val();
	
	if(name==""){
		
		alert("姓名不能为空！");
		
		if(name==""){
			$( "#name").effect( "bounce", "slow" );
		}
		
	}
//	else{
//		
//		$.ajax({
//			
//			cache : false, 
//			
//			type : 'POST',
//			
//			url : js_path + '/preStudent/isExit',
//			
//			async: true,
//			
//			data : {
//				name : name
//			},
//			
//			dataType : 'json',
//			
//			success : function(returnData){
//				
//				var result = returnData.result;
//				
//				if (result != "") {
//					
//					if(result=="1") {
//						alert( "您填写的 团队名称 已经被使用，请换个试试！");
//						$( "#fname").effect( "bounce", "slow" );
//						$( "#fname").focus();
//					}else{
//						if(flag=="1"){
//							return document.form1.submit();
//						}
//					}
//				}
//				
//			}
//			
//		});
//	}
	
}

function addPreStudentSubmit(js_path){

	var fname = $("#name").val();
	var mobile = $("#mobile").val();
	var intent = $("#intent").val();
	
	if(fname=="" || mobile=="" || intent==""){
		
		alert("姓名、联系电话、提升意向 都不能为空！");
		
		if(fname==""){
			$( "#name").effect( "bounce", "slow" );
		}
		if(mobile==""){
			$( "#mobile").effect( "bounce", "slow" );
		}
		if(intent==""){
			$( "#intent").effect( "bounce", "slow" );
		}		
	}else{
		return document.form1.submit();
	}
	
//	chkName(js_path, '1');
		
	
}

