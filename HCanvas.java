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
import javax.swing.*;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.image.BufferedImage;
import java.awt.image.renderable.RenderableImage;



public class HCanvas extends GCanvas {
	
	public HCanvas(){
		GImage starter = new GImage("start.jpg");
		add(starter);
	}
	
	public void Encrypt(){
		GImage starter = new GImage("Encrypt.jpg");
		add(starter);
	}
	public void Eovr(){
		GImage starter = new GImage("Eovr.jpg");
		add(starter);
	}
	public void Etovr(int key){
		GImage starter = new GImage("Etovr.jpg");
		add(starter);
		GLabel lb =new GLabel(Integer.toString(key));
		add(lb,260,200);
	}
	public void Decrypt(){
		GImage starter = new GImage("Decrypt.jpg");
		add(starter);
	}
	public void Deovr(){
		GImage starter = new GImage("Deovr.jpg");
		add(starter);
	}
	
	
	
}
