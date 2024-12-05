package com.admin.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.admin.model.AdmService;
import com.admin.model.AdmVO;


public class AdmServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String str = req.getParameter("admId");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("請輸入管理員編號");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/Back_End/adm/select_adm_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			Integer admId = null;
			try {
				admId = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.add("管理員編號格式不正確");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/Back_End/adm/select_adm_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 2.開始查詢資料 *****************************************/
			AdmService admSvc = new AdmService();
			AdmVO admVO = admSvc.getOneAdm(admId);
			if (admVO == null || admVO.getAdmId() == null) {
			    errorMsgs.add("查無管理員資料");
			    RequestDispatcher failureView = req.getRequestDispatcher("/Back_End/adm/select_adm_page.jsp");
			    failureView.forward(req, res);
			    return;
			}
			
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/Back_End/adm/select_adm_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("admVO", admVO); // 資料庫取出的empVO物件,存入req
			String url = "/Back_End/adm/listOneAdm.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
			successView.forward(req, res);
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ****************************************/
			Integer admId = Integer.valueOf(req.getParameter("admId"));

			/*************************** 2.開始查詢資料 ****************************************/
			AdmService admSvc = new AdmService();
			AdmVO admVO = admSvc.getOneAdm(admId);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			req.setAttribute("admVO", admVO); // 資料庫取出的empVO物件,存入req
			String url = "/Back_End/adm/update_adm_input.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
			successView.forward(req, res);
		}

		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			Integer admId = Integer.valueOf(req.getParameter("admId").trim());

			String admName = req.getParameter("admName");
			String admNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (admName == null || admName.trim().length() == 0) {
				errorMsgs.add("員工姓名: 請勿空白");
			} else if (!admName.trim().matches(admNameReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
			}

			String admEmail = req.getParameter("admEmail");
			String admEmailReg = "^[(a-zA-Z0-9_.@)]{5,30}$";
			if (admName == null || admName.trim().length() == 0) {
				errorMsgs.add("員工Email: 請勿空白");
			} else if (!admEmail.trim().matches(admEmailReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("員工Email: 請輸入正確格式");
			}

			String admPassword = req.getParameter("admPassword");
			String admPasswordReg = "^[(a-zA-Z0-9)]{2,20}$";
			if (admPassword == null || admPassword.trim().length() == 0) {
				errorMsgs.add("員工密碼: 請勿空白");
			} else if (!admPassword.trim().matches(admPasswordReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("員工密碼: 請輸入正確格式");
			}

			String admPhone = req.getParameter("admPhone");
			String admPhoneReg = "^[(0-9)]{10}$";
			if (admPhone == null || admPhone.trim().length() == 0) {
				errorMsgs.add("員工電話: 請勿空白");
			} else if (!admPhone.trim().matches(admPhoneReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("員工電話: 請輸入正確格式");
			}

			Boolean admSta = Boolean.valueOf(req.getParameter("admSta"));

			java.sql.Date hrDate = null;
			try {
				hrDate = java.sql.Date.valueOf(req.getParameter("hrDate").trim());
			} catch (IllegalArgumentException e) {
				errorMsgs.add("請輸入雇用日期");
			}

			java.sql.Date admBday = null;
			try {
				admBday = java.sql.Date.valueOf(req.getParameter("admBday").trim());
			} catch (IllegalArgumentException e) {
				errorMsgs.add("請輸入生日");
			}

			Boolean supvsr = Boolean.valueOf(req.getParameter("supvsr").trim());

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

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("admVO", admVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/Back_End/adm/update_adm_input.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}

			/*************************** 2.開始修改資料 *****************************************/
			AdmService admSvc = new AdmService();
			admVO = admSvc.updateAdm(admId, admEmail, admPassword, admName, admPhone, admSta, hrDate, admBday, supvsr);

			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			req.setAttribute("admVO", admVO); // 資料庫update成功後,正確的的empVO物件,存入req
			String url = "/Back_End/adm/listOneAdm.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
			successView.forward(req, res);
		}

		if ("insert".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String admName = (req.getParameter("admName") == null) ? "" : req.getParameter("admName").trim();
				String admEmail = (req.getParameter("admEmail") == null) ? "" : req.getParameter("admEmail").trim();
				String admPassword = (req.getParameter("admPassword") == null) ? ""
						: req.getParameter("admPassword").trim();
				String admPhone = (req.getParameter("admPhone") == null) ? "" : req.getParameter("admPhone").trim();

				// 1. 名稱驗證
				if (admName.trim().length() == 0) {
					errorMsgs.add("管理員姓名: 請勿空白");
				}

				// 2. Email驗證
				if (admEmail.trim().length() == 0) {
					errorMsgs.add("Email: 請勿空白");
				}

				// 3. 密碼驗證
				if (admPassword.trim().length() == 0) {
					errorMsgs.add("密碼: 請勿空白");
				}

				// 4. 電話驗證
				if (admPhone.trim().length() == 0) {
					errorMsgs.add("電話: 請勿空白");
				}

				// 5. 員工狀態
				Boolean admSta = null;
				try {
					admSta = Boolean.valueOf(req.getParameter("admSta"));
				} catch (Exception e) {
					admSta = false;
					errorMsgs.add("員工狀態: 資料錯誤");
				}

				// 6. 日期處理
				java.sql.Date hrDate = null;
				try {
					String hrDateStr = req.getParameter("hrDate");
					if (hrDateStr != null && !hrDateStr.trim().isEmpty()) {
						hrDate = java.sql.Date.valueOf(hrDateStr);
					} else {
						errorMsgs.add("請輸入雇用日期");
					}
				} catch (IllegalArgumentException e) {
					hrDate = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("雇用日期格式錯誤");
				}

				java.sql.Date admBday = null;
				try {
					String admBdayStr = req.getParameter("admBday");
					if (admBdayStr != null && !admBdayStr.trim().isEmpty()) {
						admBday = java.sql.Date.valueOf(admBdayStr);
					} else {
						errorMsgs.add("請輸入生日");
					}
				} catch (IllegalArgumentException e) {
					admBday = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("生日格式錯誤");
				}

				// 7. 主管狀態
				Boolean supvsr = null;
				try {
					supvsr = Boolean.valueOf(req.getParameter("supvsr"));
				} catch (Exception e) {
					supvsr = false;
					errorMsgs.add("主管狀態: 資料錯誤");
				}

				// 封裝資料
				AdmVO admVO = new AdmVO();
				admVO.setAdmName(admName);
				admVO.setAdmEmail(admEmail);
				admVO.setAdmPassword(admPassword);
				admVO.setAdmPhone(admPhone);
				admVO.setAdmSta(admSta);
				admVO.setHrDate(hrDate);
				admVO.setAdmBday(admBday);
				admVO.setSupvsr(supvsr);

				// 若有錯誤，返回原頁面
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("admVO", admVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/Back_End/adm/addAdm.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				AdmService admSvc = new AdmService();
				admVO = admSvc.addAdm(admEmail, admPassword, admName, admPhone, admSta, hrDate, admBday, supvsr);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/Back_End/adm/listAllAdm.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("新增資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/Back_End/adm/addAdm.jsp");
				failureView.forward(req, res);
			}
		}

		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ***************************************/
			Integer admId = Integer.valueOf(req.getParameter("admId"));

			/*************************** 2.開始刪除資料 ***************************************/
			AdmService admSvc = new AdmService();
			admSvc.deleteAdm(admId);

			/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
			String url = "/Back_End/adm/listAllAdm.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
			successView.forward(req, res);
		}
	}
}
