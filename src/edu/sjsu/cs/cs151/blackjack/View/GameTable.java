package edu.sjsu.cs.cs151.blackjack.View;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Label;

public class GameTable {

	protected Shell shell;
	private Text text;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			GameTable window = new GameTable();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(450, 300);
		shell.setText("SWT Application");
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.setBounds(0, 55, 87, 33);
		btnNewButton.setText("New Button");
		
		Button button = new Button(shell, SWT.NONE);
		button.setText("New Button");
		button.setBounds(0, 87, 87, 33);
		
		Button button_1 = new Button(shell, SWT.NONE);
		button_1.setText("New Button");
		button_1.setBounds(0, 119, 87, 33);
		
		text = new Text(shell, SWT.BORDER);
		text.setBounds(86, 124, 48, 19);
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setBounds(186, 184, 56, 71);
		lblNewLabel.setText("New Label");
		
		Label label = new Label(shell, SWT.NONE);
		label.setText("New Label");
		label.setBounds(248, 184, 56, 71);
		
		Label label_1 = new Label(shell, SWT.NONE);
		label_1.setText("New Label");
		label_1.setBounds(124, 184, 56, 71);
		
		Label label_2 = new Label(shell, SWT.NONE);
		label_2.setText("New Label");
		label_2.setBounds(186, 10, 56, 71);
		
		Label label_3 = new Label(shell, SWT.NONE);
		label_3.setText("New Label");
		label_3.setBounds(248, 10, 56, 71);
		
		Label label_4 = new Label(shell, SWT.NONE);
		label_4.setText("New Label");
		label_4.setBounds(124, 10, 56, 71);

	}

}
