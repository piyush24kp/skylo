package com.gb.service.impl;

import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gb.model.OrderDetail;
import com.gb.repository.OrderRepository;
import com.gb.repository.ReviewRepository;
import com.gb.service.ReviewService;
import com.gb.vo.BrandDetailVo;
import com.gb.vo.OrderCountVo;
import com.gb.vo.OrderDetailVo;
import com.gb.vo.SellDetailVo;
import com.gb.vo.chartDetailVo;

@Service
public class ReviewServiceImpl implements ReviewService{

	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	OrderServiceImpl orderServiceImpl; 
	
	@Autowired
	BillingServiceImpl billingServiceImpl; 
	
	@Autowired
	ReviewRepository reviewRepository;
	
	private static final String STOCK_FILE_NAME = "d:/test/STOCK.xlsx";
	private static final String SELL_FILE_NAME = "d:/test/SELL.xlsx";
	private Class cls;
    private Field[] fields = null;
	
    @Override
	public void genrateExcel(String saleType, Date from, Date to) {
		if(saleType.equals("stock")){
			genrateStockExcel(from,to);
		}else if(saleType.equals("sell")){
			genrateSellExcel(from,to);
		}
	}

	private void genrateSellExcel( Date from, Date to) {
		List<SellDetailVo> odersList = billingServiceImpl.getSellOrders();
		XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("SELL Details");
        CellStyle style = sheet.getWorkbook().createCellStyle();
        XSSFFont font= workbook.createFont();
        font.setFontHeightInPoints((short)10);
        font.setFontName("Arial");
        font.setBold(true);
        style.setFont(font);
        Object[][] datatypes = {
                {"orderId", "invoiceNo","customerName","contantNo","imeiNo", "brand","model","saleType","address","sellDate","amount"},
        };
        int rowNum = 0;
        for (Object[] datatype : datatypes) {
            Row row = sheet.createRow(rowNum++);
            int colNum = 0;
            for (Object field : datatype) {
                Cell cell = row.createCell(colNum++);
                cell.setCellStyle(style);
                if (field instanceof String) {
                    cell.setCellValue((String) field);
                   
                } else if (field instanceof Integer) {
                    cell.setCellValue((Integer) field);
                }
            }
        }
        try {
			cls = Class.forName("com.gb.vo.SellDetailVo");
			fields = cls.getDeclaredFields();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
        
        for(SellDetailVo order : odersList){
        	Row row = sheet.createRow(rowNum++);
        	int colNum = 0;
        	for(Field field : fields){
        		Cell cell = row.createCell(colNum);
        		if(field.getName().equals("orderId")){
        			cell.setCellValue((order.getOrderId()));
        		}else if(field.getName().equals("invoiceNo")){
        			cell.setCellValue(order.getInvoiceNo());
        		}else if(field.getName().equals("customerName")){
        			cell.setCellValue(order.getCustomerName());
        		}else if(field.getName().equals("contantNo") && order.getContantNo() !=null){
        			cell.setCellValue(order.getContantNo());
        		}else if(field.getName().equals("brand")){
        			cell.setCellValue(order.getBrand().getBrandName());
        		}else if(field.getName().equals("model")){
        			cell.setCellValue(order.getModel().getModelName());
        		}else if(field.getName().equals("saleType")){
        			cell.setCellValue(order.getSaleType());
        		}else if(field.getName().equals("address")){
        			cell.setCellValue(order.getAddress());
        		}else if(field.getName().equals("sellDate")){
        			cell.setCellValue(order.getSellDate());
        		}else if(field.getName().equals("amount") && order.getAmount() !=null){
        			cell.setCellValue(order.getAmount());
        		}
        		colNum++;
        	}
        }
        
        try {
            FileOutputStream outputStream = new FileOutputStream(SELL_FILE_NAME);
            workbook.write(outputStream);
            workbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
		
	}

	private void genrateStockExcel(Date from, Date to) {
		List<OrderDetailVo> odersList = orderServiceImpl.getOrdersByDate(from,to);
		XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Stock Details");
        CellStyle style = sheet.getWorkbook().createCellStyle();
        XSSFFont font= workbook.createFont();
        font.setFontHeightInPoints((short)10);
        font.setFontName("Arial");
        font.setBold(true);
        style.setFont(font);
        Object[][] datatypes = {
                {"orderId", "orderName","amount","quantity","brand", "category","purchasePrice","sellPrice","suppliedBy","orderDate","model"},
        };
        int rowNum = 0;
        for (Object[] datatype : datatypes) {
            Row row = sheet.createRow(rowNum++);
            int colNum = 0;
            for (Object field : datatype) {
                Cell cell = row.createCell(colNum++);
                cell.setCellStyle(style);
                if (field instanceof String) {
                    cell.setCellValue((String) field);
                   
                } else if (field instanceof Integer) {
                    cell.setCellValue((Integer) field);
                }
            }
        }
        try {
			cls = Class.forName("com.gb.vo.OrderDetailVo");
			fields = cls.getDeclaredFields();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
        
        for(OrderDetailVo order : odersList){
        	Row row = sheet.createRow(rowNum++);
        	int colNum = 0;
        	for(Field field : fields){
        		Cell cell = row.createCell(colNum);
        		if(field.getName().equals("orderId")){
        			cell.setCellValue((order.getOrderId()));
        		}else if(field.getName().equals("orderName")){
        			cell.setCellValue(order.getOrderName());
        		}else if(field.getName().equals("amount") && order.getAmount() !=null){
        			cell.setCellValue(order.getAmount());
        		}else if(field.getName().equals("quantity")&& order.getQuantity() !=null){
        			cell.setCellValue(order.getQuantity());
        		}else if(field.getName().equals("brand")){
        			cell.setCellValue(order.getBrand().getBrandName());
        		}else if(field.getName().equals("purchasePrice")){
        			cell.setCellValue(order.getPurchasePrice());
        		}else if(field.getName().equals("sellPrice")){
        			cell.setCellValue(order.getSellPrice());
        		}else if(field.getName().equals("suppliedBy")){
        			cell.setCellValue(order.getSuppliedBy().getSupplierName());
        		}else if(field.getName().equals("orderDate")){
        			cell.setCellValue(order.getOrderDate());
        		}else if(field.getName().equals("model")){
        			cell.setCellValue(order.getModel().getModelName());
        		}
        		colNum++;
        	}
        }
        
        try {
            FileOutputStream outputStream = new FileOutputStream(STOCK_FILE_NAME);
            workbook.write(outputStream);
            workbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
		
	}

	public chartDetailVo getChartDetail() {

		chartDetailVo cdv = new chartDetailVo();
		List<BrandDetailVo> volist = orderServiceImpl.getBrands();
		
		List b = new ArrayList();
		List pOrder = new ArrayList();
		List sOrder = new ArrayList();
		int index = 0;
		for(BrandDetailVo bdv : volist){
			b.add(bdv.getBrandName());
			
			
		List<OrderCountVo> orderList = reviewRepository.getOrderCountByBrandId();
			for(OrderCountVo odv : orderList) {
				if(bdv.getBrandId().equals(odv.getBrand())){
					pOrder.add(index,odv.getQuantity());
				}
			}
		List<OrderCountVo> sellList = reviewRepository.getSellCountByBrandId();
			for(OrderCountVo odv : sellList) {
				if(bdv.getBrandId().equals(odv.getBrand())){
					sOrder.add(index,odv.getCount());
				}
			}
			if(pOrder.size()> index){}else{
				pOrder.add(index,0);
			}
			if(sOrder.size()>index){}else{
				sOrder.add(index,0);
			}
			index++;
		}
		Map<String, List > map = new HashMap<String, List>();
		map.put("Purchase", pOrder);
		map.put("Sell", sOrder);
		cdv.setSeries(map);
		cdv.setBrands(b);
		
		
		return cdv;
		 
	}
}
