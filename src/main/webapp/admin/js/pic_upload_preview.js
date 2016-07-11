//图片预览
function previewImage(file) {
	
	var preview = document.getElementById('preview');  //$('#preview');
	
	if (file.files && file.files[0]) {
		
		preview.innerHTML = "<img id='imghead'>";
		var img = document.getElementById('imghead');  // $('#imghead');
		img.onload = function() {
			img.width = 100;
			img.height = 100;
			img.style.marginLeft = 0 + 'px';
			img.style.marginTop = 0 + 'px';
		};
		var reader = new FileReader();
		reader.onload = function(evt) {
			img.src = evt.target.result;
		};
		reader.readAsDataURL(file.files[0]);
		
	} else {
		
		var sFilter = 'filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src=\"';
		file.select();
		var src = document.selection.createRange().text;
		preview.innerHTML = "<img id='imghead'>";
		var img = document.getElementById('imghead'); //$('#imghead');
		img.filters.item('DXImageTransform.Microsoft.AlphaImageLoader').src = src;
		
		preview.innerHTML = "<div id='divhead' style=width:100px;height:100px;margin-top:0px;margin-left:0px;" + sFilter + src + "\"></div>";
	}
	
//	preview.className = "show";
//	$("#imghead").attr("style", "display:block;");
//	$("#imghead1").attr("style", "display:none;");
}

function isNumber(item){

	 var re =  /^[1-9]+[0-9]*]*$/;   //判断正整数 /^[1-9]+[0-9]*]*$/  
	 var nubmer = item.value;
	    
     if (!re.test(nubmer)) {
        alert("请输入数字");
        item.value = "";
        return false;
     }

}

