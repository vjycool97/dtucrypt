import acm.program.*;
import acm.io.*;
import acm.util.*;
import java.awt.*;
import acm.graphics.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.*;
import java.util.ArrayList;
import javax.imageio.ImageIO;



public class Encrypt extends ConsoleProgram {
	
	public static void main(String[] args) {
		new Encrypt().start(args);
	}

	public void init(){
		can = new HCanvas();
		add(can);		
	}
	
	

public void run(){
	String chr="yes";
	while( chr.equals("yes")||chr.equals("YES")){
		
		println("Welcome To iCrypt 101 !");
		println(" 1) Encrypt Yout Text ?");
		println(" 2) Decrypt Yout Text ?");
		println(" 3) Decrypt Yout Image ?");
		println("Enter Your Choice");
	
	
		int choice = readInt();
	
		if(choice==1){
			can.Encrypt();
			println("Please use only a-z, 0-9 and spaces ");
			println("Special Character may lead to wrong message");
			println("Enter the String to Be Encrypted");
		
			SR=Initialise();
			println("what Do You want to Enccrypt to ");
			println(" 1) If you want to Encrypt to TXT");
			println(" 2) If you want to Encrypt to Image");
			int ch = readInt();
			if(ch==1){
				int Key=rgen.nextInt(5,20);
				ENC=EncryptA(SR,Key);
				Print(ENC);
				can.Etovr(Key);
			
			}
			if(ch==2){
				int Key=rgen.nextInt(5,20);
				println(""+Key);
				ENC=EncryptA(SR,Key);
				println("Enter the File Source :");
				String path ;
				path=readLine();
				BufferedImage ENIMG =null;
				ENIMG=EncryptImg(ENC,path,Key);
				write(ENIMG);
				can.Eovr();

			}
		

		}
	
		if(choice==2){
			can.Decrypt();
			println("Enter your Key?:");
			int key=readInt();
			println("Entered the coded txt?:");
			SR=Initialise();
			ENC=EncryptA(SR,-key);
			Print(ENC);
			can.Deovr();
	
			}
	
		if(choice==3){
			can.Decrypt();
			BufferedImage EImage =InitIM();
			DecryptIMG(EImage);
			can.Deovr();
	
			}
		println("Do you wish to run the program again(yes/no)");
		chr=readLine();
		println(chr);
	} 
}



public void DecryptIMG (BufferedImage DEM){
	
	ArrayList<String> Detxt= new ArrayList<String>();
	
	int pixelarray[][] = new int[DEM.getHeight()][DEM.getWidth()];
	int height= DEM.getHeight();
	int width= DEM.getWidth();
	for(int i =0 ; i<height ; i++){
		for(int j=0; j<width ; j++)
			pixelarray[i][j]=DEM.getRGB(j, i);
	}
	
	int pix,key=0;
	pix=pixelarray[height-1][width-1];
	if(GImage.getRed(pix)==GImage.getBlue(pix))
	key=GImage.getRed(pix);
	pix=pixelarray[height-1][width-2];
	int size=GImage.getRed(pix);
	int[] strsize = new int[size];
	for(int i=0;i<size;i++){
		int pixe=pixelarray[height-i-2][width-1];
		strsize[i]=GImage.getRed(pixe);
	}
	
	
	for(int i =0 ; i<height ; i++){
		String str = "";
		for(int j=0; j<width ; j++){
			int pixel  = pixelarray[i][j]; 
			int g = GImage.getGreen(pixel);
			if (i<size&&j<strsize[i]){
			char ch;
			if(g<27){
			ch=(char)('A'+ g );
			str+=ch;
			}
			if(g==27){
				ch=' ';
				str+=ch;
				}
			if(g>27){
				int num=0+g-27;
				str+=Integer.toString(num);
				}
			}
		}
		if (i<size)
		Detxt.add(i, str);
	}
	ArrayList<String> DE = EncryptA(Detxt,-key);
	Print(DE);
	
}

private BufferedImage EncryptImg(ArrayList<String> EN,String path,int key){
	
	int cnt=0;
	BufferedImage src = null;
	try {
		src = ImageIO.read(new File(path));
	} catch (IOException e) {
		e.printStackTrace();
	}
	int height=src.getHeight();
	int width=src.getWidth();
	
	
	int[][] pixelarray=new int[height][width];
	for (int i=0; i<EN.size(); i++){
		cnt+=EN.get(i).length();
	}
	
	for(int i =0 ; i<height ; i++){
		for(int j=0; j<width ; j++)
	pixelarray[i][j] = src.getRGB(j, i);
	}
	
	for(int i =0 ; i<height ; i++){
		for(int j=0; j<width ; j++){
			int pixel  = pixelarray[i][j]; 
			
			int r = GImage.getRed(pixel);
			int b = GImage.getBlue(pixel);
			int g = GImage.getGreen(pixel);
			
			if(cnt>0&&i<EN.size()&&j<EN.get(i).length()){
				char temp;
				temp=EN.get(i).charAt(j);
				if(Character.isLetter(temp)){
					g=temp-'A';
					}
				if(Character.isDigit(temp)){
					g=temp-'0'+27;
					}
				if(Character.isSpaceChar(temp)){
					g=27;
					
				}
				pixelarray[i][j]=GImage.createRGBPixel(r,g,b);			
			}
			
		}
		
	}
	pixelarray[height-1][width-1]=GImage.createRGBPixel(key, key, key);
	pixelarray[height-1][width-2]=GImage.createRGBPixel(EN.size(), EN.size(), EN.size());
	
	for(int i=2; i<EN.size()+2;i++){
		pixelarray[height-i][width-1]=GImage.createRGBPixel(EN.get(i-2).length(),EN.get(i-2).length(),EN.get(i-2).length());
	}
	
	BufferedImage I = null;
	try {
		I = ImageIO.read(new File(path));
	} catch (IOException e) {
		e.printStackTrace();
	}
	for(int i =0 ; i<height ; i++){
		for(int j=0; j<width ; j++)
			I.setRGB(j,i, pixelarray[i][j]);
	}
	
	return I;
	
}


public BufferedImage InitIM(){
	println("Enter the Source");
	String path=readLine();
	BufferedImage src = null;
	try {
		src = ImageIO.read(new File(path));
	} catch (IOException b) {
		b.printStackTrace();
	}
	return src;
}
	
public void write(BufferedImage ENIMG){
	String ext="bmp";
	String fileName = "Encrypted";
	    File file = new File(fileName + "." + ext);
	    try {
	        ImageIO.write((RenderedImage)ENIMG, ext, file);  // ignore returned boolean
	    } catch(IOException e) {
	        System.out.println("Write error for " + file.getPath() +
	                           ": " + e.getMessage());
	    }
}


private ArrayList<String> Initialise(){
	
	ArrayList<String> EN = new ArrayList<String>();
	String str;
	int cnt=0;
	while(true){
		println("Enter The Message ");
		str=readLine();
		if(str.equals("")) 
			break;
		EN.add(cnt, str);
		cnt++;
	}
	return EN;
}

private ArrayList<String> EncryptA(ArrayList<String>  EN,int Key ){
	for(int i=0; i<EN.size();i++){
		String Str="";
		for(int j=0; j<EN.get(i).length() ;j++ ){
			char ch,temp;
			temp=EN.get(i).charAt(j);
			if(Character.isLetter(temp))
			ch=(char)('A'+( Character.toUpperCase(temp)-'A'+Key+26)%26);
			else if(Character.isDigit(temp)){
			ch=(char)('0'+ 0+( temp-'0'+Key+20)%10);
			
			}
			else
				ch=temp;
			Str+=ch;
			
		}
		EN.set(i, Str);
	}
	return EN;
}

private void Print(ArrayList<String> EN){
	
	for(int i=0; i<EN.size();i++){
		String Str=EN.get(i);
		println("Encrypted : "+Str);
	
	}
	
}

HCanvas can;
ArrayList<String> ENC = new ArrayList<String>();
private ArrayList<String> SR = new ArrayList<String>();
private RandomGenerator rgen = new RandomGenerator();	

}