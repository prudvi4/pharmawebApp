package com.excelr.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.excelr.pharma.exceptions.PharmaBusinessException;
import com.excelr.pharma.exceptions.PharmaException;
import com.excelr.pharma.service.IPharmaServiceImpl;
import com.excelr.pharma.vo.BatchInfo;

/**
 * Servlet implementation class EditBatchServlet
 */
@WebServlet("/UpdateBatchServlet")
public class UpdateBatchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String batchCode = req.getParameter("batchCode");
		String medicineCode = req.getParameter("medicineCode");
		int weight = Integer.parseInt(req.getParameter("weight"));
		double price = Double.parseDouble(req.getParameter("price"));
		String medicineTypeCode = req.getParameter("medicineTypeCode");
		String isrefrigiration = req.getParameter("refrigeration");

		System.out.println(batchCode + " " + medicineCode + " " + weight + " " + price + " " + medicineTypeCode + " "
				+ isrefrigiration);

		BatchInfo batchVO = new BatchInfo();
		batchVO.setBatchCode(batchCode);
		batchVO.setMedicineCode(medicineCode);
		batchVO.setMedicineTypeCode(medicineTypeCode);
		batchVO.setWeight(weight);
		batchVO.setPrice(price);
		batchVO.setRefrigiration(isrefrigiration);
		HttpSession session = req.getSession();
		IPharmaServiceImpl service = new IPharmaServiceImpl();
		try {
			boolean addFlag = service.updateBatch(batchVO);
			if (addFlag) {
				session.setAttribute("succMsg", "Batch Edited Successfully");
				resp.sendRedirect("viewBatch.jsp");
			} else {
				session.setAttribute("failedMsg", "Something Error On Server");
				resp.sendRedirect("viewBatch.jsp");

			}
		} catch (PharmaException e) {
			e.printStackTrace();
		} catch (PharmaBusinessException e) {
			e.printStackTrace();
		}

	}
}