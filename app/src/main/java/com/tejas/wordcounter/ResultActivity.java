package com.tejas.wordcounter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
    TextView wordsCountTxt;
    TextView charCountTxt;
    TextView sentencesCountTxt;
    TextView rtCountTxt;
    TextView stCountTxt;
    TextView mclCountTxt;

    public String getWordsCount(String text){
        String words[]=text.split("\\s");
        int length = words.length;
        return Integer.toString(length);
    }

    public String getCharsCount(String text){
        int length = text.length();
        return Integer.toString(length);
    }

    public String getSentencesCount(String text){
        int noOfSentences = 0;
        for (int i=text.length()-1; i>=0; i--){
            if ((text.charAt(i) == '.') || (text.charAt(i) == '?') || (text.charAt(i) == '!') &&
                    (text.charAt(i + 1) == ' ') && Character.isUpperCase(text.charAt(i + 2)))
                noOfSentences+=1;
            }
        return Integer.toString(noOfSentences);
        }


    public String getReadingTime(String text){
        int lengthOfText = Integer.parseInt(getWordsCount(text));
        int readingTime = (int) Math.round(lengthOfText/4.58333333);
        return Integer.toString(readingTime);
    }
    public String getSpeakingTime(String text){
        int lengthOfText = Integer.parseInt(getWordsCount(text));
        int speakingTime = (int) Math.round(lengthOfText/3);
        return Integer.toString(speakingTime);
    }

    public String getMCL(String text){
        char[] alphabet_list = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        int[] number_list = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
//        int[] number_list = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26};
        char[] text_list = text.toUpperCase().toCharArray();

        for (int i=0; i<text_list.length; i++){
            for (int j=0; j<alphabet_list.length; j++){
                if (text_list[i]==alphabet_list[j]){
                    number_list[j] = number_list[j]+1;
                }
            }
        }

        int max = number_list[0];
        int max_index = 0;
        for (int k = 0; k < number_list.length; k++) {
            if(number_list[k] > max) {
                max = number_list[k];
                max_index = k;
            }
        }

        Log.d("test", ""+max_index);
        char max_char = alphabet_list[max_index];

        return max_char+" "+"("+max*100/text.length()+"%"+")";

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // Declaring Variables
        wordsCountTxt = findViewById(R.id.words);
        charCountTxt = findViewById(R.id.characters);
        sentencesCountTxt = findViewById(R.id.sentences);
        rtCountTxt = findViewById(R.id.readingTime);
        stCountTxt = findViewById(R.id.speakingTime);
        mclCountTxt = findViewById(R.id.mcl);

        Bundle extras = getIntent().getExtras();
        if (extras!=null){
            String value = extras.getString("text");
            // Adding Values in variables
            String wordsCount = getWordsCount(value);
            String charsCount = getCharsCount(value);
            String sentencesCount = getSentencesCount(value);
            String readingTime = getReadingTime(value);
            String speakingTime = getSpeakingTime(value);
            String mostCommonLetter = getMCL(value);

            // Putting Values on the screen
            wordsCountTxt.setText("Words : " + wordsCount);
            charCountTxt.setText("Characters : " + charsCount);
            sentencesCountTxt.setText("Sentences : " + sentencesCount);
            rtCountTxt.setText("Reading Time : " + readingTime + " sec");
            stCountTxt.setText("Speaking Time : " + speakingTime + " sec");
            mclCountTxt.setText("Most Common Letter : " + mostCommonLetter);
        }

    }
}