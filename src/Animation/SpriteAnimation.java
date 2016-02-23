package Animation;

import Animation.enums.AnimationOrientation;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.awt.*;
import java.io.File;

public class SpriteAnimation
{

    //Private Variables
    private Image _spriteSheet;
    private int _tileWidth;
    private int _tileHeight;
    private int _frameQuantity;
    private int _engineFps;

    private int _animationDeviation;
    private int _animationReset;
    private int _animationCounter;

    private boolean _loopAnimation;

    //Properties
    private int _animationFps;

    //Constructor
    public SpriteAnimation(String file, int tileWidth, int tileHeight,
                           int animationFps, int engineFps, boolean loop)
    {
        this.loadSprite(file);
        _tileWidth = tileWidth;
        _tileHeight = tileHeight;
        _engineFps = engineFps;
        _frameQuantity = (int)Math.ceil(_spriteSheet.getHeight()/_tileHeight);
        this.SetAnimationFps(animationFps);
        _loopAnimation = loop;

        InitAnimationCounter(animationFps,0);
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

    public boolean DrawSpriteFrame(GraphicsContext gc, Point drawLocation,
                                   AnimationOrientation orientation)
    {
        if(_animationCounter >= _animationReset && !_loopAnimation)
            return false;

        if(_animationCounter < _animationReset)
        {
            _animationCounter++;
        }
        else
        {
            _animationCounter = 0;
        }

        int index = Math.min((_animationCounter/_animationDeviation), _frameQuantity-1);

        this.DrawSpriteOrientation(gc, drawLocation,index, orientation);

        return true;
    }

    public boolean DrawFrameAtIndex(GraphicsContext gc, Point drawLocation, int i,
                                    AnimationOrientation orientation)
    {
        if(i >= _frameQuantity) return false;

        this.DrawSpriteOrientation(gc, drawLocation, i, orientation);
        return true;
    }

    //Set Methods
    public void SetAnimationFps(int animationFps)
    {
        int oldfps = _animationFps;
        _animationFps = animationFps;
        _animationDeviation = Math.max(_engineFps/_animationFps, 1);

        InitAnimationCounter(oldfps, _animationFps);
    }

    //Get Methods
    public int GetAnimationFps()
    {
        return _animationFps;
    }

    //Private Methods
    private void InitAnimationCounter(int oldfps, int newfps)
    {
        int fpsShift;
        if(newfps == 0 || oldfps == 0)
            fpsShift = 0;
        else
            fpsShift = (oldfps/newfps);

        if(fpsShift == 0) return;

        _animationCounter *= fpsShift;
        _animationReset = _frameQuantity * _animationDeviation;
    }

    private void DrawSpriteOrientation(GraphicsContext gc, Point drawLocation,
                                       int tileIndex,
                                       AnimationOrientation orientation)
    {
        switch(orientation)
        {
            case Default:
                gc.drawImage(_spriteSheet, 0,
                        _tileHeight*tileIndex, _tileWidth, _tileHeight,
                        drawLocation.x, drawLocation.y, _tileWidth, _tileHeight);
                break;
            case MirrorXAxis:
                gc.drawImage(_spriteSheet, 0,
                        _tileHeight*tileIndex, _tileWidth, _tileHeight,
                        drawLocation.x + _tileWidth, drawLocation.y,
                        -_tileWidth, _tileHeight);
                break;
            case MirrorYAxis:
                gc.drawImage(_spriteSheet, 0,
                        _tileHeight*tileIndex, _tileWidth, _tileHeight,
                        drawLocation.x, drawLocation.y + _tileHeight,
                        _tileWidth, -_tileHeight);
                break;
            case MirrorBothAxis:
                gc.drawImage(_spriteSheet, 0,
                        _tileHeight*tileIndex, _tileWidth, _tileHeight,
                        drawLocation.x + _tileWidth,
                        drawLocation.y + _tileHeight,
                        -_tileWidth, -_tileHeight);
                break;
        }
    }
}
