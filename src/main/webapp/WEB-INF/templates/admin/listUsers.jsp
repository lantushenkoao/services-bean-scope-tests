<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
	<link rel="stylesheet" href='<c:url value="/css/base.css" />'/>
	<link rel="stylesheet" href="<c:url value="/js/libs/jquery/jquery-ui.min.css"/>"/>
	<link rel="stylesheet" href="<c:url value="/js/libs/jqgrid/css/ui.jqgrid.css"/>"/>
	<style type="text/css">
	#usersTableWrapper{
		width: 50%;
	}
	</style>
	
	<script charset="utf-8" type="text/javascript" src="<c:url value="/js/libs/jquery/jquery.js"/>"></script>
	<script charset="utf-8" type="text/javascript" src="<c:url value="/js/libs/jquery/jquery-ui.min.js"/>"></script>
	<script charset="utf-8" type="text/javascript" src="<c:url value="/js/libs/jqgrid/js/jquery.jqGrid.min.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/js/libs/jqgrid/js/i18n/grid.locale-en.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/js/loading.js"/>"></script>
	
	<script type="text/javascript">
	
	loading = new AjaxLoadingBar();
	
	function deleteUser(userId){
		if(confirm("Are you sure that you want to delete this user?")){
			jQuery.ajax({
				type:'POST',
				url: 'users/delete',
				data:{
					'userId' : userId,
					"${_csrf.parameterName}": "${_csrf.token}"
				},
				success: function(data){
					$("#usersTable").jqGrid('setGridParam',{datatype:'json'}).trigger('reloadGrid');
				}
			});
		}
	}
	
	$(document).ready(function(){
		
		loading.init('Loading...', 'Unknown error');
		$("#usersTable").jqGrid({
			datatype: 'json',
			loadonce: true,
			url: 'ajax/listUsersJSON',
	        autoencode: true,
	        pager: 'usersTablePager',
	        rowList: [10,20,50,100],
	        hoverrows:false,
	        multiselect: false,
	        autowidth: true,
	        scrollOffset: 20,
	        rowNum: 20,
	        viewrecords: true,
		   	colNames:['Id'
		   	          ,'Login'
		   	          ,'Full Name'
		   	          ,''
		   	          ],
		   	colModel:[	   	          
		   		{name:'id',index:'id',  searchtype:"string", searchoptions:{clearSearch:false}, width:"20", formatter:function(cellValue, options, rowObject){
		   			return '<a href="users/edit?userId='+rowObject.id+'">' + cellValue + '</a>'
		   		}},
		   		{name:'login', searchtype:"string", width:"50", searchoptions:{clearSearch:false}},
		   		{name:'fullName', searchtype:"string", width:"50", searchoptions:{clearSearch:false}},
		   		{name:'delete', index:'delete', search:false, width:"50", formatter:function (cellValue, options, rowObject){
	   				return '<a href="users/edit?userId='+rowObject.id+'">Edit</a>, <a href="javascript:void(0);" onclick="deleteUser('+rowObject.id+');void(0);">Delete</a>';}
	   			}
		   	],
		   	beforeRequest: function(){
		   		loading.showAjaxLoadingMessage();
		   	},
		   	gridComplete: function() {
		   		loading.hideAjaxLoadingMessage();
		   	}
		});
	});
	</script>
</head>
<body>
<jsp:include page="../includes/frame_header.jsp"/>
<h1>Manage Users page</h1>
<p><a href='<c:url value="/admin/addUser"/>' class="button">Add User</a></p>
<div id="usersTableWrapper">
	<table id='usersTable'></table>
	<div id='usersTablePager'></div>
</div>
<jsp:include page="../includes/frame_footer.jsp"/>
</body>
</html>