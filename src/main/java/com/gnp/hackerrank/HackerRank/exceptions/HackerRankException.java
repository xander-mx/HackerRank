package com.gnp.hackerrank.HackerRank.exceptions;

public class HackerRankException extends Exception{
        public String message;
        public String field;
        public String method;

        public HackerRankException(){}

    public HackerRankException(String message, String field, String method){
            this.message = message;
            this.field = field;
            this.method = method;
    }
}
