package com.calcul.diabetif.commun.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.ProgressBar;
import android.widget.ViewSwitcher;

public class LoadingImageView extends ViewSwitcher {

    private ProgressBar loadingSpinner;
    private ImageView imageView;
    private ScaleType scaleType = ScaleType.CENTER_INSIDE;
    private LoadingImageViewDeleagate deleagate = null;

    public LoadingImageView(Context context) {
        super(context);
        initialize(context);
    }

    public LoadingImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize(context);
    }

    private void initialize(Context context) {
        addLoadingSpinnerView(context);
        addImageView(context);
    }

    private void addLoadingSpinnerView(Context context) {
        loadingSpinner = new ProgressBar(context);
        loadingSpinner.setIndeterminate(true);
        Drawable progressDrawable = loadingSpinner.getIndeterminateDrawable();

        LayoutParams lp = new LayoutParams(progressDrawable.getIntrinsicWidth(),
                progressDrawable.getIntrinsicHeight());
        lp.gravity = Gravity.CENTER;

        addView(loadingSpinner, 0, lp);
    }

    private void addImageView(Context context) {
        imageView = new ImageView(context);
        imageView.setScaleType(scaleType);
        LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        lp.gravity = Gravity.CENTER;
        addView(imageView, 1, lp);
    }

    /**
     * Often you have resources which usually have an image, but some don't. For these cases, use
     * this method to supply a placeholder drawable which will be loaded instead of a web image.
     * 
     * @param imageResourceId
     *            the resource of the placeholder image drawable
     */
    public void setNoImageDrawable(int imageResourceId) {
        imageView.setImageDrawable(getContext().getResources().getDrawable(imageResourceId));
        setDisplayedChild(1);
    }

    public void showLoadingView() {
        this.setDisplayedChild(0);
    }

    public void setImageDrawable(Drawable drawable) {
        imageView.setImageDrawable(drawable);
        if(drawable instanceof TransitionDrawable){
            TransitionDrawable transition = (TransitionDrawable)drawable;
            BitmapDrawable bitmap = (BitmapDrawable)transition.getDrawable(1);
            if(deleagate!=null){
                deleagate.afterSetImageBitmap(imageView, bitmap.getBitmap());
            }
        }
        setDisplayedChild(1);
    }

    public void setImageBitmap(Bitmap bitmap) {
        imageView.setImageBitmap(bitmap);
        Log.d("LoadingImageView", "this.deleagate::"+this.deleagate);
        if(deleagate!=null){
            deleagate.afterSetImageBitmap(imageView, bitmap);
        }
        setDisplayedChild(1);
    }

    public void setAlpha(int alpha) {
        imageView.setAlpha(alpha);
    }

    public Drawable getDrawable() {
        return imageView.getDrawable();
    }

    @Override
    public void reset() {
        super.reset();
        this.setDisplayedChild(0);
    }
    
    public void setScaleType(ScaleType scaleType) {
		imageView.setScaleType(scaleType);
	}
    
    public void setDeleagate(LoadingImageViewDeleagate deleagate) {
		this.deleagate = deleagate;
	}
    
    public static class LoadingImageViewDeleagate{    	
    	public void afterSetImageBitmap(ImageView imageView,Bitmap bitmap) {
            throw new UnsupportedOperationException("Manager delegate function is not override");
        }
    }
}
