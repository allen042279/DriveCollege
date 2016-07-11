
$(document).ready(function(){
	
	$("#data_div").hide();
	$("#hint_div").show();
	
	$("#paging").myPagination({
		
		currPage : 1,
		pageCount : 5, 
		cssStyle : 'scott',
	    
		ajax : {
			
			on : true, // 开启状态
			
			type: 'POST',  //配置请求类型,提供二种选项."POST","GET".
			url : js_path + '/subscribe/getPage',  // 访问服务器地址
			dataType : 'json', // 返回类型 也可以是 html 和 xml
			
			param : {   // 参数列表，其中 on 必须开启，page 参数必须存在，其他的都是自定义参数，如果是多条件查询，可以序列化表单，然后增加 page 参数
				on : true,
				page : 1,
				pageSize:15
			},
			
			ajaxStart:function(){
		        ZENG.msgbox.show(" 正在加载中，请稍后...", 6, 10000);
			},
			
			callback :  function(result) {
				
	            ZENG.msgbox.hide(); //隐藏加载提示
	            
	            var insetViewData = ""; // 视图数据
	            
				if((result!=undefined || result!=null ) && result!=="") {
					
					$.each(result, function(index,items) {
						
						insetViewData += createTR(items);
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


function createTR(item) {
 
	var tr = "<tr>";
	tr += "<td style='display:none'>" + item.id + "</td>";
	tr += "<td>" + item.title + "</td>";
	tr += "<td>" + item.description + "</td>";
	tr += "<td><img src='" + base_path + item.picPath + "' width='90px' height='80px'></td>";
	tr += "<td>" + item.url + "</td>"; 
//	tr += "<td>" + item.contentUrl + "</td>"; 
	
	if (item.top == false) {
		tr += "<td>否</td>";
	} else {
		tr += "<td>是</td>";
	}
	
	if (item.used === false) {
		tr += "<td>否</td>";
	} else {
		tr += "<td>是</td>";
	}
	
	tr += "<td>" + item.showOrder + "</td>";
	
	tr += "<td><a  href='" + js_path + "/admin/views/amendSubscribeMenu.jsp?id=" + item.id + "'>" + "<img src='" + base_path + "/admin/img/modify.png'>"
			+ "</a></td>";
	tr += "<td><a  href='" + js_path + "/subscribe/delete/" + item.id
			+ "' onclick='return del();'>" + "<img src='" + base_path + "/admin/img/delete.png'>" + "</a></td>";
	tr += "</tr>";
	return tr;
}

// 提示用户确认删除
function del() {
	return window.confirm("你确认要删除吗?");
}

//// 显示所有
//function show_all() {
//	window.open(js_path + "/admin/staff/Staff.jsp", "_self");
//}
//
//// str.result.name
//function getOrganizationName(val) {
//	var value = "";
//	$.getJSON(js_path + "/OrganizationServlet?type=object&id=" + val, function(str) {
//		alert(str.result.name);
//		value += str.result.name;
//		alert(value);
//	});
//	return value + "";
//}
//
//function getDepartmentName(val) {
//	$.getJSON(js_path + "/DepartmentServlet?type=object&id=" + val, function(str) {
//		alert(str.result.name);
//	});
//}
//
//function getPositionName(val) {
//	$.getJSON(js_path + "/PositionServlet?type=object&id=" + val, function(str) {
//		alert(str.result.name);
//	});
//}
