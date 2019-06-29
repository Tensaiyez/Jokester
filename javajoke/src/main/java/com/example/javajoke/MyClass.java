package com.example.javajoke;

import java.util.Random;

public class MyClass {
    Random rand =new Random();
//Jokes are from: https://www.rd.com/jokes/
    public String getJoke() {
        String []Jokes={" Q. Why is Peter Pan flying all the time?\n" +
                " A. He Neverlands!"," Q. Why was the birthday cake as hard as a rock?\n" +
                " A. Because it was marble cake!"," Q. What do you call a bee that can not make up its mind? \n" +
                " A. A Maybe"," Q. What did the volcano say to his wife?\n" +
                " A. I lava you"};
        int randomJokes=rand.nextInt(Jokes.length);
        return Jokes[randomJokes];
    }
}
