package ui;

import main.Action;
import main.Event;
import main.MatchListener;
import main.Team;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.List;
import java.io.File;
import java.io.IOException;

public class VersusPanel implements MatchListener {

    private final JLabel timeLabel;
    private final JLabel score1Label;
    private final JLabel score2Label;
    private final JLabel commentLabel;
    private final JLabel goalScorer1Label;
    private final JLabel goalScorer2Label;
    private final Team team1;
    private final Team team2;

    public VersusPanel(Team team1, Team team2) {
        this.timeLabel = new JLabel();
        this.score1Label = new JLabel();
        this.score2Label = new JLabel();
        this.commentLabel = new JLabel();
        this.goalScorer1Label = new JLabel();
        this.goalScorer2Label = new JLabel();
        this.team1 = team1;
        this.team2 = team2;
    }

    public JPanel createVersusPanel() {
        JPanel wrapPanel = new JPanel();
        wrapPanel.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        c.weightx = 0;
        c.weighty = 1;
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.anchor = GridBagConstraints.PAGE_START;
        timeLabel.setText("00:00");
        timeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        timeLabel.setFont(timeLabel.getFont().deriveFont(18.0f));
        timeLabel.setBorder(new EmptyBorder(15, 0, 0, 0));
        wrapPanel.add(timeLabel, c);

        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 10;
        c.weighty = 8;
        c.anchor = GridBagConstraints.PAGE_START;
        wrapPanel.add(createTeamFlagPanel(team1, score1Label, goalScorer1Label), c);

        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 1;
        c.weightx = 1;
        c.weighty = 8;
        c.anchor = GridBagConstraints.PAGE_START;
        c.ipady = 180;
        JLabel vsLabel = new JLabel("VS");
        vsLabel.setFont(vsLabel.getFont().deriveFont(18.0f));
        wrapPanel.add(vsLabel, c);

        c = new GridBagConstraints();
        c.gridx = 2;
        c.gridy = 1;
        c.weightx = 10;
        c.weighty = 10;
        c.anchor = GridBagConstraints.PAGE_START;

        wrapPanel.add(createTeamFlagPanel(team2, score2Label, goalScorer2Label), c);

        c = new GridBagConstraints();
        c.gridy = 2;
        c.weighty = 16;
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.anchor = GridBagConstraints.PAGE_START;

        commentLabel.setFont(vsLabel.getFont().deriveFont(18.0f));
        wrapPanel.add(commentLabel, c);

        return wrapPanel;
    }

    private JPanel createTeamFlagPanel(Team team, JLabel scoreLabel, JLabel goalScorerLabel) {
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

        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        teamPanel.add(flagButton, c);

        JLabel teamNameLabel = new JLabel(team.getTeamName(), SwingConstants.CENTER);
        teamNameLabel.setBorder(new EmptyBorder(10, 0, 0, 0));
        teamNameLabel.setFont(teamNameLabel.getFont().deriveFont(18.0f));

        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 1;
        teamPanel.add(teamNameLabel, c);

        scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
        scoreLabel.setText(String.valueOf(team.getScore()));
        scoreLabel.setFont(scoreLabel.getFont().deriveFont(36.0f));

        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 2;
        c.ipady = 20;
        teamPanel.add(scoreLabel, c);

        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 3;
        teamPanel.add(goalScorerLabel, c);

        teamPanel.setBorder(new EmptyBorder(20, 60, 20, 60));

        return teamPanel;
    }

    @Override
    public void onEvent(Event event) {
        String team1Score = String.valueOf(team1.getScore());
        String team2Score = String.valueOf(team2.getScore());

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                score1Label.setText(team1Score);
                score2Label.setText(team2Score);
            }
        });

        List<String> comments = event.getComments();
        for(int i = 0; i < comments.size(); i++) {
            String comment = comments.get(i);
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    commentLabel.setText(comment);
                }
            });

            if(i < comments.size() - 1 ) {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                String scoreText1 = getGoalScorer(team1);
                goalScorer1Label.setText(scoreText1);

                String scoreText2 = getGoalScorer(team2);
                goalScorer2Label.setText(scoreText2);
            }
        });
    }

    private String getGoalScorer(Team team) {
        String scoreText="";

        for(Event event: team.getGoalEvents()) {
            String text = String.valueOf(event.getMinutes()) + "' " + event.getActionPlayer().getPlayerName();
            scoreText = scoreText + "<br>" + text;
        }

        return "<html>" + scoreText + "</html>";
    }

    @Override
    public void onAction(Action action) {
        List<String> comments = action.getComments();
        for(int i = 0; i < comments.size(); i++) {
            String comment = comments.get(i);
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    commentLabel.setText(comment);
                }
            });

            if(i < comments.size() - 1 ) {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void onTick(int minutes, int seconds) {
        final String time = String.format("%02d:%02d", minutes, seconds);
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                timeLabel.setText(time);
            }
        });
    }
}
