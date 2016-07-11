$(document).ready(function() {

	$("#urlContent").height = 300;
	
	$.ajax({
	    	
			cache : false, 
			
			type : 'POST',
			
			url : js_path + '/subscribe/getAll',
			
			async: false,
			
			dataType : 'json',
			
			success : function(result){
				
				if((result!=undefined || result!=null ) && result!=="") {
					
					$.each(result, function(index, items) {
						if(x==items.showOrder){
							$("#headline").text(items.title);
							$("#urlContent").load(items.contentUrl);
//							$("#mainFrame").attr("src", items.contentUrl);
						}
					});
				}
			},
			complete: function (XMLHttpRequest, textStatus) {
				//完成处理
				getContent();
           },
           error: function (e) {
               //异常处理
           }
			
		});
	    
});

function getContent(){
	
	$.ajax({
    	
		cache : false, 
		
		type : 'POST',
		
		url : js_path + '/user/getUrlContent',
		
		async: false,
		
		dataType : 'html',
		
		data:{
			urlString: url
		},
		success : function(result){
//			$("#mainFrame").srcdoc(result);
			$("#urlContent").innerHtml(result);
		},
		complete: function (XMLHttpRequest, textStatus) {

		},
        error: function (e) {
            //异常处理
        }
		
	});
}
////$("#mainFrame").load(function(){
////var mainheight = $(this).contents().find("body").height();
////$(this).height(mainheight);
////}); 
//
////$(document).ready(function() {
////	
////	    $.ajax({
////	    	
////			cache : false, 
////			
////			type : 'POST',
////			
////			url : js_path + '/subscribe/getAll',
////			
////			async: true,
////			
////			dataType : 'json',
////			
////			success : function(result){
////				
////				if((result!=undefined || result!=null ) && result!=="") {
////					
////					$.each(result, function(index, items) {
////						if(x==items.showOrder){
////							$("#headline").text(items.title);
////							$("#mainFrame").attr("src", items.contentUrl);
//////							$("#mainFrame").attachEvent("onReadyStateChange", function(){
//////								var bHeight = $("#mainFrame").contentWindow.document.body.scrollHeight;
//////					            var dHeight = $("#mainFrame").contentWindow.document.documentElement.scrollHeight;
//////					            var height = Math.max(bHeight, dHeight);
//////					            $("#mainFrame").height = height;
//////							});
//////							$("#mainFrame").html("<iframe id='iFrame' frameBorder=0 scrolling=no width='100%;' height='100' marginheight='0'" + 
//////									" marginwidth='0' src=&quot;" + items.contentUrl +"items.contentUrl> </iframe>");
////						}
////					});
////					
////				}
////			},
////			complete: function (XMLHttpRequest, textStatus) {
////				//完成处理
//////				$("#mainFrame").onload=function startInit() {
//////				    isOpera = browserVersion.indexOf("OPERA") > -1 ? true : false;
//////				    isFireFox = browserVersion.indexOf("FIREFOX") > -1 ? true : false;
//////				    isChrome = browserVersion.indexOf("CHROME") > -1 ? true : false;
//////				    isSafari = browserVersion.indexOf("SAFARI") > -1 ? true : false;
//////				    if (!!window.ActiveXObject || "ActiveXObject" in window)
//////				        isIE = true;
//////				    reinitIframe("mainFrame", 10);
//////				    if (iframeTime != null)
//////				        clearInterval(iframeTime);
//////				    newHeight += 10;
//////				    iframeTime = window.setInterval("reinitIframe('mainFrame'," + newHeight + ")", 10);
//////				};
//////				startInit("mainFrame", 10);
////				reSetIframeHeight();
////            },
////            error: function (e) {
////                //异常处理
////            }
////			
////		});
////  	});
//	    
////var iframeTime;
////
//var iframeLoaded = function (frame) {
//    if (frame.attr("src").length > 0) {
//        if (!frame.document.readyState || frame.document.readyState == "complete") {
//            var bHeight = frame.contentWindow.document.body.scrollHeight;
//            var dHeight = frame.contentWindow.document.documentElement.scrollHeight;
//            var height = Math.max(bHeight, dHeight);
//            frame.height = height;
////            clearInterval(iframeTime);
//        }
//    }
//};
////分页时重新设置 iframe 高度 ； 修改后：iframe.name = iframe.id
//var reSetIframeHeight = function(){
//    try {
//        var oIframe = $("#mainFrame");
//        oIframe.height = 1000;
//        iframeLoaded(oIframe);
////        if (iframeTime != null)
////          clearInterval(iframeTime);
////      iframeTime = setInterval("iframeLoaded('" + iframeId + "')", 100);
//      
//    }  catch (err) {
//        try {
//        	oIframe.height = 1000;
//          } catch (err2) { }
//    }
//};
//
////var sendcount = 0;
////var completecount = 0;
////// 添加ajax全局事件处理。
////
////$(document).ajaxComplete(function(e, xhr, opts) {
////	reSetIframeHeight();
////});
//	
//// 高度自适应
////var iframeHeight = function() {    
////    var hash = window.location.hash.slice(1), h;
////    if (hash && /height=/.test(hash)) {
////        h = hash.replace("height=", "");
////        iframe.height = h;
////    }
////    setTimeout(iframeHeight, 200);
////};
//
//
//
////var browserVersion = window.navigator.userAgent.toUpperCase();
////var isOpera = false;
////var isFireFox = false;
////var isChrome = false;
////var isSafari = false;
////var isIE = false;
////var iframeTime;
////var newHeight=10;
////
////function reinitIframe(iframeId, minHeight) {
////    try {
////        var iframe = document.getElementById(iframeId);
////        var bHeight = 0;
////        if (isChrome == false && isSafari == false)
////            bHeight = iframe.contentWindow.document.body.scrollHeight;
////
////        var dHeight = 0;
////        if (isFireFox == true)
////            dHeight = iframe.contentWindow.document.documentElement.offsetHeight + 2;
////        else if (isIE == false && isOpera == false)
////            dHeight = iframe.contentWindow.document.documentElement.scrollHeight;
////        else
////            bHeight += 3;
////        var height = Math.max(bHeight, dHeight);
////        if (height < minHeight) height = minHeight;
////        iframe.style.height = height + "px";
////        newHeight += 10;
////    } catch (ex) { }
////}
//
////function startInit(iframeId, height) {
////    isOpera = browserVersion.indexOf("OPERA") > -1 ? true : false;
////    isFireFox = browserVersion.indexOf("FIREFOX") > -1 ? true : false;
////    isChrome = browserVersion.indexOf("CHROME") > -1 ? true : false;
////    isSafari = browserVersion.indexOf("SAFARI") > -1 ? true : false;
////    if (!!window.ActiveXObject || "ActiveXObject" in window)
////        isIE = true;
////    reinitIframe(iframeId, height);
////    if (iframeTime != null)
////        clearInterval(iframeTime);
////    newHeight += 10;
////    iframeTime = window.setInterval("reinitIframe('" + iframeId + "'," + newHeight + ")", 10);
////}
//
////function reinitIframe(){  
////var iframe = document.getElementById("iframepage");  
////try{  
////    var bHeight = iframe.contentWindow.document.body.scrollHeight;  
////    var dHeight = iframe.contentWindow.document.documentElement.scrollHeight;  
////    var height = Math.max(bHeight, dHeight);  
////    iframe.height = height;  
////}catch (ex){}  
////}  
////  
////var timer1 = window.setInterval("reinitIframe()", 500); //定时开始  
////  
////function reinitIframeEND(){  
////var iframe = document.getElementById("iframepage");  
////try{  
////    var bHeight = iframe.contentWindow.document.body.scrollHeight;  
////    var dHeight = iframe.contentWindow.document.documentElement.scrollHeight;  
////    var height = Math.max(bHeight, dHeight);  
////    iframe.height = height;  
////}catch (ex){}  
////// 停止定时  
////window.clearInterval(timer1);  
////  
////}  
//
//
////function iFrameHeight() {   
////var ifm= document.getElementById("mainFrame");   
////var subWeb = document.frames ? document.frames["mainFrame"].document : ifm.contentDocument;   
////if(ifm != null && subWeb != null) {
////   ifm.height = subWeb.body.scrollHeight;
////   ifm.width = subWeb.body.scrollWidth;
////}   
////}