package littleblackbox.graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Loader {
    public static BufferedImage ImgLoader(String path){
        try{
            return ImageIO.read(Loader.class.getResource(path));
        }catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }

    public static Clip loadSound(String path){
        try{
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(Loader.class.getResource(path)));
            return clip;
        }catch(LineUnavailableException | IOException | UnsupportedAudioFileException e){
            e.printStackTrace();	
        }
        
        return null;
    }
}
