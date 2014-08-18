<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.io.*"%>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>


<style type="text/css">
#filedrag {
	display: none;
	font-weight: bold;
	text-align: center;
	height:150px;
	width:300px;
	color: red;
	border: 2px dashed #555;
	border-radius: 7px;
}

#filedrag.hover {
	color: #f00;
	border-color: #f00;
	border-style: solid;
	box-shadow: inset 0 3px 4px #888;
}
</style>
</head>
<body>
	<form id="upload" action="UploadServlet" enctype="multipart/form-data"
		method="post" onsubmit="return upLoad();">
		<p>
			<label for="fileselect">文件:</label><input multiple="true"
				type="file" id="fileselect" name="fileselect[]" />
		<div id="filedrag">或者将文件拖拽到这里</div>
		<div id="submitbutton">
			<input type="submit" value="提交">
		</div>
	</form>
	<div id="messages">		
	</div>
	
	<script type="text/javascript">
		var upfiles = new Array();
		// getElementById
		function getElementById(id) {
			return document.getElementById(id);
		}

		// output information
		function Output(msg) {
			var m = getElementById("messages");
			m.innerHTML = msg + m.innerHTML;
		}

		// file drag hover
		function FileDragHover(e) {
			var t1=e.stopPropagation();
			var t2=e.preventDefault();
			e.target.className = (e.type == "dragover" ? "hover" : "");
			
			
		}

		// file selection
		function FileSelectHandler(e) {

			// cancel event and hover styling
			FileDragHover(e);

			// fetch FileList object
			var files = e.target.files || e.dataTransfer.files;

			// process all File objects
			for ( var i = 0, f; f = files[i]; i++) {
				ParseFile(f);
				upfiles.push(f);
			}
				

		}

		// output file information
		function ParseFile(file) {

			Output("<p>文件信息: <strong>" + file.name
					+ "</strong> 类型: <strong>" + file.type
					+ "</strong> 大小: <strong>" + file.size
					+ "</strong> bytes</p>");
		}
		function upLoad() {
			if (upfiles[0]) {
				var xhr = new XMLHttpRequest();   //Ajax异步传输数据
				xhr.open("POST", "UploadServlet", true);
				var formData = new FormData();
				for ( var i = 0, f; f = upfiles[i]; i++) {
					formData.append('myfile', f);
				}
				xhr.send(formData);
				xhr.onreadystatechange=function(e){
					history.go(0);  //由于这个页面还要显示可以下载的文件，所以需要刷新下页面
				}				
				return false;
			}
		}
		// initialize
		function Init() {

			var fileselect = getElementById("fileselect"), filedrag = getElementById("filedrag"), submitbutton = getElementById("submitbutton");

			// file select
			fileselect.addEventListener("change", FileSelectHandler, false);

			// is XHR2 available?
			var xhr = new XMLHttpRequest();
			if (xhr.upload) {

				// file drop
				filedrag.addEventListener("dragover", FileDragHover, false);
				filedrag.addEventListener("dragleave", FileDragHover, false);
				filedrag.addEventListener("drop", FileSelectHandler, false);
				filedrag.style.display = "block";
				// remove submit button
				//submitbutton.style.display = "none";
			}

		}

		// call initialization file
		if (window.File && window.FileList && window.FileReader) {
			Init();
		}
	</script>
</body>
</html>
