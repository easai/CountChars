// PieChart.java  -- PieChart class is for Count class.

// Copyright (c) 2004 - 2014 easai

// Author: easai 
// Created: Mon Jan 13 06:13:54 2014
// Keywords: 

// This file is not part of GNU Emacs.

// PieChart.java is free software; you can redistribute it and/or modify
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

import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class PieChart extends Frame
{
    ArrayList labels;
    ArrayList values;
    Integer[] index;
    double total=0;

    PieChart(String title, ArrayList labels, ArrayList values, Integer[] index)
    {
	this.labels=labels;
	this.values=values;
	this.index=index;

	addWindowListener(new WindowAdapter(){
		public void windowClosing( WindowEvent e ) { dispose(); }
	    });	

	for(int i=0;i<values.size();i++)
	    {
		total+=((Integer)values.get(i)).intValue();
	    }
	setTitle(title);
	setSize(500,500);
	setVisible(true);
    }

    public void paint(Graphics g)
    {
	int width=getWidth();
	int height=getHeight();
	int rx=(int)(width/2.0*3.0/4.0);
	int ry=(int)(height/2.0*3.0/4.0);
	int x0=width/2;
	int y0=height/2;
	int x,y;
	double theta=-Math.PI/2.0,delta=0;
	for(int i=0;i<labels.size();i++)
	    {
		int n=index[i].intValue();
		delta=((Integer)values.get(n)).intValue()/total*2*Math.PI;
		theta+=delta;
		g.drawLine(x0,y0,(int)(rx*Math.cos(theta)+x0),(int)(ry*Math.sin(theta)+y0));
		String label=(String)labels.get(n);
		FontMetrics fm=getFontMetrics(g.getFont());
		if(label.equals(" "))
		    {
			label="[Space]";
			g.drawString(label,
				     (int)(.5*rx*Math.cos(theta-delta/2.0)+x0-fm.stringWidth(label)/2.0),
				     (int)(.5*ry*Math.sin(theta-delta/2.0)+y0));
		    }
		else if(label.equals("\n"))
		    {
			label="[\\n]";
			g.drawString(label,
				     (int)(.5*rx*Math.cos(theta-delta/2.0)+x0-fm.stringWidth(label)/2.0),
				     (int)(.5*ry*Math.sin(theta-delta/2.0)+y0));
		    }
		else if(label.equals("\r"))
		    {
			label="[\\r]";
			g.drawString(label,
				     (int)(.5*rx*Math.cos(theta-delta/2.0)+x0-fm.stringWidth(label)/2.0),
				     (int)(.5*ry*Math.sin(theta-delta/2.0)+y0));
		    }
		else
		    {
			g.drawString(label,
				     (int)(.5*rx*Math.cos(theta-delta/2.0)+x0),
				     (int)(.5*ry*Math.sin(theta-delta/2.0)+y0));
		    }
	    }
	g.drawOval(x0-rx,y0-ry,2*rx,2*ry);
    }

    // For debugging purposes only
    public static void main(String args[])
    {
	ArrayList labels=new ArrayList();
	ArrayList values=new ArrayList();
	labels.add("a");
	labels.add("b");
	labels.add("c");
	labels.add("d");
	labels.add("e");
	values.add(new Integer(1));
	values.add(new Integer(2));
	values.add(new Integer(3));
	values.add(new Integer(4));
	values.add(new Integer(5));
	Integer index[]=new Integer[5];
	index[0]=new Integer(4);
	index[1]=new Integer(3);
	index[2]=new Integer(2);
	index[3]=new Integer(1);
	index[4]=new Integer(0);
	PieChart pieChart=new PieChart("Sample",labels,values,index);
    }
}


// PieChart.java ends here
