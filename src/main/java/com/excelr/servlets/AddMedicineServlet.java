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
import com.excelr.pharma.vo.MedicineInfo;

/**
 * Servlet implementation class AddMedicineServlet
 */
@WebServlet("/AddMedicineServlet")
public class AddMedicineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String medicineCode = req.getParameter("medicineCode");
		String medicineName = req.getParameter("medicineName");

		// System.out.println(medicineCode + " " + medicineName);

		MedicineInfo medicine = new MedicineInfo();
		medicine.setMedicineCode(medicineCode);
		medicine.setMedicineName(medicineName);
		HttpSession session = req.getSession();
		PharmaDaoImpl dao = new PharmaDaoImpl();
		try {
			boolean valFlag = dao.validateMedicineCode(medicineCode);
			boolean medFlag = dao.checkMedicineCode(medicineCode);
			if (valFlag) {
				if (!medFlag) {
					boolean addFlag = dao.addMedicine(medicine);
					if (addFlag) {
						session.setAttribute("succMsg", "Medicine Added Successfully");
						resp.sendRedirect("addmedicine.jsp");
					} else {
						session.setAttribute("failedMsg", "Something Error On Server");
						resp.sendRedirect("addmedicine.jsp");
					}
				} else {
					session.setAttribute("failedMsg", "Medicine Code Already Exists");
					resp.sendRedirect("addmedicine.jsp");
				}
			} else {
				session.setAttribute("failedMsg", "Medicine Code Invalid Format");
				resp.sendRedirect("addmedicine.jsp");
			}

		} catch (PharmaException e) {
			e.printStackTrace();
		} catch (PharmaBusinessException e) {
			e.printStackTrace();
		}

	}

}
