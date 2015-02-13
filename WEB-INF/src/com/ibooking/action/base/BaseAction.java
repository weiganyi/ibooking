package com.ibooking.action.base;

import java.util.ArrayList;

import com.ibooking.po.*;
import com.ibooking.service.*;
import com.ibooking.util.WebConstant;
import com.ibooking.vo.*;
import com.ibooking.vo.manager.*;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;


public class BaseAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected static final String RET_SUCC = "success";
	protected static final String RET_FAIL = "failure";
	
	protected DaoService daoService;

	//pass to the *.jsp
	protected String failReason;

	//pass to the *.jsp
	protected String strTitle;

	//the http request param
	protected String page;

	protected static final Integer defaultMinPageNum = 1;
	//pass to the jsp
	protected int currPage;
	protected int startPage;
	protected int endPage;
	protected int maxPage;
	protected String destPage;

	//pass to the index.jsp
	protected ArrayList<MenuTypeBean> lstMenuTypeBean;

	//pass to the shopping.jsp
	protected ArrayList<Shopping> lstShoppingBean;
	protected String strAddress;
	protected int totalPrice;

	//pass to the orderlist.jsp
	protected ArrayList<OrderBean> lstOrderListBean;

	//pass to the man_user.jsp
	protected ArrayList<User> lstUserBean;

	//pass to the man_order.jsp
	protected ArrayList<ManOrderBean> lstOrderBean;
	
	//pass to the orderdetail.jsp and man_orderdetail.jsp
	protected ArrayList<OrderDetail> lstOrderDetail;

	//pass to the man_pic.jsp
	protected ArrayList<ManPicBean> lstPicBean;

	public String fillTitle() {
		strTitle = daoService.getTitle();
		if (strTitle == null || strTitle.isEmpty()) {
			return RET_FAIL;
		}
		
		return RET_SUCC;
	}

	public String fillIndexPage() {
		IndexPageBean clsIndexPageBean;

		if (page == null || page.length() == 0) {
			currPage =  defaultMinPageNum;
		}else {		
			currPage = Integer.valueOf(page);
		}
		
		ActionContext ctx = ActionContext.getContext();
		String userName = (String)ctx.getSession().get(WebConstant.LOGIN_USER);
		
		clsIndexPageBean = daoService.getIndexPageBean(currPage, userName);
		if (clsIndexPageBean != null && clsIndexPageBean.getLst().size() != 0) {
			lstMenuTypeBean = clsIndexPageBean.getLst();
			startPage = clsIndexPageBean.getStartPage();
			endPage = clsIndexPageBean.getEndPage();
			maxPage = clsIndexPageBean.getMaxPage();
			destPage = "indexPageEnter";

			return RET_SUCC;
		}else {
			return RET_FAIL;
		}
	}

	public String fillShoppingPage() {
		ShoppingPageBean clsShoppingPageBean;
		
		if (page == null || page.length() == 0) {
			currPage =  defaultMinPageNum;
		}else {		
			currPage = Integer.valueOf(page);
		}
		
		ActionContext ctx = ActionContext.getContext();
		String userName = (String)ctx.getSession().get(WebConstant.LOGIN_USER);

		strAddress = daoService.getUserAddrByName(userName);

		clsShoppingPageBean = daoService.getShoppingPageBean(currPage, userName);
		if (clsShoppingPageBean.getLst().size() != 0) {
			lstShoppingBean = clsShoppingPageBean.getLst();
		}
		startPage = clsShoppingPageBean.getStartPage();
		endPage = clsShoppingPageBean.getEndPage();
		maxPage = clsShoppingPageBean.getMaxPage();
		destPage = "shoppingPageEnter";
		totalPrice = clsShoppingPageBean.getTotalPrice();
		
		return RET_SUCC;
	}

	public String fillOrderListPage() {
		OrderListPageBean clsOrderListPageBean;
		
		if (page == null || page.length() == 0) {
			currPage =  defaultMinPageNum;
		}else {		
			currPage = Integer.valueOf(page);
		}
		
		ActionContext ctx = ActionContext.getContext();
		String userName = (String)ctx.getSession().get(WebConstant.LOGIN_USER);

		clsOrderListPageBean = daoService.getOrderListPageBean(currPage, userName);
		if (clsOrderListPageBean.getLst().size() != 0) {
			lstOrderListBean = clsOrderListPageBean.getLst();
		}
		startPage = clsOrderListPageBean.getStartPage();
		endPage = clsOrderListPageBean.getEndPage();
		maxPage = clsOrderListPageBean.getMaxPage();
		destPage = "orderListPageEnter";
		
		return RET_SUCC;
	}

	public String fillOrderDetailPage(int orderId) {
		lstOrderDetail = daoService.getOrderDetailByOrderId(orderId);

		return RET_SUCC;
	}

	public String fillManUserPage() {
		ManUserPageBean clsManUserPageBean;
		
		if (page == null || page.length() == 0) {
			currPage =  defaultMinPageNum;
		}else {		
			currPage = Integer.valueOf(page);
		}

		clsManUserPageBean = daoService.getManUserPageBean(currPage);
		if (clsManUserPageBean.getLst().size() != 0) {
			lstUserBean = clsManUserPageBean.getLst();
		}
		startPage = clsManUserPageBean.getStartPage();
		endPage = clsManUserPageBean.getEndPage();
		maxPage = clsManUserPageBean.getMaxPage();
		destPage = "manUserPageEnter";
		
		return RET_SUCC;
	}

	public String fillManOrderPage() {
		ManOrderPageBean clsManOrderPageBean;
		
		if (page == null || page.length() == 0) {
			currPage =  defaultMinPageNum;
		}else {		
			currPage = Integer.valueOf(page);
		}

		clsManOrderPageBean = daoService.getManOrderPageBean(currPage);
		if (clsManOrderPageBean.getLst().size() != 0) {
			lstOrderBean = clsManOrderPageBean.getLst();
		}
		startPage = clsManOrderPageBean.getStartPage();
		endPage = clsManOrderPageBean.getEndPage();
		maxPage = clsManOrderPageBean.getMaxPage();
		destPage = "manOrderPageEnter";
		
		return RET_SUCC;
	}

	public String fillManPicPage() {
		ManPicPageBean clsManPicPageBean;
		
		if (page == null || page.length() == 0) {
			currPage =  defaultMinPageNum;
		}else {		
			currPage = Integer.valueOf(page);
		}

		clsManPicPageBean = daoService.getManPicPageBean(currPage);
		if (clsManPicPageBean.getLst().size() != 0) {
			lstPicBean = clsManPicPageBean.getLst();
		}
		startPage = clsManPicPageBean.getStartPage();
		endPage = clsManPicPageBean.getEndPage();
		maxPage = clsManPicPageBean.getMaxPage();
		destPage = "manPicPageEnter";
		
		return RET_SUCC;
	}

	public DaoService getDaoService() {
		return daoService;
	}

	public void setDaoService(DaoService daoService) {
		this.daoService = daoService;
	}
	
	public String getFailReason() {
		return failReason;
	}

	public void setFailReason(String failReason) {
		this.failReason = failReason;
	}
	
	public String getStrTitle() {
		return strTitle;
	}

	public void setStrTitle(String strTitle) {
		this.strTitle = strTitle;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public int getCurrPage() {
		return currPage;
	}

	public void setCurrPage(int currPage) {
		this.currPage = currPage;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public int getMaxPage() {
		return maxPage;
	}

	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}

	public String getDestPage() {
		return destPage;
	}

	public void setDestPage(String destPage) {
		this.destPage = destPage;
	}

	public ArrayList<MenuTypeBean> getLstMenuTypeBean() {
		return lstMenuTypeBean;
	}

	public void setLstMenuTypeBean(ArrayList<MenuTypeBean> lstMenuTypeBean) {
		this.lstMenuTypeBean = lstMenuTypeBean;
	}

	public ArrayList<Shopping> getLstShoppingBean() {
		return lstShoppingBean;
	}

	public void setLstShoppingBean(ArrayList<Shopping> lstShoppingBean) {
		this.lstShoppingBean = lstShoppingBean;
	}

	public String getStrAddress() {
		return strAddress;
	}

	public void setStrAddress(String strAddress) {
		this.strAddress = strAddress;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public ArrayList<OrderBean> getLstOrderListBean() {
		return lstOrderListBean;
	}

	public void setLstOrderListBean(ArrayList<OrderBean> lstOrderListBean) {
		this.lstOrderListBean = lstOrderListBean;
	}

	public ArrayList<User> getLstUserBean() {
		return lstUserBean;
	}

	public void setLstUserBean(ArrayList<User> lstUserBean) {
		this.lstUserBean = lstUserBean;
	}

	public ArrayList<ManOrderBean> getLstOrderBean() {
		return lstOrderBean;
	}

	public void setLstOrderBean(ArrayList<ManOrderBean> lstOrderBean) {
		this.lstOrderBean = lstOrderBean;
	}

	public ArrayList<OrderDetail> getLstOrderDetail() {
		return lstOrderDetail;
	}

	public void setLstOrderDetail(ArrayList<OrderDetail> lstOrderDetail) {
		this.lstOrderDetail = lstOrderDetail;
	}

	public ArrayList<ManPicBean> getLstPicBean() {
		return lstPicBean;
	}

	public void setLstPicBean(ArrayList<ManPicBean> lstPicBean) {
		this.lstPicBean = lstPicBean;
	}
}
