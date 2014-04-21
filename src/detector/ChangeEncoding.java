package detector;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;

public class ChangeEncoding {

	private File tmpFile;
	
	public ChangeEncoding(File file, String defEncoding, String destEncoding) throws IOException{
		
		byte[] buf = null;
		InputStream is = null;
		InputStreamReader isr = null;
		OutputStream os = null;
		OutputStreamWriter osw = null;
		String str = null;
		String str2=null;
		
		tmpFile = new File(file.getAbsolutePath() + "_tmp");
		
		System.out.println(file.getParent());
		
		Charset def = Charset.forName(defEncoding);
		Charset des = Charset.forName(destEncoding);
		System.out.println(def);
		System.out.println(des);

		try {
			is = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		isr = new InputStreamReader(is, def);
		System.out.println("InputStream "+isr.getEncoding());
		try {
			buf = IOUtils.toByteArray(isr, def);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			os = new FileOutputStream(tmpFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		osw = new OutputStreamWriter(os, des);
		System.out.println("OutputStream "+osw.getEncoding());

		try {
			IOUtils.write(buf, osw, des);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			osw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		os.close();
		isr.close();
		is.close();
/*		try {
			str = FileUtils.readFileToString(file, def);
			System.out.println(str);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			FileUtils.write(tmpFile, str, des);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			str2=FileUtils.readFileToString(tmpFile, des);
			System.out.println(str2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		
/*		try {
			processFile(file, tmpFile, def, des);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		file.delete();
		try {
			file.createNewFile();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			processFile(tmpFile, file, des, des);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
	
	private void processFile(File source, File dest, Charset senc, Charset denc) throws IOException{
		
		InputStream is = new FileInputStream(source);
		InputStreamReader isr = new InputStreamReader(is, senc);
		OutputStream os  = new FileOutputStream(dest);
		OutputStreamWriter osr = new OutputStreamWriter(os, denc);
		
		char[] cbuf = new char[1024];
		int len;
		
		while((len = isr.read(cbuf, 0 ,cbuf.length)) > 0){
			osr.write(cbuf, 0, len);
		}
		
		isr.close();
		is.close();
		osr.close();
		os.close();
		
	}
	
	
	public File getTmpfile(){
		return tmpFile;
	}
	
	
	
}

