package gr.unipi.evaluate.model;

import java.math.BigInteger;

public class PublicKeyDetails {
	private BigInteger modulus;
	private BigInteger exponent;
	
	public PublicKeyDetails(BigInteger modulus,BigInteger exponent){
		this.modulus = modulus;
		this.exponent = exponent;
	}
	public BigInteger getModulus() {
		return modulus;
	}
	public void setModulus(BigInteger modulus) {
		this.modulus = modulus;
	}
	public BigInteger getExponent() {
		return exponent;
	}
	public void setExponent(BigInteger exponent) {
		this.exponent = exponent;
	}
}
