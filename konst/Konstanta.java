package main.konst;

public class Konstanta
{
  private Konstanta()
  {
    super();
  }
  public static final int Tembok_SPACE = 1;
  public static final int PAPAN_LEBAR = 360 + 2 * Tembok_SPACE;
  public static final int PAPAN_TINGGI = 600 + 2 * Tembok_SPACE;
  public static final int STEP_DISTANCE = 20;
  public static final int Tembok_SIZE = 30;
  public static final int X_BEGIN = Tembok_SIZE + Tembok_SPACE;
  public static final int Y_BEGIN = Tembok_SIZE + Tembok_SPACE;
  public static final int X_END = PAPAN_LEBAR - Tembok_SIZE - Tembok_SPACE;
  public static final int Y_END = PAPAN_TINGGI - Tembok_SIZE - Tembok_SPACE;
  public static final int PERAHU_POSISI_Y = Y_END - STEP_DISTANCE;
  public static final int PERAHU_POSISI_X_BEGIN = (PAPAN_LEBAR - 2 * Tembok_SPACE) / 2 - 10 + 1;
  public static final int PAPAN_TIME_DELAY = 1000;
  public static final int PERAHU_TIME_DELAY = 60;
  public static final int PELURU_TIME_DELAY = 30;
  public static final int MUSUH_TIME_DELAY = 100;
  public static final int READY_TIME_DELAY = 300;
  public static final int MUSUH_GABUNG_DELAY = 60;
  public static final String LEFT = "LEFT";
  public static final String RIGHT = "RIGHT";
  public static final String PLAY_AGAIN_MESSAGE = "< Press enter to play again >";
  public static final int READY_LENGTH = 240;
}
