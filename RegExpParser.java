package com.company;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class RegExpParser {

    private final String input;

    public RegExpParser(final String input) {
        this.input = input;
    }

    public RegExp parse() {




        return new Void();
    }

    public RegExp parseHelper(String expression) {
        if(expression.isEmpty()) {
            return new Void();
        }

        while(expression.startsWith("(") && expression.endsWith(")")) {
            String tmp = expression.substring(1, expression.length() - 1);

            if(hasValidParentheses(tmp)) {
                expression = tmp;
            } else {
                break;
            }

        }

        int parenthesesCounter = 0;
        for(int i = 0; i < input.length(); ++i) {
            char c = input.charAt(0);

            if(c == '(') {
                parenthesesCounter++;
            } else if(c == ')') {
                parenthesesCounter--;
            }

            if(parenthesesCounter < 0) {
                return new Void();
            }

            if(parenthesesCounter == 0 && c == '|') {
                return new Or(parseHelper(expression.substring(0, i)), parseHelper(expression.substring(i + 1)));
            }

        }

        List<RegExp> regExpList = new LinkedList<>();

        List<Character> characters = expression.chars().mapToObj(c -> (char) c).collect(Collectors.toList());

        while(!characters.isEmpty()) {

            char c = characters.remove(characters.size() - 1);
            
            if(c == '*') {
                String operand = getOperand(characters);
                regExpList.add(0, new ZeroOrMore(parseHelper(operand)));
            } else  {
                String operand = getOperand(characters);
                
                if(operand.length() == 1) {
                    regExpList.add(0, new Normal(operand.charAt(0)));
                } else {
                    regExpList.add(0, parseHelper(operand));
                }
            }
        }

        if(regExpList.size() == 1) {
            return regExpList.get(0);
        }

        return new Str(regExpList);

    }

    private static String getOperand(List<Character> characters) {

        int parenthesesCounter = 0;

        StringBuilder operand = new StringBuilder();
        while(!characters.isEmpty()) {

            char c = characters.remove(characters.size() - 1);
            operand.insert(0, c);

            if(c == '(') {
                parenthesesCounter++;
            } else if(c == ')') {
                parenthesesCounter--;
            }

            if(parenthesesCounter < 0) {
                return "";
            }

            if(parenthesesCounter == 0) {
                return operand.toString();
            }

        }

        return "";

    }

    private static boolean hasValidParentheses(String expression) {

        int parenthesesCounter = 0;

        for(char c : expression.toCharArray()) {

            if(c == '(') {
                parenthesesCounter++;
            } else if(c == ')') {
                parenthesesCounter--;
            }

            if(parenthesesCounter < 0) {
                return false;
            }

        }

        return parenthesesCounter == 0;

    }
}

class RegExp {

    private List<RegExp> regExps;
    private char character;

    public RegExp() {
    }

    public RegExp(char character) {
        this.character = character;
    }

    public RegExp(RegExp... regExps) {
        this.regExps = Arrays.asList(regExps);
    }

    public RegExp(char character, RegExp... regExps) {
        this.character = character;
        this.regExps = Arrays.asList(regExps);
    }

    public char getCharacter() {
        return character;
    }

    public List<RegExp> getInnerRegExp() {
        return regExps;
    }

}

class Normal extends RegExp {

    public Normal(char character) {
        super(character);
    }

}

class Any extends RegExp {

    public Any() {
        super('.');
    }

}

class ZeroOrMore extends RegExp {

    public ZeroOrMore(RegExp regexp) {
        super('*', regexp);
    }

}

class Or extends RegExp {

    public Or(RegExp r1, RegExp r2) {
        super('|', r1, r2);
    }

}

class Str extends RegExp {

    public Str(List<RegExp> lstRegexps) {
        super(lstRegexps.toArray(new RegExp[0]));
    }

}

class Void extends RegExp {

    public Void() {
    }

}
