package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ArquivoUtils {
	
	public static byte[] bytesFromFile(File file) throws IOException {
		FileInputStream is = new FileInputStream(file);
		long length = file.length();

		if (length > Integer.MAX_VALUE) {
			System.out.println("Sorry! Your given file is too large.");
			System.exit(0);
		}

		byte[] bytes = new byte[(int)length];
		int offset = 0;
		int numRead = 0;
		while (offset < bytes.length && (numRead=is.read(bytes, 
				offset, bytes.length-offset)) >= 0) {
			offset += numRead;
		}
		if (offset < bytes.length) {
			throw new IOException("Could not completely read file "
					+ file.getName());
		}
		is.close();
		return bytes;
	}

}
