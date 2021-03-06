package com.google.engedu.ghost;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

public class SimpleDictionary implements GhostDictionary {
    private ArrayList<String> words;

    public SimpleDictionary(InputStream wordListStream) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(wordListStream));
        words = new ArrayList<>();
        String line = null;
        while((line = in.readLine()) != null) {
            String word = line.trim();
            if (word.length() >= MIN_WORD_LENGTH)
                words.add(line.trim());
        }
    }


    @Override
    public boolean isWord(String word) {
        return words.contains(word);
    }

    @Override
    public String getAnyWordStartingWith(String prefix) {
        int possibleWordIndex;
        String possibleWord;
        if(prefix == ""){
            Random random = new Random();
            int randomIndex = random.nextInt(words.size());
            return words.get(randomIndex);
        }
        else {
            possibleWordIndex=searchpossibleindex(prefix);
            if(possibleWordIndex == -1){
                return "noWord";
            }
            else if (words.contains(prefix)) {
                return "sameAsPrefix";
            }
            else{
                possibleWord=words.get(possibleWordIndex);
                return possibleWord;
            }
        }
    }
    @Override
    public String getGoodWordStartingWith(String prefix, int whoEndFirst) {
        return null;
    }

    public int  searchpossibleindex(String prefix)
    {
        int checklist,low=0;
        int high=words.size()-1;
        String word;
        while(low<=high){
            int middle=(low+high)/2;
            word=words.get(middle);

            checklist=word.startsWith(prefix)? 0 : prefix.compareTo(word);
            if(checklist==0)
                return middle;
            else if(checklist<0)
                high=middle-1;
            else
                low=middle+1;

        }
        return -1;

    }

    public String getGoodWordStartingWith(String prefix) {
        String selected = null;
        return selected;
    }
}