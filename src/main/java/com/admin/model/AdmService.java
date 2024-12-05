package com.admin.model;

import java.sql.Date;
import java.util.List;

public class AdmService {
	private AdmDAO_interface dao;

	public AdmService() {
		dao = new AdmDAO();
	}

	public AdmVO addAdm(String admEmail, String admPassword, String admName, String admPhone, Boolean admSta,
			Date hrDate, Date admBday, Boolean supvsr) {

		AdmVO admVO = new AdmVO();

		admVO.setAdmEmail(admEmail);
		admVO.setAdmPassword(admPassword);
		admVO.setAdmName(admName);
		admVO.setAdmPhone(admPhone);
		admVO.setAdmSta(admSta);
		admVO.setHrDate(hrDate);
		admVO.setAdmBday(admBday);
		admVO.setSupvsr(supvsr);
		dao.insert(admVO);

		return admVO;
	}

	public AdmVO updateAdm(Integer admId, String admEmail, String admPassword, String admName, String admPhone,
			Boolean admSta, Date hrDate, Date admBday, Boolean supvsr) {

		AdmVO admVO = new AdmVO();

		admVO.setAdmId(admId);
		admVO.setAdmEmail(admEmail);
		admVO.setAdmPassword(admPassword);
		admVO.setAdmName(admName);
		admVO.setAdmPhone(admPhone);
		admVO.setAdmSta(admSta);
		admVO.setHrDate(hrDate);
		admVO.setAdmBday(admBday);
		admVO.setSupvsr(supvsr);
		dao.update(admVO);

		return admVO;
	}

	public void deleteAdm(Integer admId) {
		dao.delete(admId);
	}

	public AdmVO getOneAdm(Integer admId) {
		return dao.findByPrimaryKey(admId);
	}

	public List<AdmVO> getAll() {
		return dao.getAll();
	}
}
