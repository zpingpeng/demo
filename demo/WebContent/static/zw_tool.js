(function(){ 
	
	//使用原型模式创建对象
	function zw_tool(){
	}
	
	//我们创建的每个函数都有一个 prototype（原型）属性，这个属性是一个指针，指向一个对象，而这个对象的用途是包含可以由特定类型的所有实例共享的属性和方法。
	zw_tool.prototype = {
	    init:function(){
	        console.log('初始化');
	    }
	  ,initial: function(object){//根据所传参数规范创建元素
		  return initial(object);
	    }
	    ,get_car_type:function(){//请求服务端
	    	return get_car_type();
	    } ,get_recommend_checkbox_data:function(){//请求服务端
	    	return get_recommend_checkbox_data();
	    },get_video_type:function(){//请求服务端
	    	return get_video_type();
	    }
	    
	};	
	  
	  
	  function get_car_type() {
		  var data_related_car_type=zw.ajax({url:"../type_query",parameter:{}}).data;
		  var data_related_car_type_dataarr=[]
		  for(var i=0;i<data_related_car_type.length;i++){
		  	let arr={
		  			value:data_related_car_type[i].title,
		  			text:data_related_car_type[i].title
		  			}
		  	data_related_car_type_dataarr[i]=arr;
		  }
		  return data_related_car_type_dataarr;
		}
	  
	  function get_recommend_checkbox_data() {
		  var checkbox_data=[ {value:"是否首页",text:"是否首页"},  {value:"是否广告",text:"是否广告"}];
		  return checkbox_data;
		}  
	  function get_video_type() {
		  var video_type_data=[ {value:"市政环卫类视频",text:"市政环卫类视频"},  {value:"垃圾转运类视频",text:"垃圾转运类视频"}];
		  return video_type_data;
		}   
	  
		
	//将对象保存进全局变量中
    window["zw_tool"] =new  zw_tool();

})()
