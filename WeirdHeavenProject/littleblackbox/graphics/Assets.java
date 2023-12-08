package littleblackbox.graphics;

import java.awt.image.BufferedImage;

import javax.sound.sampled.Clip;

public class Assets {

    public static boolean isLoaded = false;
    public static float charge = 0;
    public static float maxCharge = 30;

    public static BufferedImage player;
    public static BufferedImage normalPlatform;
    public static BufferedImage boostPlatform;
    public static BufferedImage background;

    public static BufferedImage playin;
    public static BufferedImage playout;
    public static BufferedImage exitin;
    public static BufferedImage exitout;
    public static BufferedImage scorein;
    public static BufferedImage scoreout;
    public static BufferedImage returnin;
    public static BufferedImage returnout;

    public static BufferedImage menubackground;
    public static BufferedImage characterselection;
    public static BufferedImage scenarioselection;
    public static BufferedImage gametitle;

    public static BufferedImage player1in;
    public static BufferedImage player1out;
    public static BufferedImage player2in;
    public static BufferedImage player2out;
    public static BufferedImage player3in;
    public static BufferedImage player3out;
    public static BufferedImage player4in;
    public static BufferedImage player4out;

    public static BufferedImage background1in;
    public static BufferedImage background1out;
    public static BufferedImage background2in;
    public static BufferedImage background2out;
    public static BufferedImage background3in;
    public static BufferedImage background3out;
    public static BufferedImage background4in;
    public static BufferedImage background4out;
    public static BufferedImage background5in;
    public static BufferedImage background5out;

    public static Clip bgMusic;
    public static Clip bgMusicMenu;
    public static Clip bgMusicScore;
    public static Clip jumpeffect;
    public static Clip jumpboosteffect;

    public static void init() {
        player = Loader.ImgLoader("/resources/doodles/doodle3.png");
        normalPlatform = Loader.ImgLoader("/resources/platforms/normalplatform.png");
        boostPlatform = Loader.ImgLoader("/resources/platforms/boostplatform.png");
        background = Loader.ImgLoader("/resources/background/background3.jpg");

        playin = Loader.ImgLoader("/resources/buttons/playin.jpg");
        playout = Loader.ImgLoader("/resources/buttons/playout.jpg");
        exitin = Loader.ImgLoader("/resources/buttons/exitin.jpg");
        exitout = Loader.ImgLoader("/resources/buttons/exitout.jpg");
        scorein = Loader.ImgLoader("/resources/buttons/highscorein.jpg");
        scoreout = Loader.ImgLoader("/resources/buttons/highscoreout.jpg");
        returnin = Loader.ImgLoader("/resources/buttons/returnin.jpg");
        returnout = Loader.ImgLoader("/resources/buttons/returnout.jpg");

        menubackground = Loader.ImgLoader("/resources/background/background6.jpg");
        characterselection = Loader.ImgLoader("/resources/background/characterselection.png");
        scenarioselection = Loader.ImgLoader("/resources/background/scenarioselection.png");
        gametitle = Loader.ImgLoader("/resources/background/gametitle.png");

        player1in = Loader.ImgLoader("/resources/buttons/player1in.jpg");
        player1out = Loader.ImgLoader("/resources/buttons/player1out.jpg");
        player2in = Loader.ImgLoader("/resources/buttons/player2in.jpg");
        player2out = Loader.ImgLoader("/resources/buttons/player2out.jpg");
        player3in = Loader.ImgLoader("/resources/buttons/player3in.jpg");
        player3out = Loader.ImgLoader("/resources/buttons/player3out.jpg");
        player4in = Loader.ImgLoader("/resources/buttons/player4in.jpg");
        player4out = Loader.ImgLoader("/resources/buttons/player4out.jpg");

        background1in = Loader.ImgLoader("/resources/buttons/background1in.png");
        background1out = Loader.ImgLoader("/resources/buttons/background1out.png");
        background2in = Loader.ImgLoader("/resources/buttons/background2in.png");
        background2out = Loader.ImgLoader("/resources/buttons/background2out.png");
        background3in = Loader.ImgLoader("/resources/buttons/background3in.png");
        background3out = Loader.ImgLoader("/resources/buttons/background3out.png");
        background4in = Loader.ImgLoader("/resources/buttons/background4in.png");
        background4out = Loader.ImgLoader("/resources/buttons/background4out.png");
        background5in = Loader.ImgLoader("/resources/buttons/background5in.png");
        background5out = Loader.ImgLoader("/resources/buttons/background5out.png");

        bgMusic = Loader.loadSound("/resources/audio/songs/bgmusic1.wav");
        bgMusicMenu = Loader.loadSound("/resources/audio/songs/bgmusic6.wav");
        bgMusicScore = Loader.loadSound("/resources/audio/songs/bgmusic7.wav");
        jumpeffect = Loader.loadSound("/resources/audio/effects/jumpeffect.wav");
        jumpboosteffect = Loader.loadSound("/resources/audio/effects/jumpboosteffect.wav");

        isLoaded = true;
    }

    public static BufferedImage loadImage(String path) {
        charge++;
        return Loader.ImgLoader(path);
    }

    public static Clip loadAudio(String path) {
        charge++;
        return Loader.loadSound(path);
    }

    public static void loadPlayer(int play) {
        player = Loader.ImgLoader("/resources/doodles/doodle" + play + ".png");
    }

    public static void loadSong(int song) {
        bgMusic = Loader.loadSound("/resources/audio/songs/bgmusic" + song + ".wav");
    }

    public static void loadScen(int scen) {
        background = Loader.ImgLoader("/resources/background/background" + scen + ".jpg");
    }
}
