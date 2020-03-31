import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

public class CSV extends JPanel {
//--------------------------------------------------------------------
// Deklaratioiner 
//--------------------------------------------------------------------
	 String[] columNames = new String[] {
             "Ja/Nej", "Färg", "Fordon"
         };
	 Object[][] data = null;
	 List<String[]> elements = new ArrayList<String[]>();
//--------------------------------------------------------------------
// Öppnar fil
// Läser rad, delar upp värden vid kommatecken
// Stänger fil
//--------------------------------------------------------------------
	public CSV() throws IOException {
		super(new BorderLayout(3,3));
        Object[][] data = read();
        
        final JTable table = new JTable(data, columNames);
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);
        
        JButton button = new JButton("Spara");
        JPanel buttonCenter = new JPanel();
        buttonCenter.add(button);
        add(buttonCenter, BorderLayout.SOUTH);
        
 
        //Skapar ScrollPane och lägger till
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
        setBorder(new EmptyBorder(5,5,5,5));
        
	}
	public Object[][] read() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(new File("C:\\Users\\jona4454\\OneDrive - Ystads Kommun\\Programmering\\CSV\\test.csv")));
		
		String line = null;
	
		while((line = br.readLine()) != null){
			String[] splitted = line.split(";");
            elements.add(splitted);
		}//end while
		br.close();
       
        Object[][] content = new Object[elements.size()][3];
        for(int i=0; i<elements.size(); i++) {
            content[i][0] = elements.get(i)[0];
            content[i][1] = elements.get(i)[1];
            content[i][2] = elements.get(i)[2];
        }

        return content;
		     
	}//read()
	private static void createAndShowGUI() throws IOException {
        //Fönstret
        JFrame frame = new JFrame("SimpleTableDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        //ContentPane
        CSV newContentPane = new CSV();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);
 
        //Visa fönstret
        frame.pack();
        frame.setVisible(true);
    }
//--------------------------------------------------------------------
//
//--------------------------------------------------------------------
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
					createAndShowGUI();
				} catch (IOException e) {
					e.printStackTrace();
				}
            }
        });
	}//main
}//class0
