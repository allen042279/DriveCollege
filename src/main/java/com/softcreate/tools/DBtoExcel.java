package com.softcreate.tools;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.Vector;

import com.softcreate.pojo.Menu;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class DBtoExcel {
	
	
	/**
	 * 导出Excel表
	 * @param listItems 数据库结果集
	 * @param filePath 要保存的路径，文件名为 fileName.xls
	 * @param sheetName 工作簿名称 工作簿名称，本方法目前只支持导出一个Excel工作簿
	 * @param columnName 列名，类型为Vector<string>
	 */
	public boolean WritePreStudentsToExcel(List<Menu> listItems, String filePath, String sheetName, Vector<String> columnName) {
		
		WritableWorkbook workbook = null;
		WritableSheet sheet = null;
		
		boolean flag = false;
		
		int rowNum = 1; // 从第一行开始写入
		
		try {
			
			workbook = Workbook.createWorkbook(new File(filePath)); // 创建Excel文件
			sheet = workbook.createSheet(sheetName, 0); // 创建名为 sheetName 的工作簿	
			
			this.writeCol(sheet, columnName, 0); // 首先将列名写入
			
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.CHINA);
			
			// 将结果集写入
			for (Menu item : listItems) { 
				
				Vector<String>  col = new Vector<String>(); // 用以保存一行数据
				
				// 将一行内容保存在col中
//				col.add(item.getUsername());
//				col.add(item.getSexType().name());
//				col.add(item.getMobile());
//				col.add(dateFormat.format(item.getApplyDate()));
//				col.add(item.getIntent());
//				col.add(item.isDealwith()==true? "已处理":"未处理");
				
				// 写入Excel
				this.writeCol(sheet, col, rowNum++);
			}
				
			flag = true;
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				// 关闭
				workbook.write();
				workbook.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return flag;
	}
	
//	public boolean WriteTicketsToExcel(List<ShowTicket> listItems, String filePath, String sheetName, Vector<String> columnName) {
//		
//		WritableWorkbook workbook = null;
//		WritableSheet sheet = null;
//		
//		boolean flag = false;
//		
//		int rowNum = 1; // 从第一行开始写入
//		
//		try {
//			
//			workbook = Workbook.createWorkbook(new File(filePath)); // 创建Excel文件
//			sheet = workbook.createSheet(sheetName, 0); // 创建名为 sheetName 的工作簿	
//			
//			this.writeCol(sheet, columnName, 0); // 首先将列名写入
//			
//			// 将结果集写入
//			for (ShowTicket item : listItems) { 
//				
//				Vector<String>  col = new Vector<String>(); // 用以保存一行数据
//				
//				// 将一行内容保存在col中
////				col.add(item.getId() + "");
//				col.add(item.getSeatNo() + "");
//				col.add(item.getStatus().toString());
//				col.add(item.getIdentityType().toString());
//				col.add(item.getIdentityNo());
//				col.add(item.getUserName());
//				col.add(item.getMobileNo());
//				
//				// 写入Excel
//				this.writeCol(sheet, col, rowNum++);
//			}
//				
//			flag = true;
//			
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
//		finally {
//			try {
//				// 关闭
//				workbook.write();
//				workbook.close();
//			}catch(Exception e) {
//				e.printStackTrace();
//			}
//		}
//		
//		return flag;
//	}
	
	/***
	 * 将数组写入工作簿	
	 * @param sheet 要写入的工作簿
	 * @param col 要写入的数据数组
	 * @param rowNum 要写入哪一行
	 * @throws WriteException 
	 * @throws RowsExceededException 
	 */
	private void writeCol(WritableSheet sheet, Vector<String> col, int rowNum) throws RowsExceededException, WriteException {
		int size = col.size(); // 获取集合大小
		
		for(int i = 0; i < size; i++) { // 写入每一列
			Label label = new Label(i, rowNum, col.get(i)); 
			sheet.addCell(label);
		}
	}

}
