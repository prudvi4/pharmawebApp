package com.excelr.pharma.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.excelr.pharma.exceptions.PharmaBusinessException;
import com.excelr.pharma.exceptions.PharmaException;
import com.excelr.pharma.vo.BatchInfo;
import com.excelr.pharma.vo.MedicineInfo;

/**
 * @author admin
 *
 */
public class PharmaDaoImpl implements IPharmaDAO {

	@Override
	public boolean addBatch(BatchInfo batchInfo) throws PharmaException {
		final String METHOD_NAME = "addBatch";
		System.out.println("Method Invoked:" + this.getClass().getName() + ":" + METHOD_NAME + ":" + batchInfo);
		boolean addBatchFlag = false;
		final String BATCH_INSERT_QRY = "INSERT INTO pharmaprojdb.batch_info VALUES(?,?,?,?,?,?,?)";
		try (Connection connection = DBConnectionUtility.getConnection();
				PreparedStatement pstStatement = connection.prepareStatement(BATCH_INSERT_QRY)) {
			pstStatement.setString(1, batchInfo.getBatchCode());
			pstStatement.setString(2, batchInfo.getMedicineCode());
			pstStatement.setInt(3, batchInfo.getWeight());
			pstStatement.setDouble(4, batchInfo.getPrice());
			pstStatement.setString(5, batchInfo.getMedicineTypeCode());
			pstStatement.setDouble(6, batchInfo.getShippingCharge());
			pstStatement.setString(7, batchInfo.getCareLevel());
			int updateCount = pstStatement.executeUpdate();
			if (updateCount > 0) {
				addBatchFlag = true;
			}
		} catch (SQLException e) {
			throw new PharmaException(e);
		} catch (PharmaException e) {
			throw new PharmaException(e);
		}
		System.out.println("Method Exit:" + this.getClass().getName() + ":" + METHOD_NAME + ":" + addBatchFlag);
		return addBatchFlag;
	}

	@Override
	public boolean updateBatch(BatchInfo batchInfo) throws PharmaException {
		final String METHOD_NAME = "updateBatch";
		System.out.println("Method Invoked:" + this.getClass().getName() + ":" + METHOD_NAME + ":" + batchInfo);
		boolean upBatchFlag = false;
		final String BATCH_INSERT_QRY = "UPDATE pharmaprojdb.batch_info SET MEDICINE_CODE=?,WEIGHT=?,PRICE=?,MEDICINE_TYPE_CODE=?,SHIPPING_CHARGE=?,CARE_LEVEL=?   WHERE BATCH_CODE=?";
		try (Connection connection = DBConnectionUtility.getConnection();
				PreparedStatement pstStatement = connection.prepareStatement(BATCH_INSERT_QRY)) {
			pstStatement.setString(1, batchInfo.getMedicineCode());
			pstStatement.setInt(2, batchInfo.getWeight());
			pstStatement.setDouble(3, batchInfo.getPrice());
			pstStatement.setString(4, batchInfo.getMedicineTypeCode());
			pstStatement.setDouble(5, batchInfo.getShippingCharge());
			pstStatement.setString(6, batchInfo.getCareLevel());
			pstStatement.setString(7, batchInfo.getBatchCode());
			int updateCount = pstStatement.executeUpdate();
			if (updateCount > 0) {
				upBatchFlag = true;
			}
		} catch (SQLException e) {
			throw new PharmaException(e);
		} catch (PharmaException e) {
			throw new PharmaException(e);
		}
		System.out.println("Method Exit:" + this.getClass().getName() + ":" + METHOD_NAME + ":" + upBatchFlag);
		return upBatchFlag;

	}

	@Override
	public boolean checkMedicineCode(String medicineCode) throws PharmaException {
		final String METHOD_NAME = "checkMedicineCode";
		System.out.println("Method Invoked:" + this.getClass().getName() + ":" + METHOD_NAME + ":" + medicineCode);
		boolean medicineCodeFlag = false;
		final String MEDICINE_CODE_QRY = "SELECT * FROM pharmaprojdb.medicine_master WHERE medicine_code=?";
		try (Connection connection = DBConnectionUtility.getConnection();
				PreparedStatement pstStatement = connection.prepareStatement(MEDICINE_CODE_QRY)) {
			pstStatement.setString(1, medicineCode);
			ResultSet resultSet = pstStatement.executeQuery();
			while (resultSet.next()) {
				medicineCodeFlag = true;
			}
		} catch (SQLException e) {
			throw new PharmaException(e);
		} catch (PharmaException e) {
			throw new PharmaException(e);
		}
		System.out.println("Method Exit:" + this.getClass().getName() + ":" + METHOD_NAME + ":" + medicineCodeFlag);
		return medicineCodeFlag;
	}

	@Override
	public double getShippingCharge(String medicineTypeCode, String weightRange) throws PharmaException {
		final String METHOD_NAME = "getShippingCharge";
		System.out.println("Method Invoked:" + this.getClass().getName() + ":" + METHOD_NAME + ":" + medicineTypeCode
				+ ":" + weightRange);
		double shippingCharge = 0;
		final String SHIPPING_CHARGE_QRY = "SELECT shipping_charge FROM pharmaprojdb.shipping_master WHERE medicine_type_code=? AND weight_range=?";
		try (Connection connection = DBConnectionUtility.getConnection();
				PreparedStatement pstStatement = connection.prepareStatement(SHIPPING_CHARGE_QRY)) {
			pstStatement.setString(1, medicineTypeCode);
			pstStatement.setString(2, weightRange);
			ResultSet resultSet = pstStatement.executeQuery();
			if (resultSet.next()) {
				shippingCharge = resultSet.getDouble("shipping_charge");
				System.out.println(shippingCharge);
			}
		} catch (SQLException e) {
			throw new PharmaException(e);
		} catch (PharmaException e) {
			throw new PharmaException(e);
		}
		System.out.println("Method Exit:" + this.getClass().getName() + ":" + METHOD_NAME + ":" + shippingCharge);
		return shippingCharge;
	}

	@Override
	public boolean checkBatchCode(String batchCode) throws PharmaException {
		final String METHOD_NAME = "checkBatchCode";
		System.out.println("Method Invoked:" + this.getClass().getName() + ":" + METHOD_NAME + ":" + batchCode);
		boolean batchCodeFlag = false;
		final String BATCH_CODE_QRY = "SELECT * FROM pharmaprojdb.batch_info WHERE batch_code=?";
		try (Connection connection = DBConnectionUtility.getConnection();
				PreparedStatement pstStatement = connection.prepareStatement(BATCH_CODE_QRY)) {
			pstStatement.setString(1, batchCode);
			ResultSet resultSet = pstStatement.executeQuery();
			while (resultSet.next()) {
				batchCodeFlag = true;
			}
		} catch (SQLException e) {
			throw new PharmaException(e);
		} catch (PharmaException e) {
			throw new PharmaException(e);
		}
		System.out.println("Method Response:" + this.getClass().getName() + ":" + METHOD_NAME + ":" + batchCodeFlag);
		return batchCodeFlag;
	}

	@Override
	public BatchInfo findBatchByBatchCode(String batchCode) throws PharmaException {
		final String METHOD_NAME = "findBatchByBatchCode";
		BatchInfo batchValues = null;
		System.out.println("Method Invoked: " + METHOD_NAME + ":" + batchCode);
		final String SER_BTC_QRY = "SELECT * FROM pharmaprojdb.batch_info WHERE batch_code=?";
		try (Connection connection = DBConnectionUtility.getConnection();
				PreparedStatement pstStatement = connection.prepareStatement(SER_BTC_QRY)) {
			pstStatement.setString(1, batchCode);
			ResultSet resultSet = pstStatement.executeQuery();
			while (resultSet.next()) {
				batchValues = new BatchInfo();
				batchValues.setBatchCode(resultSet.getString(1));
				batchValues.setMedicineCode(resultSet.getString(2));
				batchValues.setWeight(resultSet.getInt(3));
				batchValues.setPrice(resultSet.getDouble(4));
				batchValues.setMedicineTypeCode(resultSet.getString(5));
				batchValues.setShippingCharge(resultSet.getDouble(6));
				batchValues.setCareLevel(resultSet.getString(7));
			}

		} catch (SQLException e) {
			throw new PharmaException(e);
		}
		return batchValues;
	}

	@Override
	public boolean removeBatchByBacthCode(String batchCode) throws PharmaException {
		final String METHOD_NAME = "removeBatchByBacthCode";
		boolean delFlag = false;
		System.out.println("Method Invoked: " + METHOD_NAME + ":" + batchCode);
		final String PHA_DEL_QRY = "DELETE FROM pharmaprojdb.batch_info WHERE batch_code=?";

		try (Connection connection = DBConnectionUtility.getConnection();
				PreparedStatement pstStatement = connection.prepareStatement(PHA_DEL_QRY)) {
			pstStatement.setString(1, batchCode);
			int rowsAffected = pstStatement.executeUpdate();
			if (rowsAffected > 0) {
				delFlag = true;
			} else {
				throw new PharmaException("Batch Code Not Found in the database..");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return delFlag;
	}

	@Override
	public Set<BatchInfo> allBatchesInfo() throws PharmaException {
		Set<BatchInfo> batchSet = null;
		final String PHAR_DIS_QRY = "SELECT * FROM pharmaprojdb.batch_info";

		try (Connection connection = DBConnectionUtility.getConnection();
				PreparedStatement pstStatement = connection.prepareStatement(PHAR_DIS_QRY)) {
			ResultSet resultSet = pstStatement.executeQuery();
			batchSet = new TreeSet<BatchInfo>();
			while (resultSet.next()) {
				BatchInfo batchValues = new BatchInfo();
				batchValues.setBatchCode(resultSet.getString(1));
				batchValues.setMedicineCode(resultSet.getString(2));
				batchValues.setWeight(resultSet.getInt(3));
				batchValues.setPrice(resultSet.getDouble(4));
				batchValues.setMedicineTypeCode(resultSet.getString(5));
				batchValues.setShippingCharge(resultSet.getDouble(6));
				batchValues.setCareLevel(resultSet.getString(7));
				batchSet.add(batchValues);
			}

		} catch (SQLException e) {
			throw new PharmaException(e);
		} catch (PharmaException e) {
			throw new PharmaException(e);
		}

		return batchSet;
	}

	/**
	 * @param batchCode
	 * @return boolean
	 * @throws PharmaBusinessException
	 */
	@Override
	public boolean validateBatchCode(String batchCode) throws PharmaBusinessException {
		boolean valFlag = false;
		String pattern = "BTC-\\d{4}";
		Pattern regex = Pattern.compile(pattern);
		Matcher matcher = regex.matcher(batchCode);
		valFlag = matcher.matches();
		return valFlag;
	}

	@Override
	public boolean addMedicine(MedicineInfo medicineInfo) throws PharmaException {
		boolean addMedFlag = false;
		String MED_QRY = "INSERT INTO pharmaprojdb.medicine_master VALUES(?,?)";
		try (Connection connection = DBConnectionUtility.getConnection();
				PreparedStatement pstStatement = connection.prepareStatement(MED_QRY)) {
			pstStatement.setString(1, medicineInfo.getMedicineCode());
			pstStatement.setString(2, medicineInfo.getMedicineName());
			int rowsAff = pstStatement.executeUpdate();
			if (rowsAff > 0) {
				addMedFlag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return addMedFlag;
	}

	@Override
	public boolean validateMedicineCode(String medicineCode) throws PharmaBusinessException {
		boolean valFlag = false;
		String pattern = "MC_\\d{3}";
		Pattern regex = Pattern.compile(pattern);
		Matcher matcher = regex.matcher(medicineCode);
		valFlag = matcher.matches();
		return valFlag;
	}

	@Override
	public Set<MedicineInfo> allMedicineInfo() throws PharmaException {
		Set<MedicineInfo> medSet = null;
		final String MED_DIS_QRY = "SELECT * FROM pharmaprojdb.medicine_master ORDER by MEDICINE_CODE";
		try (Connection connection = DBConnectionUtility.getConnection();
				PreparedStatement pstStatement = connection.prepareStatement(MED_DIS_QRY)) {
			ResultSet resultSet = pstStatement.executeQuery();
			medSet = new TreeSet<MedicineInfo>();
			while (resultSet.next()) {
				MedicineInfo medicine = new MedicineInfo();
				medicine.setMedicineCode(resultSet.getString(1));
				medicine.setMedicineName(resultSet.getString(2));
				medSet.add(medicine);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return medSet;
	}

	@Override
	public Set<MedicineInfo> getMedicineByMedCode(String medicineCode) throws PharmaBusinessException {
		Set<MedicineInfo> getSet = null;
		final String MED_DIS_QRY = "SELECT * FROM pharmaprojdb.medicine_master WHERE MEDICINE_CODE=?";
		try (Connection connection = DBConnectionUtility.getConnection();
				PreparedStatement pstStatement = connection.prepareStatement(MED_DIS_QRY)) {
			ResultSet resultSet = pstStatement.executeQuery();
			getSet = new TreeSet<MedicineInfo>();
			while (resultSet.next()) {
				MedicineInfo medicine = new MedicineInfo();
				medicine.setMedicineCode(resultSet.getString(1));
				medicine.setMedicineName(resultSet.getString(2));
				getSet.add(medicine);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (PharmaException e1) {
			e1.printStackTrace();
		}
		return getSet;
	}

}
