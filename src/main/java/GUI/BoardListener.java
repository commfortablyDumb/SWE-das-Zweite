package GUI;

public interface BoardListener {
    void onBoardChanged(String[][] board);
    void showMessage(String message);
    void waitForButtonClicked();
    String getInput();
    void setButtonUnClicked();
    void updatePlayer();
}
