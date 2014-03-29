// Count.java  -- Count counts occurences of characters.  The results will be shown in pie charts.

// Copyright (c) 2004 - 2014 easai

// Author: easai 
// Created: Mon Jan 13 06:12:59 2014
// Keywords: 

// This file is not part of GNU Emacs.

// Count.java<2> is free software; you can redistribute it and/or modify
// it under the terms of the GNU General Public License as published by
// the Free Software Foundation; either version 2, or (at your option)
// any later version.

// This software is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.

// You should have received a copy of the GNU General Public License
// along with this program; see the file COPYING.  If not, write to the
// Free Software Foundation, Inc., 59 Temple Place - Suite 330,
// Boston, MA 02111-1307, USA.

// Commentary:
//
//
//

// Code:

import java.util.*;
import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class CountApplet extends Applet
{
    ArrayList<String> labels=new ArrayList<String>();
    ArrayList<Integer> count=new ArrayList<Integer>();
    Integer[] index;

    public void getLetterFrequency(String text)
    {
	labels.clear();
	count.clear();
	Frame f=new Frame();
	f.setSize(100,100);
	f.setTitle("Debugging");
	f.setVisible(true);
	count(text);
	int maxLength=Math.min(10, text.length());
	new PieChart("Letter Frequency: "+text.substring(0,maxLength)+"...",labels,count,index);
	new CountTable("Letter Frequency: "+text.substring(0,maxLength)+"...",labels,count,index);
    }
    private void count(String text)
    {
	char ch;
	String str="";
	for(int i=0;i<text.length();i++)
	    {
		ch=text.charAt(i);
		str=String.valueOf(ch);		
		if(labels.contains(str))
		    {
			int k=labels.indexOf(str);
			count.set(k,new Integer(((Integer)count.get(k)).intValue()+1));
		    }
		else if(ch!=' '&&ch!='\n'&&ch!='\r')
		    {			
			labels.add(str);
			count.add(new Integer(1));
		    }
	    }
	int n=labels.size();
	index=new Integer[n];
	for(int i=0;i<n;i++)
	    index[i]=new Integer(i);
	Arrays.sort(index,new Comparator<Integer>()
	    {
		public int compare(Integer obj1, Integer obj2)
		{
		    int n1=((Integer)obj1).intValue();
		    int n2=((Integer)obj2).intValue();
		    return ((Integer)count.get(n2)).compareTo((Integer)count.get(n1));
		}
	    });	
    }
    public static void main(String args[])
    {
	CountApplet count=new CountApplet();
	count.getLetterFrequency("Mr. Speaker, Mr. Vice President, Members of Congress, my fellow Americans: Today in America, a teacher spent extra time with a student who needed it, and did her part to lift America's graduation rate to its highest level in more than three decades.  An entrepreneur flipped on the lights in her tech startup, and did her part to add to the more than eight million new jobs our businesses have created over the past four years. An autoworker fine-tuned some of the best, most fuel-efficient cars in the world, and did his part to help America wean itself off foreign oil.  A farmer prepared for the spring after the strongest five-year stretch of farm exports in our history.  A rural doctor gave a young child the first prescription to treat asthma that his mother could afford.  A man took the bus home from the graveyard shift, bone-tired but dreaming big dreams for his son.  And in tight-knit communities across America, fathers and mothers will tuck in their kids, put an arm around their spouse, remember fallen comrades, and give thanks for being home from a war that, after twelve long years, is finally coming to an end.");
    }
    class CountTable extends Frame
    {
	CountTable(String title, ArrayList<String> labels, ArrayList<Integer> count,Integer[] index)
	{
	    String result="";
	    for(int i=0;i<labels.size();i++)
		{
		    int n=index[i].intValue();
		    String label=(String)labels.get(n);
		    if(label.equals(" "))
			label="[space]";
		    else if(label.equals("\n"))
			label="[\\n]";
		    else if(label.equals("\r"))
			label="[\\r]";
		    result+=(label+"\t"+count.get(n))+"\n";
		}
	    TextArea textArea=new TextArea();
	    //ScrollPane scroll=new ScrollPane();
	    textArea.setText(result);
	    //	    scroll.add(textArea);
	    addWindowListener(new WindowAdapter(){
		    public void windowClosing( WindowEvent e ) { dispose(); }
		});	
	    //	    add(scroll);
	    add(textArea);
	    setSize(100,500);
	    setLocation(500,0);
	    setVisible(true);	    
	    setTitle(title);
	}
    }   
}

// CountApplet.java ends here
