package alphabetimal;
import javax.swing.*;
import java.awt.event.*;
import java.math.*;

public class alpha extends JFrame implements ActionListener {

	public static String[] chara = new String[255];
	public static String[] zeichen = new String[10000];
	public static String[] leer = new String[10000]; 
	public static String letter = "0";
	public static String input = "0";
	public static String lers = "0";
	public static int arix = 0;
	public static int z = 0;
	public static int zc = 0;
	public static int ctrl = 0;
	public static int len = 0;
	public static int nr = 0;
	public static int lz = 0;
	public static int izwo = 0;
	public static BigDecimal deci = new BigDecimal("0");
	public static BigDecimal resoch = new BigDecimal("0");
	
	java.awt.Dimension size = java.awt.Toolkit.getDefaultToolkit().getScreenSize(); //Textfeld
	public double width = size.getWidth();
	public double height = size.getHeight();
	
	JButton b;
	JTextArea area;
	JScrollPane scroll;
	JFrame f;
     alpha(String content) {  
        f= new JFrame();
        area=new JTextArea(content);
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        b = new JButton("Ok");
        scroll = new JScrollPane(area,
        		ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
        	    ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        b.setBounds((int) width/2,(int) height-100,120,30); 
        b.addActionListener(this);
        
        scroll.setBounds(20,20, ((int) width)-100,(int) height-200);  
        f.add(scroll);f.add(b);  
        f.setSize((int) width,(int) height);  
        f.setLayout(null);  
        f.setVisible(true);  
     }  																		//Ende Textfeld
     
     public void actionPerformed(ActionEvent e){  
    	 f.dispose();
    	 eingabe();
//    	 f.dispatchEvent(new WindowEvent(f, WindowEvent.WINDOW_CLOSING));
 	 }  
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		eingabe();
	}
	
	
	static void eingabe() {
		input = JOptionPane.showInputDialog("(1) HexaBidec -> Dec\n(2) Dec -> HexaBidec");
		if (input.equals("1")) {	//input = 1
			int bin = 0;
			int i = 0;
			String res = "";
			arix = 0;
			zc = 0;
			z = 0;
			ctrl = 0;
			
			input = JOptionPane.showInputDialog("Text",res);
			if (input.equals("")) {
//					JOptionPane.showMessageDialog(null, "...");
				JOptionPane.showMessageDialog(null, "...");
				return;
			}
			
			for (z = 1; z<=input.length(); z++) {
				if (!isLet(input.substring(ctrl, z))) {
					zeichen[arix] = input.substring(ctrl,z);
					leer[arix] = makl(input.substring(zc,z-1));
					zc = z;
					arix++;
				}
				ctrl++;
			}
			
			zeichen[arix] = "";
			leer[arix] = makl(input.substring(zc,z-1));
			
			for (int h=0;h<=arix;h++) {
				if (isLet(leer[h])) {
					if (!leer[h].equals("")) {
						res = res + one(leer[h]) + zeichen[h];
					} else {
						res = res + leer[h] + zeichen[h];
					}
				}
			}
			new alpha(res);
		} else if (input.equals("2")) {		//input = 2
			int bin = 0;
			int i = 0;
			z = 0;
			ctrl = 0;
			arix = 0;
			zc = 0;
			String res = "";
			input = JOptionPane.showInputDialog("Zahl");
			if (input.equals("")) {								//leer?
//					JOptionPane.showMessageDialog(null, "...");
				JOptionPane.showMessageDialog(null, "...");
				return;
			}
			
			for (z = 1; z<=input.length(); z++) {
				if (!isNum(input.substring(ctrl, z))) {
					zeichen[arix] = input.substring(ctrl,z);
					leer[arix] = input.substring(zc,z-1);
					zc = z;
					arix++;
				}
				ctrl++;
			}
			
			zeichen[arix] = "";
			leer[arix] = input.substring(zc,z-1);
			
			for (int h=0;h<=arix;h++) {
				if (isNum(leer[h])) {
					if (!leer[h].equals("")) {
						res = res + two(leer[h]) + zeichen[h];
					} else {
						res = res + leer[h] + zeichen[h];
					}
				}
			}
//				JOptionPane.showMessageDialog(null, res);
			new alpha(res);
			return;
		} else {
//				JOptionPane.showMessageDialog(null, "Ende");
			JOptionPane.showMessageDialog(null, "Ende");
			return;
		}
	
	}
	
	
	static String one(String hex) {
		len = hex.length();
		izwo=0;
		deci = new BigDecimal("0");
		String dec="";
		int bin = 0;
		
		for (int i = 0; i < hex.length(); i++) { //falls alles nur a

			if (hex.substring(i,i+1).equals("a")) {
				dec = "0" + dec;
			} else {
				bin = 1;
				break;
			}
		}	// Ende der for Schleife
		if (bin == 1) {
			dec = "";
			for (int i=len-1; i >= 0; i--) {
				letter = hex.substring(izwo, izwo+1);
	
				if (i == 0) {							//letzter Durchlauf?
					lz = ((int) letter.charAt(0))-97;
					deci = deci.add(BigDecimal.valueOf(lz));
				} else {
					lz = ((int) letter.charAt(0))-97;

					resoch = hoch(26,i);
					resoch=resoch.multiply(BigDecimal.valueOf(lz));
					deci=deci.add(resoch);
				}
				
				izwo++;
			}
			dec = String.valueOf(deci);
			
			if (hex.substring(0,1).equals("a")) {	//am ende nur a
				for (int i=0; i<=hex.length(); i++) {
				
					if (hex.substring(i,i+1).equals("a")) { 	//nur a
						dec = "0" + dec;
					} else {
						break;
					}
				}
			}
		}
				
		return(dec);
	}
	
	static String two(String dev) {
		BigDecimal hexa = new BigDecimal(dev);
		BigDecimal th = new BigDecimal("0");
		lers="";
		int i = 0;
		int bin = 0;
		for (int h = 0; h < dev.length(); h++) { //falls alles nur 0

			if (dev.substring(h,h+1).equals("0")) {
				lers = "a" + lers;
			} else {
				bin = 1;
				break;
			}
		}	// Ende der for Schleife
		
		if (bin == 1) {	
			lers = "";
			double doub = hexa.doubleValue();
			double thoub = 0;
			while (doub > 26) {
				i=i+1;
				doub =  doub / 26;
			}
			
			for (i = i; i>=0;i=i-1) {
				th = new BigDecimal("1");
				if (i == 0) {		//letzter Durchlauf?
					th = new BigDecimal("1");
				} else {
					th = hoch(26,i);
				}
	
				doub = hexa.doubleValue();
				thoub = th.doubleValue();
				
				lers = lers + String.valueOf((char) ((((hexa.subtract((hexa.remainder(th)))).divide(th, RoundingMode.UNNECESSARY)).add(BigDecimal.valueOf(97))).intValue()));
				hexa = hexa.subtract((((hexa.subtract((hexa.remainder(th)))).divide(th)).multiply(th)));
			}
			
			if (dev.substring(0,1).equals("0")) { //endnullen
				for (i=0; i<=dev.length(); i++) {
				
					if (dev.substring(i,i+1).equals("0")) { //immer noch
						lers = "a" + lers.substring(0);
					} else {
						break;
					}
				}
			}
		}

		return lers;
	}
	
	static BigDecimal hoch(int base, int ex) {
		BigDecimal result = new BigDecimal("1");
		for (int i = 0; i<ex; i++) {
			result = result.multiply(BigDecimal.valueOf(base));
		}
		return result;
	}
	
	public static boolean isNum(String strNum) {
		boolean ret = true;
		for (int i = 0; i<strNum.length();i=i+1) {
			if ((int) strNum.substring(i,i+1).charAt(0) >= 48 && (int) strNum.substring(i,i+1).charAt(0) <= 57) {
				ret = true;
			} else {
				ret = false;
			}
		}
		return ret;
	}
	
	public static boolean isLet(String strLet) {
		boolean ret = true;
		for (int i = 0; i<strLet.length();i=i+1) {
			if ((int) strLet.substring(i,i+1).charAt(0) >= 97 && (int) strLet.substring(i,i+1).charAt(0) <= 122) {
				ret = true;
			} else if ((int) strLet.substring(i,i+1).charAt(0) >= 65 && (int) strLet.substring(i,i+1).charAt(0) <= 90) {
				ret = true;
			} else {
				ret = false;
			}
		}
		return ret;
	}
	
	public static String makl(String text) {
		String klein = "";
		
		for (int i = 0; i<text.length();i=i+1) {
			if ((int) text.substring(i,i+1).charAt(0) >= 65 && (int) text.substring(i,i+1).charAt(0) <= 90) {
				klein = klein + (char) ((int) text.substring(i,i+1).charAt(0) + 32);
			} else if ((int) text.substring(i,i+1).charAt(0) >= 97 && (int) text.substring(i,i+1).charAt(0) <= 122) {
				klein = klein + text.substring(i,i+1);
			}
		}
		
		return klein;
	}
	
//	static int letterz(String b) {
//		nr = 0;
//		for (int y = 0; y <= 25; y++) {
//			if (b.equals(alphabet[y])) {
//				nr = y;
//				break;
//			}
//		}
//		return nr;
//	}
}
