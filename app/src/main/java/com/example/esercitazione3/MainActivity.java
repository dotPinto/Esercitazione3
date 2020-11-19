package com.example.esercitazione3;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Range;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MainActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PieChartView pieChart = this.findViewById(R.id.piechart);

        int min = 4, max = 16;
        Random rand = new Random();
        int slices = min+rand.nextInt((max-min)/2+1)*2;
        int half_sum = 50, half_pie = slices/2;

        List<Float> percent = createPieChartSlices(half_pie,half_sum);
        Integer[] colors = createPieChartColors(slices);

        pieChart.setPercent(percent);
        pieChart.setSegmentColor(Arrays.asList(colors));

        pieChart.setRadius(300);
        pieChart.setStrokeColor(Color.BLACK);
        pieChart.setStrokeWidth(4);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    protected  List<Float> createPieChartSlices(int half_pie, int half_sum){
        int buff[] = new int[half_pie];

        for(int i = 0; i < half_sum; i++){
            buff[(int)(Math.random() * half_pie)]++;
        }

        //creazione il 50% rimanente del piechart
        int[] symmetric = Arrays.copyOf(buff,half_pie);

        //lista di interi con la prima parte delle percentuali
        List<Integer> list = IntStream.of(buff)
                .boxed()
                .collect(Collectors.toList());

        //lista di interi con la seconda parte delle percentuali
        List<Integer> list2 = IntStream.of(symmetric)
                .boxed()
                .collect(Collectors.toList());


        Collections.reverse(list2);

        list.addAll(list2);

        List<Float> list3 = new ArrayList<>();

        //trasformazione int in float
        for(int i = 0; i < list.size(); i++){
            Float number = Float.valueOf(list.get(i));
            list3.add(number);
        }

        return list3;
    }

    protected Integer[] createPieChartColors(int slices){
        Integer[] colors = new Integer[slices];

        int[] androidColors = getResources().getIntArray(R.array.androidcolors);

        for (int i = 0; i < slices; i++){
            colors[i] = androidColors[new Random().nextInt(androidColors.length)];
        }
        return colors;
    }
}