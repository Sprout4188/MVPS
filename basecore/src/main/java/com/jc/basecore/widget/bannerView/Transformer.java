package com.jc.basecore.widget.bannerView;

import android.support.v4.view.ViewPager.PageTransformer;

import com.jc.basecore.widget.bannerView.transformer.AccordionTransformer;
import com.jc.basecore.widget.bannerView.transformer.BackgroundToForegroundTransformer;
import com.jc.basecore.widget.bannerView.transformer.CubeInTransformer;
import com.jc.basecore.widget.bannerView.transformer.CubeOutTransformer;
import com.jc.basecore.widget.bannerView.transformer.DefaultTransformer;
import com.jc.basecore.widget.bannerView.transformer.DepthPageTransformer;
import com.jc.basecore.widget.bannerView.transformer.FlipHorizontalTransformer;
import com.jc.basecore.widget.bannerView.transformer.FlipVerticalTransformer;
import com.jc.basecore.widget.bannerView.transformer.ForegroundToBackgroundTransformer;
import com.jc.basecore.widget.bannerView.transformer.RotateDownTransformer;
import com.jc.basecore.widget.bannerView.transformer.RotateUpTransformer;
import com.jc.basecore.widget.bannerView.transformer.ScaleInOutTransformer;
import com.jc.basecore.widget.bannerView.transformer.StackTransformer;
import com.jc.basecore.widget.bannerView.transformer.TabletTransformer;
import com.jc.basecore.widget.bannerView.transformer.ZoomInTransformer;
import com.jc.basecore.widget.bannerView.transformer.ZoomOutSlideTransformer;
import com.jc.basecore.widget.bannerView.transformer.ZoomOutTranformer;

public class Transformer {
    public static Class<? extends PageTransformer> Default = DefaultTransformer.class;
    public static Class<? extends PageTransformer> Accordion = AccordionTransformer.class;
    public static Class<? extends PageTransformer> BackgroundToForeground = BackgroundToForegroundTransformer.class;
    public static Class<? extends PageTransformer> ForegroundToBackground = ForegroundToBackgroundTransformer.class;
    public static Class<? extends PageTransformer> CubeIn = CubeInTransformer.class;
    public static Class<? extends PageTransformer> CubeOut = CubeOutTransformer.class;
    public static Class<? extends PageTransformer> DepthPage = DepthPageTransformer.class;
    public static Class<? extends PageTransformer> FlipHorizontal = FlipHorizontalTransformer.class;
    public static Class<? extends PageTransformer> FlipVertical = FlipVerticalTransformer.class;
    public static Class<? extends PageTransformer> RotateDown = RotateDownTransformer.class;
    public static Class<? extends PageTransformer> RotateUp = RotateUpTransformer.class;
    public static Class<? extends PageTransformer> ScaleInOut = ScaleInOutTransformer.class;
    public static Class<? extends PageTransformer> Stack = StackTransformer.class;
    public static Class<? extends PageTransformer> Tablet = TabletTransformer.class;
    public static Class<? extends PageTransformer> ZoomIn = ZoomInTransformer.class;
    public static Class<? extends PageTransformer> ZoomOut = ZoomOutTranformer.class;
    public static Class<? extends PageTransformer> ZoomOutSlide = ZoomOutSlideTransformer.class;
}
