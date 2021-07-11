package com.kakao.pay.vo;

import org.apache.commons.lang3.StringUtils;

public class CardVo {
	
	private String id;
	private String name;
	/** 관리번호 */
	private String uid;
	/** 카드번호 */
	private String cardNo;
	/** 유효기간 */
	private String mmyy;
	/** cvc */
	private String cvc;
	/** 할부개월수 */
	private String installment;
	/** 결제금액 */
	private String payment;
	/** 부가가치세 */
	private String vat;
	/** 결제 관리번호 */
	private String pUid;
	/** 암호화된 카드 정보 */
	private String cardEnc;
	/** String 데이터 */
	private String stringData;
	/** 결재 구분 */
	private String gubun;
	
	public String getId() {
		return id;
	}
	
	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public void setId(String id) {
		this.id = id; 
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getMmyy() {
		return mmyy;
	}

	public void setMmyy(String mmyy) {
		this.mmyy = mmyy;
	}

	public String getCvc() {
		return cvc;
	}

	public void setCvc(String cvc) {
		this.cvc = cvc;
	}

	public String getInstallment() {
		return installment;
	}

	public void setInstallment(String installment) {
		this.installment = installment;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public String getVat() {
		return vat;
	}

	public void setVat(String vat) {
		this.vat = vat;
	}

	public String getpUid() {
		return pUid;
	}

	public void setpUid(String pUid) {
		this.pUid = pUid;
	}

	public String getCardEnc() {
		return cardEnc;
	}

	public void setCardEnc(String cardEnc) {
		this.cardEnc = cardEnc;
	}

	public String getStringData() {
		return stringData;
	}

	public void setStringData(String stringData) {
		this.stringData = stringData;
	}

	public String getGubun() {
		return gubun;
	}

	public void setGubun(String gubun) {
		this.gubun = gubun;
	}
	
	
}