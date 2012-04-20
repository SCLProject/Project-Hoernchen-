package jp.ac.oit.sclab.hoernchen.main;

import java.awt.Component;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeListener;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class LissMain001 extends JFrame implements MouseListener {

	
	
	JDialog jDialog;
	JFrame mJFrame;
	JPanel mJPanel;
	JButton	mJButton;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		LissMain001 mLSMain = new LissMain001();
		
		
		
		
	}

	 public LissMain001(){
		 mJFrame = new JFrame();
		 mJFrame.setVisible(true);
		 mJFrame.setTitle("test");
		 mJButton = new JButton();
		 mJButton.setText("閉じる");
		 mJPanel = new JPanel();
		 mJButton.setActionCommand("close");
		 mJButton.addMouseListener(this);
		 
		 mJPanel.add(mJButton);
		 mJFrame.add(mJPanel);
		 
		 toggleFullScreenWindow();
		 
		 
		 
	 }
	
	private void toggleFullScreenWindow(){
		GraphicsEnvironment gEn = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice gd = gEn.getDefaultScreenDevice();
		if(gd.getFullScreenWindow()==null){
		mJFrame.dispose();
		mJFrame.setUndecorated(true);
		mJFrame.setVisible(true);
		gd.setFullScreenWindow(mJFrame);
		}else{
			gd.setFullScreenWindow(null);
			mJFrame.dispose();
			mJFrame.setUndecorated(false);
			mJFrame.setVisible(true);
			mJFrame.repaint();
			
			
			
			
			
			
		}
		
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		Component src = (Component)e.getSource();
		if(src == mJButton){
			mJFrame.dispose();
			return;
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
}
