import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
	static final String[] AnswersTexts = {
			"Answer\\:  \\line \\sect\\sbkcol\\pard \\plain\\f185\\fs20\\cf0\\pard \\plain\\f185\\fs20\\cf0 A \\par\\sect\\sectd\\sbknone",
			"Answer\\:  \\line \\sect\\sbkcol\\pard \\plain\\f185\\fs20\\cf0\\pard \\plain\\f185\\fs20\\cf0 B \\par\\sect\\sectd\\sbknone",
			"Answer\\:  \\line \\sect\\sbkcol\\pard \\plain\\f185\\fs20\\cf0\\pard \\plain\\f185\\fs20\\cf0 C \\par\\sect\\sectd\\sbknone",
			"Answer\\:  \\line \\sect\\sbkcol\\pard \\plain\\f185\\fs20\\cf0\\pard \\plain\\f185\\fs20\\cf0 D \\par\\sect\\sectd\\sbknone",
			"Answer\\:  \\line \\sect\\sbkcol \\pard {\\*\\shppict{\\pict\\jpegblip\\picscalex75\\picscaley75\\picw8\\pich8\\picwgoal150\\pichgoal150ffd8ffe000104a46494600010100000100010000ffdb0043000302020302020303030304030304050805050404050a070706080c0a0c0c0b0a0b0b0d0e12100d0e110e0b0b1016101113141515150c0f171816141812141514ffdb00430103040405040509050509140d0b0d1414141414141414141414141414141414141414141414141414141414141414141414141414141414141414141414141414ffc00011080008000803012200021101031101ffc4001f0000010501010101010100000000000000000102030405060708090a0bffc400b5100002010303020403050504040000017d01020300041105122131410613516107227114328191a1082342b1c11552d1f02433627282090a161718191a25262728292a3435363738393a434445464748494a535455565758595a636465666768696a737475767778797a838485868788898a92939495969798999aa2a3a4a5a6a7a8a9aab2b3b4b5b6b7b8b9bac2c3c4c5c6c7c8c9cad2d3d4d5d6d7d8d9dae1e2e3e4e5e6e7e8e9eaf1f2f3f4f5f6f7f8f9faffc4001f0100030101010101010101010000000000000102030405060708090a0bffc400b51100020102040403040705040400010277000102031104052131061241510761711322328108144291a1b1c109233352f0156272d10a162434e125f11718191a262728292a35363738393a434445464748494a535455565758595a636465666768696a737475767778797a82838485868788898a92939495969798999aa2a3a4a5a6a7a8a9aab2b3b4b5b6b7b8b9bac2c3c4c5c6c7c8c9cad2d3d4d5d6d7d8d9dae2e3e4e5e6e7e8e9eaf2f3f4f5f6f7f8f9faffda000c03010002110311003f00f785f105e596a26390de6a5f192eee841158a40f1cd6530625e5793a3c0570140f936939e9451457e7d96e0557f6a954947964d7bb2b5fcdef76ff00e1ac8fce72cc27d6fdaaf6b3872c9af7656bf9bdeedf57f7248fffd9}} \\line \\sect\\sbkcol\\pard \\plain\\f185\\fs20\\cf0\\pard \\plain\\f185\\fs20\\cf0 True \\line \\sect\\sbkcol\\pard \\plain\\f185\\fs20\\cf0\\pard \\plain\\f185\\fs20\\cf0 False \\par\\sect\\sectd\\sbknone \\cols1\\colno1\\colw9720 \\pard\\lin900 \\plain\\f185\\fs20\\cf0",
			"\\plain\\f185\\fs20\\cf0 Answer\\:  \\line \\sect\\sbkcol\\pard \\plain\\f185\\fs20\\cf0\\pard \\plain\\f185\\fs20\\cf0 True \\line \\sect\\sbkcol \\pard {\\*\\shppict{\\pict\\jpegblip\\picscalex75\\picscaley75\\picw8\\pich8\\picwgoal150\\pichgoal150ffd8ffe000104a46494600010100000100010000ffdb0043000302020302020303030304030304050805050404050a070706080c0a0c0c0b0a0b0b0d0e12100d0e110e0b0b1016101113141515150c0f171816141812141514ffdb00430103040405040509050509140d0b0d1414141414141414141414141414141414141414141414141414141414141414141414141414141414141414141414141414ffc00011080008000803012200021101031101ffc4001f0000010501010101010100000000000000000102030405060708090a0bffc400b5100002010303020403050504040000017d01020300041105122131410613516107227114328191a1082342b1c11552d1f02433627282090a161718191a25262728292a3435363738393a434445464748494a535455565758595a636465666768696a737475767778797a838485868788898a92939495969798999aa2a3a4a5a6a7a8a9aab2b3b4b5b6b7b8b9bac2c3c4c5c6c7c8c9cad2d3d4d5d6d7d8d9dae1e2e3e4e5e6e7e8e9eaf1f2f3f4f5f6f7f8f9faffc4001f0100030101010101010101010000000000000102030405060708090a0bffc400b51100020102040403040705040400010277000102031104052131061241510761711322328108144291a1b1c109233352f0156272d10a162434e125f11718191a262728292a35363738393a434445464748494a535455565758595a636465666768696a737475767778797a82838485868788898a92939495969798999aa2a3a4a5a6a7a8a9aab2b3b4b5b6b7b8b9bac2c3c4c5c6c7c8c9cad2d3d4d5d6d7d8d9dae2e3e4e5e6e7e8e9eaf2f3f4f5f6f7f8f9faffda000c03010002110311003f00f785f105e596a26390de6a5f192eee841158a40f1cd6530625e5793a3c0570140f936939e9451457e7d96e0557f6a954947964d7bb2b5fcdef76ff00e1ac8fce72cc27d6fdaaf6b3872c9af7656bf9bdeedf57f7248fffd9}} \\line \\sect\\sbkcol\\pard \\plain\\f185\\fs20\\cf0\\pard \\plain\\f185\\fs20\\cf0 False \\par\\sect\\sectd\\sbknone"
			
	};
	
	public static String removeFromLine (String line) {
		for (int i=0;i<AnswersTexts.length;i++) {
			String answerText = AnswersTexts[i];
			line = line.replace(answerText, "");
		}
		return line;
	}
	public static void cleanFile(String fileName) throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader("Input/"+fileName));
		PrintWriter writer = new PrintWriter(new FileWriter("Output/"+fileName));
		
		while (reader.ready()) {
			String line = reader.readLine();
			String cleanedLine = removeFromLine(line);
			writer.write(cleanedLine);
			writer.write("\n");
		}
		reader.close();
		writer.flush();
		writer.close();
	}
	public static String[] getListOfFileNames() {
		File inputDirectory = new File("Input");
		return inputDirectory.list();
	}
	
	public static void cleanAllFiles(String[] listOfFileNames) throws IOException{
		for (int i=0;i<listOfFileNames.length;i++) {
			cleanFile(listOfFileNames[i]);
		}
		return;
	}
	
	public static void main(String[] args) throws IOException{
		cleanAllFiles(getListOfFileNames());		
	}
}
