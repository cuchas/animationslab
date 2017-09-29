package br.com.cucha.animationslab;

import android.graphics.Color;
import android.support.transition.ChangeBounds;
import android.support.transition.Slide;
import android.support.transition.TransitionManager;
import android.support.transition.TransitionSet;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class CustomTransitionsActivity extends AppCompatActivity {

    TextView textView;
    ViewGroup rootView;
    private ViewGroup frameView;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_transitions);

        rootView = findViewById(android.R.id.content);
        frameView = findViewById(R.id.frame_square);
        textView = findViewById(R.id.text);
        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TransitionSet set = new TransitionSet();
                set.addTransition(new Slide(Gravity.END).excludeTarget(textView, true));
//                set.addTransition(new Slide(Gravity.START).excludeTarget(frameView, true));
                set.addTransition(new ChangeBounds().excludeTarget(button, true));
                set.addTransition(new TranslationYTransition());
                set.addTransition(new ScaleTransition().addTarget(button));

                set.setOrdering(TransitionSet.ORDERING_SEQUENTIAL);
                set.setDuration(500);
                set.setInterpolator(new FastOutSlowInInterpolator());

                TransitionManager.beginDelayedTransition(rootView, set);

                textView.setVisibility(textView.getVisibility() == View.GONE ? View.VISIBLE : View.GONE);
                frameView.setTranslationY(frameView.getTranslationY() == 0f ? 200f : 0f);

                DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
                float w = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 300f, displayMetrics);
                float h = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 150f, displayMetrics);

                button.setWidth(Math.round(w));
                button.setHeight(Math.round(h));
            }
        });
    }

    private int getRandomColor() {
        int R = (int) Math.random() * 256;
        int G = (int) Math.random() * 256;
        int B = (int) Math.random() * 256;

        return Color.rgb(R, G, B);
    }
}
