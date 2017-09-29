package br.com.cucha.animationslab;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.transition.Transition;
import android.support.transition.TransitionValues;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by eduardo on 9/28/17.
 */

public class ScaleTransition extends Transition {
    public static final String PROP_WIDTH = "width";
    public static final String PROP_HEIGHT = "height";

    @Override
    public void captureStartValues(@NonNull TransitionValues transitionValues) {
        transitionValues.values.put(PROP_WIDTH, transitionValues.view.getWidth());
        transitionValues.values.put(PROP_HEIGHT, transitionValues.view.getHeight());
    }

    @Override
    public void captureEndValues(@NonNull TransitionValues transitionValues) {
        transitionValues.values.put(PROP_WIDTH, transitionValues.view.getWidth());
        transitionValues.values.put(PROP_HEIGHT, transitionValues.view.getHeight());
    }

    @Nullable
    @Override
    public Animator createAnimator(@NonNull ViewGroup sceneRoot,
                                   @Nullable TransitionValues startValues,
                                   @Nullable TransitionValues endValues) {

        if(startValues == null || endValues == null) return null;

        int startW = (int)startValues.values.get(PROP_WIDTH);
        int endW = (int)endValues.values.get(PROP_WIDTH);

        if(startW == endW) return null;

        int startH = (int)startValues.values.get(PROP_HEIGHT);
        int endH = (int)endValues.values.get(PROP_HEIGHT);

        if(startH == endH) return null;

        final Button view = (Button)startValues.view;
        view.setWidth(startW);
        view.setHeight(startH);

        ValueAnimator animatorW = ValueAnimator.ofInt(startW, endW);
        animatorW.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int value = (int) valueAnimator.getAnimatedValue();
                view.setWidth(value);
            }
        });

        ValueAnimator animatorH = ValueAnimator.ofInt(startH, endH);
        animatorH.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int value = (int) valueAnimator.getAnimatedValue();
                view.setHeight(value);
            }
        });

        AnimatorSet set = new AnimatorSet();
        set.playTogether(animatorW, animatorH);

        return set;
    }
}
