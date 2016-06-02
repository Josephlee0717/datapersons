co.datapersons.data = {
		
};

co.datapersons.data.userCenterOption ={
		url : co.datapersons.url,
	    dataType : 'json',
	    method: 'GET',
	    autoload: true,
	    striped: true,
	    resizable: false,
	    singleSelect: true,
	    colModel : [ {	    	
	    	display : 'UID',
	        name : 'userid',	        
	        sortable : true,
	        align : 'left'
		}, {
			display : '注册时间',
			name : 'registertime',
			
			sortable : true,
			align : 'left'
	    }, {
	    	display : '名字',
	    	name : 'tag',
	    	
	    	sortable : true,
	    	align : 'left'
		}, {
			display : '手机号码',
			name : 'priority',
			
			sortable : true,
			align : 'right'
		}, {
			display : '身份证号',
			name : 'priority',
			
			sortable : true,
			align : 'right'
		} , {
			display : '消费总额',
			name : 'priority',
			
			sortable : true,
			align : 'right'
		}, {
			display : '推荐人数',
			name : 'priority',
			
			sortable : true,
			align : 'right'
		}], 
		
	    width : '100%',
	    height : 650	
};