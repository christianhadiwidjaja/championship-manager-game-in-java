package ui;

import file.MatchFile;
import main.Match;
import main.RandomUtil;
import main.Team;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainWindow {

    private final List<Team> teamList;
    private final MatchFile matchFile;
    private Match match;

    public MainWindow(List<Team> teamList, MatchFile matchFile) {
        this.teamList = teamList;
        this.matchFile = matchFile;
        this.match = matchFile.loadMatch();
    }

    public void show() {
        JFrame frame = new JFrame();
        if (match == null) {
            setMainPanel(frame);
        } else {
            setVersusPanel(frame, match);
        }

        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (match == null) {
                    System.exit(0);
                }
                else if(!match.isMatchOn()) {
                    matchFile.deleteMatch();
                    System.exit(0);
                } else {
                    int result = JOptionPane.showConfirmDialog(frame,
                            "Progress will be saved. Exit program ?",
                            "Exit",
                            JOptionPane.YES_NO_OPTION);

                    if (result == JOptionPane.YES_OPTION) {
                        matchFile.saveMatch(match);
                        System.exit(0);
                    }
                }
            }
        });

        frame.pack();
        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        frame.setVisible(true);
    }

    private void setMainPanel(JFrame frame) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JLabel title = new JLabel("Championship Manager", SwingConstants.CENTER);
        title.setFont(title.getFont().deriveFont(36.0f));
        title.setBorder(new EmptyBorder(15, 0, 0, 0));
        panel.add(title, BorderLayout.PAGE_START);

        JPanel pickTeamPanel = new JPanel();
        pickTeamPanel.setLayout(new BoxLayout(pickTeamPanel, BoxLayout.Y_AXIS));
        pickTeamPanel.setBorder(new EmptyBorder(100, 0, 0, 0));

        JLabel pilihLabel = new JLabel("Choose your team", SwingConstants.CENTER);
        pilihLabel.setFont(pilihLabel.getFont().deriveFont(24.0f));
        pilihLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        pickTeamPanel.add(pilihLabel);

        JPanel teamsPanel = new JPanel();
        teamsPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        for(Team team : teamList) {
            JPanel teamPanel = new JPanel();
            teamPanel.setLayout(new GridBagLayout());

            ImageIcon flagIcon;
            try {
                flagIcon = new ImageIcon(ImageIO.read(new File("src/images/" + team.getFlag())));
            } catch (IOException ioe) {
                throw new RuntimeException("Program tidak bisa jalan karena image tidak ketemu", ioe);
            }

            JButton flagButton = new JButton(flagIcon);
            flagButton.setBorderPainted(false);
            Cursor handCursor = new Cursor(Cursor.HAND_CURSOR);
            Cursor originalCursor = flagButton.getCursor();

            flagButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    flagButton.setCursor(handCursor);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    flagButton.setCursor(originalCursor);
                }

                @Override
                public void mouseClicked(MouseEvent e) {
                    List<Team> copyList = new ArrayList<>(teamList);
                    copyList.remove(team);

                    Team randomTeam = RandomUtil.getRandomTeam(copyList.toArray(new Team[0]));
                    match = new Match(team, randomTeam);
                    setVersusPanel(frame, match);
                }
            });

            GridBagConstraints c = new GridBagConstraints();
            c.gridx = 0;
            c.gridy = 0;
            c.anchor = GridBagConstraints.CENTER;

            teamPanel.add(flagButton, c);

            JLabel teamNameLabel = new JLabel(team.getTeamName(), SwingConstants.CENTER);
            teamNameLabel.setFont(teamNameLabel.getFont().deriveFont(18.0f));

            c = new GridBagConstraints();
            c.gridx = 0;
            c.gridy = 1;
            c.anchor = GridBagConstraints.CENTER;

            teamPanel.add(teamNameLabel, c);
            teamPanel.setBorder(new EmptyBorder(20, 60, 20, 60));

            teamsPanel.add(teamPanel);
        }

        pickTeamPanel.add(teamsPanel);

        panel.add(pickTeamPanel, BorderLayout.CENTER);
        frame.setContentPane(panel);
    }

    private void setVersusPanel(JFrame frame, Match match) {

        VersusPanel versusPanel = new VersusPanel(match.getTeam1(), match.getTeam2());
        match.setMatchListener(versusPanel);

        frame.setContentPane(versusPanel.createVersusPanel());
        frame.invalidate();
        frame.revalidate();

        match.startMatch();
    }
}
