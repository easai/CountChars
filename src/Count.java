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

public class Count extends Applet
{
    ArrayList labels=new ArrayList();
    ArrayList count=new ArrayList();
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
	String ch;
	for(int i=0;i<text.length();i++)
	    {
		ch=String.valueOf(text.charAt(i));
		if(labels.contains(ch))
		    {
			int k=labels.indexOf(new String(ch));
			count.set(k,new Integer(((Integer)count.get(k)).intValue()+1));
		    }
		else
		    {
			labels.add(ch);
			count.add(new Integer(1));
		    }
	    }
	int n=labels.size();
	index=new Integer[n];
	for(int i=0;i<n;i++)
	    index[i]=new Integer(i);
	Arrays.sort(index,new Comparator()
	    {
		public int compare(Object obj1, Object obj2)
		{
		    int n1=((Integer)obj1).intValue();
		    int n2=((Integer)obj2).intValue();
		    return ((Integer)count.get(n2)).compareTo((Integer)count.get(n1));
		}
	    });	
    }
    public static void main(String args[])
    {
	Count count=new Count();
	count.getLetterFrequency("abcd eded");
    }
    class CountTable extends Frame
    {
	CountTable(String title, ArrayList labels, ArrayList count,Integer[] index)
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

// Count.java ends here
