package gr.unipi.lecturer.model;

import java.math.BigInteger;

public class PrivateKeyDetails {
	private BigInteger modulus;
	private BigInteger privateExponent;
	
	public PrivateKeyDetails(BigInteger modulus, BigInteger privateExponent){
		this.modulus = modulus;
		this.privateExponent = privateExponent;
	}
	public BigInteger getModulus() {
		return modulus;
	}
	public void setModulus(BigInteger modulus) {
		this.modulus = modulus;
	}
	public BigInteger getPrivateExponent() {
		return privateExponent;
	}
	public void setPrivateExponent(BigInteger privateExponent) {
		this.privateExponent = privateExponent;
	}
}
