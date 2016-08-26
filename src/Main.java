import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * 
 * @author Tin
 */
public class Main extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JSplitPane splitPaneV;
	private JSplitPane splitPaneH;
	private JPanel mainPanel;
	private JPanel topologyPanel;
	private JPanel resultPanel;
	private JPanel buttonPanel;
	private TextArea result;
	private TextArea topology;
	private JButton btnCreateTopology;
	private JButton btnReadTopology;
	private JButton btnStartAlgoritham;

	private static ArrayList<Edge> edges = new ArrayList<Edge>();
	private static ReadTopology obj;
	private static int source;
	private static int nnode;
	private static Component parent;
	private JSplitPane splitPane;
	public static void main(String[] args) {
		new Main();
	}

	public Main() {
		setTitle("Bellman-Ford");

		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		getContentPane().add(panel);

		// Create the panels
		createMainPanel();
		createTopologyPanel();
		createResultPanel();
		createButtonPanel();

		// Create a splitter pane
		splitPaneV = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		panel.add(splitPaneV, BorderLayout.CENTER);

		splitPaneH = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		splitPaneH.setLeftComponent(mainPanel);

		splitPane = new JSplitPane();
		mainPanel.add(splitPane, BorderLayout.CENTER);
		splitPane.setRightComponent(resultPanel);

		splitPane.setLeftComponent(buttonPanel);

		splitPaneH.setRightComponent(topologyPanel);

		splitPaneV.setTopComponent(splitPaneH);

		setSize(700, 500);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void createMainPanel() {
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.setPreferredSize(new Dimension(500, 500));
	}

	public void createTopologyPanel() {
		topologyPanel = new JPanel();
		JLabel topologyLabel = new JLabel();
		topologyPanel.setLayout(new BorderLayout());
		topologyPanel.add(topologyLabel);

		topology = new TextArea();

		topologyLabel.add(topology);

		topology.setBounds(20, 20, 150, 420);

	}

	public void createResultPanel() {
		resultPanel = new JPanel();
		JLabel resultLabel = new JLabel();
		resultPanel.setLayout(new BorderLayout());
		resultPanel.add(resultLabel);

		JLabel textLabel = new JLabel(
				"<html><b><font size = +1>Rezultat izvo�enja algoritma</font></b></html>");
		result = new TextArea();

		resultLabel.add(result);
		resultLabel.add(textLabel);

		result.setBounds(30, 70, 330, 360);
		textLabel.setBounds(30, 10, 300, 50);
	}

	public void createButtonPanel() {
		buttonPanel = new JPanel();
		JLabel buttonLabel = new JLabel();
		JLabel chooserLabel = new JLabel("Izaberite izvori�te");
		buttonPanel.setLayout(new BorderLayout());
		buttonPanel.setPreferredSize(new Dimension(110, 500));
		buttonPanel.add(buttonLabel);
		final Choice sourceChoice = new Choice();

		btnCreateTopology = new JButton(
				"<html><center>Kreiraj <br> topologiju</center></html>");
		btnReadTopology = new JButton(
				"<html><center>U�itaj <br> topologiju</center></html>");
		btnStartAlgoritham = new JButton(
				"<html><center>Pokreni <br> algoritam</center></html>");

		buttonLabel.add(btnCreateTopology);
		buttonLabel.add(btnReadTopology);
		buttonLabel.add(btnStartAlgoritham);
		buttonLabel.add(chooserLabel);
		buttonLabel.add(sourceChoice);

		btnCreateTopology.setBounds(5, 10, 90, 40);
		btnReadTopology.setBounds(5, 80, 90, 40);
		btnStartAlgoritham.setBounds(5, 150, 90, 40);
		chooserLabel.setBounds(4, 220, 100, 20);
		sourceChoice.setBounds(4, 240, 100, 40);

		btnCreateTopology.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new CreateTopology();
			}
		});

		btnReadTopology.addActionListener(new ActionListener() {
			@Override
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.showOpenDialog(parent);
				String path = chooser.getSelectedFile().getAbsolutePath();

				obj = new ReadTopology(edges, path);

				for (int i = 0; i < edges.size(); i++) {
					topology.appendText(edges.get(i).source + ", "
							+ edges.get(i).destination + ", "
							+ edges.get(i).weight + "\n");
				}

				nnode = obj.getTopology();
				for (int i = 0; i < nnode; i++) {
					sourceChoice.add(i + "\n");
				}
				// source = 0;
			}
		});

		btnStartAlgoritham.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (nnode > 0 && edges.size() > 0 && source < nnode) {
					new BellmanFord(edges, source, nnode, result);
				}

				if (source >= nnode) {
					System.out.println("Vrijednost najve�eg �vora je "
							+ (nnode - 1));
				}
				if (nnode <= 0) {
					System.out.println("U u�itanoj topologiji nema �vorova");
				}
				if (edges.size() <= 0) {
					System.out
							.println("U�itan je nepovezan graf. Niti jedan �vor nije povezan!");
				}
			}
		});
		
		sourceChoice.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent ie) {
				source = sourceChoice.getSelectedIndex();
			}
		});

	}
}
