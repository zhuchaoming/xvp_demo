package com.lingke.xvp.demo.db.dao;

import com.jfinal.plugin.activerecord.Db;
import com.lingke.xvp.demo.db.dao.base.BaseSeller;
import com.lingke.xvp.demo.utils.SessionUtil;

/**
 * Generated by JFinal.
 */
@SuppressWarnings("serial")
public class Seller extends BaseSeller<Seller> {
	public static final Seller dao = new Seller();

	public static Seller addSeller(String phone, String password) throws Exception {
		Seller seller = getSellerByPhone(phone);
		if (seller != null) {
			throw new RuntimeException("电话号码已经注册");
		}
		seller = new Seller();
		seller.setPassword(password);
		seller.setPhone(phone);
		seller.save();
		return seller;
	}

	public static Seller getSellerByPhone(String phone) {
		return dao.findFirst("select * from seller where phone =?", phone);
	}

	public static Seller getSellerByPhoneAndPassword(String phone, String password) {
		return dao.findFirst("select * from seller where phone =? and password=?", phone, password);
	}

	public static Boolean updateSellerPasswordByPhone(String phone, String password) {
		return Db.update("update seller set password=? where phone =?", password, phone) > 0;
	}
	public static Boolean updateSellerStoreId() {
		return Db.update("update seller set store_id=? where id =?",SessionUtil.sellerGetStoreId(),SessionUtil.getSellerLoginId()) > 0;
	}
}
