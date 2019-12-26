package com.wanara.tools;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.zip.*;

/**
 * zlib compress and decompress
 * @apiNote https://thiscouldbebetter.wordpress.com/2011/08/26/compressing-and-uncompressing-data-in-java-using-zlib/
 */
public class ZlibTool {
	public static byte[] compress(File target) {
		try (FileInputStream fileInputStream = new FileInputStream(target)) {
			return compress(CommonUtil.readInputStream(fileInputStream));
		}catch (IOException e){
			e.printStackTrace();
		}
		return null;
	}
	public static byte[] compress(byte[] bytesToCompress) {
		Deflater deflater = new Deflater();
		deflater.setInput(bytesToCompress);
		deflater.finish();

		byte[] bytesCompressed = new byte[Short.MAX_VALUE];
		int numberOfBytesAfterCompression = deflater.deflate(bytesCompressed);

		byte[] returnValues = new byte[numberOfBytesAfterCompression];
		System.arraycopy(bytesCompressed, 0, returnValues, 0, numberOfBytesAfterCompression);
		return returnValues;
	}

	public static byte[] compress(String stringToCompress) {
		return compress(stringToCompress.getBytes(StandardCharsets.UTF_8));
	}

	public static byte[] decompress(byte[] bytesToDecompress) {
		byte[] returnValues = null;
		Inflater inflater = new Inflater();
		int numberOfBytesToDecompress = bytesToDecompress.length;
		inflater.setInput(bytesToDecompress, 0, numberOfBytesToDecompress);

		List<Byte> bytesDecompressedSoFar = new ArrayList<>();

		try {
			while (!inflater.needsInput()) {
				byte[] bytesDecompressedBuffer = new byte[numberOfBytesToDecompress];
				int numberOfBytesDecompressedThisTime = inflater.inflate(bytesDecompressedBuffer);
				for (int b = 0; b < numberOfBytesDecompressedThisTime; b++) {
					bytesDecompressedSoFar.add(bytesDecompressedBuffer[b]);
				}
			}
			returnValues = new byte[bytesDecompressedSoFar.size()];
			for (int b = 0; b < returnValues.length; b++) {
				returnValues[b] = bytesDecompressedSoFar.get(b);
			}
		} catch (DataFormatException dfe) {
			dfe.printStackTrace();
		}

		inflater.end();
		return returnValues;
	}

	public static String decompressToString(byte[] bytesToDecompress) {
		byte[] bytesDecompressed = decompress(bytesToDecompress);
		return new String(bytesDecompressed, 0, bytesDecompressed.length, StandardCharsets.UTF_8);
	}
}