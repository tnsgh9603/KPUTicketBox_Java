package org.kpu.ticketbox.util;

import java.util.*;
import org.kpu.ticketbox.payment.Receipt;

public class Statistics {
	public static int cnt = 0;

	// ��ũ�� �� ���� �ݾ� �Ѿ� ���
	public static double sum(HashMap<Integer, Receipt> map) {
		Set<Integer> keys = map.keySet();
		Iterator<Integer> it = keys.iterator();
		double sum = 0;
		while (it.hasNext()) {
			cnt++;
			int num = it.next();
			Receipt receipt = map.get(num);
			sum += receipt.get_totalAmount();
		}
		return sum;
	}
}
