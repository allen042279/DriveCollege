
$(document).ready(function(){
	
	$("#data_div").hide();
	$("#hint_div").show();
	
	$("#paging").myPagination({
		
		currPage : 1,
		pageCount : 5, 
		cssStyle : 'scott',
	    
		ajax : {
			
			on : true, // 开启状态
			
			type: 'POST',
			url : js_path + '/preStudent/getPage',  // 访问服务器地址
			dataType : 'json', // 返回类型 也可以是 html 和 xml
			
			param : {   // 参数列表，其中 on 必须开启，page 参数必须存在，其他的都是自定义参数，如果是多条件查询，可以序列化表单，然后增加 page 参数
				on : true,
				page : 1,
				pageSize:15
			},
			
			ajaxStart: function(){
		        ZENG.msgbox.show(" 正在加载中，请稍后...", 6, 10000);
			},
			
			callback :  function(result) {

				ZENG.msgbox.hide();  //隐藏加载提示
				
				var insetViewData = ""; // 视图数据

				if((result!=undefined || result!=null ) && result!=="") {
					
					$.each(result, function(index,item) {
						
						insetViewData += createTR(index, item);
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
		}
		
	});
});


function createTR(index, item) {
	var tr = "<tr>";
	tr += "<td style='display:none'>" + item.id + "</td>";
	tr += "<td>" + index + "</td>";
	tr += "<td>" + item.username + "</td>";
	tr += "<td>" + item.sexType + "</td>";
	tr += "<td>" + item.mobile + "</td>";
	tr += "<td>" + item.intent + "</td>";
	
	var date = new Date();
	date.setTime(item.applyDate);
	var month = date.getMonth() + 1;
	var dateStr = date.getFullYear() + "/" + month + "/" + date.getDate() + " " + date.getHours() + ":" + date.getMinutes();
	tr += "<td>" + dateStr + "</td>";
	
//	tr += "<td><a  href='" + js_path + "/admin/views/updatePrestudent.jsp?id=" + item.id + "'>" + "<img src='" + base_path + "admin/img/ok.png'>"
//		    + "</a></td>";
	
	tr += "</tr>";
	return tr;
}


