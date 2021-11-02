package org.kpu.ticketbox.util;

import java.io.*;
import java.util.*;
import org.kpu.ticketbox.payment.Receipt;

public class BackupWriter {
	public void backupFile(String filename, HashMap<Integer, Receipt> map) {
		try {
			BufferedWriter fout = new BufferedWriter(new FileWriter(filename, true));
			Set<Integer> keys = map.keySet();
			Iterator<Integer> it = keys.iterator();
			while (it.hasNext()) {
				int num = it.next();
				Receipt receipt = map.get(num);
				fout.write(receipt.toBackupString());
			}
			fout.close();
		} 
		catch (IOException e) {
			System.out.println(filename + "에 저장할 수 없었습니다. 경로명을 확인해 주세요");
			return;
		}
	}
}