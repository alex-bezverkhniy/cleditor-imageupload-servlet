<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<link rel="stylesheet" media="screen" href="css/bootstrap.css" />
<link rel="stylesheet" href="js/cleditor/jquery.cleditor.css"
	type="text/css">
<title>Example usage cleditor-imageupload-plugin</title>
<style type="text/css">
body {
	padding: 30px;
}
</style>
<script type="text/javascript" src="js/jquery-1.8.2.js"></script>
<script type="text/javascript" src="js/cleditor/jquery.cleditor.js"></script>
<script type="text/javascript" src="cleditor-imageupload-plugin.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		// Url's for plugn
		$.cleditor.buttons.image.uploadUrl = '/imageuploader';
		$.cleditor.buttons.image.imageListUrl = '/imagelist';

		$.cleditor.defaultOptions.width = 600;
		$.cleditor.defaultOptions.height = 300;
		$("#input").cleditor();
	});
</script>

</head>
<body>
	<h2>Servlet uploader for cleditor-imageupload-plugin</h2>
	<div class="container-narrow">
		<textarea id="input" name="input"></textarea>
	</div>
	<script src="js/bootstrap.min.js"></script>

	<form action="/imageuploader" method="post"
		enctype="multipart/form-data">
		<input type="file" name="file" /> 
		<input type="submit" value="upload" />
	</form>
</body>
</html>