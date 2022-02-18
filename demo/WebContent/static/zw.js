document.write("<link rel=\"stylesheet\" href=\"zw.css\" type=\"text/css\" media=\"screen\"/>"); 
document.write("<link rel=\"stylesheet\" href=\"before.css\" type=\"text/css\" media=\"screen\"/>"); 
document.write("<link rel=\"stylesheet\" href=\"phone.css\" type=\"text/css\" media=\"screen\"/>"); 
document.write("<script src=\"zw_tool.js\"></script>"); 
(function(){ 
	
	//使用原型模式创建对象
	function zw(){
	}
	
	//我们创建的每个函数都有一个 prototype（原型）属性，这个属性是一个指针，指向一个对象，而这个对象的用途是包含可以由特定类型的所有实例共享的属性和方法。
	  zw.prototype = {
	    init:function(){
	        console.log('初始化');
	    }
	  ,initial: function(object){//根据所传参数规范创建元素
		  return initial(object);
	    },createElement:function(id,html,click,style){//创建元素
	    	createElement(id,html,click,style);
	    },get:function(object){//选中元素
	    	return get(object);
	    }
	    ,ajax:function(object){//请求服务端
	    	return ajax(object);
	    },create_input:function(object){//文本框
	    	return create_input(object);
	    },create_buttom:function(object){//按钮
	    	return create_buttom(object);
	    },create_table:function(object){//表格
	    	return create_table(object);
	    },create_pagination:function(object){//分页器
	    	return create_pagination(object);
	    },create_popups:function(object){//弹出框
	    	return create_popups(object);
	    },create_select:function(object){//下拉框
	    	return create_select(object);
	    },create_checkbox:function(object){//多选框
	    	return create_checkbox(object);
	    },create_rich_text:function(object){//富文本
	    	return create_rich_text(object);
	    },create_rich_text_img:function(object){//富文本上传图片
	    	return create_rich_text_img(object);
	    },create_file_image:function(object){//文件上传file
	    	return create_file_image(object);
	    },create_file_a:function(object){//文件上传查看图片
	    	return create_file_a(object);
	    },create_file_onchange:function(object){//文件上传触发事件
	    	return create_file_onchange(object);
	    },is_phone:function(object){//是否手机端
	    	return is_phone(object);
	    },create_tree_table:function(object){//树形列表
	    	return create_tree_table(object);
	    },create_tree_table_show:function(object){//树形列表打开
	    	return create_tree_table(object);
	    },execute_component:function(object){//创建组件
	    	return execute_component(object);
	    },execute_table:function(object){//创建表格
	    	return execute_table(object);
	    },execute_remove:function(object){//删除事件
	    	return execute_remove(object);
	    },execute_insert:function(object){//新增事件
	    	return execute_insert(object);
	    },execute_modify:function(object){//修改事件
	    	return execute_modify(object);
	    },execute_submit:function(object){//提交事件
	    	return execute_submit(object);
	    },get_drelated_car_type_data:function(object){//汽车分类数据
	    	return get_drelated_car_type_data(object);
	    },get_drelated_car_site_data:function(object){//汽车地盘数据
	    	return get_drelated_car_site_data(object);
	    },get_recommend_data:function(object){//推荐位数据
	    	return get_recommend_data(object);
	    },get_engin_data:function(object){//发动机品牌
	    	return get_engin_data(object);
	    },get_gearbox_data:function(object){//地盘品牌
	    	return get_gearbox_data(object);
	    }
	    
	    
	};	
	  
	  
	  
	  function ajax(object ) {//暂时只提供id选择
		let url=  object.url;
		let parameter=object.parameter||{};
		let type=  object.type;
		let async=  object.async||false;
			//第一步 创建ajax对象
			 var xhr = new XMLHttpRequest();
			//第二步 打开要发送的地址  如果是post就把get变为post false设置同步请求
			
			if(type != "post"){
				  var str = "";
				  for(var key in parameter){
				        str+="&"+key+"="+parameter[key];
				    }
				    url += "?"+str;
			        xhr.open("get",url,async);
			     //   xhr.setRequestHeader("content-type","application/json;charset=utf-8");
			        xhr.send();
			}
			if(type == "post"){
				 xhr.open("post",url,async);
				 
				 if(Object.prototype.toString.call(parameter) === '[object FormData]'){
					 xhr.send(parameter);
				 }
				 if(Object.prototype.toString.call(parameter) !== '[object FormData]'){
					 xhr.setRequestHeader("content-type","application/json;charset=utf-8");
					 xhr.send(JSON.stringify(parameter));
				 }
			
			}
			 return JSON.parse(xhr.responseText);
			 }
	  
	  function create_input(object) {
			let id=object.id;//在那个div下面创建
			let label=object.label;//文本框前面显示的文本
			let style=object.style||"";//控件样式
			let name=object.name;//控件name
			let element=document.getElementById(id);
			let html=""
			if(label!==undefined){
			  html =html+"<lable class='input_lable'>"+label+":</lable>"
			}
			 html = html+'<input type="text" name="'+name+'" style="'+style+'"  id="'+id+'_input" name="input" class="input"  />';
			element.innerHTML=html
		}
	  
	  function create_buttom(object) {// 创建按钮
			// 定义div中创建的元素
			let id=object.id;
			let label=object.label||"";
			let style=object.style||"";
			let element=document.getElementById(id);
			let onclick=object.onclick;
			let html='<button style="'+style+'" type="button" id="'+id+'_button" class="button" onlick="">'+label+'</button>';
			element.innerHTML=html
			element.onclick=onclick;
		}
	  
	//创建表格
		function create_table(object) {
				let data=object.data;
				let cols=object.cols;
				let id=object.id;
			let element = document.getElementById(id);
			var html="";
			  html += "<table>" + "<tr  >"
			  //创建表头
			for (var i = 0; i < cols.length; i++) {
				if("remove"==cols[i].field){
					html += '<th  style="width:5rem;">删除</th>'
				}
				if("modify"==cols[i].field){
					html += '<th  style="width:5rem;">修改</th>'
				}
				if("remove"!=cols[i].field&&"modify"!=cols[i].field){
					html += "<th >" + cols[i].title + "</th>"
				}
					
			}
			  html += "</tr>"
			for (var y = 0; y < data.length; y++) {
				html += "<tr>"
				for (var u = 0; u < cols.length; u++) {
					
					if("remove"==cols[u].field){
						 html += '<td  onclick=\''+ cols[u].onclick+'("'+JSON.stringify(data[y]).replace(/\"/g,'\\"')+'")\' ><span class="box_close" ></span> </td>'
					}
					if("modify"==cols[u].field){
						html += '<td  onclick=\''+ cols[u].onclick+'("'+JSON.stringify(data[y]).replace(/\"/g,'\\"')+'")\'  style="padding-left:1rem;padding-right:1rem;"><div class="box_modify"></div></td>'
					}
					
					if("remove"!=cols[u].field&&"modify"!=cols[u].field){
						html += "<td  >" + (data[y][cols[u].field] ||"")+ "</td>"
					}
					
					
				}
				html += "</tr>"
			}
			  html+="</table> ";
			element.innerHTML = html; 
			
			}
		
		
		
		function create_pagination(object){
			if(object.content!==undefined){
				window.zw_pagin_content=object.content
			}
			if(window.pagin_content!==null){
				window.zw_pagin_content(object.current)
			}
			let id=object.id;
			let element = document.getElementById(id);
			let html="";
			let total=parseInt(object.total);//总行数
			let rows=object.rows;//每页数
			let current=object.current;//当前页
			let page=Math.floor((total+rows-1)/rows);
			let start=current-3;//起始页
			let end=current+3;//结束页
			if(current<4){//如果是小于4那么不能分页
				start=1;
				end=page>7?7:page;
			}

			if(current>=page-3){
				start=(page-6)>0?(page-6):1;
				end=page;
			}
			
			html += '<div class="center">'
			html += '<ul class="pagination">'
			//创建首页并给首页的数值赋为1
				object.current=1;
			html += '<li><a   href="#"  onclick=" zw.create_pagination(' + JSON.stringify(object).replace(/"/g, '&quot;') + ');">首页</a></li>';
		  	//创建翻上几页
			object.current=(current-1)==0?1:(current-1);
			html += '<li><a   href="#" onclick=" zw.create_pagination(' + JSON.stringify(object).replace(/"/g, '&quot;') + ');">«</a></li>';
			
			//循环创建中间的页数
			for (var i = start; i <= end; i++) {
				var cla = "";
				i == current ? cla = "class='active'" : cla = "";
				object.current=i;
				html += ' <li><a   href="#" ' + cla + '    onclick=" zw.create_pagination(' + JSON.stringify(object).replace(/"/g, '&quot;') + ');">' + i + '</a></li>';
			}
			//创建下几页
			object.current=current+1>page?page:(current+1);
			html += '<li><a  href="#" onclick=" zw.create_pagination(' + JSON.stringify(object).replace(/"/g, '&quot;') + ');">»</a></li>';
			//创建末页 和总页数
			object.current=page;
			html += '<li><a  href="#" onclick=" zw.create_pagination(' + JSON.stringify(object).replace(/"/g, '&quot;') + ');">末页</a></li>';
			html += '<li > <a  >总页数:' + page + '</a></li>'
			html += '</ul>'
				html += '</div>';
				element.innerHTML = html; 
		}
		
		
		function create_select(object) {// 创建下拉框
			// 定义div中创建的元素
				let style=object.style||"display: inline-block !important;"; //默认高宽
				let id=object.id ;
				let data=object.data;
				let event_type=object.event_type;
				let element=document.getElementById(object.id);
				let label=object.label;
				let multiple=object.multiple;
				let html="";
				if(label!==undefined){
					  html =html+"<lable class='input_lable'>"+label+":</lable>"
					}
				
				if(object.multiple!==undefined){
					 html +=' <select  id="' + id + '_select"  class="select" multiple="true" style="  width: 10.6rem;height: 4.6rem;">';
				}
				if(object.multiple===undefined){
					 html +=' <select  id="' + id + '_select"  class="select" >';
				}
					 
					for (var i = 0; i < data.length; i++) {
						html+='<option value="'+data[i].value+'">'+data[i].text+'</option>';
					}
					html+= '</select> ';
					
					element.innerHTML = html;//设置当前元素中的内容
				}
		
		function create_checkbox(object) {// 创建复选框
			let id=object.id;
			let label=object.label;
			let data=object.data;
			let element=document.getElementById(object.id);
			let html="";
			if(label!==undefined){
				  html =html+"<lable class='input_lable'>"+label+":</lable>"
				}
			for (var i = 0; i < data.length; i++) {
				html+='	<input type="checkbox" class="checkbox"  name="' + id + '_checkbox" value="'+data[i].value+'" />'+data[i].text+'';
			}
			element.innerHTML = html;//设置当前元素中的内容
		}
		
		function create_rich_text(object) {// 创建富文本
			let id=object.id+ '_rich_text';
			let label=object.label;
			let style=object.style||"";
			let data=object.data;
			let element=document.getElementById(object.id);
			let html="";
			if(label!==undefined){
				  html =html+"<lable class='input_lable' style='float:left;'>"+label+":</lable>"
				}
			html +='<a href="javascript:;" class="file">插入图片 <input id="'+id+'_file" type="file"  onchange="zw.create_rich_text_img(this)"> </a>'

			html +=' <div  id="' + id + '" class="rich_text" contenteditable="true" style="'+style+'"></div>'
			element.innerHTML = html;//设置当前元素中的内容
		}
		
		  function create_rich_text_img(source) {
			  console.log(source)
		      var file = source.files[0];
			  let id=source.id;
		      if (window.FileReader) {
		          let fr = new FileReader();
		          let send_content = document.getElementById(id.substring(0,id.length-5));
		          fr.onloadend = function (e) {
		              send_content.src = e.target.result;
		              send_content.focus();
		              document.execCommand('InsertImage', false, send_content.src );
		          };
		          fr.readAsDataURL(file);
		      }
			  }
		  
		  
		  
		  
		  
		  
			function create_file_image(object) {// 上传图片
				let id=object.id+ '_file_image';
				let element=document.getElementById(object.id);
				let label=object.label;
				let html="";
		 	if(label!==undefined){
				  html =html+"<lable class='input_lable' style='float:left;'>"+label+":</lable>"
		 		}
			html +='<a href="javascript:;" class="file">上传 <input name="'+id+'" type="file" onchange="zw.create_file_onchange(this)" multiple>  </a>'
			+'<a href="javascript:void(0);" onclick="zw.create_file_a(\''+id+'\')">查看图片</a><input type="hidden" name="id" id="'+id+'">'
				element.innerHTML = html;//设置当前元素中的内容
		  }
			
			function create_file_a(id){
				
				
				  let open = document.createElement("div");
					
				  open.id=id+"_open";
				  open.setAttribute("class","open");
				  document.body.appendChild(open);
				  open.style.top = (window.innerHeight -  open.offsetHeight) /1.5 + "px";
				  open.style.left = (window.innerWidth -  open.offsetWidth) / 2 + "px";
				  //设置元素内内容
				  open.innerHTML=''
		+'<div id="'+id+'"_open_title" class="open_title">图片查看<div id= "'+id+'_open_close"  class="close"><span ></span></div></div> '
		+''+document.getElementById(id).value;
				  console.log(document.getElementById(id+"_open_close"));
			      //关闭元素
				//窗口无法关闭，元素还没创建就开始赋予事件了。考虑用计时器
				  setTimeout(   function(e){ 
			      document.getElementById(id+"_open_close").onclick = function(e){ 
			    	  open.remove();
		}},1000 );
				  
				
			}
			
				  function create_file_onchange(source) {
					  
					  document.getElementById(source.name).value='';
					   for(var i=0;i<source.files.length;i++){
//		 	                if (!input['value'].match(/.jpg|.gif|.png|.bmp/i)){　　//判断上传文件格式
//		 	                    return alert("上传的图片格式不正确，请重新选择")　　　　　　　　
//		 	                }
			                var reader = new FileReader();
 		             reader.readAsDataURL(source.files[i]);
			                reader.onload = function(e){
			                	document.getElementById(source.name).value+='<img src="'+e.target.result+'" alt=""/>||';
			                //	document.getElementById(source.name+"_div").innerHTML+='<img src="'+e.target.result+'" alt=""/>';
			            }
			                
			        }
					  }
		
		function create_popups(object) {
			let id=object.id;
			let open_id=id+"_open";
			let html=document.getElementById(id).innerHTML;
			let htmlA=document.getElementById(id).innerHTML;
			
			if(document.getElementById(open_id)!==null){
				return null
			}
				//创建窗体元素，并给元素赋予属性
				  let open = document.createElement("div");
			
				  open.id=open_id;
			      open.setAttribute("style",object.style);
				  open.setAttribute("class","open");
				  document.body.appendChild(open);
				  open.style.top = (window.innerHeight -  open.offsetHeight) /1.5 + "px";
				  open.style.left = (window.innerWidth -  open.offsetWidth) / 2 + "px";
				  //设置元素内内容
				  open.innerHTML=''
		+'<div id="'+object.id+'_title" class="open_title">'+object.title+'<div id= "'+object.id+'_close"  class="close"><span ></span></div></div> '
		+''+html;
		document.getElementById(id).innerHTML=""
			      //关闭元素
			      document.getElementById(object.id+"_close").onclick = function(e){ 
			    	  open.remove();
			    	  document.getElementById(id).innerHTML=htmlA;
		}
			    
		}
		
		
		
		 function is_phone(object) {
			 let phone=object.phone;
			 let sUserAgent = navigator.userAgent.toLowerCase();
			 let bIsIpad = sUserAgent.match(/ipad/i) == "ipad";
			 let bIsIphoneOs = sUserAgent.match(/iphone os/i) == "iphone os";
			 let bIsMidp = sUserAgent.match(/midp/i) == "midp";
			 let bIsUc7 = sUserAgent.match(/rv:1.2.3.4/i) == "rv:1.2.3.4";
			 let bIsUc = sUserAgent.match(/ucweb/i) == "ucweb";
			 let bIsAndroid = sUserAgent.match(/android/i) == "android";
			 let bIsCE = sUserAgent.match(/windows ce/i) == "windows ce";
			 let bIsWM = sUserAgent.match(/windows mobile/i) == "windows mobile";
	    	    if (bIsIpad || bIsIphoneOs || bIsMidp || bIsUc7 || bIsUc || bIsAndroid || bIsCE || bIsWM) {
	    	    	//如果当前是手机，并且是手机页面就不跳转
	    	    	if(!phone){
	    	    		window.location.replace("phone_index.html");
	    	    	}
	    	        //跳转移动端页面
	    	        
	    	    } else {
	    	        //跳转pc端页面
	    	    	if(phone){
	    	            window.location.replace("index.html");
	    	    	}
	    	    }
	    	}
		 
			function create_tree_table(object) {
				let data=object.data;
				let cols=object.cols;
				let id=object.id;
	 		let element = document.getElementById(id);
		 	var html_title="<div style=\"float:left;width:10rem;border: 1px #e6e6e6 solid;\"><p>名称</p>";
		 	var html_content="<div style=\"float:left;width:10rem;border: 1px #e6e6e6 solid;\"><p>排序</p>";
			var html_remove="<div style=\"float:left;width:10rem;border: 1px #e6e6e6 solid;\"><p>删除</p>";
		 	var html_modify="<div style=\"float:left;width:10rem;border: 1px #e6e6e6 solid;\"><p>修改</p>";
		 	
		 	
		 	let parent_arr=[]
		 	//先找出第一级别
		 	 for (var i in data) {
		            if(data[i].parentId==="0"){
		            	parent_arr.push(data[i])
		            }
		        }
		 	//可以根据动态列创建map然后返回到map中。当前就这样
		 	
		 	//然后根据第一级别编写第二级别左边
		 	 for (var y in parent_arr) {
		 		 
		 		html_title+='<ul><a href="javascript:onclick=create_tree_table_show(\''+parent_arr[y].id+'\') ">+'+parent_arr[y].title+'</a></ul>'
		 		+'<ul name="'+parent_arr[y].id+'" class="no_circle" style="display: block;">'
		 		 for (var u in data) {
		 			 if(data[u].parentId===parent_arr[y].id){
		 				html_title+='<li>'+data[u].title+'</li>'
			            }
		 		}
		 		html_title+='</ul>'
		 		
		 		
		 	}
		 	//编写第二级别右边
		 	 for (var j in parent_arr) {
		 		html_content+='<ul><a href="javascript:onclick=create_tree_table_show(\''+parent_arr[j].id+'\') ">'+parent_arr[j].sort+'</a></ul>'
		 		+'<ul name="'+parent_arr[j].id+'" class="no_circle" style="display: block;">'
		 		 for (var k in data) {
		 			 if(data[k].parentId===parent_arr[j].id){
		 				html_content+='<li>'+data[k].sort+'</li>'
			            }
		 		}
		 		html_content+='</ul>'
		 	}
		 	
		 	
		  	//编写删除右边
		 	 for (var j in parent_arr) {
		 		 let json=JSON.stringify(parent_arr[j]).replace(/\"/g,'\\"')
 				 json=json.replace(" ",'&nbsp;')
		 		html_remove+='<ul style="cursor:pointer"  ><a onclick=remove(\''+json+'\') >删除</a></ul>'
		 		+'<ul name="'+parent_arr[j].id+'" class="no_circle" style="display: block;">'
		 		 for (var k in data) {
		 			 if(data[k].parentId===parent_arr[j].id){
		 				 let json=JSON.stringify(data[k]).replace(/\"/g,'\\"')
		 				 json=json.replace(" ",'&nbsp;')
		 				html_remove+='<li style="cursor:pointer"   onclick=remove(\''+json+'\')>删除</li>'
			            }
		 		}
		 		html_remove+='</ul>'
		 	}
		   	//编写修改右边
		 	 for (var j in parent_arr) {
		 		 let json=JSON.stringify(parent_arr[j]).replace(/\"/g,'\\"')
 				 json=json.replace(" ",'&nbsp;')
		 		 
		 		html_modify+='<ul style="cursor:pointer"  ><a onclick=modify(\''+json+'\') >修改</a></ul>'
		 		+'<ul name="'+parent_arr[j].id+'" class="no_circle" style="display: block;">'
		 		 for (var k in data) {
		 			 if(data[k].parentId===parent_arr[j].id){
		 				   json=JSON.stringify(data[k]).replace(/\"/g,'\\"')
		 				 json=json.replace(" ",'&nbsp;')
		 				html_modify+='<li style="cursor:pointer"   onclick=modify(\''+json+'\')>修改</li>'
		 				
			            }
		 		}
		 		html_modify+='</ul>'
		 	}
		 	
		 	
		 	html_title+="</div>"
		 		html_content+="</div>"
		 			html_remove+="</div>"
		 				html_modify+="</div>"
		 			
			element.innerHTML = html_title+html_content+html_remove+html_modify; 
			
			}
		 
			
			  function create_tree_table_show(object){
				  var name_arr = document.getElementsByName(object);
			        for(var i = 0; i < name_arr.length; i++) {
			        	  if(name_arr[i].style.display=='block'){
			        		  name_arr[i].style.display='none';  //触动的层如果处于显示状态，即隐藏
							  }
							  else{
							   name_arr[i].style.display='block';  //触动的层如果处于隐藏状态，即显示
							}
			      
			        }
				  
				  }
			  
			  
			  
			//执行创建组件总语句
				function execute_component(object) {
					let data=object.data;
					let div_name=object.div_name ;
					//创建组件
					for(var i=0;i<data.length;i++){
						 //判断类型
						 if(data[i].type==="input"){
							 document.getElementById(div_name).innerHTML+='<div id="'+data[i].name+'" style="overflow: auto;"></div>';
							 create_input({id:data[i].name,label:data[i].label,name:data[i].name});
						 }
						 if(data[i].type==="select"){
							 document.getElementById(div_name).innerHTML+='<div id="'+data[i].name+'" style="overflow: auto;"></div>';
							 create_select({id:data[i].name,label:data[i].label,data:data[i].data});
						 }
						 if(data[i].type==="recommend_checkbox"){
							 document.getElementById(div_name).innerHTML+='<div id="'+data[i].name+'" style="overflow: auto;"></div>';
							 create_checkbox({id:data[i].name,label:data[i].label,data:data[i].data});
						 }
						 
						 if(data[i].type==="rich_text"){
							 document.getElementById(div_name).innerHTML+='<div id="'+data[i].name+'" style="overflow: auto;"></div>';
							 create_rich_text({id:data[i].name,label:data[i].label,data:data[i].data});
						 }
						 
						 if(data[i].type==="rich_text"){
							 document.getElementById(div_name).innerHTML+='<div id="'+data[i].name+'" style="overflow: auto;"></div>';
							 create_rich_text({id:data[i].name,label:data[i].label,data:data[i].data});
						 }
						 
						 if(data[i].type==="file_image"){
							 document.getElementById(div_name).innerHTML+='<div id="'+data[i].name+'" style="overflow: auto;"></div>';
							 create_file_image({id:data[i].name,label:data[i].label,data:data[i].data});
						 }
						 
						 
					}
					//循环完了创建添加和删除按钮
					 document.getElementById(div_name).innerHTML+='<div id="'+div_name+'_submit" style="float:left;"></div>';
					 document.getElementById(div_name).innerHTML+='<div id="'+div_name+'_remove" style="float:left;"></div>';
					
				}
			  
			//执行总语句
				function execute_table(object) {
					let select_component=object.select_component;
					let table_cols=object.table_cols;
					let div_name=object.div_name ;
					let port="../total";
					let sql_type=object.sql_type;
					let sql_table=object.sql_table;
					let paramter={
							sql_type:sql_type,
					        sql_table:sql_table
					};
					//创建查询条件
					for(var i=0;i<select_component.length;i++){
						 //判断类型
						 if(select_component[i].type==="input"){
							 paramter[select_component[i].name]=document.getElementById(select_component[i].id+"_input").value
						 }
					}
					//查询
//						  var dataarr=ajax({url:port,parameter:paramter});
//				       create_table({cols:table_cols,data:dataarr.data,id:div_name});
				       
				        create_pagination({id:"pagination",total:10,rows:10,current:1,content: function(current ){  
				      	  paramter.current=(current||1)
				     	  var dataarr=ajax({url:port,parameter:paramter}); 
				         create_table({cols:table_cols,data:dataarr.data,id:div_name});
				         }
				        })
				}
			  
			  
				function	execute_remove(object){
					let object_component=object.object_component;
					// let id=JSON.parse(object).id;
					let id=object.id;
					let sql_type=object_component.sql_type;
					let sql_table=object_component.sql_table;
					let port="../total";
				     var msg = "确认删除 ？";
				        if (confirm(msg)==true){
				        	var paramter={
				        			id:id,
				        			sql_type:sql_type,
							        sql_table:sql_table
				        	}
				      	  var re=ajax({url:port,type:"post",parameter:paramter});
						}
				}
			  
			  function execute_insert(object_component){
					let insert_id=object_component.insert_id;//添加按钮id
					let div_name=object_component.div_name ;//父组件名称
					let data=object_component.data;//组件数据
					create_buttom({id:insert_id,label:"新增",onclick: function( ){   	
						   create_popups({id:div_name,title:"信息维护",style:"width:55rem;height:50rem;"});
						    create_buttom({id: div_name+"_submit" ,label:"确定",style:"margin-left: 3rem;",onclick: function( ){  
							synchronous_submit();
							}});
						 
						   create_buttom({id:div_name+"_remove",label:"取消",style:"margin-left: 2rem;",onclick: function( ){  
						   document.getElementById(div_name+"_close").onclick();
						    }});
						   
							//清空数据
							document.getElementById(div_name+"_id").value=null
							for(var i=0;i<data.length;i++){
								 //判断类型
								 if(data[i].type==="input"){
									 document.getElementById(data[i].name+"_input").value=null
								 }
								 if(data[i].type==="select"){
									 document.getElementById(data[i].name+"_select").value=null;
								 }
								 if(data[i].type==="recommend_checkbox"){
											  document.getElementsByName(data[i].name+"_checkbox")[0].checked=false;
									    	document.getElementsByName(data[i].name+"_checkbox")[1].checked=false;
								 }
								 
								 if(data[i].type==="rich_text"){
									 document.getElementById(data[i].name+"_rich_text").value=null;
						          }
								 if(data[i].type==="file_image"){
									 document.getElementById(data[i].name+"_file_image").value=null;
						          }
								 
								 
								 
							}
						}});
					
				}
			  
			  
			  
				function	execute_modify(object){
					let object_component=object.object_component;
					let insert_id=object_component.insert_id;//添加按钮id
					let div_name=object_component.div_name ;//父组件名称
					let data=object_component.data;//组件数据
					
					 create_popups({id:div_name,title:"信息维护",style:"width:55rem;height:50rem;"});
				 	   	//弹出框确定按钮
				 	    create_buttom({id: div_name+"_submit" ,label:"确定",style:"margin-left: 3rem;",onclick: function( ){  
							synchronous_submit();
							}});
						 
						   create_buttom({id:div_name+"_remove",label:"取消",style:"margin-left: 2rem;",onclick: function( ){  
						   document.getElementById(div_name+"_close").onclick();
						    }});
						 
				 	 //   let return_data=JSON.parse(object)
						   let return_data=object
				 	    document.getElementById(div_name+"_id").value=return_data.id
				 		for(var i=0;i<data.length;i++){
							 //判断类型
							 if(data[i].type==="input"){
								 document.getElementById(data[i].name+"_input").value=return_data[data[i].name];
							 }
							 
							 if(data[i].type==="select"){
								 document.getElementById(data[i].name+"_select").value=return_data[data[i].name];
							 }
							 if(data[i].type==="recommend_checkbox"){
								  if(return_data.whether_home==="是否首页"){
									  document.getElementsByName(data[i].name+"_checkbox")[0].checked=true;
								  }
							    if(return_data.whether_ad==="是否广告"){
							    	document.getElementsByName(data[i].name+"_checkbox")[1].checked=true;
							    }
							 }
							 
							 if(data[i].type==="rich_text"){
								 document.getElementById(data[i].name+"_rich_text").innerHTML=decodeURIComponent(return_data[data[i].name]);
							 }
							 
							 if(data[i].type==="file_image"){
								 document.getElementById(data[i].name+"_file_image").value=decodeURIComponent(return_data[data[i].name]);
							 }
							 
							 
						}
				}
			  
			  function	execute_submit(object_component){
					let insert_id=object_component.insert_id;//添加按钮id
					let div_name=object_component.div_name ;//父组件名称
					let data=object_component.data;//组件数据
					let port="../total";
					let sql_type=object_component.sql_type;
					let sql_table=object_component.sql_table;
					let customize=object_component.customize||[];
					let parameter={
							id:document.getElementById(div_name+"_id").value,
							sql_type:sql_type,
					        sql_table:sql_table
					};
					//自定义的查询条件
					for(var j=0;j<customize.length;j++){
						 parameter[customize[j].name]= customize[j].value;
					}
					
					
					//创建查询条件
					for(var i=0;i<data.length;i++){
						 //判断类型
						 if(data[i].type==="input"){
							 parameter[data[i].name]=document.getElementById(data[i].name+"_input").value
						 }
						 if(data[i].type==="select"){
							 parameter[data[i].name]=document.getElementById(data[i].name+"_select").value
						 }
						 if(data[i].type==="recommend_checkbox"){
							 parameter.whether_home="否";
							  parameter.whether_ad="否";
								
							   if( document.getElementsByName(data[i].name+"_checkbox")[0].checked){
								   parameter.whether_home="是否首页"
								 }
								  if( document.getElementsByName(data[i].name+"_checkbox")[1].checked){
									  parameter.whether_ad="是否广告"
								  }
						 }
						 
						 if(data[i].type==="rich_text"){
							 parameter[data[i].name]=encodeURIComponent(document.getElementById(data[i].name+"_rich_text").innerHTML);
						 }
						 
						 if(data[i].type==="file_image"){
							 parameter[data[i].name]=encodeURIComponent(document.getElementById(data[i].name+"_file_image").value);
						 }
						 
						 
					}
					  var dataarr=ajax({url:port,type:"post",parameter:parameter});
					alert(dataarr.message)
					document.getElementById(div_name+"_close").onclick();//关闭弹窗
				}
			  
			  
			  function	get_drelated_car_type_data(object){
				  let port="../total";
					let parameter={
							sql_type:"select",
					        sql_table:"type"
					};
				  var ajax_data=ajax({url:port,type:"post",parameter:parameter}).data;
				  var return_data=[]
				  for(var i=0;i<ajax_data.length;i++){
				  	let arr={
				  			value:ajax_data[i].title,
				  			text:ajax_data[i].title
				  			}
				  	return_data[i]=arr;
				  }

				  return return_data;
				}
			  
			  function	get_drelated_car_site_data(object){
				  let port="../total";
					let parameter={
							sql_type:"select",
					        sql_table:"site"
					};
				  let ajax_data=ajax({url:port,type:"post",parameter:parameter}).data;
				  
				  
				 let return_data=[]
				 for(var i=0;i<ajax_data.length;i++){
				 	let arr={
				 			value:ajax_data[i].title,
				 			text:ajax_data[i].title
				 			}
				 	return_data[i]=arr;
				 }
				  return return_data;
				}
			  
			  function	get_recommend_data(object){
				  return [ {value:"是否首页",text:"是否首页"},  {value:"是否广告",text:"是否广告"}];
				}
			  
			  
			  
			  function	get_engin_data(object){
				  let port="../total";
					let parameter={
							sql_type:"select",
					        sql_table:"engin_brand"
					};
				  let ajax_data=ajax({url:port,type:"post",parameter:parameter}).data;
				  
				  
				 let return_data=[]
				 for(var i=0;i<ajax_data.length;i++){
				 	let arr={
				 			value:ajax_data[i].name,
				 			text:ajax_data[i].name
				 			}
				 	return_data[i]=arr;
				 }
				  return return_data;
				}
			  
			  
			  function	get_gearbox_data(object){
				  let port="../total";
					let parameter={
							sql_type:"select",
					        sql_table:"gearbox_brand"
					};
				  let ajax_data=ajax({url:port,type:"post",parameter:parameter}).data;
				  
				  
				 let return_data=[]
				 for(var i=0;i<ajax_data.length;i++){
				 	let arr={
				 			value:ajax_data[i].name,
				 			text:ajax_data[i].name
				 			}
				 	return_data[i]=arr;
				 }
				  return return_data;
				}
			  
			  
		
	//将对象保存进全局变量中
    window["zw"] =new  zw();

})()
