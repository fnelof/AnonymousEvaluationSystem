package gr.unipi.issue.dao;

import java.math.BigInteger;

public interface StudentDao {
	public BigInteger getUidFromUsername(String uid);
}
