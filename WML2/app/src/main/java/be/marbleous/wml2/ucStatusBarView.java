package be.marbleous.wml2;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class ucStatusBarView extends LinearLayout {

    private String labelText;

    public ucStatusBarView(Context context) {
        super(context);
        initializeViews(context);
    }

    public ucStatusBarView(Context context, AttributeSet attrs) {
        super(context, attrs);


        //get the attributes specified in attrs.xml using the name we included
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.ucStatusBarView, 0, 0);
        labelText = a.getString(R.styleable.ucStatusBarView_labelText);

        initializeViews(context);
    }

    public ucStatusBarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initializeViews(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ucStatusBarView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initializeViews(context);
    }




    /**
     * Inflates the views in the layout.
     *
     * @param context
     *           the current context for the view.
     */
    private void initializeViews(Context context) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.uc_status_bar_view, this);

        setText(labelText);
    }


    public void setText(String text){
        ((TextView)findViewById(R.id.labelText)).setText(text);
    }

    public void setBg(int color){
        RelativeLayout v = (RelativeLayout)findViewById(R.id.bgLayout);
        v.setBackgroundColor(Color.RED);
      //  v.invalidate();

    }

    public void setStatus(){

    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();


    }
}
