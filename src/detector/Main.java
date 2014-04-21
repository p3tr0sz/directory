package detector;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.SortedMap;

import com.ibm.icu.text.CharsetDetector;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SortedMap<String, Charset> sm;
		File file = new File(args[0]);
		String charset = args[1];
		
		DetectEncoding de=null;
		try {
			de = new DetectEncoding(file);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		System.out.println("Kodowanie: " + de.getEncoding());
		
		ChangeEncoding ce = null;
		try {
			ce = new ChangeEncoding(file, de.getEncoding(), charset);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Zmiana kodowania na: " + Charset.forName(charset));
				
		DetectEncoding de2=null;
		try {
			de2 = new DetectEncoding(ce.getTmpfile());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Kodowanie wyjsciowe: " + de2.getEncoding());
		sm = Charset.availableCharsets();
		System.out.println(sm.toString());
		System.out.println(CharsetDetector.getAllDetectableCharsets());

	}

}
