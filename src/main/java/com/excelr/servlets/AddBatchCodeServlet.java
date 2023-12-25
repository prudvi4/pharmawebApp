package com.excelr.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.excelr.pharma.dao.PharmaDaoImpl;
import com.excelr.pharma.exceptions.PharmaBusinessException;
import com.excelr.pharma.exceptions.PharmaException;
import com.excelr.pharma.service.IPharmaServiceImpl;
import com.excelr.pharma.vo.BatchInfo;

/**
 * Servlet implementation class AddBatchCodeServlet
 */
@WebServlet("/AddBatchCodeServlet")
public class AddBatchCodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String batchCode = req.getParameter("batchCode");
		String medicineCode = req.getParameter("medicineCode");
		int weight = Integer.parseInt(req.getParameter("weight"));
		double price = Double.parseDouble(req.getParameter("price"));
		String medicineTypeCode = req.getParameter("medicineTypeCode");
		String isrefrigiration = req.getParameter("refrigeration");

		// System.out.println(batchCode + " " + medicineCode + " " + weight + " " +
		// price + " " + medicineTypeCode + " "+ refrigiration);

		BatchInfo batchVO = new BatchInfo();
		batchVO.setBatchCode(batchCode);
		batchVO.setMedicineCode(medicineCode);
		batchVO.setMedicineTypeCode(medicineTypeCode);
		batchVO.setWeight(weight);
		batchVO.setPrice(price);
		batchVO.setRefrigiration(isrefrigiration);
		HttpSession session = req.getSession();
		IPharmaServiceImpl service = new IPharmaServiceImpl();
		PharmaDaoImpl dao = new PharmaDaoImpl();
		try {
			boolean batchFlag = dao.checkBatchCode(batchCode);
			boolean valFlag = dao.validateBatchCode(batchCode);
			boolean medFlag = dao.checkMedicineCode(medicineCode);

			if (valFlag) {
				if (medFlag) {
					if (!batchFlag) {
						boolean addFlag = service.addBatch(batchVO);
						if (addFlag) {
							session.setAttribute("succMsg", "Batch Added Successfully");
							resp.sendRedirect("addbatch.jsp");
						} else {
							session.setAttribute("failedMsg", "Something Error On Server");
							resp.sendRedirect("addbatch.jsp");
						}
					} else {
						session.setAttribute("failedMsg", "Batch Code Already Exists");
						resp.sendRedirect("addbatch.jsp");
					}
				} else {
					session.setAttribute("failedMsg", "Medicine Code Doesnt Exists");
					resp.sendRedirect("addbatch.jsp");
				}
			} else {
				session.setAttribute("failedMsg", "Batch Code Invalid Format");
				resp.sendRedirect("addbatch.jsp");
			}
		} catch (PharmaException e) {
			e.printStackTrace();
		} catch (PharmaBusinessException e) {
			e.printStackTrace();
		}

	}

}
