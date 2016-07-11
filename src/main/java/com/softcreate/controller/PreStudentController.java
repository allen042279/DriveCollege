package com.softcreate.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.TextMessage;

import com.softcreate.model.PreStudent;
import com.softcreate.model.PreStudent.SexType;
import com.softcreate.service.IPreStudentService;
import com.softcreate.service.IUserService;
import com.softcreate.service.ServiceImpl.MyHandler;
import com.softcreate.tools.DBtoExcel;

@Controller
@RequestMapping("/preStudent")
public class PreStudentController {

	/*
	 * @Autowired注解告诉spring，这个字段是需要自动注入的
	 */
	@Resource private IPreStudentService preStudentService;
	@Resource private IUserService userService;
	
	@Bean
    public MyHandler getMyHandler() {
        return new MyHandler();
    }
	
	@RequestMapping("getPage")
	@ResponseBody            //直接返回json格式的数据
	public List<PreStudent> getPage(int page, int pageSize) {
		
		List<PreStudent> lsitList = preStudentService.getPage(page, pageSize);
		return lsitList;
		
	}
	
	@RequestMapping(value="export", produces = "text/html; charset=UTF-8")
	@ResponseBody            //直接返回json格式的数据
	private String exportAll(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String resultString;
		
		if (!userService.isLogin(request.getSession())) {
			resultString = "<script>alert('请先登录系统！'); window.location.href='" + request.getContextPath() + "/login.jsp' </script>";
			return resultString;
		}
		
//		String flag = request.getParameter("firstflag");
//		
//		//此处这样处理是专门针对360浏览器的bug:下载excel文档时会发出两次请求
		/*
		 * http://bbs.360safe.com/thread-479360-1-1.html
		 * 
		 * 就是在点击我们页面的“导出”按钮时，会生成一个excel文档，这时浏览器界面显示文件下载对话框（此时已提交一次请求，生成了excel文档）；
		 * 奇怪的是，用户选择保存后，浏览器又会提交一次请求（请求参数和第一次不一样了）再生成一个excel文档，然后下载下来就是这个新的文档
		 * 
		 * 此处采用的解决方案：http://www.myexception.cn/web/1591573.html
		 */
//		if (flag!=null && "not_auto_post".equals(flag)) {
			
			String realPath = "/admin/prestudent_temp";
			String basePath = request.getSession().getServletContext().getRealPath(realPath);
			String filePath = basePath + "\\" + getCurrentTime() + ".xls";
			
			String sheetName = "导师指引申请";
			
			Vector<String> columnName = new Vector<String>();
			columnName.add(0, "姓名");
			columnName.add(1, "提升方向");
			columnName.add(2, "联系方式");
			columnName.add(3, "申请时间");
			columnName.add(4, "提升意向");
//			columnName.add(5, "是否已处理");
			
			
			String fromTime = request.getParameter("fromtime");// + " 00:00:00";
			String toTime = request.getParameter("totime");// + " 12:59:59";
			String fromDate = fromTime.substring(0,4) + "-" + fromTime.substring(4,6) + "-" + fromTime.substring(6, 8)  + " 00:00:00";
			String toDate = toTime.substring(0,4) + "-" + fromTime.substring(4,6) + "-" + fromTime.substring(6, 8)  + " 12:59:59";
			
			List<PreStudent> preStudentItems = preStudentService.getAll(fromDate, toDate);
			if(preStudentItems==null){
				resultString = "<script>alert('没有数据可以导出！'); history.go(-1);</script>";
				return resultString;
			}
			
			DBtoExcel dBtoExcel = new DBtoExcel();
			dBtoExcel.WritePreStudentsToExcel(preStudentItems, filePath, sheetName, columnName);
			
			File file = new File(filePath);
			if(file.exists()){
				
				try {
					
					response.reset();//清空输出流
				    response.setHeader("Content-disposition", "attachment; filename=" + filePath);//设定输出文件头
				    response.setContentType("application/msexcel"); //定义输出类型
				    response.setContentLength((int)file.length());  //写明要下载的文件的大小
				    
				    //读出文件到i/o流
				    FileInputStream fis = new FileInputStream(file);
			        BufferedInputStream buff = new BufferedInputStream(fis);

			        byte [] b=new byte[1024];//相当于我们的缓存

			        long k=0; //该值用于计算当前实际下载了多少字节

			        //从response对象中得到输出流,准备下载 
			        OutputStream myout=response.getOutputStream();

			        //开始循环下载
			        while(k<file.length()){

			            int j=buff.read(b,0,1024);
			            k+=j;

			            //将b中的数据写到客户端的内存
			            myout.write(b,0,j);

			        }

			        //将写入到客户端的内存的数据,刷新到磁盘
			        myout.flush();

			        myout.close();
			        buff.close();
			        fis.close();
				    
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					resultString = "<script>alert('导出导师指引申请失败！'); history.go(-1);</script>";
				}
			    
				resultString = "<script>alert('导出导师指引申请成功！/n文件名称：" + filePath + "'); history.go(-1);</script>";
				
			}else{
				resultString = "<script>alert('导出导师指引申请失败！'); history.go(-1); </script>";
			}
//		}
			return resultString;
	}
	
	@RequestMapping(value="add", produces = "text/html; charset=UTF-8")
	@ResponseBody            //直接返回String的数据
	public String add(HttpServletRequest request) {
		
		String resultString;
		
		//将所有数据保存到数据库
		PreStudent item = new PreStudent();
		
		item.setUsername(request.getParameter("name"));
		
		String dir = request.getParameter("direction");
		if ("dir1".equals(dir)) {
			item.setSexType(SexType.中职教育);
		}else if ("dir2".equals(dir)) {
			item.setSexType(SexType.学历教育);
		}else if ("dir3".equals(dir)) {
			item.setSexType(SexType.会计培训);
		}else if ("dir4".equals(dir)) {
			item.setSexType(SexType.其他咨询);
		} 
		
		item.setMobile(request.getParameter("mobile"));
		item.setIntent(request.getParameter("intent"));
		item.setDealwith(false);
		item.setApplyDate(new Date());
		
		if (preStudentService.addPreStudent(item)) {
			
			resultString = "<script>alert('申请成功！'); history.go(-1); WeixinJSBridge.call('closeWindow'); </script>";
			
			try {
				//通过webSocket向管理员发送消息
				getMyHandler().sendMessageToUsers(new TextMessage("有新的导师指引申请到达！"));
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else{
			resultString = "<script>alert('申请失败！'); history.go(-1); </script>";
		}
			
		return resultString;
		
	}
	
	private String getCurrentTime() {
		// TODO Auto-generated method stub
		Date dt = new java.util.Date(System.currentTimeMillis());  
        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddHHmmssSSS");  
        String time = fmt.format(dt);
        return time;
	}
}
