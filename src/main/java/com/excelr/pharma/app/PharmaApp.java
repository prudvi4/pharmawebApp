package com.excelr.pharma.app;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;

import com.excelr.pharma.dao.PharmaDaoImpl;
import com.excelr.pharma.exceptions.PharmaBusinessException;
import com.excelr.pharma.exceptions.PharmaException;
import com.excelr.pharma.service.IPharmaService;
import com.excelr.pharma.service.IPharmaServiceImpl;
import com.excelr.pharma.vo.BatchInfo;
import com.excelr.pharma.vo.MedicineInfo;

/**
 * @author admin
 *
 */
public class PharmaApp {
	private static void printLine(int noOfTimes) {
		for (int i = 0; i <= noOfTimes; i++) {
			System.out.print("*");
		}
		System.out.println();
	}

	private static void userMenu() {
		printLine(30);
		System.out.println("Pharma App Menu:");
		System.out.println("1.Enroll New Batch");
		System.out.println("2.Search Batch Info by BatchCode");
		System.out.println("3.Remove Batch Info by BatchCode");
		System.out.println("4.Display all Batches Information");
		System.out.println("5.Display all Medicines Information");
		printLine(30);
		IPharmaService service = new IPharmaServiceImpl();
		PharmaDaoImpl doOperations = new PharmaDaoImpl();
		try (Scanner scanner = new Scanner(System.in)) {
			System.out.print("Enter Choice:-");
			int userChoice = scanner.nextInt();
			switch (userChoice) {
			case 1:
				System.out.print("Enter Batch Code:");
				String batchCode = scanner.next();
				System.out.print("Enter Medicine Code:");
				String medicineCode = scanner.next();
				System.out.print("Enter Weight:");
				int weight = scanner.nextInt();
				System.out.print("Enter Price:");
				double price = scanner.nextDouble();
				System.out.print("Medicine Type Code:");
				String medicineTypeCode = scanner.next();
				System.out.print("isRefrigiration:?");
				String refrigiration = scanner.next();
				BatchInfo batchInfo = new BatchInfo();
				batchInfo.setBatchCode(batchCode);
				batchInfo.setMedicineCode(medicineCode);
				batchInfo.setMedicineTypeCode(medicineTypeCode);
				batchInfo.setPrice(price);
				batchInfo.setWeight(weight);
				batchInfo.setRefrigiration(refrigiration);
				boolean regFlag = service.addBatch(batchInfo);
				if (regFlag) {
					System.out.println("Batch Addedd Successfully");
				}
				break;
			case 2:
				System.out.print("Enter a Batch Code to Search:");
				String batchSearch = scanner.next();
				/*
				 * Set<BatchInfo> batchSet = doOperations.findBatchByBatchCode(batchSearch); for
				 * (BatchInfo data : batchSet) { System.out.println(data); }
				 */
				break;
			case 3:
				System.out.print("Enter a Batch Code to Delete from the database: ");
				String batchDelete = scanner.next();
				doOperations.removeBatchByBacthCode(batchDelete);
				break;

			case 4:
				Set<BatchInfo> allBatchSet = doOperations.allBatchesInfo();
				System.out.println("Displaying all Batches from the databases.");
				for (BatchInfo data : allBatchSet) {
					System.out.println(data);
				}
				break;

			case 5:
				Set<MedicineInfo> values1 = doOperations.allMedicineInfo();
				for (MedicineInfo medicine : values1) {
					System.out.println(medicine);
				}
				break;
			default:
				break;
			}
		} catch (InputMismatchException | PharmaException | PharmaBusinessException e) {
			System.err.println(e.getMessage());
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		userMenu();
	}
}
