package detector;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import com.ibm.icu.text.CharsetDetector;
import com.ibm.icu.text.CharsetMatch;

public class DetectEncoding {

	private CharsetMatch cm;
	private String enc;
	
	public DetectEncoding(File file) throws IOException{
		
		FileInputStream fis=null;
		InputStreamReader isr = null;
		BufferedInputStream bis = null;
		
		try {
			fis = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		isr = new InputStreamReader(fis);
		bis = new BufferedInputStream(fis);
		CharsetDetector cd = new CharsetDetector();
		System.out.println(Charset.defaultCharset());
		try {
			cd.setText(bis);
			//cd.
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		cm = cd.detect();
		bis.close();
		isr.close();
		fis.close();

	}
	
	public String getEncoding(){
		if(cm != null){
			return cm.getName();
		}else
			return "Nie udalo sie rozpoznac kodowania";
	}
	
}
