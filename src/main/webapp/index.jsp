<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>员工列表</title>

<!--用java 获取当前资源路径,App_PATH的值相当于 http://localhost:3306/crud-->
<%
	pageContext.setAttribute("APP_PATH",request.getContextPath());
%>

<!-- web路径
不以/开始的相对路径，找资源，以当前资源的路径为基准，经常容易出问题。
以/开始的相对路径，找资源，以服务器的路径为标准(http://localhost:3306)需要加上项目名crud
http://localhost:3306/crud
 -->
<script type="text/javascript" src="${APP_PATH}/static/js/jquery-1.11.0.min.js"></script>
<link href="${APP_PATH}/static/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
<script src="${APP_PATH}/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>

</head>
<body>

	<!-- Modal模态框，需要直接放在body内，作为顶级html元素 -->
	
	<!-- 员工修改的模态框 -->
	<div class="modal fade" id="update_emp_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" >员工修改</h4>
	      </div>
	      <div class="modal-body">
	      <!-- 模态框中添加表单 -->
	        <form class="form-horizontal">
	        <!-- <div class="form-group">表示一个表单项 -->
	        
	         <!-- 输入框：姓名 -->
			  <div class="form-group">
			    <label class="col-sm-2 control-label">姓名</label>
			    <div class="col-sm-10">
			    	<!-- 员工姓名 静态显示 ，不允许修改--> 
      				<p class="form-control-static" id="update_empName" ></p>
			    </div>
			  </div>
			  
			  <!-- 输入框：email -->
			  <div class="form-group">
			    <label class="col-sm-2 control-label">邮箱</label>
			    <div class="col-sm-10">
			      <input type="text" name="email" class="form-control" id="update_email" placeholder="email@qq.com">
			      <span class="help-block"></span>
			    </div>
			  </div>
			  
			  <!-- 单选框：性别 ,男为默认选中状态-->
			  <div class="form-group">
			    <label class="col-sm-2 control-label">性别</label>
			    <label class="radio-inline">
					<input type="radio" name="gender" id="update_gender1" value="M" checked="checked"> 男
				</label>
				<label class="radio-inline">
  					<input type="radio" name="gender" id="update_gender2" value="F"> 女
  				</label>
			  </div>
			  
			  <!-- 下拉列表：部门 -->
			  <div class="form-group">
				 <label class="col-sm-2 control-label">部门</label>
				 <div class="col-sm-4">
				 	 <!-- 部门提交部门id即可 -->
				 	<select class="form-control" name="dId">
				 	</select>
				 </div>
			  </div>
			  
			</form>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	        <button type="button" class="btn btn-primary" id="save_update_empt_btn">修改</button>
	      </div>
	    </div>
	  </div>
	</div>
	
	<!-- 新增员工的模态框 -->
	<div class="modal fade" id="add_emp_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="myModalLabel">员工添加</h4>
	      </div>
	      <div class="modal-body">
	      <!-- 模态框中添加表单 -->
	        <form class="form-horizontal">
	        <!-- <div class="form-group">表示一个表单项 -->
	        
	         <!-- 输入框：姓名 -->
			  <div class="form-group">
			    <label class="col-sm-2 control-label">姓名</label>
			    <div class="col-sm-10">
			    	<!-- input中的name要和类的属性名一致，name和value组成键值对，封装成json数据 -->
			      <input type="text" name="empName" class="form-control" id="add_empName" placeholder="empName">
			      <span class="help-block"></span>
			    </div>
			  </div>
			  
			  <!-- 输入框：email -->
			  <div class="form-group">
			    <label class="col-sm-2 control-label">邮箱</label>
			    <div class="col-sm-10">
			      <input type="text" name="email" class="form-control" id="add_email" placeholder="email@qq.com">
			      <span class="help-block"></span>
			    </div>
			  </div>
			  
			  <!-- 单选框：性别 ,男为默认选中状态-->
			  <div class="form-group">
			    <label class="col-sm-2 control-label">性别</label>
			    <label class="radio-inline">
					<input type="radio" name="gender" id="input_gender1" value="M" checked="checked"> 男
				</label>
				<label class="radio-inline">
  					<input type="radio" name="gender" id="input_gender2" value="F"> 女
  				</label>
			  </div>
			  
			  <!-- 下拉列表：部门 -->
			  <div class="form-group">
				 <label class="col-sm-2 control-label">部门</label>
				 <div class="col-sm-4">
				 	 <!-- 部门提交部门id即可 -->
				 	<select class="form-control" name="dId">
				 	</select>
				 </div>
			  </div>
			  
			</form>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	        <button type="button" class="btn btn-primary" id="save_add_empt_btn">保存</button>
	      </div>
	    </div>
	  </div>
	</div>

	<!-- 搭建页面 -->
	<div class="container">
		<!-- 标题行 -->
		<div class="row"></div>
			<div class="col-md-12">
				<h1>SSM-CRUD</h1>
			</div>
		<!-- 按钮行 -->
		<div class="row">
			 <div class="col-md-3 col-md-offset-9">
			 	<button class="btn btn-primary" id="add_emp_btn">新增</button>
			 	<button class="btn btn-danger" id="delete_emp_btn">删除</button>
			 </div>
		</div>
		<!-- 显示表格数据 -->
		<div class="row">
			<div class="col-md-12">
				<table class="table table-hover" id="emps_table">
					<thead>
						<tr>
							<th> 
								<input type="checkbox" id="checked_all">
							</th>
							<th>#</th>
							<th>empName</th>
							<th>gender</th>
							<th>email</th>
							<th>deptName</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						
					</tbody>
					
				</table>
			</div>
		</div>
		<!-- 显示分页信息 -->
		<div class="row">
		<!-- 分页文字信息 -->
			<div class="col-md-6" id="page_info_area">
				
			</div>
		<!-- 分页 -->
			<div class="col-md-6" id="page_nav_area">
				
			</div>
		</div>	
	</div>
	
	
	<script type="text/javascript">
		
		var totalRecord;
		var currentPage;
		//页面加载完成以后，直接发送一个ajax请求，得到分页数据
		//能不能直接用to_page(1)？
		$(function(){to_page(1);});
		
		//页面跳转函数
		function to_page(pn){
			$.ajax({
				url:"${APP_PATH}/emps",
				data:"pn="+pn,
				type:"GET",//http请求的类型，GET,POST,PUT,DELETE
				success:function(result){
					//console.log(result);
					//1、解析并显示员工数据
					build_emps_table(result);
					//2、解析并显示分页信息
					build_page_info(result);
					//3、解析并显示分页条
					build_page_nav(result);
					
				}
			});
		}
		
		//1、解析并显示员工数据
		function build_emps_table(result){
			$("#emps_table tbody").empty();
			var emps=result.extend.pageInfo.list;
			//ajax方法$.each对emps进行遍历，index为索引，item为对象 ，function为对遍历结果的处理
			$.each(emps,function(index,item){
				//tr 元素定义表格行，th 元素定义表头，td 元素定义表格单元
				//因为外部被“”包围，所以内部属性值要用‘’，而不能使用“”
				var empCheckTd=$("<td><input type='checkbox' class='check_item'/></td>");
				var empIdTd=$("<td></td>").append(item.empId);
				var empNameTd=$("<td></td>").append(item.empName);
				var genderTd=$("<td></td>").append(item.gender=="M"?"男":"女");
				var emailTd=$("<td></td>").append(item.email);
				var deptNameTd=$("<td></td>").append(item.department.deptName);
				/**
				创建编辑按钮和删除按钮
							<th>
								<button class="btn btn-primary btn-sm">
									<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
									编辑
								</button>
								<button class="btn btn-danger btn-sm">
									<span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
									删除
								</button>
							</th>
				*/
				var editBtn=$("<button></button>").addClass("btn btn-primary btn-sm edit_btn")
							.append($("<span></span>").addClass("glyphicon glyphicon-pencil"))
							.append("编辑");
				editBtn.attr("edit_id",item.empId);//为编辑按钮增加id属性
				var delBtn=$("<button></button>").addClass("btn btn-danger btn-sm delete_btn")
							.append($("<span></span>").addClass("glyphicon glyphicon-trash"))
							.append("删除");
				delBtn.attr("delete_id",item.empId);
				var btnTd=$("<td></td>").append(editBtn).append(" ").append(delBtn);
				//添加tr便签，并添加td元素
				//append方法每次执行完之后，还是返回原来的元素，所以可以进行链式操作
				//appendTo将内容添加到表格的tbody中
				$("<tr></tr>").append(empCheckTd)
					.append(empIdTd)
					.append(empNameTd)
					.append(genderTd)
					.append(emailTd)
					.append(deptNameTd)
					.append(btnTd)
					.appendTo("#emps_table tbody");
			})
			
		}
		//2、解析并显示分页信息
		function build_page_info(result){
			$("#page_info_area").empty();
			$("#page_info_area").append("当前 "+result.extend.pageInfo.pageNum +" 页，总 "+
					result.extend.pageInfo.pages+" 页，总 "+
					result.extend.pageInfo.total+" 条记录");
			totalRecord=result.extend.pageInfo.total;	
			currentPage=result.extend.pageInfo.pageNum;
		}
		
		//3、解析并显示分页条
		function build_page_nav(result){
			$("#page_nav_area").empty();
			var ul=$("<ul></ul>").addClass("pagination");
			//首页
			var firstPageLi=$("<li></li>").append($("<a></a>").append("首页"));
			firstPageLi.click(function(){
				to_page(1);
				});
			//<< 向前一页
			var prePageLi=$("<li></li>").append($("<a></a>").append("&laquo;"));
			prePageLi.click(function(){
				to_page(result.extend.pageInfo.prePage);
				});
			//如果为第一页，则失效
			if(!result.extend.pageInfo.hasPreviousPage){
				firstPageLi.addClass("disabled");
				prePageLi.addClass("disabled");
			}
			ul.append(firstPageLi).append(prePageLi);
			
			
			//页码导航
			$.each(result.extend.pageInfo.navigatepageNums,function(index,item){
				var numPageLi=$("<li></li>").append($("<a></a>").append(item));
				if(item==result.extend.pageInfo.pageNum){
					numPageLi.addClass("active");
				}
				//给numPageLi增加单击事件，单击时，发送ajax请求
				//为什么不能直接用numPageLi.click(to_page(item))？
				numPageLi.click(function(){
					to_page(item);});
				
					
				ul.append(numPageLi);
			} )
			
			//>>向后一页
			var nextPageLi=$("<li></li>").append($("<a></a>").append("&raquo;"));
			nextPageLi.click(function(){
				to_page(result.extend.pageInfo.nextPage);
				});
			//末页
			var lastPageLi=$("<li></li>").append($("<a></a>").append("末页"));
			lastPageLi.click(function(){
				to_page(result.extend.pageInfo.pages);
				});
			//如果为最后一页，则失效
			if(!result.extend.pageInfo.hasNextPage){
				nextPageLi.addClass("disabled");
				lastPageLi.addClass("disabled");
			}
			ul.append(nextPageLi).append(lastPageLi);
			
			var nav=$("<nav></nav>").attr("aria-label","Page navigation").append(ul);
			//往page_nav_area区域添加导航条
			//$("#page_nav_area").append(nav);
			nav.appendTo("#page_nav_area");
		}
		 
		//完整表单重置(包括表单的数据及表单的样式)
		function reset_form(ele){
			//清除表单数据
			$(ele)[0].reset();
			//清除表单样式
			//表单中的所有元素
			$(ele).find("*").removeClass("has-success has-error");
			//表单中的help-block元素
			$(ele).find(".help-block").text("");
		}
		
		//点击新增按钮，弹出模态框
		$("#add_emp_btn").click(function(){
			//完整表单重置(包括表单的数据及表单的样式)
			reset_form("#add_emp_modal form");
			//发送ajax请求，获取所有部门信息,显示在下拉列表中
			getDepts("#add_emp_modal select");
			//弹出模态框
			$("#add_emp_modal").modal({
				backdrop:"static"
			});
		});
		
		//为模态框中的部门下拉列表，获取部门信息
		function getDepts(ele){
			//每次都会添加查询到的部门，所以在查询前需要清空原来的select元素
			$(ele).empty();
			//ajax请求，获取所有部门信息
			$.ajax({
				url:"${APP_PATH}/depts",
				type:"GET",
				success:function(result){
					//console.log(result);
					$.each(result.extend.depts,function(){
						var opt=$("<option></option>").append(this.deptName).attr("value",this.deptId);
						//$("#add_emp_modal select").append(opt);与下面语句等价
						opt.appendTo(ele);
					});
					
				}
			});
		}
		//根据员工id，获取员工信息
		function getEmp(id){
			//ajax请求
			$.ajax({
				url:"${APP_PATH}/emp/"+id,
				type:"GET",
				success:function(result){
					//console.log(result);
					var emp=result.extend.emp;
					$("#update_empName").text(emp.empName);
					$("#update_email").val(emp.email);
					$("#update_emp_modal input[name=gender]").val([emp.gender]);
					$("#update_emp_modal select").val([emp.dId]);
				}
			});
		}
		
		//前端校验，对姓名及邮箱的格式进行校验
		function validate_add_form(){
			//1.校验用户名
			//拿到要校验的数据，使用正则表达式
			var empName=$("#add_empName").val();
			//正则表达式，以^开头，$结尾，[]字符集，{}匹配次数
			var regName=/(^[a-zA-Z0-9_-]{6,16}$)|(^[\u2E80-\u9FFF]{2,10}$)/;
			if(!regName.test(empName)){
				//alert("用户名不符合规范！");
				show_validate_msg("#add_empName","error","用户名不符合规范！");
				return false;
			}else{
				show_validate_msg("#add_empName","success","");
			};
			
			//2.校验邮箱
			var email=$("#add_email").val();
			//正则表达式，以^开头，$结尾，[]字符集，{}匹配次数
			var regEmail=/^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
			if(!regEmail.test(email)){
				//alert("邮箱不符合规范！");
				show_validate_msg("#add_email","error","邮箱不符合规范！");
				return false;
			}else{
				show_validate_msg("#add_email","success","");
			}; 
			return true;
			
		} 
		//新增表单的校验
		/* function validate_add_form(){
			//校验名字以及邮箱
			var regName=/(^[a-zA-Z0-9_-]{6,16}$)|(^[\u2E80-\u9FFF]{2,10}$)/;
			var regEmail=/^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
			if((false==validate_ele("#add_empName",regName,"用户名不符合规范！"))||
					(false==validate_ele("#add_email",regEmail,"邮箱不符合规范！"))){
				return false;	
			}
			return true;
		}*/
		
		//编辑表单的校验
		function validate_update_form(){
			var email=$("#add_email").val();
			//正则表达式，以^开头，$结尾，[]字符集，{}匹配次数
			var regEmail=/^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
			if(!regEmail.test(email)){
				//alert("邮箱不符合规范！");
				show_validate_msg("#add_email","error","邮箱不符合规范！");
				return false;
			}else{
				show_validate_msg("#add_email","success","");
			}; 
			return true;
		} 
		
		
		//前端校验元素ele是否可用
		//reg为校验规则
		//msg为校验失败时的输出信息
		function validate_ele(ele,reg,msg){
			//清除原本的状态
			$(ele).parent().removeClass("has-success has-error" );
			$(ele).next("span").text("");
			
			var value=$(ele).val();
			if(!reg.test(value)){
				$(ele).parent().addClass("has-error");
				$(ele).next("span").text(msg);
				return false;	
			}else{
				$(ele).parent().addClass("has-success");
				$(ele).next("span").text("");
				return true;
			}; 
			
		}
		
		//根据校验状态改变输入框及文本的显示
		function show_validate_msg(ele,status,msg){
			//清除原本的状态
			$(ele).parent().removeClass("has-success has-error" );
			$(ele).next("span").text("");
			
			if("success"==status){
				$(ele).parent().addClass("has-success");
				$(ele).next("span").text(msg);
				
			}else if("error"==status){
				$(ele).parent().addClass("has-error");
				$(ele).next("span").text(msg);
			}
			
		};
		
		//数据库重复性校验,如果用户名已存在，则不能保存
		//当输入框value发生改变时，发送ajax请求，进行校验
		$("#add_empName").change(function(){
			var empName = this.value;
			$.ajax({
				url:"${APP_PATH}/checkuser",
				type:"POST",
				data:"empName="+empName,
				success:function(result){
					//Msg状态码 100-成功 200-失败
					if(result.code==100){
						show_validate_msg("#add_empName","success","用户名可用");
						//为保存按钮自定义"ajax_va"属性，如果校验失败，那么按钮失效
						$("#save_add_empt_btn").attr("ajax_va","success");
					}else{
						//{"code":200,"msg":"处理失败！","extend":{"err_msg":"输入用户名不符合规范！"}}
						//alert(result.extend.err_msg);
						show_validate_msg("#add_empName","error",result.extend.err_msg);
						$("#save_add_empt_btn").attr("ajax_va","error");
					}
				}
			});
		});
		
		
		//为新增模态框中的保存按钮"save_add_empt_btn"，增加单击事件
		$("#save_add_empt_btn").click(function(){
			//1.发送ajax请求，保存新增的数据
			//对提交的数据进行校验
			if($("#save_add_empt_btn ").attr("ajax_va")=="error"){
				return false;
			}
			if(!validate_add_form()){
				return false;
			}
			//alert($("#add_emp_modal form").serialize());
			$.ajax({
				url:"${APP_PATH}/emp",
				type:"POST",
				//将模态框中的表单数据序列化为要传输的数据
				data:$("#add_emp_modal form").serialize(),
				//result为controller处理之后，返回的数据，即Msg对象
				success:function(result){
					//后台校验成功
					if(result.code==100){
						//alert(result.msg);
						//2.关闭模态框
						$("#add_emp_modal").modal('hide');
						//3.跳转到最后一页
						//当页码数>总页码时，将最后一页返回
						to_page(totalRecord);
					}else{
						//后台校验失败，显示失败信息
						//undefined未定义，没有错误时为未定义
						if(undefined!=result.extend.errorFields.email){
							show_validate_msg("#add_email","error",result.extend.errorFields.email);
						}
						if(undefined!=result.extend.errorFields.empName)
						show_validate_msg("#add_empName","error",result.extend.errorFields.empName);
					}

				}
			});
			
		});
		
		
		
		//为编辑按钮添加点击事件
		//方法1.在创建按钮之后，绑定click   方法2.用jquery的live方法，但是此方法在新版jquery中被取消，用on方法替代
		//.on(event,ele,function)
		//("btn btn-primary btn-sm edit_btn")
		$(document).on("click",".edit_btn",function(){
			//完整表单重置(包括表单的数据及表单的样式)
			reset_form("#update_emp_modal form");
			//获取员工数据
			//editBtn.attr("edit_id",item.empId);
			var id=$(this).attr("edit_id");//this表示click事件的对象
			getEmp(id);
			//获取部门信息
			getDepts("#update_emp_modal select");
			//弹出模态框
			$("#update_emp_modal").modal({
				backdrop:"static"
			});	
			//将edit_id的值传递给模态框中的编辑按钮
			$("#save_update_empt_btn").attr("edit_id",id);
		});
		
		//为编辑模态框中的更新按钮，增加click事件
		//"save_update_empt_btn"
		$("#save_update_empt_btn").click(function(){
			//1.对提交的数据进行校验
			var email=$("#update_email").val();
			//正则表达式，以^开头，$结尾，[]字符集，{}匹配次数
			var regEmail=/^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
			if(!regEmail.test(email)){
				//alert("邮箱不符合规范！");
				show_validate_msg("#update_email","error","邮箱不符合规范！");
				return false;
			}else{
				show_validate_msg("#update_email","success","");
			}; 

			//2.提交更新请求
			$.ajax({
				url:"${APP_PATH}/emp/"+$(this).attr("edit_id"),
				type:"PUT",
				//将模态框中的表单数据序列化为要传输的数据
				//因为表单中没有输入ID和empName，所以传递的json数据中这两项的值为空
				data:$("#update_emp_modal form").serialize(),
				//result为controller处理之后，返回的数据，即Msg对象
				success:function(result){
					//后台校验成功
					if(result.code==100){
						//关闭模态框
						$("#update_emp_modal").modal('hide');
						//刷新当前页
						to_page(currentPage);
						
					}else{
						alert("更新失败！");
					}	
				}
			});
		});
		
		
		//为单个删除按钮添加点击事件
		//delBtn.attr("delete_id",item.empId);
		//"btn btn-danger btn-sm delete_btn"
		$(document).on("click",".delete_btn",function(){
			//获取员工数据
			//editBtn.attr("edit_id",item.empId);
			var id=$(this).attr("delete_id");//this表示click事件的对象
			var delName=$(this).parents("tr").find("td:eq(2)").text();
			if(confirm("确认删除【"+delName+"】?")){
				$.ajax({
					url:"${APP_PATH}/emp/"+id,
					type:"DELETE",
					success:function(result){
						if(100==result.code){
							alert("删除成功");
							to_page(currentPage);//刷新当前页面
						}
					}
					
				});
			}
		});
		
		
		//批量删除功能
		$("#checked_all").click(function(){
			//prop功能与attr一致，prop用于原生属性，attr用于自定义属性
			//所有子项的选中状态都跟ckecked_all按钮一致
			$(".check_item").prop("checked",$(this).prop("checked"));
		})
		
		//check_item如果全选，那么check_all按钮被选中
		$(document).on("click",".check_item",function(){
			//被选中的条目数等于总条目数则为全选
			var flag=$(".check_item:checked").length==$(".check_item").length;
			$("#checked_all").prop("checked",flag);
		});
		
		//点击批量删除按钮
		$("#delete_emp_btn").click(function(){
			 var delNames="";
			 var delIds="";
			 $.each($(".check_item:checked"),function(){
				 delNames+=$(this).parents("tr").find("td:eq(2)").text()+",";
				 delIds+=$(this).parents("tr").find("td:eq(1)").text()+"-";
			 });
			//弹出确认框
			delNames=delNames.substring(0,delNames.length-1);
			delIds=delIds.substring(0,delIds.length-1);
			if(confirm("确认删除【"+delNames+"】?")){
				$.ajax({
					url:"${APP_PATH}/emp/"+delIds,
					type:"DELETE",
					success:function(result){
						alert(result.msg);
						to_page(currentPage);
					}
				});
			}
		});
	</script>

</body>
</html>