package Animation;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.awt.*;
import java.io.File;

public class SpriteAnimation
{

    //Private Variables
    private static Image _spriteSheet;
    private static int _tileWidth;
    private static int _tileHeight;

    private static int _animationDeviation;
    private static int _animationReset;

    private int _animationCounter;

    private boolean _loopAnimation;

    //Constructor
    public SpriteAnimation(String file, int tileWidth, int tileHeight,
                           int animationFps, int engineFps, boolean loop)
    {
        this.loadSprite(file);
        _tileWidth = tileWidth;
        _tileHeight = tileHeight;
        _animationDeviation = animationFps/engineFps;
        _loopAnimation = loop;

        InitAnimationCounter();
    }

    //Public Methods
    public Image loadSprite(String file) {

        try {
            _spriteSheet = new Image(new File(file).toURI().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return _spriteSheet;
    }

    public boolean DrawSpriteFrame(GraphicsContext gc, Point drawLocation)
    {
        if(_animationCounter == _animationReset && !_loopAnimation) return false;

        if(_animationCounter < _animationReset)
        {
            _animationCounter++;
        }
        else
        {
            _animationCounter = 0;
        }

        gc.drawImage(_spriteSheet, 0,
                _tileHeight*(_animationCounter/_animationDeviation),
                _tileWidth, _tileHeight, drawLocation.x, drawLocation.y,
                _tileWidth, _tileHeight);

        return true;
    }

    //Private Methods
    private void InitAnimationCounter()
    {
        _animationCounter = 0;
        int numberOfFrames = (int)Math.ceil(_spriteSheet.getHeight()/_tileHeight);

        _animationReset = numberOfFrames*_animationDeviation;
    }
}
