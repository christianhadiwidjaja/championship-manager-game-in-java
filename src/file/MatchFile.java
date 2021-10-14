package file;

import main.Match;

import java.io.*;

public class MatchFile {
    private final String fileName;

    public MatchFile(String fileName) {
        this.fileName = fileName;
    }

    public void saveMatch(Match match) {
        try {
            FileOutputStream fileOut =
                    new FileOutputStream(fileName);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(match);
            out.close();
            fileOut.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Match loadMatch() {
        try {
            FileInputStream fileIn = new FileInputStream(fileName);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            Match match = (Match) in.readObject();
            in.close();
            fileIn.close();
            return match;
        } catch(FileNotFoundException ex) {
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void deleteMatch() {
        File file = new File(fileName);
        file.delete();
    }
}
